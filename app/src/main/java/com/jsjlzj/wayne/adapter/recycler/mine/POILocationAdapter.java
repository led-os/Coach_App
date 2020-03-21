package com.jsjlzj.wayne.adapter.recycler.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.address.LocationBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: POILocationAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/11/13 13:30
 */
public class POILocationAdapter extends RecyclerView.Adapter<POILocationAdapter.ViewHolder> {

    private Context context;
    private List<LocationBean> list;

    public POILocationAdapter(Context context, List<LocationBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_poi_location, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(position);
    }

    public void setList(List<LocationBean> list) {
        if (this.list != null) {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_cpy_name)
        TextView tvCpyName;
        @BindView(R.id.tv_location)
        TextView tvLocation;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            LocationBean bean = list.get(pos);
            tvCpyName.setText(bean.getCity());
            tvLocation.setText(bean.getAddress());
            itemView.setOnClickListener(v -> {
                if(listener != null){
                    listener.onLocationClick(bean);
                }
            });
        }
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener{

        void onLocationClick(LocationBean bean);
    }
}
