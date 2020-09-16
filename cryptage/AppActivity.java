package com.sci.fergani.cryptage.cryptage;

/**
 * Created by fergani on 25/04/2017.
 */

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sci.fergani.cryptage.R;

import java.util.ArrayList;
import java.util.List;


public class AppActivity {
    private View rootView;


    public AppActivity(View rootView) {
        this.rootView = rootView;

    }

    public View getFrag() {
        return rootView;
    }

    public static class AppAdapter extends ArrayAdapter<ApplicationInfo> {

        private List<ApplicationInfo> appList = null;
        private Context context;
        private PackageManager packageManager;

        public AppAdapter(Context context, int resource,
                          List<ApplicationInfo> objects) {
            super(context, resource, objects);

            this.context = context;
            this.appList = objects;
            packageManager = context.getPackageManager();
        }

        @Override
        public int getCount() {
            return ((null != appList) ? appList.size() : 0);
        }

        @Override
        public ApplicationInfo getItem(int position) {
            return ((null != appList) ? appList.get(position) : null);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;

            if(null == view) {
                LayoutInflater layoutInflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(R.layout.app_list, null);
            }

            ApplicationInfo data = appList.get(position);

            if(null != data) {
                TextView appName = (TextView) view.findViewById(R.id.app_name);
                TextView packageName = (TextView) view.findViewById(R.id.app_package);
                ImageView iconView = (ImageView) view.findViewById(R.id.app_icon);

                appName.setText(data.loadLabel(packageManager));
                packageName.setText(data.packageName);
                iconView.setImageDrawable(data.loadIcon(packageManager));
            }
            return view;
        }
    }

    /**
     * Created by fergani on 13/04/2017.
     */

    public static class MainList extends ListActivity {

        private PackageManager packageManager = null;
        private List<ApplicationInfo> applist = null;
        private AppAdapter listadapter = null;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.app_main);
            packageManager = getPackageManager();
            new LoadApplications().execute();
        }


        @Override
        protected void onListItemClick(ListView l, View v, int position, long id) {
            super.onListItemClick(l, v, position, id);

            ApplicationInfo app = applist.get(position);

            try{
                Intent intent = packageManager.getLaunchIntentForPackage(app.packageName);

                if(intent != null) {
                    startActivity(intent);
                }
            } catch(ActivityNotFoundException e) {
                Toast.makeText(MainList.this, e.getMessage(), Toast.LENGTH_LONG).show();
            } catch(Exception e) {
                Toast.makeText(MainList.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

        private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {

            ArrayList<ApplicationInfo> appList = new ArrayList<ApplicationInfo>();

            for(ApplicationInfo info : list) {
                try{
                    if(packageManager.getLaunchIntentForPackage(info.packageName) != null) {
                        appList.add(info);
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }

            return appList;
        }

        private class LoadApplications extends AsyncTask<Void, Void, Void> {

            private ProgressDialog progress = null;

            @Override
            protected Void doInBackground(Void... params) {

                applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));

                listadapter = new AppAdapter(MainList.this, R.layout.app_list, applist);

                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                setListAdapter(listadapter);
                progress.dismiss();
                super.onPostExecute(result);
            }

            @Override
            protected void onPreExecute() {
                progress = ProgressDialog.show(MainList.this, null, "Loading apps info...");
                super.onPreExecute();
            }
        }
    }
}
