package com.free.diary.support.test;

import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.free.diary.R;
import com.free.diary.model.bean.Diary;
import com.free.diary.model.bean.Grid;
import com.free.diary.model.dao.DiaryDao;
import com.free.diary.model.dao.GridDao;
import com.free.diary.model.dao.base.DatabaseHelper;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

/**
 * Created by tangqi on 16/5/15.
 */
public class TestOrmActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "Test";
    private Looper mBackgroundLooper;
    private DiaryDao mDiaryDao;
    private GridDao mGridDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_orm);
        ButterKnife.bind(this);

        ListView listView = (ListView) findViewById(R.id.lv_main);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                new String[]{"AddUser", "DeleteUser", "UpdateUser", "RxAndroid", "AddDiary",
                        "UpdateDiary"
                }));
        listView.setOnItemClickListener(this);

        BackgroundThread backgroundThread = new BackgroundThread();
        backgroundThread.start();
        mBackgroundLooper = backgroundThread.getLooper();

        mDiaryDao = new DiaryDao(TestOrmActivity.this);
        mGridDao = new GridDao(TestOrmActivity.this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                addUser();
                break;

            case 1:
                deleteUser();
                break;

            case 2:
                updateUser();
                break;

            case 3:
                doRxAndroid();
                break;

            case 4:
                addDiary();
                break;

            case 5:
                updateDiary();
                break;
        }
    }

    private void addDiary() {
        Diary diary = new Diary();

        List<Grid> grids = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Grid grid = new Grid();
            grid.setContent("" + i);
            grid.setDiary(diary);
            grids.add(grid);
        }

        diary.getGrids().addAll(grids);
        mDiaryDao.insert(diary);
        mGridDao.insert(grids);

        printDiary();
    }

    private void updateDiary() {
        List<Diary> list = mDiaryDao.queryAll();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Diary diary = list.get(0);

                Grid grid = new Grid();
                grid.setContent(new Random().nextInt(10) + "");
                grid.setDiary(diary);
                mGridDao.insert(grid);

                diary.getGrids().add(grid);
                mDiaryDao.update(diary);
                break;
            }
        }

        printDiary();
    }

    private void printDiary() {
        List<Diary> list = mDiaryDao.queryAll();
        Log.i("Test", "diary:" + list.toString());
    }

    public void addUser() {
        User u1 = new User("zhy", "2B青年");
        DatabaseHelper helper = DatabaseHelper.getHelper(TestOrmActivity.this);
        try {
            Dao dao = helper.getDao(User.class);
            dao.create(u1);
            u1 = new User("zhy-android", "2B青年");
            dao.create(u1);

            testList();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser() {
        DatabaseHelper helper = DatabaseHelper.getHelper(TestOrmActivity.this);
        try {
            helper.getDao(User.class).deleteById(2);
            testList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser() {
        DatabaseHelper helper = DatabaseHelper.getHelper(TestOrmActivity.this);
        try {
            User u1 = new User("zhy-android", "2B青年");
            u1.setId(2);
            helper.getDao(User.class).update(u1);

            testList();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void testList() {
        DatabaseHelper helper = DatabaseHelper.getHelper(TestOrmActivity.this);
        try {
            List<User> users = helper.getDao(User.class).queryForAll();
            Log.e("Test", "size:" + users.size() + " -" + users.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void doRxAndroid() {
        sampleObservable()
                .subscribeOn(AndroidSchedulers.from(mBackgroundLooper))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError()", e);
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "onNext(" + s + ")");
                    }
                });
    }

    static Observable<String> sampleObservable() {
        return Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e(TAG, "Observable.defer");
                return Observable.just("one", "two", "three");
            }
        });
    }

    static class BackgroundThread extends HandlerThread {
        BackgroundThread() {
            super("SchedulerSample-BackgroundThread", THREAD_PRIORITY_BACKGROUND);
        }
    }
}
