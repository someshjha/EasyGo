package com.easygo.rbcdev.easygo.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by rbcdev on 15-10-06.
 */

@Table(name = "Cart")
public class Cart extends Model implements Serializable {

    private ArrayList<Item> cartItems;

    public String get_item() {
        return _item;
    }

    public void set_item(String _item) {
        this._item = _item;
    }

    private String _item;


    public ArrayList<Item> getCartItems() {
        return cartItems;
    }

    public void setCartItems(ArrayList<Item> cartItems) {
        this.cartItems = cartItems;
    }


}
