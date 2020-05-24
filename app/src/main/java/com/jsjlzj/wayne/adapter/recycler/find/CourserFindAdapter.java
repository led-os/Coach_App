package com.jsjlzj.wayne.adapter.recycler.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.find.FindLessonBean;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: CourserFindAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/4/19 22:24
 */
public class CourserFindAdapter extends RecyclerView.Adapter<CourserFindAdapter.ViewHolder> {


    private Context context;
    private List<FindLessonBean> list = new ArrayList<>();

    public CourserFindAdapter(Context context, List<FindLessonBean> list) {
        this.context = context;
        this.list.addAll(list);
    }


    public void setData(List<FindLessonBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_courser_find, parent, false);
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

        @BindView(R.id.img_courser)
        ImageView imgCourser;
        @BindView(R.id.tv_type)
        TextView tvType;
        FindLessonBean categoryBean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            categoryBean = list.get(pos);
            GlidUtils.setRoundGrid(context,categoryBean.getCoverImg(),imgCourser,5);
            itemView.setOnClickListener(v -> {
                WebViewContainerActivity.go2this(context,categoryBean.getTitle(), HttpConstant.WEB_URL_NEW_COURSE_DETAIL+categoryBean.getId(),
                        WebViewContainerFragment.TYPE_NEW_COURSE_DETAIL);

            });
        }
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {

        void onItemClick(FindLessonBean bean);
    }
}
