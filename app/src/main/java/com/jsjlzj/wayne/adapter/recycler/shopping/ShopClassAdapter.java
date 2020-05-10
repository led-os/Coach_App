package com.jsjlzj.wayne.adapter.recycler.shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: ShopClassAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/4/25 13:07
 */
public class ShopClassAdapter extends RecyclerView.Adapter<ShopClassAdapter.ViewHolder> {


    private Context context;
    private List<String> list = new ArrayList<>();
    private int selectIndex = 0;

    public ShopClassAdapter(Context context, List<String> list) {
        this.context = context;
        this.list.addAll(list);
    }


    public void setData(List<String> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shop_class, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_des)
        TextView tvDes;
        String categoryBean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
//            categoryBean = list.get(pos);
            if (pos == selectIndex) {
                tvTitle.setTextColor(ContextCompat.getColor(context,R.color.color_f1404b));
                tvDes.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_solid_class_shop));
                tvDes.setTextColor(ContextCompat.getColor(context,R.color.white));
            }else {
                tvTitle.setTextColor(ContextCompat.getColor(context,R.color.color_999999));
                tvDes.setBackground(null);
                tvDes.setTextColor(ContextCompat.getColor(context,R.color.color_999999));
            }
            itemView.setOnClickListener(v -> {
                selectIndex = pos;
                notifyDataSetChanged();
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

        void onItemClick(String bean);
    }
}
