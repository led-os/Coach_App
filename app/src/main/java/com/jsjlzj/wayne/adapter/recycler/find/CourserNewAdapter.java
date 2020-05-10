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
import com.jsjlzj.wayne.entity.find.FindLessonBean;
import com.jsjlzj.wayne.utils.DateUtil;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: CourserNewAdapter
 * @Description: 课程条目适配器
 * @Author: 曾海强
 * @CreateDate: 2020/4/19 23:18
 */
public class CourserNewAdapter extends RecyclerView.Adapter<CourserNewAdapter.ViewHolder> {

    private Context context;
    private List<FindLessonBean> list = new ArrayList<>();

    public CourserNewAdapter(Context context, List<FindLessonBean> list) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_courser_new, parent, false);
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

        @BindView(R.id.img_url)
        ImageView imgUrl;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_des)
        TextView tvDes;
        @BindView(R.id.tv_play_num)
        TextView tvPlayNum;
        FindLessonBean findLessonBean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            findLessonBean = list.get(pos);
            GlidUtils.setRoundGrid(context, findLessonBean.getCoverImg(), imgUrl,5);
            tvTitle.setText(findLessonBean.getTitle());
            tvDes.setText(findLessonBean.getLessonDesc());
            tvPlayNum.setText(DateUtil.getNumByInteger(findLessonBean.getStudyPersons())+"次播放量");
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(findLessonBean);
                }

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
