package com.synergywebdesigners.nima;

import android.Manifest;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;


public class ChatActivity extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private EditText txtmessage;

    private Button btnsend;
    private ListView listView;
    private String JSON_STRING;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.img);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        txtmessage = (EditText) findViewById(R.id.txtmessage);
        btnsend = (Button) findViewById(R.id.buttonSend);
        btnsend.setOnClickListener(this);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        //View message
        listView = (ListView) findViewById(R.id.list);
       // listView.setOnItemClickListener(ChatActivity.this);

       swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        getJSON();
                                    }
                                }
        );
    }
    @Override
    public void onRefresh() {
        getJSON();
    }
    //Adding an Message
    private void addMessage(){
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String user = sharedPreferences.getString(Config.USERNAME_SHARED_PREF,"Not Available");
     //SharedPreferences   sharedpreferences = getSharedPreferences("APP", MODE_PRIVATE);
        String org = sharedPreferences.getString(Config.TAG_ORG,"Not Available");
        //store string variable into another string
        final String username = new String(user);
        final String organisation = new String(org);
        final String date = new String(getDateTime());
        final String message = txtmessage.getText().toString().trim();
        class AddMessage extends AsyncTask<Void,Void,String> {

           // ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
               // Toast.makeText(ChatActivity.this,"Your Message sending..",Toast.LENGTH_LONG).show();
               // loading = ProgressDialog.show(ChatActivity.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
               // loading.dismiss();
                Toast.makeText(ChatActivity.this,s,Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_USER,username);
                params.put(Config.KEY_MESSAGE,message);
                params.put(Config.KEY_ORGNISATION,organisation);
                params.put(Config.KEY_CREATE,date);
                ReuestHandler rh = new ReuestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }
        }
        AddMessage ae = new AddMessage();
        ae.execute();
    }
    @Override
    public void onClick(View v) {

            if (v == btnsend) {
                    addMessage();
                     getJSON();
                txtmessage.setText("");
            }



    }
    //calling data
    public void call(View v) {
       /* SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        final  String mob = sharedPreferences.getString(Config.TAG_ORG,"Not Available");*/
        TextView tv = (TextView) findViewById(R.id.user);
        String val = tv.getText().toString();
        String mob = new String("+91"+val);
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+mob));
        startActivity(intent);
        Log.v("Calling", "Calling..... "+mob);
    }
   /* public void clear(View v){
        txtmessage.setText("");
    }*/

    ///showing message
    private void showMesage(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String username = jo.getString(Config.TAG_UNAME);
                String message = jo.getString(Config.TAG_MESSAGE);
                String org = jo.getString(Config.TAG_ORG);
                String created = jo.getString(Config.TAG_CREATED);
                HashMap<String,String> employees = new HashMap<>();
                employees.put(Config.TAG_UNAME,username);
                employees.put(Config.TAG_MESSAGE,message);
                employees.put(Config.TAG_ORG,org);
                employees.put(Config.TAG_CREATED,created);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                ChatActivity.this, list, R.layout.message_layout,
                new String[]{Config.TAG_UNAME,Config.TAG_MESSAGE,Config.TAG_ORG,Config.TAG_CREATED},
                new int[]{R.id.user, R.id.message,R.id.org,R.id.created});

        listView.setAdapter(adapter);
      /* final String phone = R.id.mob.getText().toString();
        call = (ImageButton) findViewById(R.id.btncall);
        call.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+phone));
                if (ActivityCompat.checkSelfPermission(ChatActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });*/
    }
    private void getJSON(){
        swipeRefreshLayout.setRefreshing(true);
        class GetJSON extends AsyncTask<Void,Void,String>{

            //ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //Toast.makeText(ChatActivity.this,"Please Wait..",Toast.LENGTH_SHORT).show();
                //loading = ProgressDialog.show(ChatActivity.this,"Showing Message","Please Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //loading.dismiss();
                JSON_STRING = s;
                showMesage();
            }

            @Override
            protected String doInBackground(Void... params) {
                ReuestHandler rh = new ReuestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
        swipeRefreshLayout.setRefreshing(false);
    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd MMM hh :mm aa", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}
