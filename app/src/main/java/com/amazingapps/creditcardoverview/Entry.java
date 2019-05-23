package com.amazingapps.creditcardoverview;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by max on 8/14/17.
 */

public class Entry implements Serializable {

    float amount;
    Date date;
    String text;
    int ID;

    public Entry(float amount) {
        new Entry(new Date(), amount);
    }

    public Entry(Date date, float amount) {
        new Entry(date, amount, null);
    }

    public Entry(Date date, float amount, String text) {
        this.date = date;
        this.amount = amount;
        this.text = text;
    }

    @Override
    public String toString(){
        return "" + this.getAmount() + " for " + this.getText() + " on " + this.getDate().toString();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
