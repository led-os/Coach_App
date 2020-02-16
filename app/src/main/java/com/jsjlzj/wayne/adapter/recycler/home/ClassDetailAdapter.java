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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName: ClassDetailAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/11 22:11
 */
public class ClassDetailAdapter extends RecyclerView.Adapter<ClassDetailAdapter.ViewHolder> {


    private Context context;
    private List<String> list = new ArrayList<>();

    public ClassDetailAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_classic_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return 8;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_head)
        ImageView imgHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_official)
        TextView tvOfficial;
        @BindView(R.id.tv_favorite)
        TextView tvFavorite;
        @BindView(R.id.img_one)
        ImageView imgOne;
        @BindView(R.id.img_play)
        ImageView imgPlay;
        @BindView(R.id.img_play_num)
        ImageView imgPlayNum;
        @BindView(R.id.tv_play_num)
        TextView tvPlayNum;
        @BindView(R.id.tv_play_time)
        TextView tvPlayTime;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.img_zan)
        ImageView imgZan;
        @BindView(R.id.tv_zan_num)
        TextView tvZanNum;
        @BindView(R.id.img_message)
        ImageView imgMessage;
        @BindView(R.id.tv_message_num)
        TextView tvMessageNum;
        @BindView(R.id.img_mark)
        ImageView imgMark;
        @BindView(R.id.tv_mark_num)
        TextView tvMarkNum;
        private String bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {

        }

        @OnClick({R.id.img_head, R.id.tv_name, R.id.tv_favorite, R.id.img_play, R.id.img_zan, R.id.tv_zan_num, R.id.img_message, R.id.tv_message_num, R.id.img_mark, R.id.tv_mark_num})
        public void onViewClicked(View view) {
            if(listener == null){
                return;
            }
            switch (view.getId()) {
                case R.id.img_head:
                case R.id.tv_name:
                    listener.onClickHead(bean);
                    break;
                case R.id.tv_favorite:
                    listener.onFavorite(bean);
                    break;
                case R.id.img_play:
                    listener.onPlayVideo(bean);
                    break;
                case R.id.img_zan:
                case R.id.tv_zan_num:
                    listener.onClickZan(bean);
                    break;
                case R.id.img_message:
                case R.id.tv_message_num:
                    listener.onClickMessage(bean);
                    break;
                case R.id.img_mark:
                case R.id.tv_mark_num:
                    listener.onClickMark(bean);
                    break;
                    default:break;
            }
        }
    }
    private OnClassicDetailListener listener;

    public void setListener(OnClassicDetailListener listener) {
        this.listener = listener;
    }

    public interface OnClassicDetailListener {

        void onClickHead(String str);

        void onFavorite(String str);

        void onPlayVideo(String str);

        void onClickZan(String str);

        void onClickMessage(String str);

        void onClickMark(String str);
    }
}
