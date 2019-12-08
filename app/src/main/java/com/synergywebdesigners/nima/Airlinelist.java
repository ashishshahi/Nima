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

public class Airlinelist extends AppCompatActivity {
    private ListView lv;
   // EditText inputSearch;
   // ArrayAdapter<String> adapter;
   CustomList adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airlinelist);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.img);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        lv = (ListView) findViewById(R.id.list_view);
        //inputSearch = (EditText) findViewById(R.id.inputSearch);
        // getJSON();
        showResources();
    }
    String  products[] = {"Air Canada","Air Asia","Air India","Air Arabia","Asiana Airlines","Air Astana",
            "Air China","Aeroflot","Air France","Air Austral","Air Mauritus","Air Manas","Air Seychelles",
            "Ariana Afghan Airlines","Bangkok Airways","Bhutan Airlines","British Airways","Biman Bangladesh Airlines","Brussels Airlines",
            "Buddha Air","Cathay Pacific","China Air","China Eastern","China Southern","Druk Air",
            "Egypt Air","Easy Jet","Ethiopian Air","Etihad Airways","EL AL Israel Airlines","Emirates",
            "Fly Dubai","Finnair","GoAir","Gulf Air","Hongkong Dragon","IndiGo Airlines","Iraqi Airways",
            "Iran Air","Japan Airlines","Jet Airways","Kam Air","Kenya Airways","Klm Royal Dutch Airlines","Korean Air", "Kuwait Airways",
            "Lufthansa German Airlines","Mahan Air","Maldivian","Malaysia Airlines","Myanmar Airways International","Nepal Airlines","Nippon Airlines","Oman Air",
            "PT Garuda Indonesia","Qatar Airways","Regent Airways","Safi Airways","Saudi Arabian Airlines","Shandong Airlines","Silk Air","Singapore Airlines",
            "Scoot Air","Sri Lankan Airlines","Swiss International Airlines","Skylanes","SpiceJet","Tata Airlines","Tajik Air","Tiger Airways","Thai Airways","Thai Smile",
            "Turkish Airlines","United Airlines","Uzbekistan Airways","Virgin Atlantic Airways","Vistara Air"};
    Integer[] imgid={
            R.drawable.air_canada_airlines,R.drawable.air_asia_airlines,R.drawable.air_india_airlines,R.drawable.air_arabia_airlines,R.drawable.asiana_airlines, R.drawable.air_astana_airlines,
            R.drawable.air_china_airlines, R.drawable.aeroflot_airlines, R.drawable.air_france_airlines, R.drawable.air_austral_airlines,R.drawable.air_mauritius_airlines, R.drawable.airmanas_airlines, R.drawable.air_selchelles_airlines,
            R.drawable.air_afganistan_airlines, R.drawable.bangkok_airlines, R.drawable.bhutan_airlines, R.drawable.british_airlines,R.drawable.biman_bangladesh_airlines,R.drawable.brussels_airlines,
            R.drawable.buddha_airlines, R.drawable.cathay_pacific_airlines, R.drawable.china_airlines,R.drawable.china_eastern_airlines,R.drawable.china_southern_airlines,R.drawable.druk_airlines,
            R.drawable.egypt_airlines, R.drawable.easyjet_airlines, R.drawable.ethiopian_airlines, R.drawable.etihad_airlines,R.drawable.el_al_israel_airlines,R.drawable.emirates_airlines,
            R.drawable.fly_dubai_airlines, R.drawable.finnair_airlines,R.drawable.go_air_airlines,R.drawable.gulf_airlines,R.drawable.dragon_airlines,R.drawable.indigo_airlines,R.drawable.iraqi_airlines,
            R.drawable.iran_airlines,R.drawable.japan_airlines,R.drawable.jet_airways_airlines,R.drawable.kam_airlines,R.drawable.kenya_airlines,R.drawable.klm_airlines,R.drawable.korean_airlines,R.drawable.kuwait_airlines,
            R.drawable.lufthansa_airlines,R.drawable.mahan_airlines,R.drawable.maldivian_airlines,R.drawable.malaysia_airlines,R.drawable.mayanma_airlines,R.drawable.nepal_airlines,R.drawable.nippon_airlines,R.drawable.oman_airlines,
            R.drawable.garuda_indonesia_airlines,R.drawable.qatar_airlines,R.drawable.regent_airlines,R.drawable.safi_airlines,R.drawable.saudi_arabian_airlines,R.drawable.shandong_airlines,R.drawable.silk_airlines,R.drawable.singapore_airlines,
            R.drawable.scoot_airlines,R.drawable.srilanka_airlines,R.drawable.swiss_airlines,R.drawable.skylane_airlines,R.drawable.spicejet_airlines,R.drawable.tata_airlines,R.drawable.tajik_airlines,R.drawable.tiger_airways_airlines,R.drawable.thai_airlines,R.drawable.thai_smile_airlines,
            R.drawable.turkish_airlines,R.drawable.united_airlines,R.drawable.uzebikistan_airlines,R.drawable.virgin_atlantic_airlines,R.drawable.vistara_airlines
    };
    private void showResources(){
       // adapter=new ArrayAdapter<String>(this,R.layout.list_country,R.id.resource_name,products);
        adapter = new  CustomList(Airlinelist.this,  products, imgid);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()

                                  {
                                      public void onItemClick (AdapterView < ? > parent, View view, int position, long id) {
              Intent intent = new Intent(Airlinelist.this, Airline.class);
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
              Toast.makeText(Airlinelist.this, "You have Selected : " + " " + o, Toast.LENGTH_SHORT).show();
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
               Airlinelist.this.adapter.getFilter().filter(cs);
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
}
