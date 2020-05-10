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
 * @ClassName: MotionFindAdapter
 * @Description: 更多运动适配器
 * @Author: 曾海强
 * @CreateDate: 2020/4/19 21:44
 */
public class MotionFindAdapter extends RecyclerView.Adapter<MotionFindAdapter.ViewHolder> {

    private Context context;
    private List<FindLessonBean> list = new ArrayList<>();

    public MotionFindAdapter(Context context, List<FindLessonBean> list) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_motion_find, parent, false);
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
        @BindView(R.id.img_play)
        ImageView imgPlay;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_position)
        TextView tvPosition;
        @BindView(R.id.tv_study)
        TextView tvStudy;
        FindLessonBean findLessonBean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            findLessonBean = list.get(pos);
            GlidUtils.setGrid(context,findLessonBean.getCoverImg(),imgUrl);
            tvTitle.setText(findLessonBean.getTitle());
            tvPosition.setText(findLessonBean.getTeacherName()+"  "+findLessonBean.getTeacherDesc());
            tvStudy.setText(DateUtil.getNumByInteger(findLessonBean.getStudyPersons())+"人加入学习");
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
