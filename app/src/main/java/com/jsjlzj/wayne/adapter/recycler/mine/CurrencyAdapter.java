package com.jsjlzj.wayne.adapter.recycler.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.find.CurrencyBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: CurrencyAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/8 23:17
 */
public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder> {


    private Context context;
    private List<CurrencyBean.DataBean.ProductListBean> list = new ArrayList<>();
    private int isSelectPos;


    public CurrencyBean.DataBean.ProductListBean getSelectMoney(){
        return list.get(isSelectPos);
    }

    public CurrencyAdapter(Context context, List<CurrencyBean.DataBean.ProductListBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(List<CurrencyBean.DataBean.ProductListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_currency, parent, false);
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

        @BindView(R.id.tv_currency)
        TextView tvCurrency;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.ll_bg)
        LinearLayout llBg;

        private CurrencyBean.DataBean.ProductListBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            tvCurrency.setText(bean.getName()+"币");
            tvMoney.setText(context.getResources().getString(R.string.chinese_money)+bean.getPrice());
            if(pos == isSelectPos){
                llBg.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_stroke_f1404b_solid_fff8f8_4));
            }else {
                llBg.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_stroke_dddddd_4));
            }

            llBg.setOnClickListener(v -> {
                isSelectPos = pos;
                notifyDataSetChanged();
            });
        }

    }


    public interface OnItemClickListener {
        void onItemClick(String bean);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
