package com.jsjlzj.wayne.adapter.recycler.shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.shopping.ShoppingBean;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.utils.DateUtil;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: NewHotProductAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/4/24 23:52
 */
public class NewHotProductAdapter extends RecyclerView.Adapter<NewHotProductAdapter.ViewHolder> {


    private Context context;
    private List<ShoppingBean> list = new ArrayList<>();

    public NewHotProductAdapter(Context context, List<ShoppingBean> list) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_new_hot_product, parent, false);
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
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.img_plus)
        ImageView imgPlus;
        @BindView(R.id.tv_old_money)
        TextView tvOldMoney;
        ShoppingBean shoppingBean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            shoppingBean = list.get(pos);
            GlidUtils.setGrid(context,shoppingBean.getPic(),imgProduct);
            tvMoney.setText(context.getResources().getString(R.string.chinese_money)+ DateUtil.getTwoDotByFloat(shoppingBean.getPrice()));
            tvOldMoney.setText(context.getResources().getString(R.string.chinese_money)+DateUtil.getTwoDotByFloat(shoppingBean.getOriginalPrice()));
            itemView.setOnClickListener(v -> {
                WebViewContainerActivity.go2this(context,context.getResources().getString(R.string.shopping_detail), HttpConstant.WEB_URL_NEW_SHOPPING_DETAIL+shoppingBean.getId(),
                        WebViewContainerFragment.TYPE_NEW_SHOPPING_DETAIL);
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
