package com.jsjlzj.wayne.adapter.recycler.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.find.OptimizationData2Bean;
import com.jsjlzj.wayne.ui.store.find.MoreLessonActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: ClassRecommendAdapter
 * @Description: 分类推荐适配器
 * @Author: 曾海强
 * @CreateDate: 2020/4/19 22:34
 */
public class ClassRecommendAdapter extends RecyclerView.Adapter<ClassRecommendAdapter.ViewHolder> {


    private Context context;
    private List<OptimizationData2Bean.DataBean.CategoryListBean> list = new ArrayList<>();

    public ClassRecommendAdapter(Context context, List<OptimizationData2Bean.DataBean.CategoryListBean> list) {
        this.context = context;
        this.list.addAll(list);
    }


    public void setData(List<OptimizationData2Bean.DataBean.CategoryListBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_class_recommend, parent, false);
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
        @BindView(R.id.tv_des)
        TextView tvDes;
        @BindView(R.id.tv_more)
        TextView tvMore;
        @BindView(R.id.ll_title)
        LinearLayout llTitle;
        @BindView(R.id.rv_recommend)
        RecyclerView rvRecommend;
        OptimizationData2Bean.DataBean.CategoryListBean categoryBean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            categoryBean = list.get(pos);
            switch (pos%5) {
                case 0:
                    llTitle.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_find_increasing_muscle));
                    break;
                case 1:
                    llTitle.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_find_fat_reduction));
                    break;
                case 2:
                    llTitle.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_find_maternity));
                    break;
                case 3:
                    llTitle.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_find_pilates));
                    break;
                case 4:
                    llTitle.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_find_recovery));
                    break;
            }
            tvType.setText(categoryBean.getName());
            tvDes.setText(categoryBean.getCategoryDesc());
            rvRecommend.setHasFixedSize(true);
            rvRecommend.setNestedScrollingEnabled(false);
            rvRecommend.setLayoutManager(new LinearLayoutManager(context));
            CourserNewAdapter courserNewAdapter = new CourserNewAdapter(context,categoryBean.getLessonList());
            rvRecommend.setAdapter(courserNewAdapter);

            tvMore.setOnClickListener(v -> {
                MoreLessonActivity.go2this(context,categoryBean.getName(),6,categoryBean.getCategoryId());

            });
        }
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {

        void onItemClick(OptimizationData2Bean.DataBean.CategoryListBean bean);
    }
}
