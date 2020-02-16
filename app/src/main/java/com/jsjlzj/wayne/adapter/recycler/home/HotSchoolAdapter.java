package com.jsjlzj.wayne.adapter.recycler.home;

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
 * @ClassName: HotSchoolAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/13 0:34
 */
public class HotSchoolAdapter extends RecyclerView.Adapter<HotSchoolAdapter.ViewHolder> {

    private Context context;
    private List<String> list = new ArrayList<>();

    public HotSchoolAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HotSchoolAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hot_school, parent, false);
        return new HotSchoolAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotSchoolAdapter.ViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void bindView(int pos){
            itemView.setOnClickListener(v -> {
                if(listener != null){
                    listener.onItemClick("");
                }
            });
        }
    }


    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(String str);
    }
}
