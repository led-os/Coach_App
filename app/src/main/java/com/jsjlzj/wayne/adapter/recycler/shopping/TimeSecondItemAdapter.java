package com.jsjlzj.wayne.adapter.recycler.shopping;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.shopping.ShoppingBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingCarBean;
import com.jsjlzj.wayne.utils.DateUtil;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: TimeSecondItemAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/4/25 22:38
 */
public class TimeSecondItemAdapter extends RecyclerView.Adapter<TimeSecondItemAdapter.ViewHolder> {


    private Context context;
    private List<ShoppingBean> list = new ArrayList<>();
    private int type;

    public TimeSecondItemAdapter(Context context, int type, List<ShoppingBean> list) {
        this.context = context;
        this.type = type;
        this.list.addAll(list);
    }


    public void setData(List<ShoppingBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_time_second_item, parent, false);
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

        @BindView(R.id.img_shop)
        ImageView imgShop;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_des)
        TextView tvDes;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_old_money)
        TextView tvOldMoney;
        @BindView(R.id.tv_rob)
        TextView tvRob;
        ShoppingBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            GlidUtils.setGrid(context,bean.getPic(),imgShop);
            tvTitle.setText(bean.getName());
            tvDes.setText(bean.getSdate());
            tvMoney.setText(context.getResources().getString(R.string.chinese_money)+ DateUtil.getTwoDotByFloat(bean.getFlashPromotionPrice()));
            tvOldMoney.setText(context.getResources().getString(R.string.chinese_money)+DateUtil.getTwoDotByFloat(bean.getPrice()));
            if(type == 0){
                tvRob.setText("立即抢");
                tvRob.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_rob_shop));
            }else {
                tvRob.setText("开抢提醒");
                tvRob.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_will_start_shop));
            }
            tvOldMoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//中划线
            tvOldMoney.getPaint().setAntiAlias(true); //去掉锯齿
            tvRob.setOnClickListener(v -> {
                if(listener != null){
                    listener.onRobClick(type,bean);
                }
            });
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

        void onItemClick(ShoppingBean bean);

        void onRobClick(int type,ShoppingBean bean);
    }
}
