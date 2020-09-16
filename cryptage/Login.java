package com.sci.fergani.cryptage.cryptage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sci.fergani.cryptage.R;

import java.io.File;
import java.io.FilePermission;
import java.io.IOException;

public class Login extends AppCompatActivity {
Button login ;
    EditText password ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        password= (EditText) findViewById(R.id.editText5);
       login = (Button) findViewById(R.id.login);

    }

    public void Login(View view) {
        final SharedPreferences preferences = this.getSharedPreferences("user", MODE_PRIVATE);
        String passwordBD = preferences.getString("password", "");
        final SharedPreferences preferences1 = this.getSharedPreferences("NEW_USER", MODE_PRIVATE);
        String NEW_PASSWORD = preferences1.getString("NEW_password","");

        if (preferences1.getBoolean("NEW_USER", false) == false){


    if (!(password.getText().toString()).equals(passwordBD)){

        password.setError("invalid Password ");
        password.requestFocus();

    }else {

        Intent intent = new Intent(Login.this,HomeActivity.class);
        startActivity(intent);
//        File file = new File(Environment.getExternalStorageDirectory()+File.separator+"/ramy");
        File file = new File(getFilesDir()+File.separator+"/ramy");
        file.mkdirs();
        Log.e("File","Creite"+ Environment.getExternalStorageDirectory()+File.separator+"ramy");
    //   ContextWrapper cw = new ContextWrapper(this);
      //  File directory = cw.getDir("media", Context.MODE_PRIVATE);
        //Log.e("File","Creite"+directory);
        finish();


    }}else {


            if (!(password.getText().toString()).equals(NEW_PASSWORD)){

                password.setError("invalid Password ");
                password.requestFocus();

            }else {

                Intent intent = new Intent(Login.this,HomeActivity.class);
                startActivity(intent);

                File file = new File(Environment.getExternalStorageDirectory()+File.separator+"/ramy");
                file.mkdirs();
                file.getTotalSpace();
                Log.e("",""+file.getTotalSpace());
                File file1=new File(file.getPath()+"/abc.txt");
                try {

                    file1.createNewFile();
                   file1.setReadOnly();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e("File","Creite"+ Environment.getExternalStorageDirectory()+File.separator+"ramy");

                FilePermission permission=new FilePermission("ramy", "r");

                //   ContextWrapper cw = new ContextWrapper(this);
                //  File directory = cw.getDir("media", Context.MODE_PRIVATE);
                //Log.e("File","Creite"+directory);
                finish();



            }
        }


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menulogin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent = new Intent(Login.this,PasswordActivity.class);
        startActivity(intent);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}


