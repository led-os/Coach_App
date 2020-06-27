package com.jsjlzj.wayne.adapter.recycler.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.find.FindStoreBean;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: FindLessonAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/6/26 16:21
 */
public class FindLessonAdapter extends RecyclerView.Adapter<FindLessonAdapter.ViewHolder> {


    private Context context;
    private List<FindStoreBean.LessonsBean> list = new ArrayList<>();

    public FindLessonAdapter(Context context, List<FindStoreBean.LessonsBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_find_lesson, parent, false);
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_tuan)
        TextView tvTuan;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_des)
        TextView tvDes;
        FindStoreBean.LessonsBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(int pos) {
            bean = list.get(pos);
            if(bean.getType() == 1){
                tvTuan.setText("团");
            }else if(bean.getType() == 2){
                tvTuan.setText("私");
            }
            tvMoney.setText(context.getResources().getString(R.string.chinese_money)+ DateUtil.getTwoDotByFloatFY(bean.getPrice()));
            tvDes.setText(bean.getName());
            itemView.setOnClickListener(v -> {
                WebViewContainerActivity.go2this(context,context.getResources().getString(R.string.tuan_detail), HttpConstant.WEB_URL_LESSON_DETAIL+bean.getId(),
                        WebViewContainerFragment.TYPE_TUAN_DETAIL);
                if(listener != null){
                    listener.onItemClick(bean);
                }
            });
        }
    }

    public OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(FindStoreBean.LessonsBean bean);
    }
}
