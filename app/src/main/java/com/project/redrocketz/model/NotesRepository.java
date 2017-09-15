package com.project.redrocketz.model;

import com.project.redrocketz.object.Notes;

import java.util.List;

import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Dell on 9/14/2017.
 */

@Singleton
public class NotesRepository implements NotesModel {

    @Override
    public void deleteNotes(Realm realm) {
        realm.where(Notes.class).findAll().deleteAllFromRealm();
    }

    @Override
    public RealmResults<Notes> getNotes(Realm realm) {
        return realm.where(Notes.class).findAll();
    }

    @Override
    public void insertNotes(Realm realm, List<Notes> notes) {
        realm.copyToRealmOrUpdate(notes);
    }
}