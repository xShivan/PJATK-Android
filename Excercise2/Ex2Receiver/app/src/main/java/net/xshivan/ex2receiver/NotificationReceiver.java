package net.xshivan.ex2receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

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

        NotificationManager mNotificationManager = (NotificationManager) applicationContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, nBuilder.build());
    }
}
