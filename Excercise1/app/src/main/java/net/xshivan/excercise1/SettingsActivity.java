package net.xshivan.excercise1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

public class SettingsActivity extends AppCompatActivity {

    public void handleBtnSaveClick(View view) {
        ApplicationSettings applicationSettings = new ApplicationSettings();
        applicationSettings.navigateToProductsOnStart = ((CheckBox)findViewById(R.id.checkBoxGoToProductListOnStart)).isChecked();
        applicationSettings.promptToSeeWebsite = ((CheckBox)findViewById(R.id.checkBoxPromptToSeeWebsite)).isChecked();
        SharedPreferencesAccess.savePreferences(applicationSettings, getApplicationContext());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ApplicationSettings applicationSettings = SharedPreferencesAccess.loadPreferences(getApplicationContext());

        ((CheckBox)findViewById(R.id.checkBoxGoToProductListOnStart)).setChecked(applicationSettings.navigateToProductsOnStart);
        ((CheckBox)findViewById(R.id.checkBoxPromptToSeeWebsite)).setChecked(applicationSettings.promptToSeeWebsite);
    }
}
