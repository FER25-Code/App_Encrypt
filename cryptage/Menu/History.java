package com.sci.fergani.cryptage.cryptage.Menu;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sci.fergani.cryptage.R;
import com.sci.fergani.cryptage.cryptage.user.DB_sqlite;

import java.util.ArrayList;

public class History extends AppCompatActivity {


    private static final String TAG = "ListDataActivity";

    DB_sqlite mDatabaseHelper;

    private ListView mListView;

    DB_sqlite BD = new DB_sqlite(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        setTitle("History");
        mListView = (ListView) findViewById(R.id.listView);
        mDatabaseHelper = new DB_sqlite(this);
        final ArrayList<String> listData = mDatabaseHelper.getInfo();
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(arrayAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, final int position, final long id1) {

                String[] list = {"Delete", "Delete ALL"};
                AlertDialog.Builder builder = new AlertDialog.Builder(History.this);
                builder.setTitle("Choose your class");
                builder.setItems(list, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Log.e("Dialog", "Position = " + id1);
                        if (id == 0) {
                            int u = Integer.parseInt(listData.get(position).split("-")[0]);
                            Long i = BD.delete(u);
                            listData.remove(position);

                            Toast.makeText(getApplicationContext(), i+" ", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "sup all", Toast.LENGTH_LONG).show();
                         BD.deleteAll();
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }





}































