package com.recycler.library.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by xugang on 2017/8/7.
 * Describe 横、纵向的RecyclerView 使用的 分割线
 * @see LinearLayoutManager
 */

public class LinearDecoration extends RecyclerView.ItemDecoration {

    private Context mContext;
    private int mOrientation;
    private int mDividerHeight;
    private int paddingLeft;
    private int paddingTop;
    private int paddingRight;
    private int paddingBottom;
    private Drawable mDivider;
    public static final int STYLE_LINE_HORIZONTAL = LinearLayoutManager.HORIZONTAL;
    public static final int STYLE_LINE_VERTICAL = LinearLayoutManager.VERTICAL;

    public LinearDecoration(Context mContext, @ColorRes int colorId) {
        this(mContext, STYLE_LINE_HORIZONTAL, 1, new ColorDrawable(ContextCompat.getColor(mContext, colorId)));
    }

    public LinearDecoration(Context mContext, int mOrientation, int mDividerHeight, Drawable mDivider) {
        this.mContext = mContext;
        this.mOrientation = mOrientation;
        this.mDividerHeight = mDividerHeight;
        this.mDivider = mDivider;
    }

    public void setDividerPadding(int left, int top, int right, int bottom) {
        this.paddingLeft = left;
        this.paddingBottom = bottom;
        this.paddingRight = right;
        this.paddingTop = top;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == STYLE_LINE_VERTICAL) {
            drawVerticalLine(c, parent, state);
        } else {
            drawHorizontalLine(c, parent, state);
        }
    }

    //画横线, 这里的parent其实是显示在屏幕显示的这部分
    public void drawHorizontalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin ;
            final int bottom = top + mDividerHeight ;
            mDivider.setBounds(left , top, right , bottom);
            mDivider.draw(c);
            //Log.d("wnw", left + " " + top + " "+right+"   "+bottom+" "+i);
        }
    }

    //画竖线
    public void drawVerticalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin ;
            final int right = left + mDividerHeight ;
            mDivider.setBounds(left, top , right, bottom );
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == STYLE_LINE_HORIZONTAL) {
            //画横线，就是往下偏移一个分割线的高度
            outRect.set(0, 0, 0, mDividerHeight );
        } else {
            //画竖线，就是往右偏移一个分割线的宽度
            outRect.set(0, 0, mDividerHeight , 0);
        }
    }
}

