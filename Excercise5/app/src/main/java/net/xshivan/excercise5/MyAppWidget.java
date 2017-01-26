package net.xshivan.excercise5;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MyAppWidget extends AppWidgetProvider {

    private final String INTENT_SOUND = "net.xshivan.excercise5.INTENT_SOUND";

    private final String INTENT_PICTURE = "net.xshivan.excercise5.INTENT_PICTURE";

    public void onUpdate(Context context, AppWidgetManager appWidgetManager,int[] appWidgetIds) {

        for (int i = 0; i < appWidgetIds.length; i++){
            int currentWidgetId = appWidgetIds[i];
            String url = "http://www.michal-cywinski.pl";

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse(url));

            PendingIntent pending = PendingIntent.getActivity(context, 0,intent, 0);
            RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.my_app_widget);

            views.setOnClickPendingIntent(R.id.btnWeb, pending);
            views.setOnClickPendingIntent(R.id.btnSound, getPendingSelfIntent(context, INTENT_SOUND));
            views.setOnClickPendingIntent(R.id.btImage, getPendingSelfIntent(context, INTENT_PICTURE));

            appWidgetManager.updateAppWidget(currentWidgetId,views);
            Toast.makeText(context, "widget added", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (INTENT_SOUND.equals(intent.getAction())) {
            MediaPlayer mp = MediaPlayer.create(context, R.raw.sound);
            mp.start();
        }

        if (INTENT_PICTURE.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_app_widget);
            views.setImageViewResource(R.id.imageView, R.drawable.nokia);
        }
    }

    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}

