package com.synergywebdesigners.nima;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Ashish Shahi on 19-04-2017.
 */

public class NotificationDestination extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private EditText txtmessage;
    private Button btnsend;
    private ListView listView;
    private ImageView img1;
    private TextView title;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String JSON_STRING;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_chat);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(0,46,91)));
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.title, null);
        getSupportActionBar().setCustomView(mCustomView);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        Toolbar parent =(Toolbar) mCustomView.getParent();//first get parent toolbar of current action bar
        parent.setContentInsetsAbsolute(0,0);
        doTheAutoRefresh();
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String img = sharedPreferences.getString("img","not Available");
        Intent i = getIntent();
        String name = i.getStringExtra("reciver");
        img1 = (ImageView) findViewById(R.id.imgtitle);
        title = (TextView) findViewById(R.id.title);
        Picasso.with(NotificationDestination.this).load("http://synergywebdesigners.com/synergywebdesigners.com/ashish/nimadesktop/admin/"+img).transform(new CircleTransform()).into(img1);
        title.setText(name);
        txtmessage = (EditText) findViewById(R.id.txtmessage);
        btnsend = (Button) findViewById(R.id.buttonSend);
        btnsend.setOnClickListener(this);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        //View message
        listView = (ListView) findViewById(R.id.list);

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
    @Override
    public void onClick(View v) {
        if (v == btnsend) {
            addMessage();
            txtmessage.setText("");
            getJSON();
        }
    }
    private void addMessage(){
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String SenderName = sharedPreferences.getString("sender","Not Available");
        String company = sharedPreferences.getString("company","Not Available");
        Intent i = getIntent();
        String name = i.getStringExtra("reciver");
       // String company = i.getStringExtra("company");
        final String sname = new String(SenderName);
        final String rname = new String(name);
        final String  ucompany = new String(company);
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
                Toast.makeText(NotificationDestination.this,s, Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_SENDER_PCHAT,sname);
                params.put(Config.KEY_RECIVER_PCHAT,rname);
                params.put(Config.KEY_MESSAGE_PCHAT,message);
                params.put(Config.KEY_COMPANY_PCHAT,ucompany);
                params.put(Config.KEY_DAY_PCHAT,date);
                ReuestHandler rh = new ReuestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD_PCHAT, params);
                return res;
            }
        }
        AddMessage ae = new AddMessage();
        ae.execute();
    }
    ///showing message
    private void showMesage(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY_PCHAT);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String message = jo.getString(Config.TAG_MESSAGE_PCHAT);
                String created = jo.getString(Config.TAG_CREATED_PCHAT);
                String sender = jo.getString(Config.TAG_SENDER_PCHAT);
                HashMap<String,String> employees = new HashMap<>();
                employees.put(Config.TAG_MESSAGE_PCHAT,message);
                employees.put(Config.TAG_CREATED_PCHAT,created);
                employees.put(Config.TAG_SENDER_PCHAT,sender);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(NotificationDestination.this, list, R.layout.layout_message,
                new String[]{Config.TAG_MESSAGE_PCHAT,Config.TAG_CREATED_PCHAT,Config.TAG_SENDER_PCHAT},
                new int[]{R.id.message,R.id.created,R.id.u_name});
        listView.setAdapter(adapter);

    }

    private void getJSON(){
        // swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setRefreshing(true);
        class GetJSON extends AsyncTask<Void,Void,String> {

            // ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // Toast.makeText(Message.this,"Please Wait..",Toast.LENGTH_LONG).show();
                // loading = ProgressDialog.show(Message.this,"Showing Message","Please Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                // loading.dismiss();

                JSON_STRING = s;
                showMesage();

            }

            @Override
            protected String doInBackground(Void... params) {
                ReuestHandler rh = new ReuestHandler();
                SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                String SenderName = sharedPreferences.getString("sender","Not Available");
                String sname = new String(SenderName);
                Intent i = getIntent();
                String name = i.getStringExtra("reciver");
                String reciver = (name.replaceAll(" ", "%20"));
                String sender = (sname.replaceAll(" ", "%20"));
                String s = rh.sendGetRequest(Config.URL_GET_ALL_PCHAT+sender+Config.URL_GET_DUMMY_PCHAT+reciver);
                return s;
            }
        }

        GetJSON gj = new GetJSON();
        gj.execute();
        swipeRefreshLayout.setRefreshing(false);
    }
    /*Date and Time Format and Date and Time Function*/
    private String getDateTime() {
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
                "dd MMM hh :mm aa", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);

    }
        /*Page Auto Refresh Code Refreshing every Five second*/
    private void doTheAutoRefresh() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Write code for your refresh logic
                getJSON();
                doTheAutoRefresh();
                trimCache(getBaseContext());
            }
        }, 5000);
    }
    public static void trimCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    /*code for Cashe Cleaning*/
    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        // The directory is now empty so delete it
        return dir.delete();
    }
}
