package com.easygo.rbcdev.easygo.models;

import com.activeandroid.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by rbcdev on 15-10-06.
 */
public class Cart extends Model implements Serializable {

    private ArrayList<Item> cartItems;

}
