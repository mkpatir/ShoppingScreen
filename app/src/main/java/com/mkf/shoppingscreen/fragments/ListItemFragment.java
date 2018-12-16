package com.mkf.shoppingscreen.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.mkf.shoppingscreen.R;
import com.mkf.shoppingscreen.activitys.OrderListActivity;
import com.mkf.shoppingscreen.extras.CustomToast;
import com.mkf.shoppingscreen.extras.ListItemAdapter;
import com.mkf.shoppingscreen.extras.Products;
import com.mkf.shoppingscreen.models.Items;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammed Kemal Patır on 23.07.2018.
 */

public class ListItemFragment extends Fragment {

    ListView listitem_listview;
    View thisView;
    int selectedItemPosition;
    View selectedItemView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_item,container,false);
        listitem_listview = (ListView)view.findViewById(R.id.listitem_listview);
        thisView = view;
        setListAdapter();
        listviewClick();
        return view;
    }

    // Set Listview Adapter
    public void setListAdapter(){
        ListItemAdapter listItemAdapter = new ListItemAdapter(Products.Items.getItems(),getLayoutInflater());
        listitem_listview.setAdapter(listItemAdapter);
    }

    // Listview Item Click Event
    private void listviewClick(){
        listitem_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try{
                    selectedItemPosition = position;
                    selectedItemView = view;
                    IntentIntegrator ıntentIntegrator = IntentIntegrator.forSupportFragment(ListItemFragment.this);
                    ıntentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                    ıntentIntegrator.setPrompt("ScanBarcode");
                    ıntentIntegrator.setCameraId(0);
                    ıntentIntegrator.setBeepEnabled(false);
                    ıntentIntegrator.setBarcodeImageEnabled(false);
                    ıntentIntegrator.initiateScan();
                }
                catch (Exception ex){
                    CustomToast toast = new CustomToast(getContext());
                    toast.setCustomView(getLayoutInflater(),(ViewGroup)thisView.findViewById(R.id.custom_toast_layout),ex.getMessage());
                }
            }
        });
    }

    // Result of barcode scanning
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try{
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
            //Items item = (Items) listitem_listview.getAdapter().getItem(selectedItemPosition);
            if (result.getContents() != null){
                Items item = Products.Items.getItems().get(selectedItemPosition);
                item.setConfirm(true);
                ImageView listitem_checkedimage = (ImageView)selectedItemView.findViewById(R.id.listitem_checkedimage);
                listitem_checkedimage.setImageResource(R.drawable.confirm_true);
                OrderListActivity.isScrool = true;
            }
        }
        catch (Exception ex){

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
