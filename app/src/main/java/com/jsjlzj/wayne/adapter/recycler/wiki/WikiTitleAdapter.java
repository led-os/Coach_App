package com.jsjlzj.wayne.adapter.recycler.wiki;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.wiki.WikiCategoryBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: WikiTitleAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/7/26 13:52
 */
public class WikiTitleAdapter extends RecyclerView.Adapter<WikiTitleAdapter.ViewHolder> {


    private Context context;
    private List<WikiCategoryBean.DataBean> list = new ArrayList<>();

    public WikiTitleAdapter(Context context, List<WikiCategoryBean.DataBean> list) {
        this.context = context;
        this.list.addAll(list);
    }


    public void setData(List<WikiCategoryBean.DataBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wiki_title, parent, false);
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

        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.rv_category)
        RecyclerView rvCategory;
        WikiCategoryBean.DataBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);

            tvTitle.setText(bean.getName());
            rvCategory.setLayoutManager(new LinearLayoutManager(context));
            WikiItemCategoryAdapter adapter = new WikiItemCategoryAdapter(context,bean.getItems());
            rvCategory.setAdapter(adapter);
        }
    }
}
