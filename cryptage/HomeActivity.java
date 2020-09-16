package com.sci.fergani.cryptage.cryptage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.sci.fergani.cryptage.R;
import com.sci.fergani.cryptage.cryptage.Menu.About;
import com.sci.fergani.cryptage.cryptage.Menu.Help;
import com.sci.fergani.cryptage.cryptage.Menu.History;
import com.sci.fergani.cryptage.cryptage.Menu.Profile;
import com.sci.fergani.cryptage.cryptage.Menu.Settings;
import com.sci.fergani.cryptage.cryptage.Menu.Theme;
import com.sci.fergani.cryptage.cryptage.SMS.SMSActivity;
import com.sci.fergani.cryptage.cryptage.system.Encryption;
import com.sci.fergani.cryptage.cryptage.user.BD_Path;
import com.sci.fergani.cryptage.cryptage.user.DB_sqlite;
import com.sci.fergani.cryptage.cryptage.user.Decrypted;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Calendar;

import javax.crypto.Cipher;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "HomeActivity";
    private ImageButton DEC;
    private ImageButton other;
    public  Toolbar toolbar;

    DB_sqlite BD = new DB_sqlite(this);
    BD_Path DB = new BD_Path(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
        initListeners();
        DEC = (ImageButton) findViewById(R.id.encept);

        final SharedPreferences preferences = this.getSharedPreferences("user", MODE_PRIVATE);
        String email1 = preferences.getString("email", "");



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_draw);
        navigationView.setNavigationItemSelectedListener(this);


    }

    public void Decrypt(View view) {
        Intent i = new Intent(HomeActivity.this, Decrypted.class);
        startActivity(i);


    }

    private void initViews() {

        other = (ImageButton) findViewById(R.id.imageButton4);

    }




    private void initListeners() {

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otherSelector();
            }
        });
    }

    private void otherSelector() {

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("*/*");
        galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(galleryIntent, 1);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id;
        id = item.getItemId();
        if (id == R.id.nav_profile) {
            Intent intent = new Intent(HomeActivity.this, Profile.class);
            startActivity(intent);
        } else if (id == R.id.nav_theme) {
            Intent intent = new Intent(HomeActivity.this, Theme.class);
            startActivity(intent);
        } else if (id == R.id.nav_history) {
            Intent intent = new Intent(HomeActivity.this, History.class);
            startActivity(intent);
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(HomeActivity.this, Settings.class);
            startActivity(intent);
        } else if (id == R.id.nav_help) {
            Intent intent = new Intent(HomeActivity.this, Help.class);
            startActivity(intent);
        } else if (id == R.id.nav_About) {
            Intent intent = new Intent(HomeActivity.this, About.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(HomeActivity.this, Theme.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(HomeActivity.this, Theme.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            if (!(data == null)) {
                Uri uri = data.getData();

                try {
                    PathUtil.getPath(this, uri);
                    Log.e("path", PathUtil.getPath(this, uri).toString());
                    Encryption.copy(Cipher.ENCRYPT_MODE, PathUtil.getPath(this, uri).toString(),
                            getFilesDir() + "/ramy/ "
                            , "password12345678");

                    String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                    Log.e("Date", "time" + mydate);
                    File fileD = new File(PathUtil.getPath(this, uri));
                    String name = fileD.getName();
                    DB.insertPATh(PathUtil.getPath(this, uri).toString(),name);
                    Log.e("",""+fileD.getName());
                    Boolean result = BD.insertData("Encryption", fileD.getName().toString(), "\n" + mydate);
                    fileD.delete();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            } else {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);

            }
        }
    }


    public void RAMY(View view2) {
        Intent i = new Intent(HomeActivity.this, AppActivity.MainList.class);
        startActivity(i);

    }

    public void SMS(View view2) {

        Intent i = new Intent(HomeActivity.this, SMSActivity.class);
        startActivity(i);


    }

    public void theme(View v) {
        toolbar.setBackgroundColor(getResources().getColor(R.color.material_deep_teal_500));
       // drawerLayout.setBackgroundColor(getResources().getColor(R.color.material_deep_teal_500));
        //  navigationView.setBackgroundColor(getResources().getColor(R.color.material_deep_teal_500));
        //drawerLayout.closeDrawer(Gravity.START);
    }
    public void show_Folder(View view){
        Intent x = new Intent(HomeActivity.this, Folder.class);
        startActivity(x);
    }

    public void xxx(View view) {
        Intent fi= new Intent(this,Folder.class);
    startActivity(fi);
    }

}


