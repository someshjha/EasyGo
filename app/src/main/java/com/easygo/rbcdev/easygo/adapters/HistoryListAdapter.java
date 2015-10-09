package com.easygo.rbcdev.easygo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.easygo.rbcdev.easygo.R;
import com.easygo.rbcdev.easygo.models.Item;
import com.easygo.rbcdev.easygo.models.ShoppingHistory;

import java.util.ArrayList;

/**
 * Created by rbcdev on 15-10-09.
 */
public class HistoryListAdapter extends ArrayAdapter<ShoppingHistory> {

    public HistoryListAdapter(Context context, ArrayList<ShoppingHistory> items) {
        super(context,0,items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ShoppingHistory item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_itemhistorylist, parent, false);
        }

        TextView itemName = (TextView) convertView.findViewById(R.id.lblStore);
        TextView itemPrice = (TextView) convertView.findViewById(R.id.lblPrice);
        TextView itemDate = (TextView) convertView.findViewById(R.id.lblDate);

        if(item.getDate() != null) {
            itemDate.setText(item.getDate());
        }

        if(item.getTotal() != null) {
            itemPrice.setText("$" + item.getTotal());
        }

        if(item.getStore() != null) {
            itemName.setText("$" + item.getStore());
        }

        return convertView;
    }
}
