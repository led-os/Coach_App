package com.jsjlzj.wayne.adapter.recycler.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.store.find.PictureDetailActivity;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: PictureAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/6/28 21:12
 */
public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {


    private Context context;
    private ArrayList<String> list = new ArrayList<>();


    public PictureAdapter(Context context, List<String> list) {
        this.context = context;
        this.list.addAll(list);
    }

    public void setData(List<String> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_picture, parent, false);
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_pic)
        ImageView imgPic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(int pos) {
            String imgUrl = list.get(pos);
            GlidUtils.setRoundGrid(context, imgUrl, imgPic,2);
            itemView.setOnClickListener(v -> {
                PictureDetailActivity.go2this(context,list,pos);
            });
        }
    }
}
