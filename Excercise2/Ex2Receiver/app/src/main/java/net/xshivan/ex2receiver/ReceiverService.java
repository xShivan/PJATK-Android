package net.xshivan.ex2receiver;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class ReceiverService extends Service {

    private NotificationReceiver notificationReceiver;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        notificationReceiver = new NotificationReceiver(getApplicationContext());
        registerReceiver(notificationReceiver, new IntentFilter("net.xshivan.permissions.NOTIFY_BROADCAST_SENT"));

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
