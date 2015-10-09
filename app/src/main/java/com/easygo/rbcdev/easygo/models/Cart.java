package com.easygo.rbcdev.easygo.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rbcdev on 15-10-06.
 */

@Table(name = "Cart")
public class Cart extends Model implements Serializable {

    @Column(name = "customerEmail")
    private String customerEmail;

    @Column(name = "itemName")
    private String itemName;

    @Column(name = "itemPrice")
    private String itemPrice;

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public static ArrayList<Cart> getCart(String email) {
        List<Cart> all = new Select()
                .all()
                .from(Cart.class)
                .where("customerEmail = ?", email)
                .execute();

        ArrayList<Cart> returnedCart = new ArrayList<Cart>(all);
        return returnedCart;
    }

    public static void emptyCart(String email) {
        new Delete().from(Cart.class).where("customerEmail = ?", email).execute();
    }

//    private void calculateTotals() {
//        Double total = 0.0;
//        Double tax = 0.0;
//
//        for(Item item:cartItems){
//            total += Double.parseDouble(item.getItemPrice());
//        }
//
//        mSubTotal = total;
//        mTax = 0.15 * mSubTotal;
//        mTotal = mTax + mSubTotal;
//    }
}
