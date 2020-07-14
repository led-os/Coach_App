package com.jsjlzj.wayne.adapter.recycler.wiki;

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
 * @ClassName: WikiMenuAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/7/14 22:41
 */
public class WikiMenuAdapter extends RecyclerView.Adapter<WikiMenuAdapter.ViewHolder> {



    private Context context;
    private List<String> list = new ArrayList<>();
    private int selPos;

    public WikiMenuAdapter(Context context, List<String> list) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_wiki_menu, parent, false);
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
        @BindView(R.id.tv_name)
        TextView tvName;
        String name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            name = list.get(pos);
            tvName.setText(name);
            if(selPos == pos){
                tvName.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_solid_f1404b_15));
                tvName.setTextColor(ContextCompat.getColor(context,R.color.white));
            }else {
                tvName.setBackground(null);
                tvName.setTextColor(ContextCompat.getColor(context,R.color.color_333333));
            }

            itemView.setOnClickListener(v -> {
                if(listener != null){
                    selPos = pos;
                    notifyDataSetChanged();
                    listener.onItemClick(name);
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
