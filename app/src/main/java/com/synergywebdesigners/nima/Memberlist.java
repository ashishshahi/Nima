package com.synergywebdesigners.nima;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Memberlist extends AppCompatActivity {

    public static final String JSON_URL = "http://synergywebdesigners.com/synergywebdesigners.com/ashish/nima/contact_list.php?sender=";
    private ListView listView;
    TextView username,ucompany;
    ImageView imageView;
    View v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.img);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        listView  = (ListView) findViewById(R.id.listView);
        v = new View(getBaseContext());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                v.setBackgroundResource(0);
                view.setBackgroundResource(R.color.colorPrimaryDark);
                v = view;
                view.setSelected(true);
                imageView = (ImageView) view.findViewById(R.id.image_user);
                username = (TextView) view.findViewById(R.id.name);
                ucompany = (TextView) view.findViewById(R.id.c_name);
                Intent i = new Intent(Memberlist.this ,PersonalChat.class);
                String name = username.getText().toString();
                String company = ucompany.getText().toString();
                i.putExtra("name",name);
                i.putExtra("company",company);
                i.putExtra("img",ParseJSONPCHAT.upicture[position]);
                startActivity(i);
                view.setSelected(true);
                SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("company",company);
                editor.putString("img",ParseJSONPCHAT.upicture[position]);
                editor.commit();


            }
        });
        listView = (ListView) findViewById(R.id.listView);

        sendRequest();

    }


    ProgressDialog loading;
    private void sendRequest() {

        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String SenderName = sharedPreferences.getString("sender","Not Available");
        String sender = (SenderName.replaceAll(" ", "%20"));
        loading = ProgressDialog.show(Memberlist.this, "NIMA Member Contact List", "Please Wait...", false, false);
        loading.setIcon(R.mipmap.ic_launcher);
        loading.setCanceledOnTouchOutside(true);
        StringRequest stringRequest = new StringRequest(JSON_URL+sender, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                showJSON(response);
                loading.dismiss();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Memberlist.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    private void showJSON(String json) {
        ParseJSONPCHAT pj = new ParseJSONPCHAT(json);
        pj.parseJSONPCHAT();
        MemberListView cl = new MemberListView(Memberlist.this, ParseJSONPCHAT.ids, ParseJSONPCHAT.uname,ParseJSONPCHAT.ucompany,ParseJSONPCHAT.upicture);
        listView.setAdapter(cl);
    }
}
