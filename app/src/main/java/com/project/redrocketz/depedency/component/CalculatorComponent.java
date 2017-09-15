package com.project.redrocketz.depedency.component;

import com.project.redrocketz.depedency.module.CalculatorModule;
import com.project.redrocketz.depedency.module.PreferenceModule;
import com.project.redrocketz.depedency.module.RealmModule;
import com.project.redrocketz.view.CalculatorActivity;
import com.project.redrocketz.view.NotesActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Dell on 9/14/2017.
 */

@Singleton
@Component(modules = {CalculatorModule.class, PreferenceModule.class, RealmModule.class})
public interface CalculatorComponent {
    void injectCalculatorActivity(CalculatorActivity activity);
    void injectNotesActivity(NotesActivity activity);
}
