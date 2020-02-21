package com.jsjlzj.wayne.adapter.recycler.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * @ClassName: MineSignUpAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/19 21:32
 */
public class MineSignUpAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_MATCH = 1;
    public static final int TYPE_COURSE = 2;

    private List<String> list = new ArrayList<>();
    private Context context;
    /**
     * 0 :全部显示  1 ：只显示赛事   2 ：只显示课程
     */
    private int showType ;

    public MineSignUpAdapter(Context context,List<String> list) {
        this.list.addAll(list);
        this.context = context;
    }

    public void setData(List<String> list){
        if(list != null && list.size() > 0){
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void setShowType(int showType) {
        this.showType = showType;
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        String bean = list.get(position);
        if("1".equals(bean)){
            return TYPE_MATCH;
        }else {
            return TYPE_COURSE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == TYPE_MATCH) {
            view = LayoutInflater.from(context).inflate(R.layout.item_sign_up_match, parent, false);
            return new MatchViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_sign_up_course, parent, false);
            return new CourseViewHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MatchViewHolder) {
            ((MatchViewHolder) holder).bindView(position);
        } else if (holder instanceof CourseViewHolder) {
            ((CourseViewHolder) holder).bindView(position);
        }

    }

    @Override
    public int getItemCount() {
        return getCountByShowType(showType);
    }


    private int getCountByShowType(int showType){
        if(showType == 0){
            return list != null ? list.size() : 0;
        }else if(showType == 1){
            int size = 0;
            for (int i = 0 ; i < list.size() ; i++ ){
                if(list.get(i).equals("1")){
                    size++;
                }
            }
            return size;
        }else {
            int size = 0;
            for (int i = 0 ; i < list.size() ; i++ ){
                if(list.get(i).equals("2")){
                    size++;
                }
            }
            return size;
        }
    }

    class MatchViewHolder extends RecyclerView.ViewHolder {


        public MatchViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {

        }


    }

    class CourseViewHolder extends RecyclerView.ViewHolder {


        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {

        }
    }

}
