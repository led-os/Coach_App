package com.jsjlzj.wayne.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.android.material.tabs.TabLayout;
import com.jsjlzj.wayne.R;

/**
 * Description: java类作用描述
 *
 * @author: 曾海强
 * CreateDate: 2019/9/5 9:27
 */
public class TabLayoutUtils {

    /**
     * 这个一定要在setAdapter之后执行
     */
    public static void setTabStyle(TabLayout tabLayout, String[] mTitles, int position, int itemLayoutId, Context context) {
        //根据Tab数量循环来设置
        for (int i = mTitles.length - 1; i >= 0; i--) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                View view = tab.getCustomView();
                if(view == null){
                    view = LayoutInflater.from(context).inflate(itemLayoutId, null);
                }
                TextView tvTitle = view.findViewById(R.id.tv_title);
                tvTitle.setText(mTitles[i]);
                //第一个默认为选择样式
                if (i == position) {
                    tvTitle.setTextSize(16);
                    tvTitle.setTextColor(ContextCompat.getColor(context,R.color.color_222222));
                } else {
                    tvTitle.setTextSize(14);
                    tvTitle.setTextColor(ContextCompat.getColor(context,R.color.color_999999));
                }
                //最后添加view到Tab上面
                tab.setCustomView(view);
            }
        }
    }


    /**
     * 这个一定要在setAdapter之后执行
     */
    public static void setSearchTabStyle(TabLayout tabLayout, String[] mTitles, int position, int itemLayoutId, Context context) {
        //根据Tab数量循环来设置
        for (int i = mTitles.length - 1; i >= 0; i--) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                View view = tab.getCustomView();
                if(view == null){
                    view = LayoutInflater.from(context).inflate(itemLayoutId, null);
                }
                TextView tvTitle = view.findViewById(R.id.tv_title);
                tvTitle.setText(mTitles[i]);
                //第一个默认为选择样式
                if (i == position) {
                    tvTitle.setTextSize(15);
                    tvTitle.setTextColor(ContextCompat.getColor(context,R.color.color_222222));
                } else {
                    tvTitle.setTextSize(13);
                    tvTitle.setTextColor(ContextCompat.getColor(context,R.color.color_999999));
                }
                //最后添加view到Tab上面
                tab.setCustomView(view);
            }
        }
    }


    /**
     * 社区
     */
    public static void setCommunityTabStyle(TabLayout tabLayout, String[] mTitles, int position, int itemLayoutId, Context context) {
        //根据Tab数量循环来设置
        for (int i = mTitles.length - 1; i >= 0; i--) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                View view = tab.getCustomView();
                if(view == null){
                    view = LayoutInflater.from(context).inflate(itemLayoutId, null);
                }
                TextView tvTitle = view.findViewById(R.id.tv_title);
                View inView = view.findViewById(R.id.view);
                tvTitle.setText(mTitles[i]);
                //第一个默认为选择样式
                if (i == position) {
                    tvTitle.setTextSize(17);
                    inView.setVisibility(View.VISIBLE);
                    tvTitle.setTextColor(ContextCompat.getColor(context,R.color.color_222222));
                } else {
                    inView.setVisibility(View.GONE);
                    tvTitle.setTextSize(15);
                    tvTitle.setTextColor(ContextCompat.getColor(context,R.color.color_999999));
                }
                //最后添加view到Tab上面
                tab.setCustomView(view);
            }
        }
    }


    /**
     * 这个一定要在setAdapter之后执行
     */
    public static void setTabStyle2(TabLayout tabLayout, String[] mTitles, int position, int itemLayoutId, Context context) {
        //根据Tab数量循环来设置
        for (int i = mTitles.length - 1; i >= 0; i--) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                View view = tab.getCustomView();
                if(view == null){
                    view = LayoutInflater.from(context).inflate(itemLayoutId, null);
                }
                TextView tvTitle = view.findViewById(R.id.tv_title);
                View bottomView = view.findViewById(R.id.view);
                tvTitle.setText(mTitles[i]);
                //第一个默认为选择样式
                if (i == position) {
                    tvTitle.setTextSize(16);
                    bottomView.setVisibility(View.VISIBLE);
                    tvTitle.setTextColor(ContextCompat.getColor(context,R.color.color_222222));
                } else {
                    tvTitle.setTextSize(14);
                    bottomView.setVisibility(View.GONE);
                    tvTitle.setTextColor(ContextCompat.getColor(context,R.color.color_999999));
                }
                //最后添加view到Tab上面
                tab.setCustomView(view);
            }
        }
    }
}
