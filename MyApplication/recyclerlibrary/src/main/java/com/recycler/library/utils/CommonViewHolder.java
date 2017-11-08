package com.recycler.library.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by xugang on 2017/8/2.
 * 公用的ViewHolder，用于RecyclerView
 */

public class CommonViewHolder extends RecyclerView.ViewHolder {

    private int viewType;
    private ViewManager viewManager;

    public CommonViewHolder(View view) {
        super(view);
        viewManager = new ViewManager(view);
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public ViewManager getViewManager() {
        return viewManager;
    }
}
