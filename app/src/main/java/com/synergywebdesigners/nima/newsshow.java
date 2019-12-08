package com.synergywebdesigners.nima;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;


public class newsshow  extends  ArrayAdapter<String> {
    private String[] ids;
    private String[] ttitle;
    private String[] tmessage;
    private String[] tcreated;
    private String[] picture;
    private Bitmap[] bitmaps;
    private Activity context;
    public String img;
    public newsshow(Activity context, String[] ids, String[] title, String[] picture,Bitmap[] bitmaps, String[] message, String[] created) {
        super(context, R.layout.list_view_layout, picture);
        this.context = context;
        this.ids = ids;
        this.ttitle = title;
        this.picture= picture;
        this.bitmaps= bitmaps;
        this.tmessage = message;
        this.tcreated = created;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_view_layout, null, true);
        /// TextView id = (TextView) listViewItem.findViewById(R.id.id);
        TextView title = (TextView) listViewItem.findViewById(R.id.title);
        ImageView image = (ImageView) listViewItem.findViewById(R.id.imageDownloaded);
        TextView message = (TextView) listViewItem.findViewById(R.id.message);
        TextView created = (TextView) listViewItem.findViewById(R.id.created);
        //id.setText(ids[position]+".");
        title.setText(ttitle[position]);
        // spanned=Html.fromHtml(tmessage[position]);
      // image.setImageBitmap(Bitmap.createScaledBitmap(bitmaps[position],100,50,true));

        //Bitmap bmp = BitmapFactory.decodeFile(new java.net.URL(bitmaps[position]).openStream());
       // image.setImageBitmap(bmp);
        //image.setImageDrawable();

        Picasso.with(getContext()).load("http://synergywebdesigners.com/synergywebdesigners.com/ashish/nimadesktop/admin/"+picture[position]).into(image);
      // img = new String("http://synergywebdesigners.com/synergywebdesigners.com/ashish/nimadesktop/admin/"+picture[position]);
        message.setText(Html.fromHtml(tmessage[position]),TextView.BufferType.SPANNABLE);
        created.setText(tcreated[position]);
        return listViewItem;
    }

}



