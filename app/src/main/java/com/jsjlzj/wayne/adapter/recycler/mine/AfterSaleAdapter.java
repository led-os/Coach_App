package com.jsjlzj.wayne.adapter.recycler.mine;

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
import com.jsjlzj.wayne.adapter.recycler.shopping.MineOrderAdapter;
import com.jsjlzj.wayne.adapter.recycler.shopping.OrderDetailAdapter;
import com.jsjlzj.wayne.entity.shopping.AfterSalePageBean;
import com.jsjlzj.wayne.entity.shopping.MineOrderPageBean;
import com.jsjlzj.wayne.utils.DateUtil;
import com.jsjlzj.wayne.widgets.TimeCounter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: AfterSaleAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/25 22:31
 */
public class AfterSaleAdapter extends RecyclerView.Adapter<AfterSaleAdapter.ViewHolder> {

    private Context context;
    private List<AfterSalePageBean.DataBean.ResultBean> list = new ArrayList<>();

    private SparseArray<TimeCounter> countDownMap = new SparseArray<>();

    public AfterSaleAdapter(Context context, List<AfterSalePageBean.DataBean.ResultBean> list) {
        this.context = context;
        this.list.addAll(list);
    }


    public void setData(List<AfterSalePageBean.DataBean.ResultBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_after_sale, parent, false);
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
        @BindView(R.id.tv_right_click)
        TextView tvRightClick;
        @BindView(R.id.tv_scan)
        TextView tvScan;
        TimeCounter mTimeCounter;
        AfterSalePageBean.DataBean.ResultBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            tvOrderCode.setText("订单编号："+bean.getOrderCode());
            rvShopping.setLayoutManager(new LinearLayoutManager(context));
            AfterSaleItemAdapter shoppingCarAdapter = new AfterSaleItemAdapter(context,bean.getGetMyAftersaleOrderResponseVo());
            rvShopping.setAdapter(shoppingCarAdapter);
            if(bean.getStatus() == 9){//退款失败
                tvRetreat.setText("退货失败");
                tvScan.setVisibility(View.VISIBLE);
                tvScan.setText("联系客服");
            }else if(bean.getStatus() == 1){
                tvRetreat.setText("退货取消");
                tvScan.setVisibility(View.GONE);
            }else if(bean.getStatus() == 12){
                tvScan.setVisibility(View.GONE);
                tvRetreat.setText("退款成功");
            }else {
                tvScan.setVisibility(View.VISIBLE);
                tvScan.setText("撤销申请");
                tvRetreat.setText("退款审核中");
            }
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
            rvShopping.setOnClickListener(view -> {
                if(listener != null){
                    listener.onItemCLick(bean);
                }
            });
            itemView.setOnClickListener(v -> {
                if(listener != null){
                    listener.onItemCLick(bean);
                }
            });
        }
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {

        void onLeftClick(AfterSalePageBean.DataBean.ResultBean bean);

        void onRightClick(AfterSalePageBean.DataBean.ResultBean bean);

        void onItemCLick(AfterSalePageBean.DataBean.ResultBean bean);
    }
}
