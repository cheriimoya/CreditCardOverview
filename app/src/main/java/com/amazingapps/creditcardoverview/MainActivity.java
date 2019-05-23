package com.amazingapps.creditcardoverview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.nio.file.Path;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.amazingapps.creditcardoverview.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, Overview.class);

        //intent.putExtra(EXTRA_MESSAGE, );
        startActivity(intent);
    }
}
