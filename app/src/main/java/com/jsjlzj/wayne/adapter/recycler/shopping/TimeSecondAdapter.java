package com.jsjlzj.wayne.adapter.recycler.shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: TimeSecondAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/4/25 22:03
 */
public class TimeSecondAdapter extends RecyclerView.Adapter<TimeSecondAdapter.ViewHolder> {

    private Context context;
    private List<String> list = new ArrayList<>();
    private int type;

    public TimeSecondAdapter(Context context, int type, List<String> list) {
        this.context = context;
        this.type = type;
        this.list.addAll(list);
    }


    public void setData(List<String> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_time_second, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_start_time)
        TextView tvStartTime;
        @BindView(R.id.tv_h)
        TextView tvH;
        @BindView(R.id.tv_m)
        TextView tvM;
        @BindView(R.id.tv_s)
        TextView tvS;
        @BindView(R.id.rel_time)
        RelativeLayout relTime;
        @BindView(R.id.rv_data)
        RecyclerView rvData;
        String categoryBean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
//            categoryBean = list.get(pos);

            relTime.setVisibility(type == 0 ? View.VISIBLE : View.GONE);
            TimeSecondItemAdapter adapter = new TimeSecondItemAdapter(context,type,new ArrayList<>());
            rvData.setLayoutManager(new LinearLayoutManager(context));
            rvData.setAdapter(adapter);
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(categoryBean);
                }

            });
        }
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {

        void onItemClick(String bean);
    }
}
