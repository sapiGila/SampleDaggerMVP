package com.project.redrocketz.object;

import com.project.redrocketz.utils.StringHelper;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Dell on 9/14/2017.
 */

@RealmClass
public class Notes extends RealmObject {

    @PrimaryKey
    private Integer id;

    private String note;

    public Notes() {
    }

    public Notes(Number uid) {
        this.id = generateId(uid).intValue();
        this.note = StringHelper.getStringBuilderToString("Note ", String.valueOf(id));
    }

    public Notes(Integer id,
                 String note) {
        this.id = id;
        this.note = note;
    }

    public Number generateId(Number number) {
        long id = (number != null) ? number.intValue() + 1 : 0;
        return id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
