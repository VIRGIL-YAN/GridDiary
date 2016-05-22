package com.free.diary.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.free.diary.R;


/**
 * 运行时动态调整尺寸的LinearLayout
 *
 * @author dugd
 * @time 2015-02-28
 */
public class DynamicLinearLayout extends LinearLayout {

    private double mHeightRatio = 1.0;

    public DynamicLinearLayout(Context context) {
        this(context, null);
    }

    public DynamicLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DynamicLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DynamicSpecImage, defStyle, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.DynamicSpecImage_ratio:
                    mHeightRatio = a.getFloat(attr, (float) 0.0);
                    break;

                default:
                    break;
            }
        }
        a.recycle();
    }

    public void setHeightRatio(double ratio) {
        mHeightRatio = ratio;
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mHeightRatio > 0.0) {
            // set the image views size
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = (int) (width * mHeightRatio);
            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

        // 计算出所有的childView的宽和高
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
