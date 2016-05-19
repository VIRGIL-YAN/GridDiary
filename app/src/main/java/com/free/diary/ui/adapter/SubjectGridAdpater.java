package com.free.diary.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.free.diary.R;
import com.free.diary.model.bean.Subject;

/**
 * Created by tangqi on 16/5/19.
 */
public class SubjectGridAdpater extends BaseAbstractAdapter<Subject>{

    public SubjectGridAdpater(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Holder holder = null;
        if(convertView == null){
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.girditem_subject, null);
            holder.tvSubject = (TextView) convertView.findViewById(R.id.tv_subject_grid);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }

        Subject subject = getItem(position);
        holder.tvSubject.setHint(subject.getQuestion());
        return convertView;
    }

    private class Holder{
        TextView tvSubject;
    }
}
