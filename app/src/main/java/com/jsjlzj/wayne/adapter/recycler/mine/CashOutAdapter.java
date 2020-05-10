package com.jsjlzj.wayne.adapter.recycler.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.find.CashOutPageBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: CashOutAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/6 23:21
 */
public class CashOutAdapter extends RecyclerView.Adapter {

    public static final int SHOW_TYPE_TIME_TITLE = 0;
    public static final int SHOW_TYPE_CONTENT = 1;



    private Context context;
    private List<CashOutPageBean.DataBean.ResultBean> list = new ArrayList<>();
    private HashMap<String, Boolean> hashMap = new HashMap<>();

    public CashOutAdapter(Context context, List<CashOutPageBean.DataBean.ResultBean> list) {
        this.context = context;
        initData(list);
    }

    public void setData(List<CashOutPageBean.DataBean.ResultBean> list) {
        initData(list);
        notifyDataSetChanged();
    }

    private List<CashOutPageBean.DataBean.ResultBean> initData(List<CashOutPageBean.DataBean.ResultBean> data) {
        hashMap.clear();
        if (data == null || data.size() <= 0) {
            this.list = new ArrayList<>();
        } else {
            for (int i = 0; i < data.size(); i++) {
                CashOutPageBean.DataBean.ResultBean bean = data.get(i);
                if(bean.getCreateTime().length() > 8){
                    String time = bean.getCreateTime().substring(0,8);
                    if (hashMap.containsKey(time)) {
                        this.list.add(bean);
                    } else {
                        CashOutPageBean.DataBean.ResultBean timeBean = new CashOutPageBean.DataBean.ResultBean(bean.getCreateTime(), true);
                        hashMap.put(time, true);
                        this.list.add(timeBean);
                        this.list.add(bean);
                    }
                }
            }
        }
        return this.list;
    }


    @Override
    public int getItemViewType(int position) {
        CashOutPageBean.DataBean.ResultBean bean = list.get(position);
        if (bean.isTitle()) {
            return SHOW_TYPE_TIME_TITLE;
        } else {
            return SHOW_TYPE_CONTENT;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == SHOW_TYPE_CONTENT) {
            view = LayoutInflater.from(context).inflate(R.layout.item_time_title, parent, false);
            return new TimeViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_cash_out_content, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TimeViewHolder) {
            ((TimeViewHolder) holder).tvTime.setText(list.get(position).getCreateTime());
        } else if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).bindView(position);
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class TimeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_time)
        TextView tvTime;

        public TimeViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.tv_money)
        TextView tvMoney;

        private CashOutPageBean.DataBean.ResultBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            tvTime.setText(bean.getCreateTime());
            if(bean.getStatus() == 1){
                tvState.setText("审核中");
            }else if(bean.getStatus() == 2){
                tvState.setText("审核通过");
            }else {
                tvState.setText("审核驳回");
            }
            tvMoney.setText(context.getResources().getString(R.string.chinese_money)+bean.getAmount());

        }


    }

}
