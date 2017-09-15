package com.project.redrocketz.realm;

import com.project.redrocketz.object.Notes;

import io.realm.annotations.RealmModule;

/**
 * Created by Dell on 9/14/2017.
 */

@RealmModule(classes = {Notes.class,})
public class DaggerAppRealmModule {}
