package com.jsjlzj.wayne.adapter.recycler.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.entity.shopping.MineCouponBean;
import com.jsjlzj.wayne.entity.trainer.InvitationBean;
import com.jsjlzj.wayne.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
    private List<MineCouponBean.DataBean> list = new ArrayList<>();
    /**
     * 0 未使用  1  已使用   2 已过期
     */
    private int type;

    public CouponAdapter(Context context, List<MineCouponBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(List<MineCouponBean.DataBean> list) {
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
        return list != null ? list.size() : 0;
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
        @BindView(R.id.tv_use)
        TextView tvUse;
        @BindView(R.id.img_bg)
        ImageView imgBg;
        private MineCouponBean.DataBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            if(type == 0){
                imgType.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_yhq_use_bg));
                tvUse.setVisibility(View.VISIBLE);
                imgBg.setVisibility(View.GONE);
            }else if(type == 1){
                imgType.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_yhq_use_bg));
                tvUse.setVisibility(View.GONE);
                imgBg.setVisibility(View.VISIBLE);
                imgBg.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_coupon_used));
            }else {
                imgType.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_yhq_nouse_bg));
                tvUse.setVisibility(View.GONE);
                imgBg.setVisibility(View.VISIBLE);
                imgBg.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_coupon_ygq));
            }
            tvZhe.setText(context.getResources().getString(R.string.chinese_money) + bean.getAmount());

            tvDes.setText(bean.getName());
            tvTime.setText("有效期至"+DateUtil.getTime(bean.getEndTime(), new SimpleDateFormat(ExtraConstant.DATE_FORMAT_0, Locale.getDefault())));
            if(bean.getType() == 0){
                tvType.setText("全场赠券");
            }else if(bean.getType() == 1){
                tvType.setText("会员赠券");
            }else if(bean.getType() == 2){
                tvType.setText("购物赠券");
            }else if(bean.getType() == 3){
                tvType.setText("注册增劵");
            }else {
                tvType.setText("购物赠券");
            }
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
