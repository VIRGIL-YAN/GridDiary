/**
 * Copyright © 2015-2020 100msh.com All Rights Reserved
 */
package com.free.diary.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter基类
 *
 * @author tangqi
 * @date 2015年11月20日下午1:04:44
 */

public abstract class BaseAbstractAdapter<T> extends BaseAdapter {

    public Context mContext;
    protected LayoutInflater mLayoutInflater;
    protected List<T> mItemList = new ArrayList<T>();

    public BaseAbstractAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    /**
     * 判断数据是否为空
     *
     * @return 为空返回true，不为空返回false
     */
    public boolean isEmpty() {
        return mItemList.isEmpty();
    }

    /**
     * 在原有的数据上添加新数据
     *
     * @param itemList
     */
    public void addItems(List<T> itemList) {
        this.mItemList.addAll(itemList);
        notifyDataSetChanged();
    }

    /**
     * 设置为新的数据，旧数据会被清空
     *
     * @param itemList
     */
    public void setItems(List<T> itemList) {
        if (null != itemList) {
            this.mItemList.clear();
            this.mItemList = itemList;
            notifyDataSetChanged();
        }
    }

    /**
     * 清空数据
     */
    public void clearItems() {
        if (null != mItemList) {
            mItemList.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * 删除数据
     *
     * @param position
     */
    public void deleteItem(int position) {
        mItemList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (null != mItemList) {
            return mItemList.size();
        }
        return 0;
    }

    @Override
    public T getItem(int position) {
        return mItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public ArrayList<T> getItemList() {
        return (ArrayList<T>) mItemList;
    }

    @Override
    abstract public View getView(int position, View convertView, ViewGroup viewGroup);
}
