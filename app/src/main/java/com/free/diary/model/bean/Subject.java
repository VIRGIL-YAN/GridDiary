package com.free.diary.model.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 主题库
 * Created by tangqi on 16/5/18.
 */
@DatabaseTable(tableName = "subject")
public class Subject {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "type")
    private String type;

    @DatabaseField(columnName = "question")
    private String question;

    @DatabaseField(columnName = "reserve")
    private String reserve;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }
}
