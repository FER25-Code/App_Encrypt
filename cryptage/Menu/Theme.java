package com.sci.fergani.cryptage.cryptage.Menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sci.fergani.cryptage.R;


public class Theme extends AppCompatActivity {

    SharedPreferences shard ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        shard = getSharedPreferences("Setting", Context.MODE_PRIVATE);

        int myStyle = shard.getInt("myStyle",0);

        switch (myStyle){

            case 0:

                this.setTheme(R.style.AppTheme);

                break;

            case 1:

              this. setTheme(R.style.AppTheme1);

                break;

            case 2:

                setTheme(R.style.AppTheme2);

            //    toolbar.setTheme(R.style.AppTheme2);

                break;


        }


        setContentView(R.layout.activity_theme);



        findViewById(R.id.btn_0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyStyle(0);
            }
        });

        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyStyle(1);
            }
        });

        findViewById(R.id.btn_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyStyle(2);
            }
        });

    }

    public void  MyStyle(int myStyle){
        SharedPreferences.Editor editor = shard.edit();
        editor.putInt("myStyle",myStyle);
        editor.apply();

        finish();

        startActivity(new Intent(this, Theme.class));

    }

}
