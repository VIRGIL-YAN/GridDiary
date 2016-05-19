/** Copyright © 2015-2020 100msh.com All Rights Reserved */
package com.free.diary.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 圆形的开关按钮，设置成CheckBox或者ToggleButton不起作用。。。
 * 
 * @author Frank
 * @date 2015年11月19日下午5:25:41
 */

public class SquareTextView extends TextView {

	public SquareTextView(Context context) {
		this(context, null);
	}

	public SquareTextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SquareTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = width;
		setMeasuredDimension(width, height);
	}
}
