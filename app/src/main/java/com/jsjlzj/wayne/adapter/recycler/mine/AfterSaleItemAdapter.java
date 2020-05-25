package com.jsjlzj.wayne.adapter.recycler.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.shopping.AfterSalePageBean;
import com.jsjlzj.wayne.utils.DateUtil;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: AfterSaleItemAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/25 22:46
 */
public class AfterSaleItemAdapter extends RecyclerView.Adapter<AfterSaleItemAdapter.ViewHolder> {

    private Context context;
    private List<AfterSalePageBean.DataBean.ResultBean.GetMyAftersaleOrderResponseVoBean> list = new ArrayList<>();


    public AfterSaleItemAdapter(Context context, AfterSalePageBean.DataBean.ResultBean.GetMyAftersaleOrderResponseVoBean list) {
        this.context = context;
        this.list.add(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_after_sale_item, parent, false);
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

        AfterSalePageBean.DataBean.ResultBean.GetMyAftersaleOrderResponseVoBean bean;
        int position;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {

            bean = list.get(pos);
            this.position = pos;
            tvName.setText(bean.getName());
            tvPrice.setText("退款： ¥ " + DateUtil.getTwoDotByFloatFY(bean.getAmount()));
            GlidUtils.setGrid(context, bean.getProductPic(), imgShopping);

            JSONArray array = JSON.parseArray(bean.getProductSpec());
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < array.size(); i++) {
                JSONObject jsonObject = JSONObject.parseObject(array.get(i).toString());
                String value = jsonObject.getString("value");
                stringBuilder.append(value);
                stringBuilder.append(" ");
            }

            tvAttribute.setText(stringBuilder.toString());

        }

    }

    public void setData(List<AfterSalePageBean.DataBean.ResultBean.GetMyAftersaleOrderResponseVoBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }


}
