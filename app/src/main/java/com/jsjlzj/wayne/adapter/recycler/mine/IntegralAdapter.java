package com.jsjlzj.wayne.adapter.recycler.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.find.JiFenPageBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: IntegralAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/8 0:43
 */
public class IntegralAdapter extends RecyclerView.Adapter<IntegralAdapter.ViewHolder> {


    private Context context;
    private List<JiFenPageBean.DataBean.ResultBean> list = new ArrayList<>();

    public IntegralAdapter(Context context, List<JiFenPageBean.DataBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(List<JiFenPageBean.DataBean.ResultBean> list) {
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
        return 8;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_num)
        TextView tvNum;
        private JiFenPageBean.DataBean.ResultBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
//            bean = list.get(pos);
//            switch (bean.getType()){
//                case 1:
//                    tvState.setText("签到");
//                    break;
//                case 2:
//                    tvState.setText("抽奖");
//                    break;
//                case 3:
//                    tvState.setText("连续7天签到奖励");
//                    break;
//                case 4:
//                    tvState.setText("连续15天签到奖励");
//                    break;
//                case 5:
//                    tvState.setText("连续30天签到奖励");
//                    break;
//            }
//            tvTime.setText(bean.getSignInDate());
//            tvNum.setText(bean.getAmount());

        }

    }


    public interface OnItemClickListener {
        void onItemClick(JiFenPageBean.DataBean.ResultBean bean);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
