package com.synergywebdesigners.nima;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by PC on 16-Dec-16.
 */

public class ParseJSON {
    public static String[] ids;
    public static String[] ttitle;
    public static String[] tmessage;
    public static String[] tcreated;
    public static String[] picture;
    public static Bitmap[] bitmaps;
    public static final String JSON_ARRAY = "result";
    public static final String KEY_ID = "id";
    public static final String KEY_Title = "title";
    public static final String KEY_Message = "message";
    public static final String KEY_Created = "created";
    public static final String KEY_IMAGE ="picture";
    private JSONArray users = null;

    private String json;

    public ParseJSON(String json){
        this.json = json;
        try {
            JSONObject jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private Bitmap getImage(JSONObject jo){
        URL url = null;
        Bitmap image = null;
        try {
            url = new URL(jo.getString(KEY_IMAGE));
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return image;
    }
    protected void parseJSON(){
        JSONObject jsonObject=null;

        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);
            ids = new String[users.length()];
            ttitle = new String[users.length()];
            bitmaps = new Bitmap[users.length()];
            picture = new String[users.length()];
            tmessage = new String[users.length()];
            tcreated = new String[users.length()];
            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                ids[i] = jo.getString(KEY_ID);
                ttitle[i] = jo.getString(KEY_Title);
               // picture[i] = users.getJSONObject(i).getString(KEY_IMAGE);
                picture[i] =jo.getString(KEY_IMAGE);
                bitmaps[i]= getImage(jo);
                tmessage[i] = jo.getString(KEY_Message);
                tcreated[i] = jo.getString(KEY_Created);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
