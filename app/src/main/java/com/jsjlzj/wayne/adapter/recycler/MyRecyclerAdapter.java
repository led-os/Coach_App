package com.jsjlzj.wayne.adapter.recycler;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public abstract class MyRecyclerAdapter<T> extends RecyclerView.Adapter implements IAdapter<T> {

    private final Context mContext;
    private final int mLayoutResId;
    public List<T> mData;

    private OnItemClickListener mItemClickListener;
    private OnItemLongClickListener mItemLongClickListener;

    private int curPosition = 0;

    public MyRecyclerAdapter(Context context, int layoutResId, List<T> data) {
        this.mData = data;
        if (data == null) {
            throw new IllegalArgumentException("非法参数，此处的data只能是引用！！！");
        }
        this.mContext = context;
        this.mLayoutResId = layoutResId;
    }

    public MyRecyclerAdapter(Context context, int layoutResId) {
        this.mData = new ArrayList<>();
        this.mContext = context;
        this.mLayoutResId = layoutResId;
    }

    public void setData(List<T> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public void setData(T data, int position) {
        if (position < 0 || position >= this.mData.size()) return;
        this.mData.set(position, data);
        notifyDataSetChanged();
    }

    public void addData(List<T> data) {
        this.mData.addAll(data);
        notifyDataSetChanged();
    }

    public void addData(T data) {
        this.mData.add(data);
        notifyDataSetChanged();
    }

    public void addData(T data, int position) {
        if (position < 0 || position >= this.mData.size()) return;
        this.mData.add(data);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.mData.clear();
        notifyDataSetChanged();
    }

    public void remove(int index) {
        if (mData.size() > 0 && index < mData.size()) mData.remove(index);
        notifyDataSetChanged();
    }
    public void remove(T item) {
        mData.remove(item);
        notifyDataSetChanged();
    }


    public List<T> getItems() {
        return this.mData;
    }

    public T getItem(int position) {
        return position >= mData.size() ? null : mData.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final BaseAdapterHelper helper = BaseAdapterHelper.get(mContext, null, parent, viewType, -1);
        return new RecyclerViewHolder(helper.getView(), helper);
    }

    public BaseAdapterHelper getAdapterHelper(RecyclerView.ViewHolder holder) {
        return ((RecyclerViewHolder) holder).mAdapterHelper;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseAdapterHelper helper = getAdapterHelper(holder);
        helper.setAssociatedObject(getItem(position));
        onUpdate(helper, getItem(position), position);
        onUpdate(holder, helper, getItem(position), position);
    }

    public void onUpdate(RecyclerView.ViewHolder holder, BaseAdapterHelper helper, T item, int position) {
    }


    @Override
    public int getItemViewType(int position) {
        return getLayoutResId(getItem(position), position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }

    @Override
    public int getLayoutResId(T item, int position) {
        return this.mLayoutResId;
    }


    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener itemLongClickListener) {
        this.mItemLongClickListener = itemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(RecyclerView.ViewHolder viewHolder, View view, int position);
    }

    private final class RecyclerViewHolder extends RecyclerView.ViewHolder {
        BaseAdapterHelper mAdapterHelper;

        public RecyclerViewHolder(View itemView, BaseAdapterHelper adapterHelper) {
            super(itemView);
            this.mAdapterHelper = adapterHelper;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mItemClickListener) {
                        mItemClickListener.onItemClick(MyRecyclerAdapter.RecyclerViewHolder.this, v, getAdapterPosition());
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (null != mItemLongClickListener) {
                        mItemLongClickListener.onItemLongClick(MyRecyclerAdapter.RecyclerViewHolder.this, v, getAdapterPosition());
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    public void setCurPosition(int position) {
        this.curPosition = position;
    }

    public int getCurPosition() {
        return this.curPosition;
    }
}
