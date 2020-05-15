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

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.shopping.ShoppingCarBean;
import com.jsjlzj.wayne.utils.DateUtil;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: ShoppingCarAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/11/19 15:23
 */
public class ShoppingCarAdapter extends RecyclerView.Adapter<ShoppingCarAdapter.ViewHolder> {


    private Context context;
    private List<ShoppingCarBean.DataBean.ListResultsBean> list;
    /**
     * 0:购物车界面  1 ：确认订单界面
     */
    private int type;

    public ShoppingCarAdapter(Context context, List<ShoppingCarBean.DataBean.ListResultsBean> list,int type) {
        this.context = context;
        this.list = list;
        this.type = type;
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

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.img_shopping)
        ImageView imgShopping;
        @BindView(R.id.img_select)
        ImageView imgSelect;
        @BindView(R.id.ll_select)
        LinearLayout llSelect;
        @BindView(R.id.tv_name)
        TextView tvName;
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
//        @BindView(R.id.ll_skill_second)
//        LinearLayout llSkillSecond;
        ShoppingCarBean.DataBean.ListResultsBean bean;
        int position;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            this.position = pos;
            tvName.setText(bean.getProductName());
            tvPrice.setText("¥ " + DateUtil.getTwoDotByFloat(bean.getPrice()));
            if (bean.getBuyNum() > 0) {
                imgDelete.setVisibility(View.VISIBLE);
                tvNum.setVisibility(View.VISIBLE);
                tvNum.setText("" + bean.getBuyNum());
            } else {
                imgDelete.setVisibility(View.GONE);
                tvNum.setVisibility(View.GONE);
            }
            GlidUtils.setGrid(context, bean.getProductUrl(), imgShopping);
            if(type == 1){
                llSelect.setVisibility(View.GONE);
                imgAdd.setVisibility(View.GONE);
                imgDelete.setVisibility(View.GONE);
                tvNum.setVisibility(View.GONE);
                tvShopCarNum.setVisibility(View.VISIBLE);
                tvShopCarNum.setText("x"+bean.getBuyNum());
            }else {
                llSelect.setVisibility(View.VISIBLE);
                imgAdd.setVisibility(View.VISIBLE);
                imgDelete.setVisibility(View.VISIBLE);
                tvNum.setVisibility(View.VISIBLE);
                tvShopCarNum.setVisibility(View.GONE);
            }
            if (bean.isSelect()) {
                imgSelect.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.cbx_select));
            } else {
                imgSelect.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.cbx_unselect));
            }
            imgAdd.setOnClickListener(this);
            imgDelete.setOnClickListener(this);
            itemView.setOnClickListener(this);
            llSelect.setOnClickListener(this);
            itemView.setOnLongClickListener(v -> {
                if (listener != null) {
                    listener.onDeleteItem(bean, pos);
                }
                return false;
            });
        }

        @Override
        public void onClick(View v) {
            if (listener == null) {
                return;
            }
            switch (v.getId()) {
                case R.id.ll_select:
                    if (bean.isSelect()) {
                        bean.setSelect(false);
                        imgSelect.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.cbx_unselect));
                    } else {
                        bean.setSelect(true);
                        imgSelect.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.cbx_select));
                    }
                    listener.onSelectClick();
                    break;
                case R.id.tv_type:
                    listener.onTypeClick(bean);
                    break;
                case R.id.img_add:
                    if(bean.getBuyNum() >= 0){
                        imgDelete.setVisibility(View.VISIBLE);
                        tvNum.setVisibility(View.VISIBLE);
                        bean.setBuyNum(bean.getBuyNum() +1);
                        notifyDataSetChanged();
                    }
                    listener.onAddClick(bean);
                    break;
                case R.id.img_delete:
                    if(bean.getBuyNum() >= 1){
                        if(bean.getBuyNum() == 1){
                            imgDelete.setVisibility(View.GONE);
                            tvNum.setVisibility(View.GONE);
                        }
                        bean.setBuyNum(bean.getBuyNum() -1);
                        notifyDataSetChanged();
                    }
                    listener.onDeleteClick(bean);
                    break;
                default:
                    break;
            }
        }
    }

    public void setData(List<ShoppingCarBean.DataBean.ListResultsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public void setSelectData(boolean isAllSelect) {
        for (ShoppingCarBean.DataBean.ListResultsBean bean : list) {
            bean.setSelect(isAllSelect);
        }
        notifyDataSetChanged();
    }

    /**
     * 设置需要购买的商品
     *
     * @param selectData 需要购买的商品列表
     */
    public void setSelectData(List<ShoppingCarBean.DataBean.ListResultsBean> selectData) {
        for (ShoppingCarBean.DataBean.ListResultsBean bean : list) {
            for (ShoppingCarBean.DataBean.ListResultsBean selectBean : selectData) {
                if (selectBean.getProductId()==(bean.getProductId())) {
                    bean.setBuyNum(selectBean.getBuyNum());
                }
            }
        }
        notifyDataSetChanged();
    }


    public List<ShoppingCarBean.DataBean.ListResultsBean> getSelectList() {
        List<ShoppingCarBean.DataBean.ListResultsBean> selectList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (ShoppingCarBean.DataBean.ListResultsBean bean : list) {
                if (bean.isSelect()) {
                    selectList.add(bean);
                }
            }
        }
        return selectList;
    }

    /**
     * 获取需要购买的商品列表
     * @return 需要购买的商品列表
     */
    public List<ShoppingCarBean.DataBean.ListResultsBean> getShoppingList() {
        List<ShoppingCarBean.DataBean.ListResultsBean> shoppingList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (ShoppingCarBean.DataBean.ListResultsBean bean : list) {
                if (bean.getBuyNum() > 0) {
                    shoppingList.add(bean);
                }
            }
        }
        return shoppingList;
    }

    /**
     * 清空选择的商品
     */
    public void clearSelect() {
        for (ShoppingCarBean.DataBean.ListResultsBean bean : list) {
            if (bean.getBuyNum() > 0) {
                bean.setBuyNum(0);
            }
        }
        notifyDataSetChanged();
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        /**
         * 商品数加1
         *
         * @param bean 实体类
         */
        void onAddClick(ShoppingCarBean.DataBean.ListResultsBean bean);

        /**
         * 商品数减1
         *
         * @param bean 实体类
         */
        void onDeleteClick(ShoppingCarBean.DataBean.ListResultsBean bean);

        /**
         * 条目点击
         *
         * @param bean 实体类
         */
        void onItemClick(ShoppingCarBean bean);

        /**
         * 点击选择规格
         *
         * @param bean 实体类
         */
        void onTypeClick(ShoppingCarBean.DataBean.ListResultsBean bean);

        /**
         * 点击选择
         */
        void onSelectClick();

        /**
         * 删除
         */
        void onDeleteItem(ShoppingCarBean.DataBean.ListResultsBean bean, int pos);
    }
}
