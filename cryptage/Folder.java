package com.sci.fergani.cryptage.cryptage;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fergani on 17/05/2017.
 */

public class Folder extends ListActivity {

    private List<String> file1 = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File root22 = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        ListDir(root22);
    }
    public void Menu(View V){
        Intent intent = new Intent(Folder.this, Context.class);
        startActivity(intent);
    }


    void ListDir(File f) {
        File[] files = f.listFiles();
        file1.clear();
        for (File file : files) {
            file1.add(file.getPath());
        }
        ArrayAdapter<String> directoryList12 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, file1);
        setListAdapter(directoryList12);
    }
}
