package com.jsjlzj.wayne.adapter.recycler.shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.shopping.ShoppingBean;
import com.jsjlzj.wayne.utils.DateUtil;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: ProductAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/4/25 0:18
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {


    private Context context;
    private List<ShoppingBean> list = new ArrayList<>();

    public ProductAdapter(Context context, List<ShoppingBean> list) {
        this.context = context;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_shop, parent, false);
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
        @BindView(R.id.img_product)
        ImageView imgProduct;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.img_plus)
        ImageView imgPlus;
        @BindView(R.id.tv_old_money)
        TextView tvOldMoney;

        ShoppingBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            GlidUtils.setGrid(context,bean.getPic(),imgProduct);
            tvTitle.setText(bean.getName());
            tvMoney.setText(context.getResources().getString(R.string.chinese_money)+ DateUtil.getTwoDotByFloat(bean.getPrice()));
            tvOldMoney.setText(context.getResources().getString(R.string.chinese_money)+DateUtil.getTwoDotByFloat(bean.getOriginalPrice()));
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
    }
}
