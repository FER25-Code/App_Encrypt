package com.sci.fergani.cryptage.cryptage.Menu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sci.fergani.cryptage.R;
import com.sci.fergani.cryptage.cryptage.HomeActivity;
import com.sci.fergani.cryptage.cryptage.MainActivity;

public class Profile extends AppCompatActivity {
    Button NEW_PROF;
    EditText NEW_EMAIL , NEW_CONFPASS, NEW_PASS;

//    android.support.design.widget.TextInputLayout NEW_PASS ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        setTitle("Profile");
        NEW_PROF = (Button) findViewById(R.id.new_Profil);
        NEW_EMAIL= (EditText) findViewById(R.id.new_email);
        NEW_CONFPASS= (EditText) findViewById(R.id.new_conf);
        NEW_PASS = (EditText) findViewById(R.id.inpass);

    }
  public void NEW_USER (View view){

      if (!MainActivity.validateEmail(NEW_EMAIL.getText().toString())) {
          NEW_EMAIL.setError("invalid Email");
          NEW_EMAIL.requestFocus();

      } else {

          if (!MainActivity.validatePass(NEW_PASS.getText().toString())) {
              NEW_PASS.setError("invalid Password 8 caracter");
              NEW_PASS.requestFocus();

          } else {

              if ( NEW_CONFPASS.getText().toString().equals(NEW_PASS.getText().toString()) ) {
                  final SharedPreferences preferences1 = this.getSharedPreferences("NEW_USER", MODE_PRIVATE);
                  final SharedPreferences.Editor editor1 = preferences1.edit();
                  editor1.putString("NEW_Email", NEW_EMAIL.getText().toString());
                  editor1.putString("NEW_password", NEW_PASS.getText().toString());
                  editor1.putBoolean("NEW_USER", true);
                  editor1.commit();
                  Toast.makeText(this, "NEW USER ", Toast.LENGTH_SHORT).show();
                  Log.e("NEW",""+ NEW_PASS.getText().toString()+"  "+ NEW_EMAIL.getText().toString());
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


}
