package com.jsjlzj.wayne.adapter.recycler.search;

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
import com.jsjlzj.wayne.entity.store.search.TaoLearnListBean;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: SeaerchTaoLearnAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/3/2 11:16
 */
public class SeaerchTaoLearnAdapter extends RecyclerView.Adapter<SeaerchTaoLearnAdapter.ViewHolder> {


    private Context context;
    private List<TaoLearnListBean> list = new ArrayList<>();
    private boolean isSearch;

    public SeaerchTaoLearnAdapter(Context context, List<TaoLearnListBean> list) {
        this.context = context;
        this.list.addAll(list);
    }


    public void setData(List<TaoLearnListBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SeaerchTaoLearnAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_authentication, parent, false);
        return new SeaerchTaoLearnAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeaerchTaoLearnAdapter.ViewHolder holder, int position) {
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
        @BindView(R.id.tv_add_number)
        TextView tvAddNumber;
        @BindView(R.id.tv_add_number_left)
        TextView tvAddNumberLeft;
        @BindView(R.id.ll_right_red)
        LinearLayout llRightRed;
        @BindView(R.id.ll_left_white)
        LinearLayout llLeftWhite;
        @BindView(R.id.img_hot_right)
        ImageView imgHotRight;

        TaoLearnListBean categoryBean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            categoryBean = list.get(pos);
            GlidUtils.setRoundGrid(context,categoryBean.getLessonImg(),imgOne,2);
            tvTitle.setText(categoryBean.getName());
            tvAddNumberLeft.setText(""+categoryBean.getEnrollCount());

            llLeftWhite.setVisibility(View.VISIBLE);
            llRightRed.setVisibility(View.GONE);
            imgHotRight.setVisibility(View.VISIBLE);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(categoryBean);
                }

            });
        }
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {

        void onItemClick(TaoLearnListBean bean);
    }
}
