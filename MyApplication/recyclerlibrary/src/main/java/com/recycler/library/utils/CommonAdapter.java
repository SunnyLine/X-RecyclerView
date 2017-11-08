package com.recycler.library.utils;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xugang on 2017/8/2.
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {

    protected List<T> mList;
    protected Context mContext;
    protected
    @LayoutRes
    int mLayoutId;

    public CommonAdapter(Context mContext, @LayoutRes int mLayoutId) {
        this(null, mContext, mLayoutId);
    }

    /**
     * 此构造的数组长度固定，无法进行增加删除操作
     *
     * @param mContext
     * @param t
     * @see Arrays#asList(Object[])
     * @see ArrayList
     */
    public CommonAdapter(Context mContext, T[] t, @LayoutRes int mLayoutId) {
        this(Arrays.asList(t), mContext, mLayoutId);
    }

    public CommonAdapter(List<T> mList, Context mContext, @LayoutRes int mLayoutId) {
        this.mList = mList == null ? new ArrayList<T>() : mList;
        this.mContext = mContext;
        this.mLayoutId = mLayoutId;
    }

    public List<T> getList() {
        return mList;
    }

    public void setList(List<T> mList) {
        this.mList = mList;
    }

    public void addBean(T t) {
        if (t != null) {
            mList.add(t);
        }
    }

    public void addBeans(List<T> list) {
        if (list != null && !list.isEmpty()) {
            mList.addAll(list);
        }
    }

    public void addBean(int index, T t) {
        if (t != null) {
            mList.add(index, t);
        }
    }

    public void addBeans(int index, List<T> list) {
        if (list != null && !list.isEmpty()) {
            mList.addAll(index, list);
        }
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommonViewHolder(LayoutInflater.from(mContext).inflate(mLayoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        convert(holder, mList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public abstract void convert(CommonViewHolder holder, T t, int position);
}
