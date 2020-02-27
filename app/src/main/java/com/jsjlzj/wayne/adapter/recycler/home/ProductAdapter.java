package com.jsjlzj.wayne.adapter.recycler.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.store.home.CategoryBean;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: ProductAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/8 16:48
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {


    private Context context;
    private List<CategoryBean> list = new ArrayList<>();

    public ProductAdapter(Context context, List<CategoryBean> list) {
        this.context = context;
        this.list.addAll(list);
    }

    public void setData(List<CategoryBean> list) {
        if (list != null) {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
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
        @BindView(R.id.img_one)
        ImageView imgOne;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_location)
        TextView tvLocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            CategoryBean bean = list.get(pos);
            GlidUtils.setRoundGrid(context,bean.getCoverImg(),imgOne,2);
            tvTitle.setText(bean.getName());
            tvPrice.setText(bean.getPrice());
            tvLocation.setText(bean.getPlace());
            itemView.setOnClickListener(v -> {
                if(listener!= null){
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
        void onItemClick(CategoryBean bean);
    }
}
