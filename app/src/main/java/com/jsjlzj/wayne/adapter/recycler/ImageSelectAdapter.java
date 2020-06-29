package com.jsjlzj.wayne.adapter.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.List;

/**
 * @author zenghaiqiang
 * @date 2018/6/11
 */
public class ImageSelectAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<String> imgs;
    private OnImageClickListener listener;
    /**
     * 0 :图片  1 ：视频
     */
    private int type ;

    public OnImageClickListener getListener() {
        return listener;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setListener(OnImageClickListener listener) {
        this.listener = listener;
    }

    public ImageSelectAdapter(Context context, List<String> imgs) {
        this.context = context;
        this.imgs = imgs;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_feedback_img, parent, false);
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

    private class ImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView ivPhoto;
        private final ImageView removeImg;
        private final ImageView imgPlay;
        private int position;

        ImageHolder(View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.iv_photo);
            removeImg = itemView.findViewById(R.id.remove_iv);
            imgPlay = itemView.findViewById(R.id.img_play);
            removeImg.setOnClickListener(this);
            ivPhoto.setOnClickListener(this);
        }

        private void bindView(String imgUrl, int position) {
            this.position = position;
            if (imgUrl == null || imgUrl.isEmpty()) {
                removeImg.setVisibility(View.GONE);
                GlidUtils.setGrid(context, R.drawable.ic_upload, ivPhoto);
            } else {
                removeImg.setVisibility(View.VISIBLE);
                GlidUtils.setGrid(context, imgUrl, ivPhoto);
            }
            if(type != 0 && !imgUrl.isEmpty()){
                imgPlay.setVisibility(View.VISIBLE);
            }else {
                imgPlay.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_photo:
                    if (listener != null) {
                        listener.onImageClick(position);
                    }
                    break;
                case R.id.remove_iv:
                    if (listener != null) {
                        listener.onRemoveImgClick(position);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public interface OnImageClickListener {
        /**
         * 点击图片
         * @param position 点击的角标
         */
        void onImageClick(int position);

        /**
         * 删除图片
         * @param position 删除的角标
         */
        void onRemoveImgClick(int position);
    }
}
