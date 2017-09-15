package com.project.redrocketz.presenter;

import com.project.redrocketz.configurator.realm.RealmConfigurator;
import com.project.redrocketz.contract.NotesContract;
import com.project.redrocketz.model.NotesModel;
import com.project.redrocketz.object.Notes;
import com.project.redrocketz.utils.StringHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Dell on 9/14/2017.
 */

public class NotesPresenter implements NotesContract.Presenter {

    private final RealmConfigurator realmConfigurator;
    private final NotesModel model;
    private NotesContract.View view;
    private PublishSubject<RealmResults<Notes>> notesSubject = PublishSubject.create();


    @Inject
    public NotesPresenter(RealmConfigurator realmConfigurator,
                          NotesModel model) {
        this.realmConfigurator = realmConfigurator;
        this.model = model;
    }

    public void setView(NotesContract.View view) {
        this.view = view;
    }

    @Override
    public void setNotes() {
        Realm.getInstanceAsync(realmConfigurator.getRealmConfiguration(), new Realm.Callback() {
            @Override
            public void onSuccess(Realm realm) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        final List<Notes> notes = new ArrayList<>();
                        Number id = realm.where(Notes.class).max("id");
                        Notes note = new Notes(id);
                        notes.add(note);
                        model.insertNotes(realm, notes);
                        notesSubject.onNext(model.getNotes(realm));
                    }
                });
            }

            @Override
            public void onError(Throwable exception) {
                super.onError(exception);
                view.showError(exception.getMessage());
            }
        });

    }

    @Override
    public void getNotes() {
        notesSubject.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RealmResults<Notes>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RealmResults<Notes> notes) {
                        List<String> noteList = new ArrayList<>();
                        for (Notes note : notes) {
                            noteList.add(StringHelper.getStringBuilderToString(String.valueOf(note.getId()),
                                    ", ",
                                    note.getNote()));
                        }
                        view.showHistory(StringHelper.getStringBuilderToStringFromList(noteList, "\n"));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void deleteNotes() {
        realmConfigurator.getRealm().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteNotes(realm);
                notesSubject.onNext(model.getNotes(realm));
            }
        });
    }
}
