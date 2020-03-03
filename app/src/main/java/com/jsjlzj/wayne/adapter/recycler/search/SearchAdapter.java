package com.jsjlzj.wayne.adapter.recycler.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.store.home.VideoBean;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: SearchAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/10 10:50
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {


    private Context context;
    private List<VideoBean> list = new ArrayList<>();

    public SearchAdapter(Context context, List<VideoBean> list) {
        this.context = context;
        this.list = list;
    }


    public void setData(List<VideoBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_dynamic, parent, false);
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

        @BindView(R.id.img_dynamic)
        ImageView imgDynamic;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.img_head)
        ImageView imgHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.img_collect)
        ImageView imgCollect;
        @BindView(R.id.tv_collect)
        TextView tvCollect;
        @BindView(R.id.rel_item)
        RelativeLayout relItem;
        private VideoBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            GlidUtils.setRoundGrid(context,bean.getCoverImg(),imgDynamic,2);
            GlidUtils.setCircleGrid(context,bean.getChannelAvatar(),imgHead);
            tvTitle.setText(bean.getName());
            tvName.setText(bean.getChannelName());
            tvCollect.setText(""+bean.getCollectCount());
            if(bean.isCollect()){
                imgCollect.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.collected));
            }else {
                imgCollect.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.uncollected));
            }
            relItem.setOnClickListener(clickListener);
            imgHead.setOnClickListener(clickListener);
            tvName.setOnClickListener(clickListener);
            imgCollect.setOnClickListener(clickListener);
            tvCollect.setOnClickListener(clickListener);
        }


        public MyViewClickListener clickListener = new MyViewClickListener();


        private class MyViewClickListener extends OnMultiClickListener {
            @Override
            public void OnMultiClick(View view) {
                if(listener == null) {
                    return;
                }
                switch (view.getId()) {
                    case R.id.rel_item:
                        listener.onItemClick(bean);
                        break;
                    case R.id.tv_name:
                    case R.id.img_head:
                        listener.onHearClick(bean);
                        break;
                    case R.id.tv_favorite:
                    case R.id.img_favorite:
                        listener.onFavoriteClick(bean);
                        break;
                    default:
                        break;
                }
            }
        }

    }


    public interface OnSearchItemClickListener {
        void onItemClick(VideoBean bean);

        void onHearClick(VideoBean bean);

        void onFavoriteClick(VideoBean bean);

    }

    private OnSearchItemClickListener listener;

    public void setListener(OnSearchItemClickListener listener) {
        this.listener = listener;
    }
}
