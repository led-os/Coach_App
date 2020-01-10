package com.jsjlzj.wayne.widgets;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.adapter.recycler.IData;
import com.jsjlzj.wayne.adapter.recycler.MyRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerView<T> extends XRecyclerView implements IData<T> {
    private final int STATE_ADD = 0;
    private final int STATE_ADD_INDEX = 1;
    private final int STATE_ADD_ALL = 2;
    private final int STATE_SET = 3;
    private final int STATE_REMOVE = 4;
    private final int STATE_REPLACE = 5;
    private final int STATE_CLEAR = 6;
    private int refreshState = STATE_ADD_ALL;
    private int lastIndex;

    private boolean isSliding, dataIsChange;
    private List<T> data = new ArrayList<>();

    public MyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void fillData(List<T> data) {
        if (data != null) this.data = data;
    }

    @Override
    public List<T> getData() {
        return data;
    }

    @Override
    public void add(T elem) {
        data.add(elem);
        dataIsChange = true;
        refreshState = STATE_ADD;
        lastIndex = data.size() - 1;
        if (!isSliding) {
            dataIsChange = false;
            if (getAdapter() instanceof MyRecyclerAdapter) {
                ((MyRecyclerAdapter) getAdapter()).addData(elem);
            } else notifyItemInserted(data, lastIndex);
        }
    }

    @Override
    public void add(int index, T elem) {
        if (index > data.size()) return;
        data.add(index, elem);
        dataIsChange = true;
        refreshState = STATE_ADD_INDEX;
        lastIndex = index;
        if (!isSliding) {
            dataIsChange = false;
            if (getAdapter() instanceof MyRecyclerAdapter) {
                ((MyRecyclerAdapter) getAdapter()).addData(elem, index);
            } else notifyItemInserted(data, index);
        }
    }

    @Override
    public void addAll(List<T> elem) {
        int len = data.size();
        addAll(len, elem);
    }

    @Override
    public void addAll(int index, List<T> elem) {
        data.addAll(index, elem);
        dataIsChange = true;
        refreshState = STATE_ADD_ALL;
        lastIndex = index;
        if (!isSliding) {
            dataIsChange = false;
            if (getAdapter() instanceof MyRecyclerAdapter) {
                ((MyRecyclerAdapter) getAdapter()).addData(elem, index);
            } else notifyItemInserted(data, index);
        }
    }

    @Override
    public void set(T oldElem, T newElem) {
        int index = data.indexOf(oldElem);
        if (index > -1)
            set(index, newElem);
        else set(data.size(), newElem);
    }

    @Override
    public void set(int index, T elem) {
        data.set(index, elem);
        dataIsChange = true;
        refreshState = STATE_SET;
        lastIndex = index;
        if (!isSliding) {
            dataIsChange = false;
            if (getAdapter() instanceof MyRecyclerAdapter) {
                ((MyRecyclerAdapter) getAdapter()).setData(elem, index);
            } else getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void remove(T elem) {
        int index = data.indexOf(elem);
        if (index > -1) remove(data.indexOf(elem));
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= data.size()) return;
        data.remove(index);
        dataIsChange = true;
        refreshState = STATE_REMOVE;
        lastIndex = index;
        if (!isSliding) {
            dataIsChange = false;
            if (getAdapter() instanceof MyRecyclerAdapter) {
                ((MyRecyclerAdapter) getAdapter()).remove(index);
            } else notifyItemRemoved(data, index);
        }
    }

    @Override
    public void replaceAll(List<T> elem) {
        if (elem == null) return;
        data = elem;
        dataIsChange = true;
        refreshState = STATE_REPLACE;
        if (!isSliding) {
            dataIsChange = false;
            if (getAdapter() instanceof MyRecyclerAdapter) {
                ((MyRecyclerAdapter) getAdapter()).setData(data);
            } else getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public boolean contains(T elem) {
        return data.contains(elem);
    }

    @Override
    public void clear() {
        data.clear();
        dataIsChange = true;
        refreshState = STATE_CLEAR;
        if (!isSliding) {
            dataIsChange = false;
            if (getAdapter() instanceof MyRecyclerAdapter) {
                ((MyRecyclerAdapter) getAdapter()).clearData();
            } else getAdapter().notifyDataSetChanged();
        }
    }

    public void notifyDataSetChanged() {
        if (getAdapter() != null) getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        if (state == RecyclerView.SCROLL_STATE_IDLE) {//是否是静止的
            isSliding = false;
            refreshLastData();
        } else isSliding = true;
    }

    private void refreshLastData() {
        if (!dataIsChange) return;
        if (getMainAdapter()==null || getMainAdapter().getState()==ADAPTER_STATE_RECYLE)return;
            switch (refreshState) {
                case STATE_ADD:
                    dataIsChange = false;
                    if (getAdapter() instanceof MyRecyclerAdapter) {
                        ((MyRecyclerAdapter) getAdapter()).setData(data);
                    } else notifyItemInserted(data, lastIndex);
                    break;
                case STATE_ADD_INDEX:
                    dataIsChange = false;
                    if (getAdapter() instanceof MyRecyclerAdapter) {
                        ((MyRecyclerAdapter) getAdapter()).setData(data);
                    } else notifyItemInserted(data, lastIndex);
                    break;
                case STATE_ADD_ALL:
                    dataIsChange = false;
                    if (getAdapter() instanceof MyRecyclerAdapter) {
                        ((MyRecyclerAdapter) getAdapter()).addData(data.subList(lastIndex, data.size()), lastIndex);
                    } else notifyItemInserted(data, lastIndex);

                    break;
                case STATE_SET:
                    dataIsChange = false;
                    if (getAdapter() instanceof MyRecyclerAdapter) {
                        ((MyRecyclerAdapter) getAdapter()).setData(data.get(lastIndex), lastIndex);
                    } else notifyItemInserted(data, lastIndex);
                    break;
                case STATE_REMOVE:
                    dataIsChange = false;
                    if (getAdapter() instanceof MyRecyclerAdapter) {
                        ((MyRecyclerAdapter) getAdapter()).remove(lastIndex);
                    } else notifyItemInserted(data, lastIndex);
                    break;
                case STATE_REPLACE:
                    dataIsChange = false;
                    if (getAdapter() instanceof MyRecyclerAdapter) {
                        ((MyRecyclerAdapter) getAdapter()).setData(data);
                    } else notifyItemInserted(data, lastIndex);
                    break;
                case STATE_CLEAR:
                    dataIsChange = false;
                    if (getAdapter() instanceof MyRecyclerAdapter) {
                        ((MyRecyclerAdapter) getAdapter()).clearData();
                    } else notifyItemInserted(data, lastIndex);
                    break;
            }
    }

}
