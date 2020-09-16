package com.sci.fergani.cryptage.cryptage.SMS;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.sci.fergani.cryptage.R;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;




public class SMSActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
    }

}
