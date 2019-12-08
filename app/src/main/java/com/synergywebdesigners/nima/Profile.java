package com.synergywebdesigners.nima;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Profile extends AppCompatActivity {
    private TextView pname,company_name,user,m_name,m_org,m_add,m_tele,m_mob,m_ofmail,m_permail,m_web,m_branche,m_iata,m_turnover,m_pan,m_taxno,m_orgid,m_wd,m_first_destination,m_second_destination,m_no_group,m_tradew,m_fauthorized,m_sauth,m_linkedin,m_reference;
    private ProgressDialog loading;
    private ImageView image;
    String app_server_URL = "http://www.synergywebdesigners.com/synergywebdesigners.com/ashish/nima/fcm_notification.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.img);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        pname = (TextView)findViewById(R.id.name);
        company_name = (TextView) findViewById(R.id.company_name);
        user = (TextView)findViewById(R.id.user);
        m_name = (TextView)findViewById(R.id.m_name);
       // m_org = (TextView)findViewById(R.id.m_org);
        // m_trade = (TextView)findViewById(R.id.m_trade);
        // m_date = (TextView)findViewById(R.id.m_date);
        //  m_ownership = (TextView)findViewById(R.id.m_ownership);
        m_add = (TextView)findViewById(R.id.m_add);
        m_tele = (TextView)findViewById(R.id.m_tele);
        m_mob = (TextView)findViewById(R.id.m_mob);
        m_ofmail = (TextView)findViewById(R.id.m_ofmail);
        m_permail = (TextView)findViewById(R.id.m_permail);
        m_web = (TextView)findViewById(R.id.m_web);
        image = (ImageView) findViewById(R.id.image);
       /* m_branche = (TextView)findViewById(R.id.m_branche);
        m_turnover = (TextView)findViewById(R.id.m_turnover);
        m_pan = (TextView)findViewById(R.id.m_pan);
        m_taxno = (TextView)findViewById(R.id.m_taxno);
        m_orgid = (TextView)findViewById(R.id.m_orgid);
        m_wd = (TextView)findViewById(R.id.m_wd);
        m_first_destination = (TextView)findViewById(R.id.m_first_destination);
        m_second_destination = (TextView)findViewById(R.id.m_second_destination);
        m_no_group = (TextView)findViewById(R.id.m_no_group);
        m_tradew = (TextView)findViewById(R.id.m_tradew);
        m_fauthorized = (TextView)findViewById(R.id.m_fauthorized);
        m_sauth = (TextView)findViewById(R.id.m_sauth);
        m_linkedin = (TextView)findViewById(R.id.m_linkedin);
        m_reference = (TextView)findViewById(R.id.m_reference);*/
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String user = sharedPreferences.getString(Config.USERNAME_SHARED_PREF,"Not Available");
        //Showing the current logged in email to textview
        // loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);
        //username.setText("User Name : " + user);
        String url = Config.DATA_URL+user;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Toast.makeText(Profile.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
       /* int timeout = 40000; // make the activity visible for 4 seconds

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                finish();
                Intent homepage = new Intent(Profile.this, MainActivity.class);
                startActivity(homepage);
            }
        }, timeout);*/
        sendToken();//For Notification

    }
    //show Profile Detail
    private void showJSON(String response){
        String picture = "";
        String username="";
        String name="";
        String org = "";
        String trade = "";
        String date = "";
        String ownership = "";
        String add = "";
        String tele = "";
        String mob = "";
        String ofmail = "";
        String permail = "";
        String web = "";
        String branche = "";
        String iata = "";
        String turnover = "";
        String pan = "";
        String taxno = "";
        String orgid = "";
        String wd = "";
        String fdestnaiton = "";
        String sdestination = "";
        String group = "";
        String tradew = "";
        String fauth = "";
        String sauth = "";
        String linkedin = "";
        String reference = "";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);
            picture = collegeData.getString(Config.KEY_PICTURE);
            username = collegeData.getString(Config.KEY_NAME);
            name = collegeData.getString(Config.KEY_UNAME);
            org = collegeData.getString(Config.KEY_ORG);
            trade = collegeData.getString(Config.KEY_TRADE);
            date = collegeData.getString(Config.KEY_DATE);
            ownership = collegeData.getString(Config.KEY_OWNERSHIP);
            add = collegeData.getString(Config.KEY_ADD);
            tele = collegeData.getString(Config.KEY_TELE);
            mob = collegeData.getString(Config.KEY_MOB);
            ofmail = collegeData.getString(Config.KEY_OFMAIL);
            permail = collegeData.getString(Config.KEY_PERMAIL);
            web = collegeData.getString(Config.KEY_WEB);
            branche = collegeData.getString(Config.KEY_BRANCHE);
            iata = collegeData.getString(Config.KEY_IATA);
            turnover = collegeData.getString(Config.KEY_TURNOVER);
            pan = collegeData.getString(Config.KEY_PAN);
            taxno = collegeData.getString(Config.KEY_TAXNO);
            orgid = collegeData.getString(Config.KEY_ORGID);
            wd = collegeData.getString(Config.KEY_WD);
            fdestnaiton = collegeData.getString(Config.KEY_FDESTINATION);
            sdestination = collegeData.getString(Config.KEY_SDESTINATION);
            group = collegeData.getString(Config.KEY_GROUP);
            tradew = collegeData.getString(Config.KEY_TRADEW);
            fauth = collegeData.getString(Config.KEY_FAUTHORIZED);
            sauth = collegeData.getString(Config.KEY_SAUTHORIZED);
            linkedin = collegeData.getString(Config.KEY_LINKEDIN);
            reference = collegeData.getString(Config.KEY_REFERENCE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        pname.setText(name);
        company_name.setText(org);
        user.setText(username);
        m_name.setText(name);
       // m_org.setText(org);
        // m_trade.setText("Trade Name:"+trade);
        //  m_date.setText("Date of Incorporation:\t" +date);
        //  m_ownership.setText("TYPE OF OWNERSHIP:\t"+ ownership);
        m_add.setText(add);
        m_tele.setText(tele);
        m_mob.setText(mob);
        m_ofmail.setText(ofmail);
        m_permail.setText(permail);
        m_web.setText(web);
        Picasso.with(Profile.this).load("http://synergywebdesigners.com/synergywebdesigners.com/ashish/nimadesktop/admin/"+picture).transform(new CircleTransform()).into(image);
        // m_branche.setText("BRANCHES:"+branche);
        // m_iata.setText("IATA Code:\t" +iata);
        // m_turnover.setText("SALES TURNOVER:\t"+ turnover);
        // m_pan.setText("PAN #of ORG:"+pan);
        //  m_taxno.setText("COMPANY SERVICE TAX NUMBER:\t" +taxno);
        // m_orgid.setText("COMPANY ORG MICE:\t"+ orgid);
        // m_wd.setText("DESTINATIONS CURRENTLY USING AND PROMOTING:"+wd);
        // m_first_destination.setText("FIRST PREFERRED DESTINATION IN NEXT TWO YEARS:\t" +fdestnaiton);
        // m_second_destination.setText("SECOND PREFERRED DESTINATION IN NEXT TWO YEARS:\t"+ sdestination);
        // m_no_group.setText("NUMBER OF MICE GROUPS HANDLED PER ANNUM:"+group);
        // m_tradew.setText("Is your Firm/Company member of any other trade association:\t" +tradew);
        // m_fauthorized.setText("NAME OF FIRST AUTHORIZED REP FROM YOUR ORG:\t"+ fauth);
        // m_sauth.setText("NAME OF Second AUTHORIZED REP FROM YOUR ORG:"+sauth);
        // m_linkedin.setText("LinkedIn Profile:"+linkedin);
        //  m_reference.setText("References:"+reference);
        // sharedpreferences = getSharedPreferences("APP", MODE_PRIVATE);
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("sender",name);
        //editor.putString("mob", mob);
        editor.putString(Config.TAG_ORG,org);
        editor.commit();
    }


    //Logout function
    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(Config.USERNAME_SHARED_PREF, "");

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(Profile.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Adding our menu to toolbar
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuLogout) {
            //calling logout method when the logout button is clicked
            logout();
        }
        return super.onOptionsItemSelected(item);
    }
    public void sendToken ()
    {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.FCM_PREF),Context.MODE_PRIVATE);
        final String token = sharedPreferences.getString(getString(R.string.FCM_TOKEN),"");
        SharedPreferences sharedPreferences1 = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        final String name = sharedPreferences1.getString("sender","");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, app_server_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("fcm_token",token);
                params.put("sender",name);
                return params;
            }
        };
        MySingleton.getmInstance(Profile.this).addToRequestque(stringRequest);
    }

}
