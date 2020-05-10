package com.jsjlzj.wayne.adapter.recycler.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.find.CurrencyDetailPageBean;
import com.jsjlzj.wayne.entity.find.JiFenPageBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: CurrencyDetailAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/9 17:00
 */
public class CurrencyDetailAdapter extends RecyclerView.Adapter<CurrencyDetailAdapter.ViewHolder> {


    private Context context;
    private List<CurrencyDetailPageBean.DataBean.ResultBean> list = new ArrayList<>();

    public CurrencyDetailAdapter(Context context, List<CurrencyDetailPageBean.DataBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(List<CurrencyDetailPageBean.DataBean.ResultBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_integral_detail, parent, false);
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

        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_num)
        TextView tvNum;
        private CurrencyDetailPageBean.DataBean.ResultBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            switch (bean.getType()) {
                case 1:
                    tvState.setText("苹果内购充值");
                    break;
                case 3:
                    tvState.setText("微信充值 ");
                    break;
                case 10:
                    tvState.setText("购买课程");
                    break;
                case 2:
                    tvState.setText("支付宝充值");
                    break;
            }
            tvTime.setText(bean.getCreateTime());
            tvNum.setText(bean.getAmount()+"币");

        }

    }


    public interface OnItemClickListener {
        void onItemClick(JiFenPageBean.DataBean.ResultBean bean);
    }

    private IntegralAdapter.OnItemClickListener listener;

    public void setListener(IntegralAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}
