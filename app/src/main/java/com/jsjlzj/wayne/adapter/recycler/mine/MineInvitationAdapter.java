package com.jsjlzj.wayne.adapter.recycler.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.trainer.InvitationBean;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: MineInvitationAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/18 11:31
 */
public class MineInvitationAdapter extends RecyclerView.Adapter<MineInvitationAdapter.ViewHolder> {


    private Context context;
    private List<InvitationBean.DataBean.ResultBean> list = new ArrayList<>();

    public MineInvitationAdapter(Context context, List<InvitationBean.DataBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(List<InvitationBean.DataBean.ResultBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mine_invitation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0 ;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_head)
        ImageView imgHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_favorite)
        TextView tvFavorite;
        @BindView(R.id.rel_item)
        RelativeLayout relItem;
        private InvitationBean.DataBean.ResultBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            GlidUtils.setCircleGrid(context,bean.getHeadImg(),imgHead);
            tvName.setText(bean.getName());
            tvPhone.setText(bean.getMobile());
            tvTime.setText(bean.getCreateTime());
            if(bean.getType() == 2){
                tvFavorite.setText("俱乐部");
                tvFavorite.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_invitation_7695cf));
            }else {
                tvFavorite.setText("教练");
                tvFavorite.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_invitation_c67879));
            }
            relItem.setOnClickListener(clickListener);
            tvFavorite.setOnClickListener(clickListener);
        }


        public MyViewClickListener clickListener = new MyViewClickListener();


        private class MyViewClickListener extends OnMultiClickListener {
            @Override
            public void OnMultiClick(View view) {
                if (listener == null) {
                    return;
                }
                switch (view.getId()) {
                    case R.id.rel_item:
                        listener.onItemClick(bean);
                        break;
                    default:
                        break;
                }
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
