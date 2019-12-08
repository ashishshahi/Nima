package com.synergywebdesigners.nima;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.synergywebdesigners.nima.R.id.imageView;


public class news extends AppCompatActivity{
    public static final String JSON_URL = "http://www.synergywebdesigners.com/synergywebdesigners.com/ashish/nima/get_all_news.php";
    private ListView listView;
   // SharedPreferences pref;
   private String JSON_STRING;
    TextView textView,textview2,textview3;
    ImageView imageview,newsadvimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.img);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
      //  pref = getSharedPreferences("news", MODE_PRIVATE);
        final ListView list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              // String item =  list.getItemAtPosition(position).toString();
                imageview = (ImageView) view.findViewById(R.id.imageDownloaded);
                 textView = (TextView) view.findViewById(R.id.title);
                textview2 = (TextView) view.findViewById(R.id.message);
                textview3 = (TextView) view.findViewById(R.id.created);
                String item2 = textView.getText().toString();
               // String item3 = textview2.getText().toString();
                String item4 = textview3.getText().toString();
                Intent i = new Intent(news.this,newsview.class);
                i.putExtra("item2",item2);
                i.putExtra("item3",ParseJSON.tmessage[position]);
                i.putExtra("item4",item4);
                i.putExtra("item1", ParseJSON.picture[position]);
                startActivity(i);
            }
        });
       listView = (ListView) findViewById(R.id.listView);
        newsadvimage = (ImageView) findViewById(R.id.news_adv);
        sendRequest();
        getJSON();
    }
    //ProgressDialog loading;
    private void sendRequest() {
       // loading = ProgressDialog.show(news.this, "NIMA News List", "Please Wait...", false, false);
       // loading.setCanceledOnTouchOutside(false);
        StringRequest stringRequest = new StringRequest(JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response);
               // loading.dismiss();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(news.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void showJSON(String json) {
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        newsshow cl = new newsshow(this, ParseJSON.ids, ParseJSON.ttitle, ParseJSON.picture,ParseJSON.bitmaps, ParseJSON.tmessage, ParseJSON.tcreated);
        listView.setAdapter(cl);
    }
    ///showing message
    private void showadvertisment(){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_NEWSADV);
            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String image = jo.getString(Config.TAG_NEWSADV_PICTURE);
                Picasso.with(this).load("http://synergywebdesigners.com/synergywebdesigners.com/ashish/nimadesktop/admin/"+image).into(newsadvimage);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //Toast.makeText(news.this, "Please Wait ...", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                JSON_STRING = s;
                showadvertisment();
            }

            @Override
            protected String doInBackground(Void... params) {
                ReuestHandler rh = new ReuestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_NEWSADV);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
}
