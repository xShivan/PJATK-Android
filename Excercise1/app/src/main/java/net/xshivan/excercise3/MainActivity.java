package net.xshivan.excercise3;

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

        final ApplicationSettings applicationSettings = SharedPreferencesAccess.loadPreferences(getApplicationContext());

        if (applicationSettings.navigateToProductsOnStart)
            navigateToProductsOnStart();
        else if (applicationSettings.promptToSeeWebsite)
            AuthorWebsiteNavigator.promptToVisit(this);
    }

    private void navigateToProductsOnStart() {
        Intent intent = new Intent(getApplicationContext(), ProductListActivity.class);
        startActivity(intent);
    }
}
