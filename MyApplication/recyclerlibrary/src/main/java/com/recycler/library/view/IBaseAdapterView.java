package com.recycler.library.view;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Created by xugang on 2017/11/8.
 */

public interface IBaseAdapterView {
    void toast(Context ctx, String msg);

    void toast(Context ctx, @StringRes int resId);

    void showSimpleMessageDialog(Context ctx, @StringRes int resId);

    void showSimpleMessageDialog(Context ctx, String msg);
}
