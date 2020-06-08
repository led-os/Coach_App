package com.jsjlzj.wayne.adapter.recycler.shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.shopping.HomeShoppingDataBean;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: ShoppTypeAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/4/24 22:55
 */
public class ShoppTypeAdapter extends RecyclerView.Adapter<ShoppTypeAdapter.ViewHolder> {


    private Context context;
    private List<HomeShoppingDataBean.DataBean.CategoryListBean> list = new ArrayList<>();

    public ShoppTypeAdapter(Context context, List<HomeShoppingDataBean.DataBean.CategoryListBean> list) {
        this.context = context;
        this.list.addAll(list);
    }


    public void setData(List<HomeShoppingDataBean.DataBean.CategoryListBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shopp_type, parent, false);
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
        @BindView(R.id.img_shop_type)
        ImageView imgShopType;
        @BindView(R.id.tv_shop_type)
        TextView tvShopType;
        HomeShoppingDataBean.DataBean.CategoryListBean categoryBean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            categoryBean = list.get(pos);
            GlidUtils.setCircleGrid(context,categoryBean.getIcon(),imgShopType);
            tvShopType.setText(categoryBean.getName());
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(categoryBean);
                }
            });
        }
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {

        void onItemClick(HomeShoppingDataBean.DataBean.CategoryListBean bean);
    }
}
