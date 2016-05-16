package com.free.diary.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.free.diary.R;
import com.free.diary.model.DatabaseHelper;
import com.free.diary.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

/**
 * Created by tangqi on 16/5/15.
 */
public class TestOrmActivity extends Activity implements AdapterView.OnItemClickListener {

    private static final String TAG = "Test";

    private Looper mBackgroundLooper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_orm);

        ListView listView = (ListView) findViewById(R.id.lv_main);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                new String[]{"add", "delete", "update", "RxAndroid"
                }));
        listView.setOnItemClickListener(this);

        BackgroundThread backgroundThread = new BackgroundThread();
        backgroundThread.start();
        mBackgroundLooper = backgroundThread.getLooper();
    }

    public void testAddUser() {
        User u1 = new User("zhy", "2B青年");
        DatabaseHelper helper = DatabaseHelper.getHelper(TestOrmActivity.this);
        try {
            helper.getUserDao().create(u1);
            u1 = new User("zhy-android", "2B青年");
            helper.getUserDao().create(u1);

            testList();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void testDeleteUser() {
        DatabaseHelper helper = DatabaseHelper.getHelper(TestOrmActivity.this);
        try {
            helper.getUserDao().deleteById(2);
            testList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void testUpdateUser() {
        DatabaseHelper helper = DatabaseHelper.getHelper(TestOrmActivity.this);
        try {
            User u1 = new User("zhy-android", "2B青年");
            u1.setId(2);
            helper.getUserDao().update(u1);

            testList();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void testList() {
        DatabaseHelper helper = DatabaseHelper.getHelper(TestOrmActivity.this);
        try {
            List<User> users = helper.getUserDao().queryForAll();
            Log.e("Test", "size:" + users.size() + " -" + users.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                testAddUser();
                break;

            case 1:
                testDeleteUser();
                break;

            case 2:
                testUpdateUser();
                break;
            case 3:
                testRxAndroid();
                break;
        }
    }

    private void testRxAndroid() {
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

    static Observable<String> sampleObservable(){
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
