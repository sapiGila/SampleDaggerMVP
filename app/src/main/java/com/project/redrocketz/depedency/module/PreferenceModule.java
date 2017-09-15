package com.project.redrocketz.depedency.module;

import android.content.Context;

import com.project.redrocketz.utils.PreferenceManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

/**
 * Created by Dell on 9/14/2017.
 */

@Module
public class PreferenceModule {

    @Named("PreferenceInfo")
    @Provides
    @Singleton
    String providePreferenceName() {
        return "CalculatorPreference";
    }

    @Provides
    @Singleton
    PreferenceManager providePreferenceManager(@Named("ApplicationContext") Context context,
                                               @Named("PreferenceInfo") String preferenceName) {
        return new PreferenceManager(context, preferenceName);
    }
}
