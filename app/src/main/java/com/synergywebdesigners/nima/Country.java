package com.synergywebdesigners.nima;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Country extends AppCompatActivity {
    //List view
     private ListView lv;
    // EditText inputSearch;
     //ArrayAdapter<String> adapter;
    private String JSON_STRING;
    Intent i;
    CustomList adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_country);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setLogo(R.drawable.img);
    getSupportActionBar().setDisplayUseLogoEnabled(true);
    lv = (ListView) findViewById(R.id.list_view);
   // inputSearch = (EditText) findViewById(R.id.inputSearch);
    // getJSON();
    showResources();
  }

  String products[] = {"Argentina", "Australia", "Austria", "Azerbaijan","Belgium", "Bhutan", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei Darussalam", "Bulgaria",
          "Cambodia","Canada","China","Croatia","Cyprus", "Czech Republic","Denmark",
          "Estonia", "Ethiopia","Fiji", "Finland", "France", "French Polynesia","Georgia", "Germany","Greece","Hong Kong", "Hungary",
          "Iceland", "India", "Indonesia", "Ireland", "Israel", "Italy","Japan", "Jordan",
          "Kazakhstan", "Kenya","Kyrgyzstan", "Lithuania", "Luxembourg",
          "Macau","Malaysia", "Maldives","Malta","Mauritius","Mexico","Monaco",  "Montserrat", "Morocco","Myanmar", "Nepal", "Netherlands","New Zealand","Norway",
          "Oman","Panama","Peru", "Philippines","Poland", "Portugal", "Reunion Island", "Romania", "Russian Federation", "Rwanda",
          "Seychelles","Singapore", "Slovakia (Slovak Republic)", "Slovenia","South Africa","Spain", "Sri Lanka","Sweden", "Switzerland","South Korea",
          "Taiwan(Republic of China)", "Tajikistan","Thailand","Tunisia", "Turkey","Ukraine", "UAE", "United Kingdom", "United States","Uzbekistan","Vietnam"};
    Integer[] imgid={
            R.drawable.argentina_flag,R.drawable.australia_flag,R.drawable.austria_flag,R.drawable.azerbaijan_flag,R.drawable.belgium_flag, R.drawable.bhutan_flag, R.drawable.bosnia_and_herzegovina_flag, R.drawable.botswana_flag, R.drawable.brazil_flag, R.drawable.brunei_darussalam_flag, R.drawable.bulgaria_flag,
            R.drawable.cambodia_flag, R.drawable.canada_flag, R.drawable.china_flag, R.drawable.croatia_flag,R.drawable.cyprus_flag, R.drawable.czech_republic_flag, R.drawable.denmark_flag,
            R.drawable.estonia_flag, R.drawable.ethiopia_flag, R.drawable.fiji_flag, R.drawable.finland_flag,R.drawable.france_flag,R.drawable.french_polynesia_flag, R.drawable.georgia_flag, R.drawable.germany_flag, R.drawable.greece_flag, R.drawable.hong_kong_flag, R.drawable.hungary_flag,
            R.drawable.iceland_flag, R.drawable.india_flag, R.drawable.indonesia_flag,R.drawable.ireland_flag,R.drawable.israel_flag,R.drawable.italy_flag, R.drawable.japan_flag, R.drawable.jordan_flag,
            R.drawable.kazakhstan_flag, R.drawable.kenya_flag, R.drawable.kyrgyzstan_flag, R.drawable.lithuania_flag,R.drawable.luxembourg_flag,
            R.drawable.macau_flag, R.drawable.malaysia_flag,R.drawable.maldives_flag,R.drawable.malta_flag,R.drawable.mauritius_flag,R.drawable.mexico_flag,R.drawable.monaco_flag, R.drawable.montserrat_flag, R.drawable.morocco_flag, R.drawable.myanmar_flag, R.drawable.nepal_flag, R.drawable.netherlands_flag, R.drawable.new_zealand_flag, R.drawable.norway_flag,
            R.drawable.oman_flag,R.drawable.panama_flag,R.drawable.peru_flag,R.drawable.philippines_flag,R.drawable.poland_flag,R.drawable.portugal_flag,R.drawable.reunion_island_flag,R.drawable.romania_flag, R.drawable.russian_federation_flag, R.drawable.rwanda_flag,
            R.drawable.seychelles_flag,R.drawable.singapore_flag,R.drawable.slovakia_flag,R.drawable.slovenia_flag,R.drawable.south_africa_flag,R.drawable.spain_flag,R.drawable.sri_lanka_flag,R.drawable.sweden_flag, R.drawable.switzerland_flag, R.drawable.south_korea_flag,
            R.drawable.taiwan_flag,R.drawable.tajikistan_flag,R.drawable.thailand_flag,R.drawable.tunisia_flag,R.drawable.turkey_flag,R.drawable.ukraine_flag,R.drawable.uae_flag,R.drawable.united_kingdom_flag, R.drawable.united_states_flag, R.drawable.uzbekistan_flag, R.drawable.vietnam_flag
    };
  private void showResources(){
    //adapter=new ArrayAdapter<String>(this,R.layout.list_country,R.id.resource_name,products);
      adapter = new CustomList(Country.this,products,imgid);
    lv.setOnItemClickListener(new AdapterView.OnItemClickListener()

                              {
                                public void onItemClick (AdapterView < ? > parent, View view,int position, long id) {
                                  Intent intent = new Intent(Country.this, Subcountry.class);
                                  //  intent.putExtra("value", (String) lv.getItemAtPosition(position));
                                  startActivity(intent);

                                  // lv.showContextMenuForChild(view);

                                  Object value = lv.getItemAtPosition(position);
                                  String o = (String) value.toString();
                                  SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                                  SharedPreferences.Editor editor = sharedPreferences.edit();
                                  //editor.putString("mob", mob);
                                  editor.putString("value", o);
                                  editor.commit();
                                  Toast.makeText(Country.this, "You have Selected : " + " " + o, Toast.LENGTH_LONG).show();
                                }
                              }

    );

    // registerForContextMenu(lv);

    lv.setAdapter(adapter);

   /* inputSearch.addTextChangedListener(new

                                               TextWatcher() {
                                                 @Override
                                                 public void onTextChanged (CharSequence cs,int arg1, int arg2, int arg3){
                                                   // When user changed the Text
                                                   Country.this.adapter.getFilter().filter(cs);
                                                 }

                                                 @Override
                                                 public void beforeTextChanged (CharSequence arg0,int arg1, int arg2,
                                                                                int arg3){
                                                   // TODO Auto-generated method stub

                                                 }

                                                 @Override
                                                 public void afterTextChanged (Editable arg0){
                                                   // TODO Auto-generated method stub
                                                 }
                                               }

    );*/
  }
  ///showing message
  /*  private void showResources(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_RESULT);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String country = jo.getString(Config.TAG_COUNTRY);
                HashMap<String,String> resource = new HashMap<>();
                resource.put(Config.TAG_COUNTRY,country);
                list.add(resource);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

      /* ListAdapter adapter = new SimpleAdapter(
                Country.this, list, R.layout.list_country,
                new String[]{Config.TAG_COUNTRY}, new int[]{R.id.resource_name});*/

      /*  adapter = new ArrayAdapter<String>(this, R.layout.list_country, R.id.resource_name, products);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Intent intent = new Intent(Country.this, TourismBoard.class);
               // intent.putExtra("value", (String) lv.getItemAtPosition(position));
                lv.showContextMenuForChild(view);
               // startActivity(intent);
                Object value = lv.getItemAtPosition(position);
                String o =  (String)value.toString();
                SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //editor.putString("mob", mob);
                editor.putString("value",o);
                editor.commit();
                  Toast.makeText(Country.this, "You have chosen : " + " " + o, Toast.LENGTH_LONG).show();
            }
        });*/
       /* lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Country.this, TourismBoard.class);
                intent.putExtra("value", (String) lv.getItemAtPosition(position));
                startActivity(intent);
                return true;
            }
        });*/
      /* registerForContextMenu(lv);
        lv.setAdapter(adapter);

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                Country.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });


    }
    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // Toast.makeText(ChatActivity.this,"Your Message Fetching..",Toast.LENGTH_LONG).show();
                loading = ProgressDialog.show(Country.this, "Showing Message", "Please Wait...", false, false);
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
                String s = rh.sendGetRequest(Config.URL_GET_ALL_RESOURCES);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();

    }*/
  /*  @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Choose Your Resources");
        menu.setHeaderIcon(R.drawable.ic_menu_resources);
        menu.add(0, v.getId(), 0, "TOURISM BOARD");//groupId, itemId, order, title
        menu.add(0, v.getId(), 0, "MICE");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()=="TOURISM BOARD"){
            Toast.makeText(getApplicationContext(),"Wlcome To NIMA Tourism Board",Toast.LENGTH_SHORT).show();
            Intent tourism = new Intent(getApplicationContext(),TourismBoard.class);
            startActivity(tourism);

        }
        else if(item.getTitle()=="MICE"){
            Toast.makeText(getApplicationContext(),"Mice Details",Toast.LENGTH_LONG).show();
        }else{
            return false;
        }
        return true;
    }*/

}
