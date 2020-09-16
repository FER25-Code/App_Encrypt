package com.sci.fergani.cryptage.cryptage.Menu;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sci.fergani.cryptage.R;
import com.sci.fergani.cryptage.cryptage.About_site;

public class About extends AppCompatActivity {
Button site , histourique ;
    FloatingActionButton fb ,tw,google ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
     setTitle("About");
        site= (Button) findViewById(R.id.b_site);
        histourique= (Button) findViewById(R.id.b_histrique);
        fb= (FloatingActionButton) findViewById(R.id.floating_fb);
        tw = (FloatingActionButton) findViewById(R.id.floating_tw);
        google = (FloatingActionButton) findViewById(R.id.floating_g);

    }

    public void get_Site(View view) {
        Intent intentFb = new Intent(this, About_site.class);
        startActivity(intentFb);

    }

    public void get_Historique(View view) {
        String[] list = {"Bienvenue a l'application ","v1.0"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(list, new DialogInterface.OnClickListener(){
            @Override public void onClick(DialogInterface dialog, int id)
        {finish(); } });
        AlertDialog dialog = builder.create(); dialog.show();
    }

    public void go_Twiter(View view) {
        Intent intentFb = new Intent(Intent.ACTION_VIEW, Uri.parse("www.facebook.com"));
        startActivity(intentFb);
    }

    public void go_FB(View view) {
        Intent intentTW = new Intent(Intent.ACTION_VIEW, Uri.parse("www.twiter.com"));
        startActivity(intentTW);

    }
    public void go_Google(View view) {
    }
    
}
