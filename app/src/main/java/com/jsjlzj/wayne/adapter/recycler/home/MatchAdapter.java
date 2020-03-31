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
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.store.home.CategoryBean;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.utils.DateUtil;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: MatchAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/8 10:43
 */
public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {

    private Context context;
    private List<CategoryBean> list = new ArrayList<>();

    public MatchAdapter(Context context, List<CategoryBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(List<CategoryBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_match, parent, false);
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
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_time_des)
        TextView tvTimeDes;
        @BindView(R.id.tv_add_number)
        TextView tvAddNumber;
        private CategoryBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            tvTitle.setText(bean.getName());
            tvAddNumber.setText(DateUtil.getNumByInteger(bean.getEnrollCount()));
            tvTime.setText(bean.getStartTime()+" 至 " +bean.getEndTime());
            GlidUtils.setRoundGrid(context,bean.getCoverImg(),imgOne,2);
            itemView.setOnClickListener(v -> {
                WebViewContainerActivity.go2this(context,bean.getName(), HttpConstant.WEB_URL_MATCH_DETAIL+bean.getId(),
                        WebViewContainerFragment.TYPE_MATCH_DETAIL);
//                if(listener != null){
//                    listener.onItemClick(bean);
//                }
            });
            switch (bean.getStatus()){
                case 1:
                    tvState.setText("未开始");
                    tvState.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_solid_999999_15));
                    break;
                case 2:
                    tvState.setText("进行中");
                    tvState.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_solid_4f9bfa_15));
                    break;
                case 3:
                    tvState.setText("已结束");
                    tvState.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_solid_999999_15));
                    break;
            }
        }
    }

    private OnItemClickListener listener ;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(CategoryBean bean);
    }
}
