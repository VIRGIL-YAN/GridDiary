package com.free.diary.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.free.diary.R;
import com.free.diary.model.bean.Grid;
import com.free.diary.model.bean.Subject;
import com.free.diary.support.config.KeyConfig;

/**
 * Created by tangqi on 16/5/20.
 */
public class GridEditActivity extends BaseActivity implements View.OnFocusChangeListener {

    private ImageView mIvDelete;
    private ImageView mIvCommit;
    private EditText mEtContent;

    private Grid mGrid;
    private Subject mSubject;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_grid_edit);
    }

    @Override
    public void beforeInitView() {
        mSubject = (Subject) getIntent().getSerializableExtra(KeyConfig.SUBJECT);
        mGrid = (Grid) getIntent().getSerializableExtra(KeyConfig.GRID);
    }

    @Override
    public void initView() {
        TextView tvTitle = (TextView) findViewById(R.id.tv_grid_title);
        tvTitle.setText(mSubject.getSubject());

        mEtContent = (EditText) findViewById(R.id.et_grid_content);
        mEtContent.setOnFocusChangeListener(this);
        if (mGrid != null) {
            mEtContent.setText(mGrid.getContent());
            mEtContent.setSelection(mGrid.getContent().length());
        }

        mIvDelete = (ImageView) findViewById(R.id.iv_delelte);
        mIvDelete.setOnClickListener(this);

        mIvCommit = (ImageView) findViewById(R.id.iv_commit);
        mIvCommit.setOnClickListener(this);
    }

    @Override
    public void afterInitView() {

    }

    @Override
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.iv_commit:
                boolean isAddGrid = false;
                if (mGrid == null) {
                    mGrid = new Grid();
                    isAddGrid = true;
                }

                // 新添加的内容为空的，不处理
                if (isAddGrid && TextUtils.isEmpty(mEtContent.getText().toString())) {
                    finish();
                    return;
                }

                mGrid.setContent(mEtContent.getText().toString());
                mGrid.setSubject(mSubject.getSubject());
                Intent data = new Intent();
                data.putExtra(KeyConfig.IS_ADD_GRID, isAddGrid);
                data.putExtra(KeyConfig.GRID, mGrid);
                setResult(RESULT_OK, data);

                finish();
                break;

            case R.id.iv_delelte:
                finish();
                break;

            default:
                break;
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (b) {
            mIvCommit.setVisibility(View.VISIBLE);
            mIvDelete.setVisibility(View.VISIBLE);
        }
    }
}
