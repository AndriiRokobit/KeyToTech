package com.test.keytotech;



import android.app.Application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import com.test.keytotech.di.app.AppComponent;
import com.test.keytotech.di.app.AppContextModule;
import com.test.keytotech.di.app.DaggerAppComponent;
import com.test.keytotech.di.start.StartComponent;


public class AndroidApplication extends Application {

    @NonNull
    private static AppComponent appComponent;

    private static StartComponent startComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        initializeDI();
    }



    @SuppressWarnings("deprecation")
    private void initializeDI() {
        appComponent = DaggerAppComponent.builder()
                .appContextModule(new AppContextModule(this))
                .build();
    }

    @NonNull
    public static AppComponent applicationComponent() {
        return appComponent;
    }

    @NonNull
    public static StartComponent startComponent() {
        if (startComponent == null) {
            startComponent = appComponent.plusStartComponent();
        }
        return startComponent;
    }
}
