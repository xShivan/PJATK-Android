package net.xshivan.ex2receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

public class NotificationReceiver extends BroadcastReceiver {
    private Context applicationContext;

    public NotificationReceiver(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("message");
        displayNotification(message);
    }

    private void displayNotification(String text) {
        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(applicationContext);
        nBuilder.setContentTitle("xShivan broadcaster message");
        nBuilder.setContentText(text);
        nBuilder.setSmallIcon(R.drawable.icon);

        Intent broadcasterIntent = applicationContext.getPackageManager().getLaunchIntentForPackage("net.xshivan.ex2broadcaster");
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(applicationContext);
        stackBuilder.addNextIntent(broadcasterIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        nBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) applicationContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, nBuilder.build());
    }
}
