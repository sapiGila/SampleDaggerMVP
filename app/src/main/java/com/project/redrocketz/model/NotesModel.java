package com.project.redrocketz.model;

import com.project.redrocketz.object.Notes;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Dell on 9/14/2017.
 */

public interface NotesModel {
    void deleteNotes(Realm realm);
    RealmResults<Notes> getNotes(Realm realm);
    void insertNotes(Realm realm, List<Notes> notes);
}
