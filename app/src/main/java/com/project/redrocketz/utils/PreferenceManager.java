package com.project.redrocketz.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {

    private SharedPreferences sharedPreferences;

    public PreferenceManager(Context context,
                             String preferenceName) {
        sharedPreferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
    }

    public void setHistory(String text) {
        SharedPreferences.Editor editorPreference = sharedPreferences.edit();
        editorPreference.putString("history_calculator", text);
        editorPreference.commit();
    }

    public String getHistory() {
        return sharedPreferences.getString("history_calculator", "");
    }
}