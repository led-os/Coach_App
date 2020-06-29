package com.jsjlzj.wayne.adapter.recycler.mine;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_select_pic_or_video, parent, false);
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
        @BindView(R.id.img_pic)
        ImageView imgPic;
        @BindView(R.id.tv_pic)
        TextView tvPic;
        @BindView(R.id.iv_photo)
        ImageView imgSelectPic;
        @BindView(R.id.remove_iv)
        ImageView removeIv;
        @BindView(R.id.img_play)
        ImageView imgPlay;
        private String bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(int pos) {
            bean = list.get(pos);
            if ("图片".equals(bean)) {
                tvPic.setText("上传图片");
                removeIv.setVisibility(View.GONE);
                tvPic.setVisibility(View.VISIBLE);
                imgPic.setVisibility(View.VISIBLE);
                imgSelectPic.setVisibility(View.GONE);
                imgPlay.setVisibility(View.GONE);
                imgPic.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_update_pic));
            } else if ("视频".equals(bean)) {
                tvPic.setText("上传视频");
                imgPlay.setVisibility(View.GONE);
                removeIv.setVisibility(View.GONE);
                tvPic.setVisibility(View.VISIBLE);
                imgPic.setVisibility(View.VISIBLE);
                imgSelectPic.setVisibility(View.GONE);
                imgPic.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_update_video));
            } else {
                if(pos == list.size() -1){
                    imgPlay.setVisibility(View.VISIBLE);
                    removeIv.setVisibility(View.VISIBLE);
                    tvPic.setVisibility(View.GONE);
                    imgPic.setVisibility(View.GONE);
                    imgSelectPic.setVisibility(View.VISIBLE);
                    GlidUtils.setGrid(context, bean, imgSelectPic);
                }else {
                    imgPlay.setVisibility(View.GONE);
                    tvPic.setVisibility(View.GONE);
                    imgPic.setVisibility(View.GONE);
                    removeIv.setVisibility(View.VISIBLE);
                    imgSelectPic.setVisibility(View.VISIBLE);
                    GlidUtils.setGrid(context, bean, imgSelectPic);
                }

            }
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onImageClick(pos);
                }
            });
            removeIv.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onRemoveImgClick(pos);
                }
            });
        }
    }

    private OnImageClickListener listener;

    public void setListener(OnImageClickListener listener) {
        this.listener = listener;
    }

    public interface OnImageClickListener {
        /**
         * 点击图片
         *
         * @param position 点击的角标
         */
        void onImageClick(int position);

        /**
         * 删除图片
         *
         * @param position 删除的角标
         */
        void onRemoveImgClick(int position);
    }
}
