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
    private List<ShoppingCarBean> list;


    public ShoppingCarAdapter(Context context, List<ShoppingCarBean> list) {
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
        return 8;//list != null ? list.size() : 0;
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
        ShoppingCarBean bean;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
//            bean = list.get(pos);
//            tvName.setText(bean.getProductName());
//            tvPrice.setText("" + bean.getPrdAttPrice());
//            if (bean.getCount() > 0) {
//                imgDelete.setVisibility(View.VISIBLE);
//                tvNum.setVisibility(View.VISIBLE);
//                tvNum.setText("" + bean.getCount());
//            } else {
//                imgDelete.setVisibility(View.GONE);
//                tvNum.setVisibility(View.GONE);
//            }
//            GlidUtils.setGrid(context, bean.getProductPic(), imgShopping);
//            llSelect.setVisibility(View.VISIBLE);
//            if (bean.isSelect()) {
//                imgSelect.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.cbx_select));
//            } else {
//                imgSelect.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.cbx_unselect));
//            }
//            imgAdd.setVisibility(View.VISIBLE);
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
                    listener.onAddClick(bean);
                    break;
                case R.id.img_delete:
                    listener.onDeleteClick(bean);
                    break;
                default:

                    break;
            }
        }
    }

    public void setData(List<ShoppingCarBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public void setSelectData(boolean isAllSelect) {
        for (ShoppingCarBean bean : list) {
            bean.setSelect(isAllSelect);
        }
        notifyDataSetChanged();
    }

    /**
     * 设置需要购买的商品
     *
     * @param selectData 需要购买的商品列表
     */
    public void setSelectData(List<ShoppingCarBean> selectData) {
        for (ShoppingCarBean bean : list) {
            for (ShoppingCarBean selectBean : selectData) {
                if (selectBean.getProductId().equals(bean.getProductId())) {
                    bean.setCount(selectBean.getCount());
                }
            }
        }
        notifyDataSetChanged();
    }


    public List<ShoppingCarBean> getSelectList() {
        List<ShoppingCarBean> selectList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (ShoppingCarBean bean : list) {
                if (bean.isSelect()) {
                    selectList.add(bean);
                }
            }
        }
        return selectList;
    }

    /**
     * 获取需要购买的商品列表
     *
     * @return 需要购买的商品列表
     */
    public List<ShoppingCarBean> getShoppingList() {
        List<ShoppingCarBean> shoppingList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (ShoppingCarBean bean : list) {
                if (bean.getCount() > 0) {
                    shoppingList.add(bean);
                }
            }
        }
        return shoppingList;
    }

    /**
     * 情况选择的商品
     */
    public void clearSelect() {
        for (ShoppingCarBean bean : list) {
            if (bean.getCount() > 0) {
                bean.setCount(0);
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
        void onAddClick(ShoppingCarBean bean);

        /**
         * 商品数减1
         *
         * @param bean 实体类
         */
        void onDeleteClick(ShoppingCarBean bean);

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
        void onTypeClick(ShoppingCarBean bean);

        /**
         * 点击选择
         */
        void onSelectClick();

        /**
         * 删除
         */
        void onDeleteItem(ShoppingCarBean bean, int pos);
    }
}
