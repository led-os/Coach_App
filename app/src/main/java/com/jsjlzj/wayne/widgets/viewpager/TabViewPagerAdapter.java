package com.jsjlzj.wayne.widgets.viewpager;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<TabViewPagerAdapterItem> itemList;

    public TabViewPagerAdapter(FragmentManager fm, List<TabViewPagerAdapterItem> itemList) {
        super(fm);
        this.itemList = itemList;
    }

    public TabViewPagerAdapter(FragmentManager fm, TabViewPagerAdapterItem... itemList) {
        super(fm);
        this.itemList = new ArrayList<>();
        for (int i = 0; i < itemList.length; i++) {
            this.itemList.add(itemList[i]);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return itemList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return itemList.get(position).getTitle();
    }

    public Fragment getFragment(int index) {
        Fragment fragment = null;
        if (itemList != null && index < itemList.size())
            fragment = itemList.get(index).getFragment();
        return fragment;
    }
}
