package com.free.diary.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.free.diary.R;

/**
 * 阅读模式
 * Created by tangqi on 16/5/24.
 */
public class DiaryReadActivity extends BaseActivity {

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_diary_read);
    }

    @Override
    public void beforeInitView() {
        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(R.string.app_name);
    }

    @Override
    public void initView() {

    }

    @Override
    public void afterInitView() {

    }

    @Override
    public void onViewClick(View v) {

    }
}
