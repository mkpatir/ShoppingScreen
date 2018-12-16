package com.mkf.shoppingscreen.extras;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mkf.shoppingscreen.R;
import com.mkf.shoppingscreen.models.Items;

import java.util.List;

/**
 * Created by Muhammed Kemal Patır on 23.07.2018.
 */

public class GridItemAdapter extends BaseAdapter {

    private List<Items> items;
    private LayoutInflater layoutInflater;
    ImageView griditem_photo;
    TextView griditem_name;
    TextView griditem_code;
    TextView griditem_price;
    ImageView griditem_checkedimage;

    public GridItemAdapter(List<Items> items, LayoutInflater layoutInflater){
        this.items = items;
        this.layoutInflater = layoutInflater;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.gridview_griditem,null);
        griditem_photo = (ImageView)convertView.findViewById(R.id.griditem_photo);
        griditem_name = (TextView)convertView.findViewById(R.id.griditem_name);
        griditem_code = (TextView)convertView.findViewById(R.id.griditem_code);
        griditem_price = (TextView)convertView.findViewById(R.id.griditem_price);
        griditem_checkedimage = (ImageView)convertView.findViewById(R.id.griditem_checkedimage);
        Items item = items.get(position);
        griditem_name.setText(item.getName());
        griditem_code.setText(item.getCode());
        griditem_price.setText(String.valueOf(item.getPrice()));
        griditem_photo.setImageResource(Integer.parseInt(item.getPhoto()));
        if (item.isConfirm()){
            griditem_checkedimage.setImageResource(R.drawable.confirm_true);
        }
        return convertView;
    }
}
