package com.jsjlzj.wayne.adapter.recycler.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.find.VideoListBean;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: VideoAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/6/28 21:34
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {


    private Context context;
    private List<VideoListBean.DataBean.ResultBean> list = new ArrayList<>();


    public VideoAdapter(Context context, List<VideoListBean.DataBean.ResultBean> list) {
        this.context = context;
        this.list.addAll(list);
    }

    public void setData(List<VideoListBean.DataBean.ResultBean> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
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
        @BindView(R.id.img_play)
        ImageView imgPlay;
        @BindView(R.id.rel_img)
        RelativeLayout relImg;
        @BindView(R.id.tv_video)
        TextView tvVideo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(int pos) {
            VideoListBean.DataBean.ResultBean bean = list.get(pos);
            GlidUtils.setRoundGrid(context, bean.getVideoImg(), imgPic, 2);
            tvVideo.setText(bean.getTitle());
            itemView.setOnClickListener(v -> {

            });
        }
    }
}
