package com.mkf.shoppingscreen.extras;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mkf.shoppingscreen.R;
import com.mkf.shoppingscreen.models.Ecommerce;
import com.mkf.shoppingscreen.models.Items;

import java.util.List;

/**
 * Created by Muhammed Kemal Patır on 23.07.2018.
 */

public class ListItemAdapter extends BaseAdapter {

    private List<Items> items;
    LayoutInflater layoutInflater;
    TextView listitem_name;
    TextView listitem_code;
    TextView listitem_price;
    ImageView listitem_photo;
    ImageView listitem_checkedimage;

    public ListItemAdapter(List<Items> items, LayoutInflater layoutInflater){
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
        convertView = layoutInflater.inflate(R.layout.listview_listitem,null);
        listitem_name = (TextView)convertView.findViewById(R.id.listitem_name);
        listitem_code = (TextView)convertView.findViewById(R.id.listitem_code);
        listitem_price = (TextView)convertView.findViewById(R.id.listitem_price);
        listitem_photo = (ImageView)convertView.findViewById(R.id.listitem_photo);
        listitem_checkedimage = (ImageView)convertView.findViewById(R.id.listitem_checkedimage);
        Items item = items.get(position);
        listitem_name.setText(item.getName());
        listitem_code.setText(item.getCode());
        listitem_price.setText(String.valueOf(item.getPrice()));
        listitem_photo.setImageResource(Integer.parseInt(item.getPhoto()));
        if (item.isConfirm()){
            listitem_checkedimage.setImageResource(R.drawable.confirm_true);
        }
        return convertView;
    }
}
