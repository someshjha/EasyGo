package com.easygo.rbcdev.easygo.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.io.Serializable;

/**
 * Created by rbcdev on 15-10-06.
 */
@Table(name = "PaymentInformation")
public class PaymentInformation extends Model implements Serializable {

    @Column(name = "customerEmail")
    private String customerEmail;

    @Column(name = "cardType")
    private int cardType;

    @Column(name = "nameOnCard")
    private String nameOnCard;

    @Column(name = "securityCode")
    private String securityCode;

    @Column(name = "cardNumber")
    private String cardNumber;

    @Column(name = "cardExpiry")
    private String cardExpiry;

    public String getCardExpiry() {
        return cardExpiry;
    }

    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public static PaymentInformation getPaymentInformation(String id) {
        return new Select()
                .from(PaymentInformation.class)
                .where("customerEmail = ?", id)
                .executeSingle();
    }
}
