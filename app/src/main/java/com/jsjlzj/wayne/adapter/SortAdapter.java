package com.jsjlzj.wayne.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.SelectLocationAdapter;
import com.jsjlzj.wayne.entity.address.CityListBean;

import java.util.List;

/**
 * @ClassName: SortAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/3/3 14:10
 */
public class SortAdapter extends BaseAdapter {
    private List<CityListBean> list = null;
    private Context mContext;

    public SortAdapter(Context mContext, List<CityListBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     * @param list
     */
    public void updateListView(List<CityListBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        final CityListBean cityListBean = list.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_city_selector, null);
            viewHolder.tvTitle =  view.findViewById(R.id.tv_title);
            viewHolder.rvName =  view.findViewById(R.id.rv_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.rvName.setHasFixedSize(true);
        viewHolder.rvName.setNestedScrollingEnabled(false);
        viewHolder.tvTitle.setText(this.list.get(position).getTitle());
        viewHolder.rvName.setLayoutManager(new LinearLayoutManager(this.mContext));
        SelectLocationAdapter adapter = new SelectLocationAdapter(this.mContext, cityListBean.getLists());
        adapter.setListener(listener);
        viewHolder.rvName.setAdapter(adapter);
        return view;

    }

    private SelectLocationAdapter.OnItemClickListener listener;

    public void setListener(SelectLocationAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public int getPositionForSection(String charAt) {
        for (int i = 0 ; i< list.size() ; i++){
            if(charAt.equals(list.get(i).getTitle())){
                return i;
            }
        }
        return -1;
    }

    final static class ViewHolder {
        TextView tvTitle;
        RecyclerView rvName;
    }
}
