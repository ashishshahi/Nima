package com.synergywebdesigners.nima;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * Created by PC on 14-Jan-17.
 */

public class WebviewActivity extends AppCompatActivity {
    private WebView webView;
    ProgressBar progressBar;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.img);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://turningpointdxb.com");

        //String customHtml = "<html><body><h1>Hello, WebView</h1></body></html>";
       // webView.loadData(customHtml, "text/html", "UTF-8");

    }
}
