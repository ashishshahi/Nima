package com.synergywebdesigners.nima;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Ashish Shahi on 13-04-2017.
 */

public class FcmMessagingService extends FirebaseMessagingService {
    long[] pattern = {500,500,500,500,500,500,500,500,500};
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String title = remoteMessage.getNotification().getTitle();
        String message = remoteMessage.getNotification().getBody();
        String click_action = remoteMessage.getNotification().getClickAction();
        Intent intent = new Intent(/*click_action*/this,NotificationDestination.class);
        intent.putExtra("reciver",title);
       // startService(intent);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_action_title)
                .setContentTitle(title)
                .setContentText(message)
                .setColor(getResources().getColor(R.color.colorPrimary))
                .setSound(defaultSoundUri)
                .setLights(Color.MAGENTA, 500, 500)
                .setVibrate(pattern)
                .setPriority(Notification.PRIORITY_LOW)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .setAutoCancel(true);
        notificationBuilder.setStyle(new NotificationCompat.InboxStyle());
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());


    }



}
