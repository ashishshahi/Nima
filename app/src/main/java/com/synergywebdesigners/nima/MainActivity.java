package com.synergywebdesigners.nima;


import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.android.volley.RequestQueue;
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
import static com.synergywebdesigners.nima.news.JSON_URL;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView marque, marque1, marque2;
    private ListView listView;
    ImageView miceadd,dmcadd,hoteladd;
    // SharedPreferences pref;
    TextView textView,textview2,textview3;
    final Context context = this;
    String myJSON;
    ListView list;
    //for noticeboard
    private ListView lv;
    private String JSON_STRING;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_TITLE = "title";
    private static final String TAG_NEWS = "message";
    private static final String TAG_CREATED = "created";
    private JSONArray users = null;
    JSONArray peoples = null;
    ArrayList<HashMap<String, String>> newsslide;

    Animation mAnimation2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
       list = (ListView) this.findViewById(R.id.listView);
        listView = (ListView) findViewById(R.id.list_notice_board);
        miceadd = (ImageView) findViewById(R.id.img1);
        dmcadd = (ImageView)findViewById(R.id.img2);
        hoteladd = (ImageView) findViewById(R.id.img3);
        newsslide=new ArrayList<HashMap<String, String>>();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.nima);
        getJSON();
        getJSONadvertisment();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
               Intent fab_notification = new Intent(getApplicationContext(),Notification.class);
                startActivity(fab_notification);
            }
        });

      //Shows On Sliding News On Page
      // mAnimation2 = new TranslateAnimation(0f, 0f, 20f, -20f);
       // mAnimation2.setDuration(5000);
      //  mAnimation2.setStartOffset(5000);
      //  mAnimation2.setRepeatMode(Animation.RESTART);
        marque = (TextView) this.findViewById(R.id.sliding_title);
        // marque1 = (TextView) this.findViewById(R.id.sliding_news);
       // marque2 = (TextView) this.findViewById(R.id.sliding_date);
       // marque.setAnimation(mAnimation2);
       // marque1.setAnimation(mAnimation2);
       // marque2.setAnimation(mAnimation2);
       String url = Config.DATA_URL_NEWS;

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
//close news Marquee Page
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
    private void showJSON(String response) {
      // String title = "";
        String message = "";
       // String created = "";
        // String[] title;
        int position;

        try {

                JSONObject jsonObject = new JSONObject(response);
                JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY_NEWS);
           // title = new String[users.length()];
           // message = new String[users.length()];
           // created = new String[users.length()];

            for (int i = 0; i < result.length(); i++) {

                JSONObject collegeData = result.getJSONObject(i);
               // title = collegeData.getString(Config.KEY_TITLE);
                message = collegeData.getString(Config.KEY_NEWS);
               // created = collegeData.getString(Config.KEY_CREATED);
                marque.setText(Html.fromHtml(message));
               // marque1.setText(Html.fromHtml(message));
               // marque2.setText(created);
            }
            }catch(JSONException e){
                e.printStackTrace();
            }


        marque.setSelected(true);
       // marque1.setSelected(true);
       // marque2.setSelected(true);
    }
    //notisboard in main screen
    ///showing message
    private void showMesage() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_NOTICE);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String detail = jo.getString(Config.TAG_NOTICE);
                String day = jo.getString(Config.TAG_DAY);
                HashMap<String, String> notice = new HashMap<>();
                notice.put(Config.TAG_NOTICE, Html.fromHtml(detail).toString());
                notice.put(Config.TAG_DAY,day);
                list.add(notice);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(MainActivity.this, list, R.layout.screen_notice, new String[]{Config.TAG_NOTICE,Config.TAG_DAY}, new int[]{R.id.notice,R.id.date});

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()

                                  {
                                      public void onItemClick (AdapterView < ? > parent, View view,int position, long id) {
                                          Intent intent = new Intent(MainActivity.this, Noticeboard.class);
                                          startActivity(intent);
                                      }
                                  }

        );
        listView.setAdapter(adapter);
    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
               // Toast.makeText(MainActivity.this, "Your Notice has been Come Please Wait ...", Toast.LENGTH_SHORT).show();
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
                String s = rh.sendGetRequest(Config.URL_GET_ALL_NOTICE);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
//show Advertisment




    private void showadvertisment() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ADVERTIMENT);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String mice = jo.getString(Config.TAG_MICE);
                String dmc = jo.getString(Config.TAG_DMC);
                String hotel = jo.getString(Config.TAG_HOTEL_AD);
                Picasso.with(this).load("http://synergywebdesigners.com/synergywebdesigners.com/ashish/nimadesktop/admin/"+mice).into(miceadd);
                Picasso.with(this).load("http://synergywebdesigners.com/synergywebdesigners.com/ashish/nimadesktop/admin/"+dmc).into(dmcadd);
                Picasso.with(this).load("http://synergywebdesigners.com/synergywebdesigners.com/ashish/nimadesktop/admin/"+hotel).into(hoteladd);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getJSONadvertisment() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // Toast.makeText(MainActivity.this, "Your Notice has been Come Please Wait ...", Toast.LENGTH_SHORT).show();
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
                String s = rh.sendGetRequest(Config.URL_GET_ADVERTISMENT);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }



    //close Advertisment section
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
                        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(Config.USERNAME_SHARED_PREF, "");

                        //Saving the sharedpreferences
                        editor.clear();
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }

    }
   /* public void url(View v){
        Intent openURL = new Intent(android.content.Intent.ACTION_VIEW);
        openURL.setData(Uri.parse("http://www.synergywebdesigners.com"));
        startActivity(openURL);
    }*/
       public void news(View v){
        Intent intent = new Intent(getApplicationContext(),news.class);
        startActivity(intent);
    }
   public void url(View v) {
       Intent intent = new Intent(context, WebviewActivity.class);
       startActivity(intent);
   }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menuLogout) {
            logout();
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id == R.id.nav_notice){
            Intent nav_notice = new Intent(getApplicationContext(),Noticeboard.class);
            startActivity(nav_notice);
        }else if(id == R.id.nav_event){
            Intent nav_event = new Intent(getApplicationContext(),Event.class);
            startActivity(nav_event);
        }else if (id == R.id.nav_news) {
            Intent nav_news = new Intent(getApplicationContext(),news.class);
            startActivity(nav_news);

        } else if (id == R.id.nav_message) {
            Intent nav_message = new Intent(getApplicationContext(),ChatActivity.class);
            startActivity(nav_message);
        } else if (id == R.id.nav_personal_chat) {
            Intent nav_personal_chat = new Intent(getApplicationContext(),Memberlist.class);
            startActivity(nav_personal_chat);
        } else if (id == R.id.nav_profile) {
            Intent nav_profile = new Intent(getApplicationContext(), Profile.class);
            startActivity(nav_profile);
        } else if (id == R.id.nav_country) {
            Intent nav_country = new Intent(getApplicationContext(),Country.class);
            startActivity(nav_country);


        } else if (id == R.id.nav_Hotel) {
            Intent nav_Hotel = new Intent(getApplicationContext(),Hotellist.class);
            startActivity(nav_Hotel);

        } else if (id == R.id.nav_airline) {
            Intent nav_airline = new Intent(getApplicationContext(),Airlinelist.class);
            startActivity(nav_airline);
           /* Intent openURL = new Intent(android.content.Intent.ACTION_VIEW);
            openURL.setData(Uri.parse("https://in.linkedin.com/in/thenima"));
            startActivity(openURL);*/
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

}
