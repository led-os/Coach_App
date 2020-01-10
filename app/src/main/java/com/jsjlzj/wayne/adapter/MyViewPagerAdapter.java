package com.jsjlzj.wayne.adapter;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MyViewPagerAdapter<T> extends PagerAdapter {
    private List<T> modelList;

    public MyViewPagerAdapter(List<T> modelList) {
        this.modelList = modelList;
    }

    @Override
    public int getCount() {
        return modelList == null ? 0 : modelList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        T t = modelList.get(position);
        View view = listener.OnBinding(position, t);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    public interface OnBindingListener<T> {
        View OnBinding(int index, T t);
    }

    private OnBindingListener<T> listener;

    public void setOnBindingListener(OnBindingListener<T> listener) {
        this.listener = listener;
    }
}
