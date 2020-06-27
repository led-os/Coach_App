package com.jsjlzj.wayne.adapter.recycler.find;

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
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.find.FindTrainerBean;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: FindTrainAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/6/27 11:56
 */
public class FindTrainAdapter extends RecyclerView.Adapter<FindTrainAdapter.ViewHolder> {


    private Context context;
    private List<FindTrainerBean.DataBean> list = new ArrayList<>();
    private int selectTrainId = -1;


    public FindTrainAdapter(Context context, List<FindTrainerBean.DataBean> list,int selectTrainId) {
        this.context = context;
        this.list.addAll(list);
        this.selectTrainId = selectTrainId;
    }

    public void setData(List<FindTrainerBean.DataBean> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_user, parent, false);
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

        @BindView(R.id.img_head)
        ImageView imgHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_favorite)
        TextView tvFavorite;
        @BindView(R.id.tv_fen)
        TextView tvFen;
        @BindView(R.id.rel_item)
        RelativeLayout relItem;
        private FindTrainerBean.DataBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            GlidUtils.setCircleGrid(context,bean.getHeadImg(),imgHead);
            tvName.setText(bean.getName());
            tvFen.setText(bean.getTrainerType());
            if(bean.getId() == selectTrainId){
                tvFavorite.setText("取消");
            }else {
                tvFavorite.setText("选Ta");
            }
            tvFavorite.setTextColor(ContextCompat.getColor(context,R.color.color_F1404B));
            tvFavorite.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_stroke_f1404b_21));
            tvName.setOnClickListener(clickListener);
            imgHead.setOnClickListener(clickListener);
            tvFavorite.setOnClickListener(clickListener);
        }


        public ViewHolder.MyViewClickListener clickListener = new ViewHolder.MyViewClickListener();


        private class MyViewClickListener extends OnMultiClickListener {
            @Override
            public void OnMultiClick(View view) {
                if(listener == null) {
                    return;
                }
                switch (view.getId()) {
                    case R.id.tv_name:
                    case R.id.img_head:
                        WebViewContainerActivity.go2this(context,context.getResources().getString(R.string.trainer_detail), HttpConstant.WEB_URL_COACH+bean.getId(),
                                WebViewContainerFragment.TYPE_TRAINER_DETAIL);
                        listener.onItemClick(bean);
                        break;

                    case R.id.tv_favorite:
                        listener.onFavoriteClick(bean);
                        break;
                    default:
                        break;
                }
            }
        }

    }


    public interface OnSearchUserClickListener {
        void onItemClick(FindTrainerBean.DataBean bean);

        void onFavoriteClick(FindTrainerBean.DataBean bean);

    }

    private OnSearchUserClickListener listener;

    public void setListener(OnSearchUserClickListener listener) {
        this.listener = listener;
    }
}
