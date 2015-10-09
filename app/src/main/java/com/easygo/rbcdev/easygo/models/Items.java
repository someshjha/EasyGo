package com.easygo.rbcdev.easygo.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by rbcdev on 15-10-08.
 */
public class Items implements Serializable {

    private ArrayList<Item> itemList;

    public ArrayList<Item> getCurrentItems() {
        return itemList;
    }

    public void setCurrentItems(ArrayList<Item> currentItems) {
        this.itemList = currentItems;
    }
    
    public ArrayList<Item> filterItems(String category) {
        ArrayList<Item> filtered = new ArrayList<Item>();
        int i;

        for(Item item: itemList) {
            if(item.getItemCategory().equals(category)) {
                filtered.add(item);
            }
        }

        return filtered;
    }
}
