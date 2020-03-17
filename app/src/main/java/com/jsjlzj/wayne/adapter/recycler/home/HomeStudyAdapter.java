package com.jsjlzj.wayne.adapter.recycler.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.store.learn.LibraryBean;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: HomeStudyAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/14 11:42
 */
public class HomeStudyAdapter extends RecyclerView.Adapter<HomeStudyAdapter.ViewHolder> {

    private Context context;
    private List<LibraryBean> list = new ArrayList<>();

    public HomeStudyAdapter(Context context, List<LibraryBean> list) {
        this.context = context;
        this.list.addAll(list);
    }

    public void setData(List<LibraryBean> list) {
        if (list != null) {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_study, parent, false);
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

        @BindView(R.id.img_one)
        ImageView imgOne;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        private LibraryBean bean;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            if(bean.getId() == -1){
                imgOne.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.bg_gztk));
            }else if(bean.getId() == -2){
                imgOne.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.bg_gzkscx));
            }else {
                GlidUtils.setRoundGrid(context,bean.getCoverImg(),imgOne,5);
            }
            tvTitle.setText(bean.getTitle());
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(bean,pos);
                }

            });
        }
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {

        void onItemClick(LibraryBean bean,int pos);
    }
}
