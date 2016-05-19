package com.free.diary.model.bean;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

/**
 * Created by tangqi on 16/5/17.
 */
@DatabaseTable(tableName = "diary")
public class Diary {

    @DatabaseField(generatedId = true)
    private int id;

    // 创建日期
    @DatabaseField(columnName = "date")
    private long date;

    @DatabaseField(columnName = "modifyDate")
    private long modifyDate;

    @DatabaseField(columnName = "weather")
    private String weather;

    @DatabaseField(columnName = "mood")
    private String mood;

    @DatabaseField(columnName = "title")
    private String title;

    // 一篇日记对应多个格子
    @ForeignCollectionField(eager = false)
    private ForeignCollection<Grid> grids;

    @DatabaseField(columnName = "reserve")
    private String reserve;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setModifyDate(long modifyDate) {
        this.modifyDate = modifyDate;
    }

    public long getModifyDate() {
        return modifyDate;
    }

    public ArrayList<Grid> getGrids() {
        ArrayList<Grid> list = new ArrayList<>();
        if (grids != null) {
            list.addAll(grids);
            return list;
        }

        return list;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    @Override
    public String toString() {
        return "Diary{" +
                "id=" + id +
                ", date=" + date +
                ", modifyDate=" + modifyDate +
                ", weather='" + weather + '\'' +
                ", mood='" + mood + '\'' +
                ", title='" + title + '\'' +
                ", reserve='" + reserve + '\'' +
                ", grids=" + getGrids() +
                '}';

    }

}
