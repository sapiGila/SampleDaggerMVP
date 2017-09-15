package com.project.redrocketz.configurator.injector;

import android.app.Application;

import com.project.redrocketz.depedency.component.CalculatorComponent;
import com.project.redrocketz.depedency.component.DaggerCalculatorComponent;
import com.project.redrocketz.depedency.module.CalculatorModule;
import com.project.redrocketz.depedency.module.PreferenceModule;
import com.project.redrocketz.depedency.module.RealmModule;

/**
 * Created by Dell on 9/14/2017.
 */

public class Injector {

    private CalculatorComponent appComponent;

    private CalculatorComponent createComponent(Application application) {
        return DaggerCalculatorComponent.builder()
                .calculatorModule(new CalculatorModule(application))
                .preferenceModule(new PreferenceModule())
                .realmModule(new RealmModule())
                .build();
    }

    public void setAppComponent(Application application) {
        if (getAppComponent() == null) {
            setAppComponent(createComponent(application));
        }
    }

    public CalculatorComponent getAppComponent() {
        return appComponent;
    }

    public void setAppComponent(CalculatorComponent appComponent) {
        this.appComponent = appComponent;
    }
}