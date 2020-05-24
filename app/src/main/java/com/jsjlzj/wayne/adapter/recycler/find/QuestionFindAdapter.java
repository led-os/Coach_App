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
 * @ClassName: QuestionFindAdapter
 * @Description: 发现中问题适配器
 * @Author: 曾海强
 * @CreateDate: 2020/4/19 21:08
 */
public class QuestionFindAdapter extends RecyclerView.Adapter<QuestionFindAdapter.ViewHolder> {

    private Context context;
    private List<FindLessonBean> list = new ArrayList<>();


    public QuestionFindAdapter(Context context, List<FindLessonBean> list) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_question_find, parent, false);
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

        FindLessonBean findLessonBean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            findLessonBean = list.get(pos);
            GlidUtils.setRoundGrid(context,findLessonBean.getTeacherAvatar(),imgHead,2);
            tvName.setText(findLessonBean.getTeacherName()+": "+findLessonBean.getTitle());
            itemView.setOnClickListener(v -> {
                WebViewContainerActivity.go2this(context,findLessonBean.getTitle(), HttpConstant.WEB_URL_NEW_COURSE_DETAIL+findLessonBean.getId(),
                        WebViewContainerFragment.TYPE_NEW_COURSE_DETAIL);

            });
//            imgHead.setOnClickListener(v -> {
//                WebViewContainerActivity.go2this(context,context.getResources().getString(R.string.teacher_home),HttpConstant.WEB_URL_NEW_TEACHER_DETAIL+findLessonBean.getId(),
//                        WebViewContainerFragment.TYPE_NEW_TEACHER_HOME);
//            });
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
