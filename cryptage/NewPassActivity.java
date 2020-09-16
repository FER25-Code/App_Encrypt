package com.sci.fergani.cryptage.cryptage;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sci.fergani.cryptage.R;

public class NewPassActivity extends AppCompatActivity {
    EditText  NEW_CONFPASS,NEW_PASS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpass);
        NEW_CONFPASS= (EditText) findViewById(R.id.editText3);
        NEW_PASS= (EditText) findViewById(R.id.editText5);

    }
    public void New_Pass (View view){

            if (!MainActivity.validatePass(NEW_PASS.getText().toString())) {
                NEW_PASS.setError("invalid Password 8 caracter");
                NEW_PASS.requestFocus();

            } else {

                if ( NEW_CONFPASS.getText().toString().equals(NEW_PASS.getText().toString()) ) {
                    final SharedPreferences preferences1 = this.getSharedPreferences("NEW_USER", MODE_PRIVATE);
                    final SharedPreferences.Editor editor1 = preferences1.edit();
                    editor1.putString("NEW_password", NEW_PASS.getText().toString());
                    editor1.putBoolean("NEW_USER", true);
                    editor1.commit();
                    Toast.makeText(this, "NEW USER ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this,HomeActivity.class);
                    startActivity(intent);
                }
                else {
                    NEW_CONFPASS.setError("invalid Password ");
                    NEW_CONFPASS.requestFocus();
                }

            }
        }

    }



