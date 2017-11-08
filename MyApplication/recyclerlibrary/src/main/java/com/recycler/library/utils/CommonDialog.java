package com.recycler.library.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;

import com.geenk.zto.sys.R;


/**
 * Created by xugang on 2016/10/24.
 */
public class CommonDialog {
    private Dialog mDialog;
    private ViewManager viewManager;

    public CommonDialog(Context mContext, @LayoutRes int layoutId) {
        mDialog = new Dialog(mContext, R.style.CustomDialog);
        viewManager = new ViewManager(mContext, layoutId);
        mDialog.setContentView(viewManager.getRootView());
    }

    public CommonDialog setCancelable(boolean flag) {
        mDialog.setCancelable(flag);
        return this;
    }

    public ViewManager getViewManager() {
        return viewManager;
    }

    public void show() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    public void cancel() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.cancel();
        }
    }
}
