package net.xshivan.excercise1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class SettingsActivity extends AppCompatActivity {

    public void handleBtnSaveClick(View view) {
        ApplicationSettings applicationSettings = new ApplicationSettings();
        applicationSettings.navigateToProductsOnStart = ((CheckBox)findViewById(R.id.checkBoxGoToProductListOnStart)).isChecked();
        Boolean isLightTheme = ((RadioButton)findViewById(R.id.radioButtonLight)).isChecked();
        applicationSettings.isLightTheme = isLightTheme;
        SharedPreferencesAccess.savePreferences(applicationSettings, getApplicationContext());
        SharedPreferencesAccess.applyPreferences(applicationSettings, this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ApplicationSettings applicationSettings = SharedPreferencesAccess.loadPreferences(getApplicationContext());

        CheckBox checkboxNavigateToProductList = (CheckBox)findViewById(R.id.checkBoxGoToProductListOnStart);
        checkboxNavigateToProductList.setChecked(applicationSettings.navigateToProductsOnStart);

        ((RadioButton)findViewById(R.id.radioButtonLight)).setChecked(applicationSettings.isLightTheme);
        ((RadioButton)findViewById(R.id.radioButtonBlack)).setChecked(!applicationSettings.isLightTheme);
    }
}
