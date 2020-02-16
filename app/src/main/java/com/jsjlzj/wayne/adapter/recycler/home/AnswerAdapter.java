package com.jsjlzj.wayne.adapter.recycler.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.store.SelectBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: AnswerAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/15 11:36
 */
public class AnswerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_SELECT = 0;
    public static final int VIEW_TYPE_PACE = 1;

    private Context context;
    private List<SelectBean> list = new ArrayList<>();
    /**
     * 0 单选  1 ： 多选   2 ： 判断   3 填空
     */
    private int type;

    public AnswerAdapter(Context context, List<SelectBean> list) {
        this.context = context;
        this.list.addAll(list);
    }

    public void setType(int type) {
        this.type = type;
    }


    @Override
    public int getItemViewType(int position) {
        if(type == 3){
            return VIEW_TYPE_PACE;
        }else {
            return VIEW_TYPE_SELECT;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if(VIEW_TYPE_SELECT == viewType){
            view = LayoutInflater.from(context).inflate(R.layout.item_answer, parent, false);
            return new ViewHolder(view);
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.item_answer_pace, parent, false);
            return new PaceViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHolder){
            ((ViewHolder)holder).bindView(position);
        }else if(holder instanceof PaceViewHolder){
            ((PaceViewHolder)holder).bindView(position);
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public void setData(List<SelectBean> list){
        if(list != null && list.size() > 0){
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.ll_item)
        LinearLayout llItem;
        @BindView(R.id.img_select)
        ImageView imgSelect;
        private SelectBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            if(bean.isSelect()){
                tvName.setTextColor(ContextCompat.getColor(context,R.color.white));
                llItem.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_solid_696969_5));
            }else {
                tvName.setTextColor(ContextCompat.getColor(context,R.color.color_333333));
                llItem.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_stroke_dddddd_5));
            }
            tvName.setText(bean.getName());

            itemView.setOnClickListener(v -> {
                    onItemClick(pos);
            });
        }

        private void onItemClick(int pos) {
            if(bean.isSelect()){
                bean.setSelect(false);
            }else {
                bean.setSelect(true);
            }
            setSelectPos(pos);
        }
    }


    class PaceViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.et_name)
        EditText etName;
        @BindView(R.id.ll_item)
        LinearLayout llItem;
        @BindView(R.id.img_select)
        ImageView imgSelect;
        private SelectBean bean;

        public PaceViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bindView(int pos){
            bean = list.get(pos);
            etName.setText(bean.getName());
        }
    }


    private void setSelectPos(int pos){
        //单选
        if(type == 0 || type == 2){
            for (int i = 0; i < list.size() ; i++){
                SelectBean bean = list.get(i);
                if(i == pos){
                    bean.setSelect(true);
                }else {
                    bean.setSelect(false);
                }
            }
        }
        notifyDataSetChanged();
    }

    private List<SelectBean> getSelectPos(){
        List<SelectBean> selectList = new ArrayList<>();
        for (int i = 0; i< list.size() ; i++){
            SelectBean bean = list.get(i);
            if(bean.isSelect()){
                selectList.add(bean);
            }
        }
        return selectList;
    }




    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {

        void onItemClick(SelectBean bean);
    }
}
