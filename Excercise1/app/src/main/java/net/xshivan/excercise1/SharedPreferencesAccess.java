package net.xshivan.excercise1;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.AppLaunchChecker;
import android.support.v7.app.AppCompatActivity;

public class SharedPreferencesAccess {
    public static ApplicationSettings loadPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);

        ApplicationSettings applicationSettings = new ApplicationSettings();
        applicationSettings.isLightTheme = sharedPreferences.getBoolean("isLightTheme", true);
        applicationSettings.navigateToProductsOnStart = sharedPreferences.getBoolean("navigateToProductsOnStart", false);

        return applicationSettings;
    }

    public static void savePreferences(ApplicationSettings applicationSettings, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.putBoolean("isLightTheme", applicationSettings.isLightTheme);
        editor.putBoolean("navigateToProductsOnStart", applicationSettings.navigateToProductsOnStart);
        editor.commit();
    }

    public static void applyPreferences(ApplicationSettings applicationSettings, AppCompatActivity context) {

        // TODO: Przeróbka
        if (applicationSettings.isLightTheme)
            context.getApplicationContext().setTheme(android.R.style.Theme_Light);
        else
            context.getApplicationContext().setTheme(android.R.style.Theme_Black);
    }
}
