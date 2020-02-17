package com.jsjlzj.wayne.adapter.recycler.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.store.AchievementBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * @ClassName: PositionVideoAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/17 0:39
 */
public class PositionVideoAdapter extends RecyclerView.Adapter<PositionVideoAdapter.ViewHolder> {


    private Context context;
    private List<String> list = new ArrayList<>();

    public PositionVideoAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PositionVideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_position_video, parent, false);
        return new PositionVideoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PositionVideoAdapter.ViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        private String bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {

        }
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {

        void onItemClick(AchievementBean str);
    }
}
