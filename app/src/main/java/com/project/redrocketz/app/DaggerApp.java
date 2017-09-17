package com.project.redrocketz.app;

import android.app.Application;

import com.project.redrocketz.configurator.injector.Injector;
import com.project.redrocketz.configurator.realm.RealmConfigurator;

/**
 * Created by Dell on 9/13/2017.
 */

public class DaggerApp extends Application {

    private Injector injector;
    private RealmConfigurator realmConfigurator;

    @Override
    public void onCreate() {
        super.onCreate();
        realmConfigurator = new RealmConfigurator(this);
        realmConfigurator.init();
        injector = new Injector();
        injector.setAppComponent(this);
    }

    public Injector getInjector() {
        return injector;
    }
}
