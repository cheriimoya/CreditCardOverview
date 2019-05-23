package com.amazingapps.creditcardoverview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Overview extends AppCompatActivity {

    DataManager dataManager;
    TextView textView;
    EditText numberAmount;
    EditText textDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        dataManager = new DataManager();
    }

    public void updateMonthlySum(DataManager dataManager) {
        textView = (TextView) findViewById(R.id.numberSum);
        dataManager.monthly.updateSum();
        textView.setText("" + dataManager.monthly.getMonthlySum());
    }

    public void onAddListener(View view) {
        float amount = 0;
        String text = "";
        Date date;

        numberAmount = (EditText) findViewById(R.id.numberAmount);
        textDescription = (EditText) findViewById(R.id.textDescription);

        amount = Float.parseFloat(numberAmount.getText().toString());
        text = textDescription.getText().toString();
        date = new Date();
        dataManager.monthly.addEntry(new Entry(date, amount, text));
        Log.println(Log.DEBUG, "test", "added " + date.toString() + amount + text);
        dataManager.writeData();
        updateMonthlySum(dataManager);

        numberAmount.setText("");
        textDescription.setText("");

    }

    public void onShowList(View view){
        Intent intent = new Intent(this, ListView.class);

        intent.putExtra("data", dataManager.monthly.getEntries());

        startActivity(intent);
    }

    public class DataManager {

        /**
         * @author max
         * @date 8/15/17.
         */

        File file;
        Monthly monthly;
        Date date;
        Calendar cal;
        FileInputStream in;
        FileOutputStream out;
        ObjectInputStream objectInputStream;
        ObjectOutputStream objectOutputStream;
        int month;
        int year;
        String fileName;

        public DataManager() {
            date = new Date();
            cal = Calendar.getInstance();
            cal.setTime(date);
            month = cal.get(Calendar.MONTH);
            month++;
            year = cal.get(Calendar.YEAR);
            monthly = new Monthly(year, month);
            fileName = "" + year + "-" + month;

            checkIfDataIsAvailable(fileName);

        }

        public boolean checkIfDataIsAvailable(String fileName) {
            try {
                file = new File("/data/data/com.amazingapps.creditcardoverview/files/" + fileName);
                if (!file.exists()) {
                    Log.println(Log.DEBUG, "write", "trying to cr file");
                    file.createNewFile();
                    Log.println(Log.DEBUG, "write", "file created");
                    // write code for saving data to the file
                } else {
                    Log.println(Log.DEBUG, "write", "file exists");
                }

                in = openFileInput(fileName);


                ArrayList<Entry> entries;

                objectInputStream = new ObjectInputStream(in);
                entries = (ArrayList<Entry>) objectInputStream.readObject();
                monthly.setEntries(entries);

                updateMonthlySum(this);
//                Entry entry;
//                do {
//                    objectInputStream = new ObjectInputStream(in);
//                    entry = (Entry) objectInputStream.readObject();
//                    monthly.addEntry(entry);
//                    //objectInputStream.close();
//
//                    Log.println(Log.DEBUG, "test", "readobject");
//
//                } while (entry != null);

            } catch (EOFException e) {

            } catch (Exception e) {
                Log.println(Log.DEBUG, "read", "error" + e.toString());
                e.printStackTrace();
                //return false;
            }
            Log.println(Log.DEBUG, "read", "file opened");

            return true;
        }

        public boolean writeData() {
            try {
                out = openFileOutput(fileName, MODE_PRIVATE);
                objectOutputStream = new ObjectOutputStream(out);

                objectOutputStream.writeObject(monthly.getEntries());
                //Log.println(Log.DEBUG, "test", monthly.getEntryAt(i).toString());

                objectOutputStream.close();
            } catch (Exception e) {
                Log.e("Exception", "File write failed: " + e.toString());
                Log.println(Log.DEBUG, "write", "error");
                return false;
            }
            Log.println(Log.DEBUG, "write", "written");
            return true;
        }
//        public boolean writeData() {
//            try {
//                out = openFileOutput(fileName, 0);
//                objectOutputStream = new ObjectOutputStream(out);
//
//                for (int i = 0; i < monthly.entries.size(); i++) {
//                    objectOutputStream.writeObject(monthly.getEntryAt(i));
//                    objectOutputStream.flush();
//                    //Log.println(Log.DEBUG, "test", monthly.getEntryAt(i).toString());
//                }
//                objectOutputStream.close();
//            } catch (Exception e) {
//                Log.e("Exception", "File write failed: " + e.toString());
//                Log.println(Log.DEBUG, "write", "error");
//                return false;
//            }
//            Log.println(Log.DEBUG, "write", "written");
//            return true;
//        }

    }
}
