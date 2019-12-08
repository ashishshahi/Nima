package com.synergywebdesigners.nima;

import android.app.ActionBar;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class TourismBoard extends AppCompatActivity {
    private ListView listView;
    private ImageView imageView;
    private String JSON_STRING;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourism_board);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.img);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        //dynamically set Title
        Intent intent = getIntent();
        String state = intent.getStringExtra("st");
        setTitle(state);
        listView = (ListView) findViewById(R.id.list_tourism);
        imageView = (ImageView) findViewById(R.id.imageDownloaded);
        getJSON();
       /* Intent intent = getIntent();
        String state = intent.getStringExtra("st");
        ActionBar ab = getActionBar();
        ab.setTitle(state);*/
    }
    ///showing message
    private void showMesage(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_TOURISM);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String title = jo.getString(Config.TAG_TITLE);
                String detail = jo.getString(Config.TAG_DETAIL);
                String image = jo.getString(Config.TAG_IMAGE);
                HashMap<String,String> tourism = new HashMap<>();
                tourism.put(Config.TAG_TITLE,title);
                tourism.put(Config.TAG_DETAIL,Html.fromHtml(detail).toString());
                list.add(tourism);
                Picasso.with(this).load("http://synergywebdesigners.com/synergywebdesigners.com/ashish/nimadesktop/admin/"+image).into(imageView);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                TourismBoard.this, list, R.layout.layout_hotel, new String []{Config.TAG_TITLE ,Config.TAG_DETAIL}, new int[]{R.id.hotel ,R.id.detail});


        listView.setAdapter(adapter);

    }
    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Toast.makeText(TourismBoard.this,"Please Wait ...",Toast.LENGTH_SHORT).show();
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
                //SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                // String state = sharedPreferences.getString("state","Not Available");
                Intent intent = getIntent();
                String state = intent.getStringExtra("st");
                String val = (state.replaceAll(" ", "%20"));
                String s = rh.sendGetRequest(Config.URL_GET_ALL_TOURISM+val);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

}
