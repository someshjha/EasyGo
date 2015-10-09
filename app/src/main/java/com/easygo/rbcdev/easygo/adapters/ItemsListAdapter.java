package com.easygo.rbcdev.easygo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.easygo.rbcdev.easygo.R;
import com.easygo.rbcdev.easygo.models.Item;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by rbcdev on 15-10-08.
 */
public class ItemsListAdapter extends ArrayAdapter<Item> {

    public ItemsListAdapter(Context context, ArrayList<Item> items) {
        super(context,0,items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_itemlist, parent, false);
        }

        TextView itemName = (TextView) convertView.findViewById(R.id.lblItemName);
        TextView itemPrice = (TextView) convertView.findViewById(R.id.lblItemPrice);

        if(item.getItemName() != null) {
            itemName.setText(item.getItemName());
        }

        if(item.getItemPrice() != null) {
            itemPrice.setText("$" + item.getItemPrice());
        }

        return convertView;
    }
}
