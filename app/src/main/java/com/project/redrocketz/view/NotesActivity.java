package com.project.redrocketz.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;

import com.project.redrocketz.R;
import com.project.redrocketz.app.DaggerApp;
import com.project.redrocketz.contract.NotesContract;
import com.project.redrocketz.presenter.NotesPresenter;
import com.project.redrocketz.utils.StringHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Dell on 9/14/2017.
 */

public class NotesActivity extends AppCompatActivity implements NotesContract.View {

    @BindView(R.id.notes)
    AppCompatTextView notes;

    @Inject
    NotesPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((DaggerApp) getApplication()).getInjector().getAppComponent().injectNotesActivity(this);
        setContentView(R.layout.activity_notes);
        ButterKnife.bind(this);
        presenter.setView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getNotes();
    }

    @OnClick(R.id.get_notes)
    public void onGetClicked() {
        presenter.setNotes();
    }

    @Override
    public void showHistory(final String value) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notes.setText(value);
            }
        });
    }

    @Override
    public void showError(String error) {
        notes.setText(StringHelper.getStringBuilderToString("error : ", error));
    }

    @OnClick(R.id.clear_notes)
    public void onClearClicked() {
        presenter.deleteNotes();
    }
}
