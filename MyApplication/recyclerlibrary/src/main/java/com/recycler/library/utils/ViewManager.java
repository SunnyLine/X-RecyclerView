package com.recycler.library.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by xugang on 2017/8/2.
 * View 管理类，用于各种自定义Dialog，popupWindow等
 */

public class ViewManager {
    private SparseArray<View> mViews;
    private View rootView;

    public ViewManager(View rootView) {
        this.rootView = rootView;
        this.mViews = new SparseArray<>();
    }

    public ViewManager(Context context, @LayoutRes int layoutId) {
        rootView = LayoutInflater.from(context).inflate(layoutId, null);
        this.mViews = new SparseArray<>();
    }

    public View getRootView() {
        return rootView;
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T findView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = rootView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /****以下为辅助方法*****/

    public ViewManager setText(@IdRes int id, @StringRes int resId) {
        TextView tv = findView(id);
        if (tv != null) {
            tv.setText(resId);
        }
        return this;
    }

    public ViewManager setText(@IdRes int id, String res) {
        TextView tv = findView(id);
        if (tv != null) {
            tv.setText(res);
        }
        return this;
    }

    public ViewManager setImageResource(@IdRes int id, @DrawableRes int drawableId) {
        ImageView img = findView(id);
        if (img != null) {
            img.setImageResource(drawableId);
        }
        return this;
    }

    public ViewManager setImageBitmap(@IdRes int id, Bitmap bitmap) {
        ImageView img = findView(id);
        if (img != null) {
            img.setImageBitmap(bitmap);
        }
        return this;
    }

    public ViewManager setImageDrawable(@IdRes int id, Drawable drawable) {
        ImageView img = findView(id);
        if (img != null) {
            img.setImageDrawable(drawable);
        }
        return this;
    }

    public ViewManager setVisibility(@IdRes int id, int v) {
        View view = findView(id);
        if (view != null) {
            view.setVisibility(v);
        }
        return this;
    }

    public ViewManager setBackgroundResource(@IdRes int id, @DrawableRes int colorId) {
        View view = findView(id);
        if (view != null) {
            view.setBackgroundResource(colorId);
        }
        return this;
    }

    public ViewManager setBackgroundColor(@IdRes int id, @ColorRes int colorId) {
        View view = findView(id);
        if (view != null) {
            view.setBackgroundColor(ContextCompat.getColor(rootView.getContext(), colorId));
        }
        return this;
    }

    public ViewManager setTextColor(@IdRes int id, @ColorRes int colorId) {
        TextView view = findView(id);
        if (view != null) {
            view.setTextColor(ContextCompat.getColor(rootView.getContext(), colorId));
        }
        return this;
    }

    @SuppressLint("NewApi")
    public ViewManager setAlpha(@IdRes int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            findView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            findView(viewId).startAnimation(alpha);
        }
        return this;
    }

    public ViewManager setProgress(@IdRes int viewId, int progress) {
        ProgressBar view = findView(viewId);
        if (view != null)
            view.setProgress(progress);
        return this;
    }

    public ViewManager setProgress(@IdRes int viewId, int progress, int max) {
        ProgressBar view = findView(viewId);
        if (view != null) {
            view.setMax(max);
            view.setProgress(progress);
        }
        return this;
    }

    public ViewManager setMax(@IdRes int viewId, int max) {
        ProgressBar view = findView(viewId);
        if (view != null)
            view.setMax(max);
        return this;
    }

    public ViewManager setRating(@IdRes int viewId, float rating) {
        RatingBar view = findView(viewId);
        if (view != null)
            view.setRating(rating);
        return this;
    }

    public ViewManager setRating(@IdRes int viewId, float rating, int max) {
        RatingBar view = findView(viewId);
        if (view != null) {
            view.setMax(max);
            view.setRating(rating);
        }
        return this;
    }

    public ViewManager setTag(@IdRes int viewId, Object tag) {
        View view = findView(viewId);
        if (view != null)
            view.setTag(tag);
        return this;
    }

    public ViewManager setTag(@IdRes int viewId, int key, Object tag) {
        View view = findView(viewId);
        if (view != null)
            view.setTag(key, tag);
        return this;
    }

    public ViewManager setChecked(@IdRes int viewId, boolean checked) {
        Checkable view = findView(viewId);
        if (view != null)
            view.setChecked(checked);
        return this;
    }

    /**
     * 关于事件的
     */
    public ViewManager setOnClickListener(@IdRes int viewId,
                                          View.OnClickListener listener) {
        View view = findView(viewId);
        if (view != null)
            view.setOnClickListener(listener);
        return this;
    }

    public ViewManager setOnClickListener(int[] viewIds,
                                          View.OnClickListener listener) {
        if (viewIds != null)
            for (int viewId : viewIds) {
                View view = findView(viewId);
                if (view != null) view.setOnClickListener(listener);
            }
        return this;
    }
}
