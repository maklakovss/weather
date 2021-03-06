package com.mss.weather;

import android.app.Application;
import android.content.Context;

import com.mss.weather.di.ApplicationComponent;
import com.mss.weather.di.ApplicationModule;
import com.mss.weather.di.DaggerApplicationComponent;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    private static ApplicationComponent applicationComponent;
    private static MyApplication myApplication;

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static MyApplication getInstance() {
        return myApplication;
    }

    public static Context getContext() {
        return myApplication.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        myApplication = this;

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        initComponent();
    }

    private void initComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule())
                .build();
    }
}
