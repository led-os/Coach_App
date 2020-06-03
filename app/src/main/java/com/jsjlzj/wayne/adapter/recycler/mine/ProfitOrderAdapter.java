package com.jsjlzj.wayne.adapter.recycler.mine;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.shopping.ProfitOrderPageBean;
import com.jsjlzj.wayne.utils.GlidUtils;
import com.jsjlzj.wayne.utils.LogAndToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: ProfitOrderAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/6/4 0:46
 */
public class ProfitOrderAdapter extends RecyclerView.Adapter<ProfitOrderAdapter.ViewHolder> {


    private Context context;
    private List<ProfitOrderPageBean.DataBean.ResultBean> list = new ArrayList<>();
    private int type;

    public ProfitOrderAdapter(Context context, List<ProfitOrderPageBean.DataBean.ResultBean> list, int type) {
        this.context = context;
        this.list = list;
        this.type = type;
    }

    public void setData(List<ProfitOrderPageBean.DataBean.ResultBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_profit_order, parent, false);
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

        @BindView(R.id.img_pic)
        ImageView imgPic;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_order_money)
        TextView tvOrderMoney;
        @BindView(R.id.tv_order_code)
        TextView tvOrderCode;
        @BindView(R.id.tv_create_time)
        TextView tvCreateTime;
        @BindView(R.id.tv_end_time)
        TextView tvEndTime;
        @BindView(R.id.tv_copy)
        TextView tvCopy;
        private ProfitOrderPageBean.DataBean.ResultBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            GlidUtils.setGrid(context,bean.getImg(),imgPic);
            tvName.setText(bean.getTitle());
            tvMoney.setText("+ "+context.getResources().getString(R.string.chinese_money)+ bean.getProfitAmount());
            tvOrderMoney.setText("订单金额：" + bean.getOrderAmount());
            tvOrderCode.setText("订单号：" + bean.getOrderNo());
            tvCreateTime.setText("创建日：" + bean.getCreateTime());
            tvEndTime.setText("结算日：" + bean.getSettlementTime());
            tvCopy.setOnClickListener(v -> {
                copyInfo(bean.getOrderNo());
            });
        }



        private void copyInfo(String copyStr) {
            //获取剪贴板管理器：
            ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            // 创建普通字符型ClipData
            ClipData mClipData = ClipData.newPlainText("Label", copyStr);
            // 将ClipData内容放到系统剪贴板里。
            cm.setPrimaryClip(mClipData);
            LogAndToastUtil.toast("复制成功");
        }
    }


}
