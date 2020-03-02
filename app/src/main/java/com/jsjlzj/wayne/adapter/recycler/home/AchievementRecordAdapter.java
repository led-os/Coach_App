package com.jsjlzj.wayne.adapter.recycler.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.store.learn.AnswerRecordBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: AchievementRecordAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/14 16:20
 */
public class AchievementRecordAdapter extends RecyclerView.Adapter<AchievementRecordAdapter.ViewHolder> {


    private Context context;
    private List<AnswerRecordBean.DataBean> list = new ArrayList<>();

    public AchievementRecordAdapter(Context context, List<AnswerRecordBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(List<AnswerRecordBean.DataBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_achievement_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_fraction)
        TextView tvFraction;
        @BindView(R.id.tv_number)
        TextView tvNumber;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.rel_achievement)
        RelativeLayout relAchievement;

        private AnswerRecordBean.DataBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            tvFraction.setText(""+bean.getScore()+"分");
            tvTime.setText(bean.getStartTime());
            tvNumber.setText("第"+bean.getTimes()+"次");
            if(bean.getScore() > 90){
                relAchievement.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_solid_c67879_5));
            }else if(bean.getScore() > 80){
                relAchievement.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_solid_7776cf_5));
            }else if(bean.getScore() > 70){
                relAchievement.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_solid_9e9dde_5));
            }else if(bean.getScore() > 60){
                relAchievement.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_solid_5e9f95_5));
            }else {
                relAchievement.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_solid_e7c481_5));
            }
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(bean);
                }

            });
        }
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {

        void onItemClick(AnswerRecordBean.DataBean str);
    }
}
