package com.synergywebdesigners.nima;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Noticeboard extends AppCompatActivity {
    private ListView listView;
    private String JSON_STRING;
    TextView textView,textview2,textview3;
    ImageView imageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticeboard);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.img);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        listView = (ListView) findViewById(R.id.list_notice);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // String item =  list.getItemAtPosition(position).toString();
                imageview = (ImageView) view.findViewById(R.id.imageDownloaded);
                textView = (TextView) view.findViewById(R.id.title);
                textview2 = (TextView) view.findViewById(R.id.message);
                textview3 = (TextView) view.findViewById(R.id.created);
                String item2 = textView.getText().toString();
                String item3 = textview2.getText().toString();
                String item4 = textview3.getText().toString();
                Intent i = new Intent(Noticeboard.this,Notiseshow.class);
                i.putExtra("item2",item2);
                i.putExtra("item3",ParseNotice.ndetail[position]);
                i.putExtra("item4",item4);
                i.putExtra("item1", ParseNotice.npicture[position]);
                startActivity(i);
            }
        });
        sendRequest();
    }
   // ProgressDialog loading;
    private void sendRequest() {
       // loading = ProgressDialog.show(Noticeboard.this, "NIMA Member Contact List", "Please Wait...", false, false);
       // loading.setCanceledOnTouchOutside(false);
        StringRequest stringRequest = new StringRequest(Config.URL_GET_ALL_NOTICE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response);
               // loading.dismiss();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Noticeboard.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void showJSON(String json) {
        ParseNotice pj = new ParseNotice(json);
        pj.parseNotice();
        noticelayout cl = new noticelayout(this,ParseNotice.ntitle,ParseNotice.npicture,ParseNotice.ndetail,ParseNotice.nday);
        listView.setAdapter(cl);
    }
}
