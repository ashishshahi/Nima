package com.synergywebdesigners.nima;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Event extends AppCompatActivity {
  private ListView listView;
  private String JSON_STRING;
  private TextView tv;
  private WebView wv;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_event);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setLogo(R.drawable.img);
    getSupportActionBar().setDisplayUseLogoEnabled(true);
   //listView = (ListView) findViewById(R.id.list_event);
    // tv = (TextView) findViewById(R.id.text);
    wv = (WebView) findViewById(R.id.text) ;
    getJSON();
  }
  ///showing message
  private void showMesage(){
    JSONObject jsonObject = null;
    //ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
    try {
      jsonObject = new JSONObject(JSON_STRING);
      JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_EVENT);

      for(int i = 0; i<result.length(); i++){
        JSONObject jo = result.getJSONObject(i);
        String desc = jo.getString(Config.TAG_EVENT_DESC);
       // HashMap<String,String> tourism = new HashMap<>();
        // tv.setText(Html.fromHtml(desc), TextView.BufferType.SPANNABLE);
        final String mimeType = "text/html";
        final String encoding = "UTF-8";
        WebSettings webSettings = wv.getSettings();
        // webSettings.setTextSize(WebSettings.TextSize.SMALLEST);
        webSettings.setDefaultFontSize(10);
        webSettings.setTextZoom(webSettings.getTextZoom() + 20);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadDataWithBaseURL("", desc, mimeType, encoding, "");
        //tourism.put(Config.TAG_EVENT_DESC,Html.fromHtml(desc).toString().trim());
       // list.add(tourism);
      }

    } catch (JSONException e) {
      e.printStackTrace();
    }

    /*ListAdapter adapter = new SimpleAdapter(
            Event.this, list, R.layout.layout_events, new String []{Config.TAG_EVENT_DESC}, new int[]{R.id.events});


    listView.setAdapter(adapter);*/
  }
  private void getJSON() {
    class GetJSON extends AsyncTask<Void, Void, String> {

      @Override
      protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(Event.this, "Please Wait ...", Toast.LENGTH_SHORT).show();
      }

      @Override
      protected void onPostExecute(String s) {
        super.onPostExecute(s);
        JSON_STRING = s;
        showMesage();
      }

      @Override
      protected String doInBackground(Void... params) {
        ReuestHandler rh = new ReuestHandler();
        String s = rh.sendGetRequest(Config.URL_GET_ALL_EVENT );
        return s;
      }
    }
    GetJSON gj = new GetJSON();
    gj.execute();
  }
}
