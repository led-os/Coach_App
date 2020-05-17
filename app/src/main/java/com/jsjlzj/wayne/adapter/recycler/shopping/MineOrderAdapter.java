package com.jsjlzj.wayne.adapter.recycler.shopping;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.shopping.MineOrderBean;
import com.jsjlzj.wayne.widgets.TimeCounter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: MineOrderAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/17 14:10
 */
public class MineOrderAdapter extends RecyclerView.Adapter<MineOrderAdapter.ViewHolder> {

    private Context context;
    private List<MineOrderBean> list = new ArrayList<>();
    /**
     * "0,待支付；1,待发货；2，待收货；3，待评价；4，售后"
     */
    private int type;
    private SparseArray<TimeCounter> countDownMap = new SparseArray<>();

    public MineOrderAdapter(Context context, List<MineOrderBean> list, int type) {
        this.context = context;
        this.type = type;
        this.list.addAll(list);
    }


    /**
     * 清空资源
     */
    public void cancelAllTimers() {
        if (countDownMap == null) {
            return;
        }

        for (int i = 0, length = countDownMap.size(); i < length; i++) {
            CountDownTimer cdt = countDownMap.get(countDownMap.keyAt(i));
            if (cdt != null) {
                cdt.onFinish();
                cdt.cancel();
            }
        }
    }

    public void setData(List<MineOrderBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mine_order, parent, false);
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

        @BindView(R.id.tv_order_code)
        TextView tvOrderCode;
        @BindView(R.id.tv_order_state)
        TextView tvOrderState;
        @BindView(R.id.rv_shopping)
        RecyclerView rvShopping;
        @BindView(R.id.tv_retreat)
        TextView tvRetreat;
        @BindView(R.id.tv_all_money)
        TextView tvAllMoney;
        @BindView(R.id.tv_right_click)
        TextView tvRightClick;
        @BindView(R.id.tv_scan)
        TextView tvScan;
        TimeCounter mTimeCounter;
        MineOrderBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            tvOrderCode.setText("订单编号："+bean.getOrderCode());
            switch (bean.getShowStatus()){
                case 0://待支付
                    tvScan.setVisibility(View.GONE);
                    tvOrderState.setText("付款 14:59");
                    if (bean.getCreateTime() > 0) {
                        long payOverTime = bean.getCreateTime()+15*60*1000;
                        if (payOverTime < System.currentTimeMillis()) {
                            tvRightClick.setText(context.getResources().getString(R.string.again_buy));
                            tvOrderState.setText("已取消");
                        } else {
                            if (countDownMap.get(bean.hashCode()) == null) {
                                mTimeCounter = new TimeCounter(payOverTime, 1000, tvRightClick, R.string.again_buy, 2, () -> {
                                    if(listener != null){
                                        tvRightClick.setText(context.getResources().getString(R.string.again_buy));
                                        tvOrderState.setText("已取消");
                                    }
                                });
                                mTimeCounter.start();
                                countDownMap.put(bean.hashCode(), mTimeCounter);
                            } else {
                                if(mTimeCounter != null){
                                    mTimeCounter.setJoinTime(System.currentTimeMillis(),2);
                                    mTimeCounter.start();
                                }
                            }
                        }
                    } else {
                        tvRightClick.setVisibility(View.GONE);
                    }
                    break;
                case 1://已取消
                    tvScan.setVisibility(View.GONE);
                    tvOrderState.setText("已取消");
                    break;
                case 2://待发货
                    tvScan.setVisibility(View.GONE);
                    tvOrderState.setText("待发货");
                    tvRightClick.setText("提醒发货");
                    break;
                case 3://待收货
                    tvScan.setVisibility(View.VISIBLE);
                    tvOrderState.setText("待收货");
                    tvRightClick.setText("确认收货");
                    tvScan.setText("查看物流");
                    break;
                case 4://待评价
                    tvRightClick.setText(context.getResources().getString(R.string.again_buy));
                    tvScan.setVisibility(View.GONE);
                    tvOrderState.setText("待评价");
                    break;
                case 5://已完成
                case 6://交易关闭
                    tvScan.setVisibility(View.GONE);
                    tvOrderState.setText("交易成功");
                    tvRightClick.setText(context.getResources().getString(R.string.again_buy));
                    break;
            }

            rvShopping.setLayoutManager(new LinearLayoutManager(context));
            ShoppingCarAdapter shoppingCarAdapter = new ShoppingCarAdapter(context,new ArrayList<>(),2);
            rvShopping.setAdapter(shoppingCarAdapter);
            tvRightClick.setOnClickListener(v -> {
                if(listener != null){
                    listener.onRightClick(bean);
                }
            });
            tvScan.setOnClickListener(v -> {
                if(listener != null){
                    listener.onLeftClick(bean);
                }
            });

        }
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {

        void onLeftClick(MineOrderBean bean);

        void onRightClick(MineOrderBean bean);

    }
}
