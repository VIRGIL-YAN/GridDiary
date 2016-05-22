package com.free.diary.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.free.diary.R;
import com.free.diary.model.bean.Grid;
import com.free.diary.model.bean.Subject;

import java.util.List;

/**
 * Created by tangqi on 16/5/19.
 */
public class SubjectGridAdpater extends BaseAbstractAdapter<Subject> {

    private List<Grid> mGridList;

    public SubjectGridAdpater(Context context) {
        super(context);
    }

    public void setGridList(List<Grid> gridList) {
        this.mGridList = gridList;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.griditem_subject, null);
            holder.tvSubject = (TextView) convertView.findViewById(R.id.tv_grid_subject);
            holder.tvContent = (TextView) convertView.findViewById(R.id.tv_grid_content);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        Subject subject = getItem(position);
        updateItem(subject, holder);

        return convertView;
    }

    private void updateItem(Subject subject, Holder holder) {
        boolean flag = false;
        if (mGridList != null) {
            for (Grid grid : mGridList) {
                if (subject.getSubject().equals(grid.getSubject())) {
                    if (!TextUtils.isEmpty(grid.getContent())) {
                        holder.tvSubject.setText(grid.getSubject());
                        holder.tvContent.setText(grid.getContent());
                        flag = true;
                    }
                    break;
                }
            }

        }

        if (!flag) {
            holder.tvSubject.setText("");
            holder.tvSubject.setHint(subject.getSubject());
            holder.tvContent.setText("");
        }
    }

    private class Holder {
        TextView tvSubject;
        TextView tvContent;
    }
}
