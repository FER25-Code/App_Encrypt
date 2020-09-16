package com.sci.fergani.cryptage.cryptage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sci.fergani.cryptage.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText inputemail, inputpassword, inputcomfpass;
    Button btnsin;
    TextView myemail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SharedPreferences preferences = this.getSharedPreferences("user", MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        myemail = (TextView) findViewById(R.id.textViewemail);
        inputemail = (EditText) findViewById(R.id.editText);
        inputpassword = (EditText) findViewById(R.id.editText2);
        inputcomfpass = (EditText) findViewById(R.id.editText3);
        btnsin = (Button) findViewById(R.id.b_site);




        if (preferences.getBoolean("issignin", false) == true) {
            Intent i = new Intent(MainActivity.this, Login.class);
            startActivity(i);
            finish();
        }

        btnsin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmail(inputemail.getText().toString())) {
                    inputemail.setError("invalid Email");
                    inputemail.requestFocus();
                } else {
                    if (!validatePass(inputpassword.getText().toString())) {
                        inputpassword.setError("invalid Password 8 caracter");
                        inputpassword.requestFocus();

                    } else {
                        if (inputpassword.getText().toString().equals(inputcomfpass.getText().toString())) {
                            editor.putString("email", inputemail.getText().toString());
                            editor.putString("password", inputpassword.getText().toString());
                            editor.putBoolean("issignin", true);
                            editor.commit();

                            Intent i = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(i);

                            finish();
                        } else {
                            inputcomfpass.setError("invalid Password 8 caracter");
                            inputcomfpass.requestFocus();
                        }

                    }
                }

            }


        });
    }

    public static boolean validatePass(String pass) {
        if (pass != null && pass.length() >2) {
            return true;
        } else
            return false;
    }


    public static boolean validateEmail(String email) {

        String emailPatton = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[_A-Za-z0-9-]+(\\.[_A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPatton);

        Matcher matcher = pattern.matcher(email);

        return matcher.matches();

    }
}
