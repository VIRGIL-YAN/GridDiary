package com.free.diary.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.free.diary.R;
import com.free.diary.model.bean.Grid;

/**
 * Created by tangqi on 16/5/27.
 */
public class DiaryReadAdapter extends BaseAbstractAdapter<Grid> {

    public DiaryReadAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = mLayoutInflater.inflate(R.layout.listitem_readmode, null);
            holder.tvItemTitle = (TextView) convertView.findViewById(R.id.tv_item_title);
            holder.tvItemContent = (TextView) convertView.findViewById(R.id.tv_item_content);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        Grid grid = getItem(position);
        updateItem(grid, holder);
        return convertView;
    }

    private void updateItem(Grid grid, Holder holder) {
        holder.tvItemTitle.setText(grid.getSubject());
        holder.tvItemContent.setText(grid.getContent());
    }

    private static class Holder {
        TextView tvItemTitle;
        TextView tvItemContent;
    }
}
