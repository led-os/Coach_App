package com.jsjlzj.wayne.adapter.recycler.shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.shopping.MineOrderPageBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingCarBean;
import com.jsjlzj.wayne.ui.store.home.mine.AfterSaleApplyActivity;
import com.jsjlzj.wayne.ui.store.shopping.ShoppingEvaluateActivity;
import com.jsjlzj.wayne.utils.DateUtil;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: OrderDetailAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/22 0:11
 */
public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {

    private Context context;
    private List<MineOrderPageBean.DataBean.ResultBean.ListBean> list;


    public OrderDetailAdapter(Context context, List<MineOrderPageBean.DataBean.ResultBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_canteen_receiver, parent, false);
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

        @BindView(R.id.img_shopping)
        ImageView imgShopping;
        @BindView(R.id.img_select)
        ImageView imgSelect;
        @BindView(R.id.ll_select)
        LinearLayout llSelect;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_evaluate)
        TextView tvEvaluate;
        @BindView(R.id.tv_sale_after)
        TextView tvSaleAfter;
        @BindView(R.id.tv_attribute)
        TextView tvAttribute;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.img_add)
        ImageView imgAdd;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.img_delete)
        ImageView imgDelete;
        @BindView(R.id.tv_h)
        TextView tvH;
        @BindView(R.id.tv_m)
        TextView tvM;
        @BindView(R.id.tv_s)
        TextView tvS;
        @BindView(R.id.tv_shop_car_num)
        TextView tvShopCarNum;

        MineOrderPageBean.DataBean.ResultBean.ListBean bean;
        int position;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            this.position = pos;
            tvName.setText(bean.getName());
            tvPrice.setText("¥ " + DateUtil.getTwoDotByFloat(bean.getProductPrice()));
            GlidUtils.setGrid(context, bean.getProductPic(), imgShopping);
            llSelect.setVisibility(View.GONE);
            imgAdd.setVisibility(View.GONE);
            imgDelete.setVisibility(View.GONE);
            tvNum.setVisibility(View.GONE);
            tvShopCarNum.setVisibility(View.VISIBLE);
            JSONArray array = JSON.parseArray(bean.getProductSpec());
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < array.size(); i++) {
                JSONObject jsonObject = JSONObject.parseObject(array.get(i).toString());
                String value = jsonObject.getString("value");
                stringBuilder.append(value);
                stringBuilder.append(" ");
            }
            if(bean.getIsEva() == 0 && (bean.getShowStatus() == 3 || bean.getShowStatus() == 5)){//待评价
                tvEvaluate.setVisibility(View.VISIBLE);
            }else {
                tvEvaluate.setVisibility(View.GONE);
            }
            if(bean.getShowStatus() == 5){
                tvSaleAfter.setVisibility(View.VISIBLE);
            }else {
                tvSaleAfter.setVisibility(View.GONE);
            }
            tvAttribute.setText(stringBuilder.toString());
            tvShopCarNum.setText("x"+bean.getProductCount());
            tvEvaluate.setOnClickListener(v -> {
                ShoppingEvaluateActivity.go2this(context,bean);
            });
            tvSaleAfter.setOnClickListener(view -> {
                AfterSaleApplyActivity.go2this(context,bean);
            });

        }

    }

    public void setData(List<MineOrderPageBean.DataBean.ResultBean.ListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {

        /**
         * 条目点击
         *
         * @param bean 实体类
         */
        void onItemClick(MineOrderPageBean.DataBean.ResultBean.ListBean bean);
    }
}
