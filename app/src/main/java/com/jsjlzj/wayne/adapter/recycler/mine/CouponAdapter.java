package com.jsjlzj.wayne.adapter.recycler.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.trainer.InvitationBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: CouponAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/8 9:22
 */
public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.ViewHolder> {



    private Context context;
    private List<String> list = new ArrayList<>();

    public CouponAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_coupon, parent, false);
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

        @BindView(R.id.img_type)
        ImageView imgType;
        @BindView(R.id.tv_zhe)
        TextView tvZhe;
        @BindView(R.id.ll_zhe)
        LinearLayout llZhe;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_des)
        TextView tvDes;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.img_bg)
        ImageView imgBg;
        private String bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
//            bean = list.get(pos);
        }

    }


    public interface OnItemClickListener {
        void onItemClick(InvitationBean.DataBean.ResultBean bean);


    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
