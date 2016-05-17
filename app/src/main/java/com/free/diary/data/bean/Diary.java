package com.free.diary.data.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by tangqi on 16/5/17.
 */
@DatabaseTable(tableName = "diary")
public class Diary {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "date")
    private long date;

    @DatabaseField(columnName = "weather")
    private String weather;

    @DatabaseField(columnName = "moood")
    private String mood;

    @DatabaseField(columnName = "title")
    private String title;

    @DatabaseField(columnName = "grid")
    private int grid; // grid number

    // TODO 内容是多个
    @DatabaseField(columnName = "content")
    private String content;


}
