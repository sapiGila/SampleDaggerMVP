package com.project.redrocketz.depedency.module;

import com.project.redrocketz.configurator.realm.RealmConfigurator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dell on 9/14/2017.
 */

@Module
public class RealmModule {

    @Provides
    @Singleton
    public RealmConfigurator providesRealmConfigurator() {
        return new RealmConfigurator();
    }
}