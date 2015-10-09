package com.easygo.rbcdev.easygo.models;

import com.activeandroid.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
/**
 * Created by MyWorkPlace on 15-10-08.
 */

@Table(name = "ShoppingHistory")
public class ShoppingHistory extends Model implements Serializable {

    @Column(name = "date")
    private String date;

    @Column(name = "customerEmail")
    private String customerEmail;

    @Column(name = "store")
    private String store;

    @Column(name = "total")
    private String total;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public static ArrayList<ShoppingHistory> getHistory(String email) {
        List<ShoppingHistory> all = new Select()
                .all()
                .from(ShoppingHistory.class)
                .where("customerEmail = ?", email)
                .execute();

        ArrayList<ShoppingHistory> returnedHistory = new ArrayList<ShoppingHistory>(all);
        return returnedHistory;
    }


}
