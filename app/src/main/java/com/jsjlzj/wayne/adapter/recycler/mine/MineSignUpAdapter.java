package com.jsjlzj.wayne.adapter.recycler.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.trainer.SignUpPageBean;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: MineSignUpAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/19 21:32
 */
public class MineSignUpAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_MATCH = 1;
    public static final int TYPE_COURSE = 2;


    private List<SignUpPageBean.DataBean.ResultBean> list = new ArrayList<>();
    private Context context;
    /**
     * 0 :全部显示  1 ：只显示赛事   2 ：只显示课程
     */
    private int showType;

    public MineSignUpAdapter(Context context, List<SignUpPageBean.DataBean.ResultBean> list) {
        this.list.addAll(list);
        this.context = context;
    }

    public void setData(List<SignUpPageBean.DataBean.ResultBean> list) {
        if (list != null && list.size() > 0) {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void setShowType(int showType) {
        this.showType = showType;
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        SignUpPageBean.DataBean.ResultBean bean = list.get(position);
        if ("sport_event".equals(bean.getModule())) {
            return TYPE_MATCH;
        } else {
            return TYPE_COURSE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == TYPE_MATCH) {
            view = LayoutInflater.from(context).inflate(R.layout.item_sign_up_match, parent, false);
            return new MatchViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_sign_up_course, parent, false);
            return new CourseViewHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MatchViewHolder) {
            ((MatchViewHolder) holder).bindView(position);
        } else if (holder instanceof CourseViewHolder) {
            ((CourseViewHolder) holder).bindView(position);
        }

    }

    @Override
    public int getItemCount() {
        return getCountByShowType(showType);
    }


    private int getCountByShowType(int showType) {
        if (showType == 0) {
            return list != null ? list.size() : 0;
        } else if (showType == 1) {
            int size = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getModule().equals("sport_event")) {
                    size++;
                }
            }
            return size;
        } else {
            int size = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getModule().equals("tao_learn")) {
                    size++;
                }
            }
            return size;
        }
    }

    class MatchViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_time_title)
        TextView tvTimeTitle;
        @BindView(R.id.img_one)
        ImageView imgOne;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_time_des)
        TextView tvTimeDes;
        @BindView(R.id.tv_add_number)
        TextView tvAddNumber;


        public MatchViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            SignUpPageBean.DataBean.ResultBean bean = list.get(pos);
            tvTimeTitle.setText("报名时间: " + bean.getCreateTime());
            if (bean.getSportEvent() != null) {
                GlidUtils.setRoundGrid(context, bean.getSportEvent().getCoverImg(), imgOne, 4);
                tvTitle.setText(bean.getSportEvent().getName());
                if (bean.getSportEvent().getStatus() == 1) {//未开始
                    tvState.setText("未开始");
                    tvState.setTextColor(ContextCompat.getColor(context, R.color.color_666666));
                    tvState.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_solid_999999_15));
                } else if (bean.getSportEvent().getStatus() == 2) {//进行中
                    tvState.setText("进行中");
                    tvState.setTextColor(ContextCompat.getColor(context, R.color.white));
                    tvState.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_solid_4f9bfa_15));
                } else {//已结束
                    tvState.setText("已结束");
                    tvState.setTextColor(ContextCompat.getColor(context, R.color.color_666666));
                    tvState.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_solid_999999_15));
                }
                tvTime.setText(bean.getSportEvent().getStartTime() + " 至 " + bean.getSportEvent().getEndTime());
                tvAddNumber.setText(bean.getSportEvent().getEnrollCount());
            }
            itemView.setOnClickListener(v -> {

                WebViewContainerActivity.go2this(context,bean.getSportEvent().getName(),HttpConstant.WEB_URL_MATCH_DETAIL+bean.getBizId(),
                        WebViewContainerFragment.TYPE_MATCH_DETAIL);
            });
        }


    }

    class CourseViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_time_title)
        TextView tvTimeTitle;
        @BindView(R.id.img_one)
        ImageView imgOne;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_add_number)
        TextView tvAddNumber;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            SignUpPageBean.DataBean.ResultBean bean = list.get(pos);
            tvTimeTitle.setText("报名时间: " + bean.getCreateTime());
            if (bean.getTaoLearn() != null){
                GlidUtils.setRoundGrid(context,bean.getTaoLearn().getLessonImg(),imgOne,4);
                tvAddNumber.setText(bean.getTaoLearn().getEnrollCount());
                tvTitle.setText(bean.getTaoLearn().getName());
            }
            itemView.setOnClickListener(v -> {
                WebViewContainerActivity.go2this(context,bean.getTaoLearn().getName(),HttpConstant.WEB_URL_COURSE_DETAIL+bean.getBizId(),
                        WebViewContainerFragment.TYPE_COURSE_DETAIL);
            });
        }
    }

}
