package com.jsjlzj.wayne.adapter.recycler.shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.shopping.LogisticsBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: LogisticsAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/28 15:21
 */
public class LogisticsAdapter extends RecyclerView.Adapter<LogisticsAdapter.ViewHolder> {



    private Context context;
    private List<LogisticsBean.DataBean.LogisticsDetail> list = new ArrayList<>();

    public LogisticsAdapter(Context context, List<LogisticsBean.DataBean.LogisticsDetail> list) {
        this.context = context;
        this.list.addAll(list);
    }


    public void setData(List<LogisticsBean.DataBean.LogisticsDetail> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_logistics, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_location_des)
        TextView tvLocationDes;
        @BindView(R.id.tv_time)
        TextView tvTime;
        LogisticsBean.DataBean.LogisticsDetail logisticsBean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            logisticsBean = list.get(pos);
            tvLocationDes.setText(logisticsBean.getExInfo());
            tvTime.setText(logisticsBean.getCreateTime());
        }
    }

}
