package com.jsjlzj.wayne.adapter.recycler.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: FindImageAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/6/27 21:39
 */
public class FindImageAdapter extends RecyclerView.Adapter {


    private Context context;
    private List<String> imgs = new ArrayList<>();



    public FindImageAdapter(Context context, List<String> imgs) {
        this.context = context;
        this.imgs = imgs;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_find_image, parent, false);
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ImageHolder) holder).bindView(imgs.get(position), position);
    }

    @Override
    public int getItemCount() {
        return imgs == null ? 0 : imgs.size();
    }

    public class ImageHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.img_pic)
        ImageView imgPic;
        @BindView(R.id.img_play)
        ImageView imgPlay;
        private int position;

        ImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        private void bindView(String imgUrl, int position) {
            this.position = position;
            GlidUtils.setGrid(context,imgUrl,imgPic);
            itemView.setOnClickListener(v -> {
                // TODO: 2020/6/27 查看大图
            });
        }

    }

}
