package com.amazingapps.creditcardoverview;

import java.util.ArrayList;

/**
 * @author max
 * @date 8/15/17.
 */

public class Monthly {
    int year;
    int month;
    float monthlySum;
    ArrayList<Entry> entries;
    int numberOfEntries;

    public Monthly(int year, int month) {
        this.year = year;
        this.month = month;
        entries = new ArrayList<Entry>();
        numberOfEntries = 0;
        monthlySum = 0;
    }

    public float updateSum(){
        float sum = 0;
        for(int i = 0; i < entries.size(); i++){
            sum += entries.get(i).getAmount();
        }
        monthlySum = sum;
        return monthlySum;
    }



    public void addEntry(Entry entry) {
        if (entry != null) {
            entry.setID(++numberOfEntries);
            entries.add(entry);
            numberOfEntries++;
        }
    }

    public Entry getEntryAt(int i) {
        return entries.get(i);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public float getMonthlySum() {
        return monthlySum;
    }

    public void setMonthlySum(float monthlySum) {
        this.monthlySum = monthlySum;
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    public void setNumberOfEntries(int numberOfEntries) {
        this.numberOfEntries = numberOfEntries;
    }
}
