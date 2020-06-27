package com.jsjlzj.wayne.adapter.recycler.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.find.FindStoreBean;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.ui.store.find.FindStoreEvaluateActivity;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: FindStoreAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/6/20 13:36
 */
public class FindStoreAdapter extends RecyclerView.Adapter<FindStoreAdapter.ViewHolder> {


    private Context context;
    private List<FindStoreBean> list = new ArrayList<>();
    /**
     *  1： 评论成功推荐列表
     */
    private int type ;

    public FindStoreAdapter(Context context, List<FindStoreBean> list) {
        this.context = context;
        this.list.addAll(list);
    }


    public void setType(int type) {
        this.type = type;
    }

    public void setData(List<FindStoreBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_find_store, parent, false);
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
        @BindView(R.id.img_pic)
        ImageView imgPic;
        @BindView(R.id.tv_store_name)
        TextView tvStoreName;
        @BindView(R.id.tv_is_new)
        TextView tvIsNew;
        @BindView(R.id.img_star_1)
        ImageView imgStar1;
        @BindView(R.id.img_star_2)
        ImageView imgStar2;
        @BindView(R.id.img_star_3)
        ImageView imgStar3;
        @BindView(R.id.img_star_4)
        ImageView imgStar4;
        @BindView(R.id.img_star_5)
        ImageView imgStar5;
        @BindView(R.id.tv_fen)
        TextView tvFen;
        @BindView(R.id.ll_fen)
        LinearLayout llFen;
        @BindView(R.id.tv_des)
        TextView tvDes;
        @BindView(R.id.tv_distant)
        TextView tvDistant;
        @BindView(R.id.tv_to_comment)
        TextView tvToComment;
        @BindView(R.id.rv_lesson)
        RecyclerView rvLesson;

        FindStoreBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            GlidUtils.setGrid(context, bean.getStoreLogo(), imgPic);
            tvStoreName.setText(bean.getStoreName());
            tvFen.setText(bean.getScore()+"分");
            if (bean.getScore() == 1) {
                imgStar1.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_star));
                imgStar2.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_evaluate_xing_no));
                imgStar3.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_evaluate_xing_no));
                imgStar4.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_evaluate_xing_no));
                imgStar5.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_evaluate_xing_no));
            }else if(bean.getScore() > 1 && bean.getScore() < 2){
                imgStar1.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_star));
                imgStar2.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_stare_self));
                imgStar3.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_evaluate_xing_no));
                imgStar4.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_evaluate_xing_no));
                imgStar5.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_evaluate_xing_no));
            } else if (bean.getScore() == 2) {
                imgStar1.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_star));
                imgStar2.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_star));
                imgStar3.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_evaluate_xing_no));
                imgStar4.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_evaluate_xing_no));
                imgStar5.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_evaluate_xing_no));
            }else if(bean.getScore() > 2 && bean.getScore() < 3){
                imgStar1.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_star));
                imgStar2.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_star));
                imgStar3.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_stare_self));
                imgStar4.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_evaluate_xing_no));
                imgStar5.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_evaluate_xing_no));
            } else if (bean.getScore() == 3) {
                imgStar1.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_star));
                imgStar2.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_star));
                imgStar3.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_star));
                imgStar4.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_evaluate_xing_no));
                imgStar5.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_evaluate_xing_no));
            } else if(bean.getScore() > 3 && bean.getScore() < 4){
                imgStar1.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_star));
                imgStar2.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_star));
                imgStar3.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_star));
                imgStar4.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_stare_self));
                imgStar5.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_evaluate_xing_no));
            }else if (bean.getScore() == 4) {
                imgStar1.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_star));
                imgStar2.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_star));
                imgStar3.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_star));
                imgStar4.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_star));
                imgStar5.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_evaluate_xing_no));
            }else if(bean.getScore() > 4 && bean.getScore() < 5){
                imgStar1.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_star));
                imgStar2.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_star));
                imgStar3.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_star));
                imgStar4.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_star));
                imgStar5.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_stare_self));
            } else if (bean.getScore() == 5) {
                imgStar1.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_star));
                imgStar2.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_star));
                imgStar3.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_star));
                imgStar4.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_star));
                imgStar5.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_find_star));
            }else {
                imgStar1.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_evaluate_xing_no));
                imgStar2.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_evaluate_xing_no));
                imgStar3.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_evaluate_xing_no));
                imgStar4.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_evaluate_xing_no));
                imgStar5.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_evaluate_xing_no));
            }
            if(bean.getScore() == 0){
                tvFen.setText("暂无评分");
            }else {
                tvFen.setText(bean.getScore() + "分");
            }
            if(type == 1){
                tvDistant.setVisibility(View.GONE);
                tvToComment.setVisibility(View.VISIBLE);
            }else {
                tvToComment.setVisibility(View.GONE);
                if(bean.getDistance() != 0){
                    tvDistant.setText(bean.getDistance() + "m");
                    tvDistant.setVisibility(View.VISIBLE);
                }else {
                    tvDistant.setVisibility(View.GONE);
                }
            }
            tvToComment.setOnClickListener(v -> {
                FindStoreEvaluateActivity.go2this(context,bean.getId(),bean.getStoreName());
            });
            itemView.setOnClickListener(v -> {
                WebViewContainerActivity.go2this(context,context.getResources().getString(R.string.clube_detail), HttpConstant.WEB_URL_CLUB_DETAIL+bean.getId(),
                        WebViewContainerFragment.TYPE_CLUBE_DETAIL);
            });
            tvIsNew.setVisibility(bean.getNewStoreFlag() == 1 ? View.VISIBLE : View.GONE);
            tvDes.setText(bean.getBusinessDistrict());
            rvLesson.setLayoutManager(new LinearLayoutManager(context));
            FindLessonAdapter findLessonAdapter = new FindLessonAdapter(context,bean.getLessons());
            rvLesson.setAdapter(findLessonAdapter);
        }
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {

        void onItemClick(String bean);
    }
}

