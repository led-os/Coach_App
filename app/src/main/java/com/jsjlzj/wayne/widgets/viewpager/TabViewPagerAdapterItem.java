package com.jsjlzj.wayne.widgets.viewpager;

import androidx.fragment.app.Fragment;

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
