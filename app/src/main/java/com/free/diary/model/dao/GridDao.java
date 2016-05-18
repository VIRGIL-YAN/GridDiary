package com.free.diary.model.dao;

import android.content.Context;

import com.free.diary.model.dao.base.DaoImpl;
import com.free.diary.model.bean.Grid;

/**
 * Created by tangqi on 16/5/18.
 */
public class GridDao extends DaoImpl<Grid> {

    public GridDao(Context context) {
        super(context, Grid.class);
    }
}
