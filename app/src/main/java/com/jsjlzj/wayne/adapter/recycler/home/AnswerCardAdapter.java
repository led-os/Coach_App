package com.jsjlzj.wayne.adapter.recycler.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.store.learn.AnswerBean;
import com.jsjlzj.wayne.widgets.CustomGridLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: AnswerCardAdapter
 * @Description: 答题卡适配器
 * @Author: 曾海强
 * @CreateDate: 2020/2/16 0:05
 */
public class AnswerCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_TITLE = 1;
    public static final int TYPE_SUBJECT = 2;


    private HashMap<String,AnswerBean> hashMap = new HashMap<>();
    private List<AnswerBean> list = new ArrayList<>();
    private Context context;

    public AnswerCardAdapter(List<AnswerBean> list, Context context) {
        initList(list);
        this.context = context;
    }

    /**
     * 初始化列表
     * @param list
     */
    private void initList(List<AnswerBean> list) {
        if(list == null || list.size() <= 0){
            return;
        }
        for (int i = 0; i < list.size() ; i++){
            AnswerBean bean = list.get(i);
            String titleType = getTitleByType(bean.getType());
            if(hashMap.containsKey(titleType)){
                bean.setTitleType(false);
                this.list.add(bean);
            }else {
                AnswerBean newBean = new AnswerBean();
                newBean.setName(titleType);
                newBean.setTitleType(true);
                this.list.add(newBean);
                this.list.add(bean);
                hashMap.put(titleType,newBean);
            }
        }
    }


    private String getTitleByType(int type){
        String titleStr = "";
        if(type == 1){
            titleStr = "一、单选题";
        }else if(type == 2) {
            titleStr = "二、多选题";
        }else if(type == 3) {
            titleStr = "三、判断题";
        }else if(type == 4) {
            titleStr = "四、填空题";
        }
        return titleStr;
    }

    @Override
    public int getItemViewType(int position) {
        AnswerBean answerBean  =  list.get(position);
        if (answerBean.isTitleType()) {
            return TYPE_TITLE;
        } else {
            return TYPE_SUBJECT;
        }
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == TYPE_TITLE) {
            view = LayoutInflater.from(context).inflate(R.layout.item_answer_card_title, parent, false);
            return new TitleViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_answer_card_subject, parent, false);
            return new SubjectViewHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TitleViewHolder) {
            ((TitleViewHolder) holder).bindView(position);
        } else if (holder instanceof SubjectViewHolder) {
            ((SubjectViewHolder) holder).bindView(position);
        }

    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class TitleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;

        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            AnswerBean bean = list.get(pos);
            tvTitle.setText(bean.getName());
        }

    }

    class SubjectViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_subject)
        TextView tvSubject;
        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            AnswerBean bean = list.get(pos);
            tvSubject.setText(bean.getTopicNum()+"");
            if(bean.isAnswer()){
                tvSubject.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_stroke_4a9bfa_21));
                tvSubject.setTextColor(ContextCompat.getColor(context,R.color.color_4F9BFA));
            }else {
                tvSubject.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_stroke_dddddd_21));
                tvSubject.setTextColor(ContextCompat.getColor(context,R.color.color_666666));
            }
            itemView.setOnClickListener(v -> {
                if(listener != null && bean.isAnswer()){
                    listener.onItemClick(bean);
                }
            });
        }
    }


    /**
     * 判断Item 所占的宽度
     *
     * @param context
     * @return
     */
    public CustomGridLayoutManager getLayoutManager(Context context) {
        CustomGridLayoutManager layoutManager = new CustomGridLayoutManager(context, 6, GridLayoutManager.VERTICAL, false);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (getItemViewType(position)) {
                    case TYPE_TITLE:
                        return 6;
                    case TYPE_SUBJECT:
                    default:
                        return 1;
                }
            }
        });
        return layoutManager;
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(AnswerBean bean);
    }
}
