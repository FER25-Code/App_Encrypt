package com.sci.fergani.cryptage.cryptage.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sci.fergani.cryptage.R;
import com.sci.fergani.cryptage.cryptage.system.Encryption;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import javax.crypto.Cipher;

public class Decrypted extends AppCompatActivity implements SearchView.OnQueryTextListener {
    DB_sqlite BD = new DB_sqlite(this);
    BD_Path DB = new BD_Path(this);
    SearchView searchView ;
    ArrayAdapter <String>adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filesw);
        final ListView lv;
        setTitle("Decrpte");
//        searchView= (SearchView) findViewById(R.id.id_serach);


        try {   ArrayList<String> FilesInFolder = GetFiles(getFilesDir()+File.separator+"/ramy");
            if(FilesInFolder.size()>0){
                lv = (ListView)findViewById(R.id.list);
                adapter2=  new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, FilesInFolder);
                lv.setAdapter(adapter2);
  //              searchView.setOnQueryTextListener(this);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(final AdapterView<?> parent, View v, final int position, long id) {
                        // Clicking on items
                        // String itemString=lv.getSelectedItem().toString();
                        AlertDialog.Builder builder = new AlertDialog.Builder(Decrypted.this);
                        builder.setTitle("Exit Application?");
                        builder.setMessage("Click yes to exit!");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id) {
                                String data=(String)parent.getItemAtPosition(position);
                                Toast.makeText(getApplicationContext(), "File is creat"+data, Toast.LENGTH_LONG).show();
                                String s= "/sdcard/ramy/"+data;


                                try {
                                    dec(data);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                               } });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener()
                        { public void onClick(DialogInterface dialog, int id)
                        {
                            dialog.cancel();

                        } });
                        AlertDialog dialog = builder.create();
                        dialog.show();



                    }
                });}

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void dec(String data) throws Exception {
        String s= GetFiles(getFilesDir()+File.separator+"/ramy")+data;
        Log.e("",""+s);
        File f = new File(s);
       String pathD= DB.getInfo(f.getName().toString());
        Log.e("","path"+pathD);
        Encryption.copy(Cipher.DECRYPT_MODE, s ,  pathD ,"password12345678");
        String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        Boolean result = BD.insertData("Decryption", f.getName().toString(), "\n" + mydate);
        f.delete();
        this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(s))));
      startActivity(new Intent(getApplicationContext(),Decrypted.class ));
    }
    public ArrayList<String> GetFiles(String DirectoryPath) {
        ArrayList<String> MyFiles = new ArrayList<String>();
        File f = new File(DirectoryPath);

        f.mkdirs();
        File[] files = f.listFiles();
        if (files.length == 0)
            return null;
        else {
            for (int i=0; i<files.length; i++)
                MyFiles.add(files[i].getName());

        }

        return MyFiles;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text= newText;
         adapter2.getFilter().filter(newText);
        return false;
    }
}




