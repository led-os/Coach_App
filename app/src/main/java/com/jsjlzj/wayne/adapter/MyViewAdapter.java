package com.jsjlzj.wayne.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class MyViewAdapter<T> extends BaseAdapter {
    private ArrayList<T> modelList;


    public MyViewAdapter(ArrayList<T> modelList) {
        this.modelList = modelList;
    }

    public MyViewAdapter(ArrayList<T> modelList, OnBindingListener<T> listener) {
        this.modelList = modelList;
        this.listener = listener;
    }
    public MyViewAdapter(OnBindingListener<T> listener) {
        this.modelList = new ArrayList<T>();
        this.listener = listener;
    }

    public void setModelList(ArrayList<T> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    public void clear() {
        this.modelList.clear();
        notifyDataSetChanged();
    }

    public void addItem(T t) {
        this.modelList.add(t);
        notifyDataSetChanged();
    }

    public void addItemList(ArrayList<T> t) {
        this.modelList.addAll(t);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return modelList == null ? 0 : modelList.size();
    }

    public ArrayList<T> getItems() {
        return modelList;
    }

    @Override
    public Object getItem(int position) {
        return modelList == null ? 0 : modelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position >= getCount()) {
            return null;
        }

        T t = modelList.get(position);
        return listener.OnBinding(position, t, convertView);
    }

    public void refreshView(T t) {
        if (modelList.contains(t)) {
            modelList.remove(t);
            modelList.add(t);
            notifyDataSetChanged();
        }
    }

    public interface OnBindingListener<T> {
        View OnBinding(int index, T t, View view);
    }

    private OnBindingListener<T> listener;

    public void setOnBindingListener(OnBindingListener<T> listener) {
        this.listener = listener;
    }

}
