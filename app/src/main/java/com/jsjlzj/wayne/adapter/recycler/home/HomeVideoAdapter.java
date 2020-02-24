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
import com.jsjlzj.wayne.entity.store.home.CategoryBean;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: HomeVideoAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/1/15 15:52
 */
public class HomeVideoAdapter extends RecyclerView.Adapter<HomeVideoAdapter.ViewHolder> {

    private List<CategoryBean> list = new ArrayList<>();
    private Context context;
    private int type = 0;

    public HomeVideoAdapter(Context context, List<CategoryBean> list, int type) {
        this.context = context;
        this.list.addAll(list);
        this.type = type;
    }


    public void setData(List<CategoryBean> list){
        if(list != null && list.size() > 0){
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public HomeVideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if(type == 0){
            view = LayoutInflater.from(context).inflate(R.layout.item_home_video, parent, false);
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.item_all_classic, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeVideoAdapter.ViewHolder holder, int position) {
        holder.bindView(context,list,position);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_type)
        ImageView imgType;
        @BindView(R.id.tv_type)
        TextView tvType;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(Context context,List<CategoryBean> list, int pos) {
//            GlideImageLoader.displayAlbumThumb(imgType,  "", com.netease.nim.uikit.R.drawable.nim_image_default,2);
            CategoryBean data = list.get(pos);
            GlidUtils.setRoundGrid(context,data.getUrl(),imgType,2);
            tvType.setText(data.getName());
            itemView.setOnClickListener(v -> {
                if(listener != null){
                    listener.onItemClick(data);
                }
            });
        }
    }

    private OnClassicItemListener listener;

    public void setListener(OnClassicItemListener listener) {
        this.listener = listener;
    }

    public interface OnClassicItemListener{

        void onItemClick(CategoryBean data);
    }
}
