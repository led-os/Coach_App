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
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;

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
    private List<String> list = new ArrayList<>();

    public SearchAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
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
        return 8;
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
        @BindView(R.id.img_favorite)
        ImageView imgFavorite;
        @BindView(R.id.tv_favorite)
        TextView tvFavorite;
        @BindView(R.id.rel_item)
        RelativeLayout relItem;
        private String bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
//            bean = list.get(pos);
            relItem.setOnClickListener(clickListener);
            imgHead.setOnClickListener(clickListener);
            tvName.setOnClickListener(clickListener);
            imgFavorite.setOnClickListener(clickListener);
            tvFavorite.setOnClickListener(clickListener);
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
        void onItemClick(String string);

        void onHearClick(String string);

        void onFavoriteClick(String string);

    }

    private OnSearchItemClickListener listener;

    public void setListener(OnSearchItemClickListener listener) {
        this.listener = listener;
    }
}
