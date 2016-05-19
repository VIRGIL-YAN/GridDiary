package com.free.diary.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Created by tangqi on 16/5/18.
 */
public abstract class BaseActivity extends Activity implements OnClickListener {

    /**
     * 设置布局文件
     */
    public abstract void setContentLayout();

    /**
     * 在实例化布局之前处理的逻辑
     */
    public abstract void beforeInitView();

    /**
     * 实例化布局文件/组件
     */
    public abstract void initView();

    /**
     * 在实例化布局之后处理的逻辑
     */
    public abstract void afterInitView();

    /**
     * 处理点击事件
     */
    public abstract void onViewClick(View v);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentLayout();
        beforeInitView();
        initView();
        afterInitView();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                break;
        }

        onViewClick(view);
    }
}
