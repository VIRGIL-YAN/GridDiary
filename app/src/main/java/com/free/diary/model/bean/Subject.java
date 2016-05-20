package com.free.diary.model.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 主题库
 * Created by tangqi on 16/5/18.
 */
@DatabaseTable(tableName = "subject")
public class Subject extends BaseEntity {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "type")
    private String type;

    @DatabaseField(columnName = "subject")
    private String subject;

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String question) {
        this.subject = question;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", subject='" + subject + '\'' +
                ", reserve='" + reserve + '\'' +
                '}';
    }
}
