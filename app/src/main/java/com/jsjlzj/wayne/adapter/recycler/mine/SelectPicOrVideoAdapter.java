package com.jsjlzj.wayne.adapter.recycler.mine;

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
 * @ClassName: SelectPicOrVideoAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/6/23 23:24
 */
public class SelectPicOrVideoAdapter extends RecyclerView.Adapter<SelectPicOrVideoAdapter.ViewHolder> {

    private Context context;
    private List<String> list = new ArrayList<>();

    public SelectPicOrVideoAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SelectPicOrVideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_select_pic_or_video,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectPicOrVideoAdapter.ViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return 2;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(int pos) {


        }
    }
}
