package com.free.diary.ui.activity;

import android.view.View;

import com.free.diary.R;
import com.free.diary.support.app.SubjectManager;

/**
 * Created by tangqi on 16/5/19.
 */
public class MainActivity extends BaseActivity {

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void beforeInitView() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 初始化问题库
                SubjectManager subjectManager = new SubjectManager(MainActivity.this);
                subjectManager.init();
            }
        }).start();

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
