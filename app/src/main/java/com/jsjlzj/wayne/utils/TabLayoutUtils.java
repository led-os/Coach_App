package com.jsjlzj.wayne.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.jsjlzj.wayne.R;

import androidx.core.content.ContextCompat;

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
                    tvTitle.setTextSize(17);
                    tvTitle.setTextColor(ContextCompat.getColor(context,R.color.color_4F9BFA));
                } else {
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
                    tvTitle.setTextColor(ContextCompat.getColor(context,R.color.color_4F9BFA));
                } else {
                    tvTitle.setTextSize(13);
                    tvTitle.setTextColor(ContextCompat.getColor(context,R.color.color_999999));
                }
                //最后添加view到Tab上面
                tab.setCustomView(view);
            }
        }
    }
}
