package com.free.diary.model.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 日记格子
 * Created by tangqi on 16/5/18.
 */
@DatabaseTable(tableName = "grid")
public class Grid {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "subject")
    private String subject;

    @DatabaseField(columnName = "content")
    private String content;

    @DatabaseField(columnName = "reserve")
    private String reserve;

    @DatabaseField(canBeNull = false, foreign = true)
    private Diary diary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    @Override
    public String toString() {
        return "Grid{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", reserve='" + reserve + '\'' +
                '}';
    }

    public Diary getDiary() {
        return diary;
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
    }
}
