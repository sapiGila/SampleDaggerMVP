package com.project.redrocketz.depedency.module;

import android.app.Application;
import android.content.Context;

import com.project.redrocketz.model.NotesModel;
import com.project.redrocketz.model.NotesRepository;
import com.project.redrocketz.utils.CalculatorUtil;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dell on 7/15/2017.
 */

@Module
public class CalculatorModule {

    private Application application;

    public CalculatorModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return application;
    }

    @Named("ApplicationContext")
    @Provides
    @Singleton
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    CalculatorUtil providesCalculatorUtil() {
        return new CalculatorUtil();
    }

    @Provides
    @Singleton
    NotesModel providesNotesModel() {
        return new NotesRepository();
    }
}