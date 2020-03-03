package com.jsjlzj.wayne.adapter.recycler.search;

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
import com.jsjlzj.wayne.entity.store.search.ChannelListBean;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: SearchUserAdapter
 * @Description: 搜索用户列表适配器
 * @Author: 曾海强
 * @CreateDate: 2020/2/10 23:18
 */
public class SearchUserAdapter extends RecyclerView.Adapter<SearchUserAdapter.ViewHolder> {


    private Context context;
    private List<ChannelListBean> list = new ArrayList<>();

    public SearchUserAdapter(Context context, List<ChannelListBean> list) {
        this.context = context;
        this.list.addAll(list);
    }

    public void setData(List<ChannelListBean> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_user, parent, false);
        return new SearchUserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchUserAdapter.ViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_head)
        ImageView imgHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_favorite)
        TextView tvFavorite;
        @BindView(R.id.tv_fen)
        TextView tvFen;
        @BindView(R.id.rel_item)
        RelativeLayout relItem;
        private ChannelListBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            GlidUtils.setCircleGrid(context,bean.getAvatar(),imgHead);
            tvName.setText(bean.getName());
            tvFen.setText("粉丝"+bean.getFansCount()+"万");
            relItem.setOnClickListener(clickListener);
            if(bean.isFollower()){
                tvFavorite.setText("已关注");
            }else {
                tvFavorite.setText("关注");
            }
            tvFavorite.setOnClickListener(clickListener);
        }


        public ViewHolder.MyViewClickListener clickListener = new ViewHolder.MyViewClickListener();


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
                    case R.id.tv_favorite:
                        if(bean.isFollower()){
                            bean.setFollower(false);
                            tvFavorite.setText("关注");
                        }else {
                            bean.setFollower(true);
                            tvFavorite.setText("已关注");
                        }
                        listener.onFavoriteClick(bean);
                        break;
                    default:
                        break;
                }
            }
        }

    }


    public interface OnSearchUserClickListener {
        void onItemClick(ChannelListBean string);

        void onFavoriteClick(ChannelListBean string);

    }

    private OnSearchUserClickListener listener;

    public void setListener(OnSearchUserClickListener listener) {
        this.listener = listener;
    }
}
