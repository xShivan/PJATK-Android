package net.xshivan.excercise5;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Random;

public class MyAppWidget extends AppWidgetProvider {

    private Random r = new Random();

    private final String INTENT_SOUND = "net.xshivan.excercise5.INTENT_SOUND";

    private final String INTENT_PICTURE = "net.xshivan.excercise5.INTENT_PICTURE";

    private Boolean imgActive = false;

    private Boolean imgInvert = false;

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
            views.setOnClickPendingIntent(R.id.btnImage, getPendingSelfIntent(context, INTENT_PICTURE));

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
            int randomNum = r.nextInt(2);

            if (randomNum == 0)
                views.setImageViewResource(R.id.imageView, R.drawable.nokia);
            else
                views.setImageViewResource(R.id.imageView, R.drawable.nokia_negative);

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            appWidgetManager.updateAppWidget(new ComponentName(context, MyAppWidget.class), views);
        }
    }

    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}

