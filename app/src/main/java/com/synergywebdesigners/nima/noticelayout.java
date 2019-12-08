package com.synergywebdesigners.nima;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Synergy on 08-02-2017.
 */

public class noticelayout extends  ArrayAdapter<String> {
    private String[] ntitle;
    private String[] ndetail;
    private String[] nday;
    private String[] npicture;
    private Activity context;
    public String img;
    public noticelayout(Activity context, String[] title, String[] picture, String[] detail, String[] day) {
        super(context, R.layout.list_view_layout, picture);
        this.context = context;
        this.ntitle = title;
        this.npicture= picture;
        this.ndetail = detail;
        this.nday = day;
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
        title.setText(ntitle[position]);
        // spanned=Html.fromHtml(tmessage[position]);
        // image.setImageBitmap(Bitmap.createScaledBitmap(bitmaps[position],100,50,true));

        //Bitmap bmp = BitmapFactory.decodeFile(new java.net.URL(bitmaps[position]).openStream());
        // image.setImageBitmap(bmp);
        //image.setImageDrawable();

        Picasso.with(getContext()).load("http://synergywebdesigners.com/synergywebdesigners.com/ashish/nimadesktop/admin/"+npicture[position]).into(image);
        // img = new String("http://synergywebdesigners.com/synergywebdesigners.com/ashish/nimadesktop/admin/"+picture[position]);
        message.setText(Html.fromHtml(ndetail[position]),TextView.BufferType.SPANNABLE);
        created.setText(nday[position]);
        return listViewItem;
    }
}
