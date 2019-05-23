package com.amazingapps.creditcardoverview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ListView extends AppCompatActivity {

    ArrayList<Entry> entries;
    TableLayout tableLayout;
    SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        //load data
        Intent intent = getIntent();
        entries = (ArrayList<Entry>)intent.getSerializableExtra("data");


        tableLayout = (TableLayout) findViewById(R.id.allEntries);

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for(int i = 0; i < entries.size(); i++){
            createRow(i, entries.get(i).getDate(), entries.get(i).getAmount(), entries.get(i).getText());
        }
    }


    public void createRow(int n, Date entryDate, float entryAmount, String entryText){

        TableRow row = new TableRow(this);
        TableLayout innerTable = new TableLayout(this);
        TableRow upperRow = new TableRow(this);
        TableRow lowerRow = new TableRow(this);
        TextView date = new TextView(this);
        TextView amount = new TextView(this);
        TextView text = new TextView(this);


        amount.setText("" + entryAmount);
        date.setText(simpleDateFormat.format(entryDate));
        text.setText(entryText);
        amount.setTextSize(30);
        date.setTextSize(30);
        text.setTextSize(30);


        upperRow.addView(amount);
        upperRow.addView(date);
        lowerRow.addView(text);

        innerTable.addView(upperRow);
        innerTable.addView(lowerRow);

        row.addView(innerTable);
        row.setPadding(20,0,0,40);

        tableLayout.addView(row, 0);
    }
}
