package net.xshivan.excercise1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void handleBtnProductListClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ProductListActivity.class);
        startActivity(intent);
    }

    public void handleBtnSettingsClick(View view) {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApplicationSettings applicationSettings = SharedPreferencesAccess.loadPreferences(getApplicationContext());
        SharedPreferencesAccess.applyPreferences(applicationSettings, this);

        if (applicationSettings.navigateToProductsOnStart) {
            Intent intent = new Intent(getApplicationContext(), ProductListActivity.class);
            startActivity(intent);
        }
    }
}
