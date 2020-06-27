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
import com.jsjlzj.wayne.entity.find.BusinessDistrictBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: BusinessDistrictAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/6/25 20:51
 */
public class BusinessDistrictAdapter extends RecyclerView.Adapter<BusinessDistrictAdapter.ViewHolder> {


    private Context context;
    private List<BusinessDistrictBean.DataBean> list = new ArrayList<>();
    private int selectPos = 1;

    public BusinessDistrictAdapter(Context context, List<BusinessDistrictBean.DataBean> list) {
        this.context = context;
        this.list.addAll(list);
    }

    public BusinessDistrictAdapter(Context context, List<BusinessDistrictBean.DataBean> list,int selectPos) {
        this.context = context;
        this.list.addAll(list);
        this.selectPos = selectPos;
    }
    public void setData(List<BusinessDistrictBean.DataBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public int getSelectPos() {
        return selectPos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_business_district, parent, false);
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

        @BindView(R.id.tv_type)
        TextView tvType;
        BusinessDistrictBean.DataBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            tvType.setText(bean.getShortName());
            if(selectPos == pos){
                tvType.setTextColor(ContextCompat.getColor(context,R.color.color_F1404B));
            }else {
                tvType.setTextColor(ContextCompat.getColor(context,R.color.color_333333));
            }
            itemView.setOnClickListener(v -> {
                if(listener != null){
                    selectPos = pos;
                    notifyDataSetChanged();
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

        void onItemClick(BusinessDistrictBean.DataBean bean,int pos);
    }
}
