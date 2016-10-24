package net.xshivan.excercise1;

import android.app.Application;
import android.content.res.Configuration;
import com.orm.SugarApp;
import com.orm.SugarContext;

import net.xshivan.excercise1.Models.Product;

public class Excercise1Application extends SugarApp {

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(getApplicationContext());
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}