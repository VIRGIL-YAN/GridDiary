package com.free.diary.view.activity;

import android.app.Activity;
import android.os.Bundle;
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

/**
 * Created by tangqi on 16/5/15.
 */
public class TestOrmActivity extends Activity implements AdapterView.OnItemClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_orm);

        ListView listView = (ListView) findViewById(R.id.lv_main);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                new String[]{"add", "delete", "update"
                }));
        listView.setOnItemClickListener(this);
    }

    public void testAddUser() {

        User u1 = new User("zhy", "2B青年");
        DatabaseHelper helper = DatabaseHelper.getHelper(TestOrmActivity.this);
        try {
            helper.getUserDao().create(u1);
            u1 = new User("zhy2", "2B青年");
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
        }
    }
}
