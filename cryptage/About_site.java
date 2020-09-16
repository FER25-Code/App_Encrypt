package com.sci.fergani.cryptage.cryptage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sci.fergani.cryptage.R;

public class About_site extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.site);
        String s= "WWW.AppPrivacy.com";
        WebView bro = (WebView) findViewById(R.id.web);
        bro.setWebViewClient(new WebViewClient());
        bro.loadUrl(s);
    }
}
