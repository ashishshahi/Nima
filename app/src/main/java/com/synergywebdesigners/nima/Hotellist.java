package com.synergywebdesigners.nima;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Hotellist extends AppCompatActivity {
  private ListView lv;
  //EditText inputSearch;
  //ArrayAdapter<String> adapter;
  CustomList adapter;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_hotellist);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setLogo(R.drawable.img);
    getSupportActionBar().setDisplayUseLogoEnabled(true);
    lv = (ListView) findViewById(R.id.list_view);
    //inputSearch = (EditText) findViewById(R.id.inputSearch);
    // getJSON();
    showResources();
  }
  String products[] = {"Atlantis","Accor","Aman Resorts","Burj Al Arab","Best Western","Carlsons","Dusit Thani Group","FRHI Hotels And Resorts","Hilton","Hyatt","Intercontinental Hotels Group","Jumeirah",
          "Kempinski","Langham Hospitality Group","Lotte Hotels & Resorts","Mandarin Oriental Hotel Group","Melia Hotels International", "N H Group","Starwood","Shangri-La Hotels and Resorts",
          "Taj","The Oberoi","Wyndham Hotels"};
    Integer[] imgid={
            R.drawable.atlantis, R.drawable.accor_hotel,R.drawable.aman_resorts_hotel,R.drawable.burj_al_arab, R.drawable.best_western_hotel, R.drawable.carlson_hotels, R.drawable.dusit_thani_hotel,R.drawable.frhi_hotel,R.drawable.hilton_hotel, R.drawable.hyatt_hotel,R.drawable.intercontinental_hotel,R.drawable.jumeirah_hotel,
            R.drawable.kempinski_hotel, R.drawable.lagham_hotel,R.drawable.lotte_hotel,R.drawable.mandarin_oriental_hotel, R.drawable.melia_hotel, R.drawable.n_h_group_hotel, R.drawable.starwood_hotel, R.drawable.shangri_la_hotel, R.drawable.taj_hotel, R.drawable.oberoi_hotel, R.drawable.wyndham_hotel
    };
    private void showResources(){
    //adapter=new ArrayAdapter<String>(this,R.layout.layout_hotel_list,R.id.hotel_name,products);
        adapter = new  CustomList(Hotellist.this,  products, imgid);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()

                              {
                                public void onItemClick (AdapterView < ? > parent, View view, int position, long id) {
                                  Intent intent = new Intent(Hotellist.this, Hotel.class);
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
                                  Toast.makeText(Hotellist.this, "You have Selected : " + " " + o, Toast.LENGTH_SHORT).show();
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
                                                   Hotellist.this.adapter.getFilter().filter(cs);
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
