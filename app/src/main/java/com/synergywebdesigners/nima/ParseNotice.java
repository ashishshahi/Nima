package com.synergywebdesigners.nima;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Synergy on 08-02-2017.
 */

public class ParseNotice {
    public static String[] ntitle;
    public static String[] ndetail;
    public static String[] nday;
    public static String[] npicture;
    public static final String JSON_ARRAY_N = "result";
    public static final String KEY_TITLE_N = "title";
    public static final String KEY_NOTICE = "detail";
    public static final String KEY_DAY = "day";
    public static final String KEY_PICTURE ="picture";
    private JSONArray users = null;

    private String json;

    public ParseNotice(String json){
        this.json = json;
        try {
            JSONObject jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY_N);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    protected void parseNotice(){
        JSONObject jsonObject=null;

        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY_N);
            ntitle = new String[users.length()];
            npicture = new String[users.length()];
            ndetail = new String[users.length()];
            nday = new String[users.length()];
            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                ntitle[i] = jo.getString(KEY_TITLE_N);
                npicture[i] =jo.getString(KEY_PICTURE);
                ndetail[i] = jo.getString(KEY_NOTICE);
                nday[i] = jo.getString(KEY_DAY);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
