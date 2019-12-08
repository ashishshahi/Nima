package com.synergywebdesigners.nima;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity {

    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    EditText inputm_name;
    EditText inputm_org;
    EditText inputm_trade;
    EditText inputm_mob;
    EditText inputm_ofmail;
    EditText inputm_web;

    // url to create new product
    private static String url_createrecord = "http://synergywebdesigners.com/synergywebdesigners.com/ashish/nima/createrecord.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.img);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        TextView loginScreen =(TextView) findViewById(R.id.link_to_login);
        // Listening to Login Screen link
        loginScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Closing registration screen
                // Switching to Login Screen/closing register screen
               // Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                //startActivity(i);
                finish();
            }
        });
        // Edit Text
        inputm_name = (EditText) findViewById(R.id.m_name);
        inputm_org = (EditText) findViewById(R.id.m_org);
        inputm_trade = (EditText) findViewById(R.id.m_trade);
        inputm_mob = (EditText) findViewById(R.id.m_mob);
        inputm_ofmail = (EditText) findViewById(R.id.m_ofmail);
        inputm_web = (EditText) findViewById(R.id.m_web);
        // Create button
        Button btnRegister = (Button) findViewById(R.id.btnRegister);

        // button click event
        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread
               // new CreateNewRecord().execute(); This class unhide and function start working
                Toast.makeText(Register.this, "Please Contact NIMA Administration For Open Your Account and find user Name and Password", Toast.LENGTH_LONG).show();
            }
        });

    }

    /**
     * Background Async Task to Create new product
     * */
    class CreateNewRecord extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Register.this);
            pDialog.setMessage(" Member has been successfuly Created ..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
            String m_name = inputm_name.getText().toString();
            String m_org = inputm_org.getText().toString();
            String m_trade = inputm_trade.getText().toString();
            String m_mob = inputm_mob.getText().toString();
            String m_ofmail = inputm_ofmail.getText().toString();
            String m_web = inputm_web.getText().toString();
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("m_name", m_name));
            params.add(new BasicNameValuePair("m_org", m_org));
            params.add(new BasicNameValuePair("m_trade", m_trade));
            params.add(new BasicNameValuePair("m_mob", m_mob));
            params.add(new BasicNameValuePair("m_ofmail", m_ofmail));
            params.add(new BasicNameValuePair("m_web", m_web));
            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_createrecord,"POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created product
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);

                    // closing this screen
                    finish();
                } else {
                    // failed to create product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();

        }


    }
}
