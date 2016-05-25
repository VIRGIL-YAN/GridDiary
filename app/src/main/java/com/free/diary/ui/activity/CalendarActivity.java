package com.free.diary.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.free.diary.R;
import com.free.diary.support.config.KeyConfig;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Date;

/**
 * Created by tangqi on 16/5/21.
 */
public class CalendarActivity extends BaseActivity {
    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_calendar);
    }

    @Override
    public void beforeInitView() {

    }

    @Override
    public void initView() {
        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("选择日期");

        MaterialCalendarView calendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
                intent.putExtra(KeyConfig.CALENDAR_DAY, date);
                startActivity(intent);
                finish();
            }
        });

        Date date = (Date) getIntent().getSerializableExtra(KeyConfig.DATE);
        if (date != null) {
            calendarView.setSelectedDate(date);
        }
    }

    @Override
    public void afterInitView() {

    }

    @Override
    public void onViewClick(View v) {

    }
}
