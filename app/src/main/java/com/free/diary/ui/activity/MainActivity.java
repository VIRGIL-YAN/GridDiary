package com.free.diary.ui.activity;

import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.free.diary.R;
import com.free.diary.model.bean.Subject;
import com.free.diary.model.dao.base.SubjectDao;
import com.free.diary.support.app.SubjectManager;
import com.free.diary.ui.adapter.SubjectGridAdpater;

import java.util.List;

/**
 * Created by tangqi on 16/5/19.
 */
public class MainActivity extends BaseActivity {

    private TextView mTvMainDate;
    private GridView mGridMainSubject;

    private SubjectGridAdpater mGridAdpater;

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
        mTvMainDate = (TextView) findViewById(R.id.tv_main_date);
        mTvMainDate.setText("2016年05月20日");

        mGridAdpater = new SubjectGridAdpater(this);
        mGridMainSubject = (GridView) findViewById(R.id.grid_main_subject);
        mGridMainSubject.setAdapter(mGridAdpater);
    }

    @Override
    public void afterInitView() {
        SubjectDao subjectDao = new SubjectDao(this);
        List<Subject> subjects = subjectDao.queryAll();
        mGridAdpater.setItems(subjects);
    }

    @Override
    public void onViewClick(View v) {

    }
}
