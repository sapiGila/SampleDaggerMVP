package com.project.redrocketz.contract;

/**
 * Created by Dell on 9/14/2017.
 */

public interface NotesContract {
    interface Presenter {
        void setNotes();
        void getNotes();
        void deleteNotes();
    }

    interface View {
        void showHistory(String value);
        void showError(String error);
    }
}
