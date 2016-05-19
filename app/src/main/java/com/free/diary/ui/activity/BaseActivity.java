package com.free.diary.ui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;

import com.free.diary.R;
import com.free.diary.ui.view.SystemBarTintManager;

/**
 * Created by tangqi on 16/5/18.
 */
public abstract class BaseActivity extends Activity implements OnClickListener {

    /**
     * 系统状态栏管理
     */
    protected SystemBarTintManager mSystemBarTintManager;

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

        // 初始化系统状态栏
        if (isUseSystemBarTint()) {
            initSystemBarTint();
        }

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

    /**
     * 初始化系统状态栏
     */
    protected void initSystemBarTint() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 状态栏透明 需要在创建SystemBarTintManager 之前调用。
            setTranslucentStatus(true);
        }

        // create our manager instance after the content view is set
        mSystemBarTintManager = new SystemBarTintManager(this);
        // enable status bar tint
        mSystemBarTintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint
        mSystemBarTintManager.setNavigationBarTintEnabled(true);
        // set a custom tint color for all system bars
        mSystemBarTintManager.setTintColor(getTintColor());

        // set a custom navigation bar resource
        // mSystemBarTintManager.setNavigationBarTintResource(R.drawable.ic_launcher);
        // set a custom status bar drawable
        // mSystemBarTintManager.setStatusBarTintResource(R.drawable.colorPrimary);
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 是否使用沉浸式状态栏
     *
     * @return
     */
    protected boolean isUseSystemBarTint() {
        return true;
    }

    /**
     * 获取系统状态栏颜色
     *
     * @return
     */
    protected int getTintColor() {
        return getResources().getColor(R.color.colorPrimary);
    }

    /**
     * 设置系统状态栏颜色
     *
     * @param color
     */
    protected void setTintColor(int color) {
        if (mSystemBarTintManager != null) {
            mSystemBarTintManager.setTintColor(color);
        }
    }

    /**
     * 设置系统状态栏背景
     *
     * @param resId
     */
    protected void setTintResource(int resId) {
        if (mSystemBarTintManager != null) {
            mSystemBarTintManager.setStatusBarTintResource(resId);
        }
    }
}
