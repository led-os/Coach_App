package com.jsjlzj.wayne.widgets;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;

/**
 * Created by Liu on 2018/3/10.
 */

public class MyViewPager extends ViewPager {
    private boolean isSlide = false;

    public void setSlide(boolean flag) {
        this.isSlide = flag;
    }

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isSlide) {
            return super.onTouchEvent(ev);
        } else {
            return false;
        }
    }

    private int preX = 0, preY = 0;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isSlide) {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                preX = (int) ev.getX();
                preY = (int) ev.getX();
            } else {
                if (Math.abs((int) ev.getX() - preX) > 20) {
                    return true;
                } else {
                    preX = (int) ev.getX();
                }
            }
            return super.onInterceptTouchEvent(ev);

        } else {
            return false;
        }
    }
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {

        if (v instanceof HorizontalScrollView) {
            return true;
        }
        return super.canScroll(v, checkV, dx, x, y);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item,false);
    }
}
