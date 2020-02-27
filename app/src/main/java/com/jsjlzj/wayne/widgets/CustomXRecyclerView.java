package com.jsjlzj.wayne.widgets;

import android.content.Context;
import android.util.AttributeSet;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * @ClassName: CustomXRecyclerView
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/25 14:34
 */
public class CustomXRecyclerView extends XRecyclerView {
    public CustomXRecyclerView(Context context) {
        this(context,null);
    }

    public CustomXRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomXRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        setLoadingMoreProgressStyle(ProgressStyle.BallBeat);
        getDefaultRefreshHeaderView().setRefreshTimeVisible(true);
        getDefaultFootView().setNoMoreHint("自定义加载完毕提示");
    }
}
