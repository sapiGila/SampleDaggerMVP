package com.project.redrocketz.utils;

import android.support.annotation.NonNull;

import java.util.List;

public class StringHelper {

    @NonNull
    public static String getStringBuilderToString(String... items) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : items) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    @NonNull
    public static String getStringBuilderToStringFromList(List<String> list, String divider) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = list.size() - 1;
        for (int x = 0; x < list.size(); x++) {
            stringBuilder.append(list.get(x));
            if (x != i) {
                stringBuilder.append(divider);
            }
        }
        return stringBuilder.toString();
    }
}
