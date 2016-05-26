package com.free.diary.ui.activity;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.free.diary.R;
import com.free.diary.model.bean.Diary;
import com.free.diary.support.config.KeyConfig;
import com.free.diary.support.util.DateUtils;
import com.free.diary.ui.adapter.DiaryReadAdapter;

import java.util.Date;

/**
 * 阅读模式
 * Created by tangqi on 16/5/24.
 */
public class DiaryReadActivity extends BaseActivity {
    private Date mDate;
    private Diary mDiary;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_diary_read);
    }

    @Override
    public void beforeInitView() {
        mDate = (Date) getIntent().getSerializableExtra(KeyConfig.DATE);
        mDiary = (Diary) getIntent().getSerializableExtra(KeyConfig.DIARY);
    }

    @Override
    public void initView() {
        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        String diaryDateStr = DateUtils.formatDateZh(mDate);
        tvTitle.setText(diaryDateStr);

        ListView listView = (ListView) findViewById(R.id.lv_grid_read);
        DiaryReadAdapter adapter = new DiaryReadAdapter(this);
        adapter.setItems(mDiary.getGrids());
        listView.setAdapter(adapter);
    }

    @Override
    public void afterInitView() {

    }

    @Override
    public void onViewClick(View v) {

    }
}
