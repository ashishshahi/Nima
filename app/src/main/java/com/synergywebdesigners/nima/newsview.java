package com.synergywebdesigners.nima;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by PC on 23-Dec-16.
 */

public class newsview extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsshow);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.img);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        ImageView imgproduct1 = (ImageView) findViewById(R.id.imageDownloaded);
        TextView txtProduct2 = (TextView) findViewById(R.id.title);
        TextView txtProduct3 = (TextView) findViewById(R.id.message);
        TextView txtProduct4 = (TextView) findViewById(R.id.created);
        Intent i = getIntent();
        // getting attached intent data
        String item1 = i.getStringExtra("item1");
        String item2 = i.getStringExtra("item2");
        String item3 = i.getStringExtra("item3");
        String item4 = i.getStringExtra("item4");
        setTitle(item2);
        // displaying selected product name
       // imgproduct1.setImage(item1);
        Picasso.with(newsview.this).load("http://synergywebdesigners.com/synergywebdesigners.com/ashish/nimadesktop/admin/"+item1).fit().into(imgproduct1);
        txtProduct2.setText(item2);
        txtProduct3.setText(Html.fromHtml(item3), TextView.BufferType.SPANNABLE);
       // txtProduct3.getSettings().setJavaScriptEnabled(true);
       // txtProduct3.loadDataWithBaseURL("", item3, "text/html", "UTF-8", "");
        txtProduct4.setText(item4);
    }
}
