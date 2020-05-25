package com.jsjlzj.wayne.adapter.recycler.shopping;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.shopping.ShoppingBean;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.utils.DateUtil;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: SecondSkillAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/4/24 23:08
 */
public class SecondSkillAdapter extends RecyclerView.Adapter<SecondSkillAdapter.ViewHolder> {



    private Context context;
    private List<ShoppingBean> list = new ArrayList<>();


    public SecondSkillAdapter(Context context, List<ShoppingBean> list) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_second_skill, parent, false);
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
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_old_money)
        TextView tvOldMoney;
        ShoppingBean shoppingBean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            shoppingBean = list.get(pos);
            tvOldMoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//中划线
            tvOldMoney.getPaint().setAntiAlias(true); //去掉锯齿
            GlidUtils.setGrid(context,shoppingBean.getPic(),imgShopType);
            tvMoney.setText(context.getResources().getString(R.string.chinese_money)+ DateUtil.getTwoDotByFloat(shoppingBean.getFlashPromotionPrice()));
            tvOldMoney.setText(context.getResources().getString(R.string.chinese_money)+ DateUtil.getTwoDotByFloat(shoppingBean.getPrice()));
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
