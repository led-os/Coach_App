package com.jsjlzj.wayne.adapter.recycler.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.find.FindStoreConditionBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: ConditionAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/6/26 14:25
 */
public class ConditionAdapter extends RecyclerView.Adapter<ConditionAdapter.ViewHolder> {

    private Context context;
    private List<FindStoreConditionBean.DataBean> list = new ArrayList<>();
    private int selectPos = -1;

    public ConditionAdapter(Context context, List<FindStoreConditionBean.DataBean> list,int selectPos) {
        this.context = context;
        this.list = list;
        this.selectPos = selectPos;
    }

    @NonNull
    @Override
    public ConditionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_condition,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConditionAdapter.ViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_class)
        TextView tvClass;
        private FindStoreConditionBean.DataBean bean;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bindView(int pos){
            bean = list.get(pos);
            tvClass.setText(bean.getName());
            if(pos == selectPos){
                tvClass.setTextColor(ContextCompat.getColor(context,R.color.color_F1404B));
                tvClass.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_stroke_f1404b_solid_fff8f8_14));
            }else {
                tvClass.setTextColor(ContextCompat.getColor(context,R.color.color_222222));
                tvClass.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_solid_f9f9f9_12));
            }
            itemView.setOnClickListener(v -> {
                if(listener != null){
                    selectPos = pos;
                    notifyDataSetChanged();
                    listener.onItemClick(bean);
                }
            });
        }
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(FindStoreConditionBean.DataBean bean);
    }
}
