package com.jsjlzj.wayne.adapter.recycler.shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * @ClassName: NewHotProductAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/4/24 23:52
 */
public class NewHotProductAdapter extends RecyclerView.Adapter<NewHotProductAdapter.ViewHolder> {

    private Context context;
    private List<String> list = new ArrayList<>();

    public NewHotProductAdapter(Context context, List<String> list) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_new_hot_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        String categoryBean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
//            categoryBean = list.get(pos);

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

        void onItemClick(String bean);
    }
}
