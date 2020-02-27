package com.jsjlzj.wayne.adapter.recycler.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.store.home.VideoBean;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: InformationAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/8 14:17
 */
public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.ViewHolder> {


    private Context context;
    private List<VideoBean> list = new ArrayList<>();

    public InformationAdapter(Context context, List<VideoBean> list) {
        this.context = context;
        this.list.addAll(list);
    }

    public void setData(List<VideoBean> list) {
        if (list != null) {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_information, parent, false);
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
        @BindView(R.id.img_one)
        ImageView imgOne;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_number)
        TextView tvNumber;
        @BindView(R.id.tv_time)
        TextView tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            VideoBean bean = list.get(pos);
            GlidUtils.setRoundGrid(context,bean.getCoverImg(),imgOne,2);
            tvTitle.setText(bean.getName());
            tvTime.setText(bean.getCreateTime());
            tvNumber.setText(bean.getViewCount()+"次阅读");
        }
    }
}
