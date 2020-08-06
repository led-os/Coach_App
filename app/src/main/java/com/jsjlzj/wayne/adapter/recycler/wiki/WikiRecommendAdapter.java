package com.jsjlzj.wayne.adapter.recycler.wiki;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.wiki.WikiRecommendBean;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.utils.DateUtil;
import com.jsjlzj.wayne.utils.GlidUtils;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: WikiRecommendAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/7/21 23:55
 */
public class WikiRecommendAdapter extends RecyclerView.Adapter<WikiRecommendAdapter.ViewHolder> {


    private Context context;
    private List<WikiRecommendBean.DataBean> list = new ArrayList<>();

    public WikiRecommendAdapter(Context context, List<WikiRecommendBean.DataBean> list) {
        this.context = context;
        this.list.addAll(list);
    }


    public void setData(List<WikiRecommendBean.DataBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wiki_recommend, parent, false);
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
        @BindView(R.id.img_pic)
        ImageView imgPic;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_des)
        TextView tvDes;
        @BindView(R.id.img_right)
        ImageView imgRight;
        @BindView(R.id.rel_type)
        RelativeLayout relType;
        @BindView(R.id.rv_category)
        RecyclerView rvCategory;
        WikiRecommendBean.DataBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            if(pos == 0){
                tvTitle.setVisibility(View.VISIBLE);
                tvTitle.setText("热门词条");
            }else {
                tvTitle.setVisibility(View.GONE);
            }
            WikiRecommendBean.DataBean.CategoryBean categoryBean = bean.getCategory();
            GlidUtils.setRoundGrid(context,categoryBean.getImageUrl(),imgPic,4);
            tvName.setText(categoryBean.getName());
            tvDes.setText(DateUtil.getNumByInteger(categoryBean.getViewCount()) + "万人阅读 "+ DateUtil.getNumByInteger(categoryBean.getContentCount())+"条内容");
            rvCategory.setLayoutManager(new LinearLayoutManager(context));
            WikiCategoryAdapter adapter = new WikiCategoryAdapter(context,bean.getArticleList());
            rvCategory.setAdapter(adapter);
            relType.setOnClickListener(v -> {
                WebViewContainerActivity.go2this(context,"", HttpConstant.WEB_WORD_DETAIL+categoryBean.getId(),
                        WebViewContainerFragment.TYPE_WORD_DETAIL);
                if (listener != null) {
                    listener.onItemClick(bean);
                }
            });

        }
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(WikiRecommendBean.DataBean bean);
    }
}
