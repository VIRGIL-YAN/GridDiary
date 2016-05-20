package com.free.diary.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.free.diary.R;
import com.free.diary.model.bean.Diary;
import com.free.diary.model.bean.Grid;
import com.free.diary.model.bean.Subject;
import com.free.diary.model.dao.DiaryDao;
import com.free.diary.model.dao.GridDao;
import com.free.diary.model.dao.SubjectDao;
import com.free.diary.support.config.KeyConfig;
import com.free.diary.support.util.DateUtils;
import com.free.diary.ui.adapter.SubjectGridAdpater;

import java.util.List;

/**
 * Created by tangqi on 16/5/19.
 */
public class MainActivity extends BaseActivity implements OnItemClickListener {

    private static final int REQUEST_CODE_GRID_EDIT = 1000;

    private TextView mTvMainDate;
    private GridView mGridMainSubject;

    private DiaryDao mDiaryDao;
    private Diary mDiary;
    private GridDao mGridDao;
    private List<Grid> mGridList;
    private SubjectGridAdpater mGridAdpater;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void beforeInitView() {

    }

    @Override
    public void initView() {
        mTvMainDate = (TextView) findViewById(R.id.tv_main_date);
        mTvMainDate.setText("2016年05月20日");

        mGridAdpater = new SubjectGridAdpater(this);
        mGridMainSubject = (GridView) findViewById(R.id.grid_main_subject);
        mGridMainSubject.setAdapter(mGridAdpater);
        mGridMainSubject.setOnItemClickListener(this);
    }

    @Override
    public void afterInitView() {
        mGridDao = new GridDao(this);

        String currentDate = DateUtils.formatDate(System.currentTimeMillis());
        mDiaryDao = new DiaryDao(this);
        mDiary = mDiaryDao.query(currentDate);
        if (mDiary == null) {
            mDiary = new Diary();
            mDiary.setDate(currentDate);
        }
        mGridList = mDiary.getGrids();

        SubjectDao subjectDao = new SubjectDao(this);
        List<Subject> subjects = subjectDao.queryAll();
        mGridAdpater.setItems(subjects);
        mGridAdpater.setGridList(mGridList);

        queryAll();
    }

    @Override
    public void onViewClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Subject subject = (Subject) adapterView.getAdapter().getItem(position);

        Intent intent = new Intent(MainActivity.this, GridEditActivity.class);
        intent.putExtra(KeyConfig.SUBJECT, subject);
        if (mGridList != null) {
            for (Grid grid : mGridList) {
                if (subject.getSubject().equalsIgnoreCase(grid.getSubject())) {
                    intent.putExtra(KeyConfig.GRID, grid);
                    break;
                }
            }
        }
        startActivityForResult(intent, REQUEST_CODE_GRID_EDIT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_GRID_EDIT:
                if (resultCode == RESULT_OK) {
                    Grid grid = (Grid) data.getSerializableExtra(KeyConfig.GRID);
                    boolean isAddGrid = data.getBooleanExtra(KeyConfig.IS_ADD_GRID, true);
                    grid.setDiary(mDiary);

                    if (isAddGrid) {
                        mGridList.add(grid);
                        mDiaryDao.insert(mDiary);
                        mGridDao.insert(grid);
                    } else {
                        mDiaryDao.insert(mDiary);
                        mGridDao.insert(grid);
                    }

                    mGridList = mDiary.getGrids();
                    mGridAdpater.setGridList(mGridList);
                    queryAll();
                }
                break;
        }
    }

    private void queryAll() {
        List<Diary> list = mDiaryDao.queryAll();
        Log.i("Test", "diary:" + list.toString());
    }
}
