package com.synergywebdesigners.nima;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Subcountry extends AppCompatActivity {
    private ListView lv;
    //EditText inputSearch;
    private String JSON_STRING;
    Intent i;
   final ArrayAdapter<String> adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcountry);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.img);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String value = sharedPreferences.getString("value","Not Available");
        setTitle(value);
        lv = (ListView) findViewById(R.id.list_view_sub);
        //inputSearch = (EditText) findViewById(R.id.inputSearch_sub);
         getJSON();
    }
    private void showResources() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_RESULT_SUB);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String state = jo.getString(Config.TAG_SUB_COUNTRY);
                HashMap<String, String> resource = new HashMap<>();
                resource.put(Config.TAG_SUB_COUNTRY, state);
                list.add(resource);
           /* SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("state", state);
            editor.commit();*/
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
          final ListAdapter adapter = new SimpleAdapter(Subcountry.this, list, R.layout.list_country,
                new String[]{Config.TAG_SUB_COUNTRY}, new int[]{R.id.resource_name});

        /* SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);
        String st = preferences.getString("state","");
        //String products[] = {st};
        //adapter=new ArrayAdapter<String>(this,R.layout.list_country,R.id.resource_name,products);*/

        lv.setAdapter(adapter);
        lv.setTextFilterEnabled(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick (AdapterView < ? > parent, View view,int position, long id) {

               /* Object value = lv.getItemAtPosition(position);
                String o =  value.toString();
                SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String sta = o.substring(7,o.length()-1);
                editor.putString("state", sta);
                editor.commit();
                String state = sharedPreferences.getString("state","Not Available");*/
               // Toast.makeText(Subcountry.this,"Your Message Fetching.."+state,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Subcountry.this, TourismBoard.class);
                Object value = lv.getAdapter().getItem(position);
                String o =  value.toString();
                String sta = o.substring(7,o.length()-1);
                intent.putExtra("st",sta);
               // Log.d("MyLog", "Value is: "+value);
                Toast.makeText(Subcountry.this,"Your have Selected:"+sta,Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        }

       );

      /*  lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object value = lv.getItemAtPosition(position);
                String o = (String) value.toString();
                Toast.makeText(Subcountry.this,"Your value:"+o,Toast.LENGTH_LONG).show();
            }
        });*/
    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // Toast.makeText(ChatActivity.this,"Your Message Fetching..",Toast.LENGTH_LONG).show();
                loading = ProgressDialog.show(Subcountry.this, "Showing List", "Please Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showResources();
            }

            @Override
            protected String doInBackground(Void... params) {
                ReuestHandler rh = new ReuestHandler();
               SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                String value = sharedPreferences.getString("value","Not Available");
                String val = (value.replaceAll(" ", "%20"));
               // Intent intent = getIntent();
                //String value = intent.getStringExtra("value");
                String s = rh.sendGetRequest(Config.URL_GET_ALL_RESOURCES_SUB+val);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

}
