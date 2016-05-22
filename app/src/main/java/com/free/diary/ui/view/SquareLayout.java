package com.free.diary.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 正方形Layout
 *
 * @author Frank
 * @info 宽度根据屏幕适配，高度等于宽度
 * @time 2015-11-18 10:00:06
 */
public class SquareLayout extends LinearLayout {

    public SquareLayout(Context context) {
        this(context, null);
    }

    public SquareLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SquareLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = width;
        setMeasuredDimension(width, height);

        // 计算出所有的childView的宽和高
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
