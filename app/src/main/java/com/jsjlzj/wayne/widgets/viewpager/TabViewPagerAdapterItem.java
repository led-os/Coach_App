package com.jsjlzj.wayne.widgets.viewpager;

import android.support.v4.app.Fragment;

public class TabViewPagerAdapterItem {
    private String title;
    private Fragment fragment;

    public TabViewPagerAdapterItem(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }


    public String getTitle() {
        return title;
    }

    public Fragment getFragment() {
        return fragment;
    }
}
