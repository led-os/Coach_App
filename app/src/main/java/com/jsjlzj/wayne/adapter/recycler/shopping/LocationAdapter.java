package com.jsjlzj.wayne.adapter.recycler.shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.shopping.LocationListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: LocationAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/4/30 17:49
 */
public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {


    private Context context;
    private List<LocationListBean.DataBean> list = new ArrayList<>();
    private int fromType;

    public LocationAdapter(Context context, List<LocationListBean.DataBean> list,int fromType) {
        this.context = context;
        this.fromType = fromType;
        this.list.addAll(list);
    }


    public void setData(List<LocationListBean.DataBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_location, parent, false);
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
        @BindView(R.id.img_select)
        ImageView imgSelect;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.tv_default)
        TextView tvDefault;
        @BindView(R.id.tv_location)
        TextView tvLocation;
        @BindView(R.id.img_edit)
        ImageView imgEdit;
        LocationListBean.DataBean locationBean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            locationBean = list.get(pos);
            tvName.setText(locationBean.getUserName());
            tvPhone.setText(locationBean.getPhone());
            if(locationBean.getIsDefault() == 0){
                tvDefault.setVisibility(View.GONE);
            }else {
                tvDefault.setVisibility(View.VISIBLE);
            }
            tvLocation.setText(locationBean.getProvince()+" "+locationBean.getCity()+" "+locationBean.getDistrict()
            +" "+locationBean.getDetail());
            if(fromType == 1){
                imgSelect.setVisibility(View.VISIBLE);
                itemView.setOnClickListener(v -> {
                    if (listener != null) {
                        listener.onItemClick(locationBean);
                    }

                });
            }else {
                imgSelect.setVisibility(View.GONE);
            }
            imgEdit.setOnClickListener(v -> {
                if(listener != null){
                    listener.onEditClick(locationBean);
                }
            });

        }
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {

        void onItemClick(LocationListBean.DataBean bean);

        void onEditClick(LocationListBean.DataBean bean);
    }
}
