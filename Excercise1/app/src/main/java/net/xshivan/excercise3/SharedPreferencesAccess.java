package net.xshivan.excercise3;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesAccess {
    public static ApplicationSettings loadPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);

        ApplicationSettings applicationSettings = new ApplicationSettings();
        applicationSettings.promptToSeeWebsite = sharedPreferences.getBoolean("promptToSeeWebsite", true);
        applicationSettings.navigateToProductsOnStart = sharedPreferences.getBoolean("navigateToProductsOnStart", false);

        return applicationSettings;
    }

    public static void savePreferences(ApplicationSettings applicationSettings, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.putBoolean("promptToSeeWebsite", applicationSettings.promptToSeeWebsite);
        editor.putBoolean("navigateToProductsOnStart", applicationSettings.navigateToProductsOnStart);
        editor.commit();
    }
}
