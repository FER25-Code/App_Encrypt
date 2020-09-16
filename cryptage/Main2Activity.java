package com.sci.fergani.cryptage.cryptage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sci.fergani.cryptage.R;
import com.sci.fergani.materiallockview.MaterialLockView;

public class Main2Activity extends AppCompatActivity {
    private String CorrectPattern = "";
    private MaterialLockView materialLockView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}
