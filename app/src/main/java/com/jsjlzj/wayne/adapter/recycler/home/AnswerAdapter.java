package com.jsjlzj.wayne.adapter.recycler.home;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.jsjlzj.wayne.entity.store.learn.SelectBean;
import com.netease.nim.uikit.common.ToastHelper;

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
     * 1 单选  2 ： 多选   3 ： 判断   4 填空
     */
    private int type;

    private boolean isShowCorrectAnswer = false;
    public AnswerAdapter(Context context, List<SelectBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setType(int type) {
        this.type = type;
    }


    @Override
    public int getItemViewType(int position) {
        if(type == 4){
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
            this.list = list;
            notifyDataSetChanged();
        }
    }

    private String userAnswer;

    public void setData(List<SelectBean> list,String wrongAnswer){
        if(list != null && list.size() > 0){
            this.list.clear();
            this.list = list;
            this.userAnswer = wrongAnswer;
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
            tvName.setText(bean.getK()+" "+bean.getC());
            if(isShowCorrectAnswer){
                if(type == 1 || type == 3){
                    if(!bean.getAnswer().equals(bean.getK()) && bean.isSelect()){
                        tvName.setTextColor(ContextCompat.getColor(context,R.color.white));
                        llItem.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_solid_f06966_5));
                        imgSelect.setVisibility(View.VISIBLE);
                        imgSelect.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_error));
                    }else if(bean.getAnswer().equals(bean.getK())){
                        tvName.setTextColor(ContextCompat.getColor(context,R.color.white));
                        llItem.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_solid_6abe83_5));
                        imgSelect.setVisibility(View.VISIBLE);
                        imgSelect.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_correct));
                    }else {
                        tvName.setTextColor(ContextCompat.getColor(context,R.color.color_333333));
                        llItem.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_stroke_dddddd_5));
                        imgSelect.setVisibility(View.GONE);
                    }
                }else if(type == 2){
                    if(!bean.getAnswer().contains(bean.getK()) && bean.isSelect()){
                        tvName.setTextColor(ContextCompat.getColor(context,R.color.white));
                        llItem.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_solid_f06966_5));
                        imgSelect.setVisibility(View.VISIBLE);
                        imgSelect.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_error));
                    }else if(bean.getAnswer().contains(bean.getK())){
                        tvName.setTextColor(ContextCompat.getColor(context,R.color.white));
                        llItem.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_solid_6abe83_5));
                        imgSelect.setVisibility(View.VISIBLE);
                        imgSelect.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_correct));
                    }else {
                        tvName.setTextColor(ContextCompat.getColor(context,R.color.color_333333));
                        llItem.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_stroke_dddddd_5));
                        imgSelect.setVisibility(View.GONE);
                    }                }
            }else {
                if(bean.isSelect()){
                    tvName.setTextColor(ContextCompat.getColor(context,R.color.white));
                    llItem.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_solid_696969_5));
                    imgSelect.setVisibility(View.GONE);
                }else {
                    tvName.setTextColor(ContextCompat.getColor(context,R.color.color_333333));
                    llItem.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_stroke_dddddd_5));
                    imgSelect.setVisibility(View.GONE);
                }
                if(!TextUtils.isEmpty(userAnswer) && userAnswer.contains(bean.getK())){
                    tvName.setTextColor(ContextCompat.getColor(context,R.color.white));
                    llItem.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_solid_696969_5));
                    bean.setSelect(true);
                    userAnswer = "";
                    imgSelect.setVisibility(View.GONE);
                }
                itemView.setOnClickListener(v -> {
                    onItemClick(pos);
                });
            }

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


    class PaceViewHolder extends RecyclerView.ViewHolder implements TextWatcher {
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
            etName.setText(bean.getK()+""+bean.getC());
            if(isShowCorrectAnswer){
                etName.setEnabled(false);
                etName.setFocusable(false);
                if(!bean.getAnswer().equals(bean.getK()) && bean.isSelect()){
                    etName.setTextColor(ContextCompat.getColor(context,R.color.white));
                    llItem.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_solid_f06966_5));
                    imgSelect.setVisibility(View.VISIBLE);
                    imgSelect.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_error));
                }else if(bean.getAnswer().equals(bean.getK())){
                    etName.setTextColor(ContextCompat.getColor(context,R.color.white));
                    llItem.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_solid_6abe83_5));
                    imgSelect.setVisibility(View.VISIBLE);
                    imgSelect.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_correct));
                }
            }else {
                etName.setText(userAnswer);
                etName.setEnabled(true);
                etName.setFocusable(true);
                etName.setTextColor(ContextCompat.getColor(context,R.color.color_333333));
                llItem.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_stroke_dddddd_5));
                imgSelect.setVisibility(View.GONE);
                etName.addTextChangedListener(this);
            }

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            if(s.toString().length() > 0){
                bean.setSelect(true);
            }else {
                bean.setSelect(false);
            }
            bean.setK(s.toString());
        }
    }


    private void setSelectPos(int pos){
        //单选
        if(type == 1 || type == 3){
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


    public String getResult(){
        StringBuilder result = new StringBuilder();
        if(type == 2){
            result.append("[");
        }
        for (int i = 0; i< list.size() ; i++){
            SelectBean bean = list.get(i);
            if(bean.isSelect()){
                if(type == 1 || type == 3){
                    result.append(bean.getK());
                    break;
                }else if(type == 2){
                    result.append("\""+bean.getK()+"\",");
                }else if(type == 4){
                    result.append(bean.getK());
                }
            }
        }
        if(type == 2){
            if(result.length() > 1){
                result.replace(result.length()-1,result.length(),"]");
            }else {
                ToastHelper.showToast(context,"请选择您的答案");
                return "";
            }
        }else if(type == 1 || type == 3){
            if(result.length() <= 0){
                ToastHelper.showToast(context,"请选择您的答案");
                return "";
            }
        }else if(type == 4){
            if(result.length() <= 0){
                ToastHelper.showToast(context,"请输入您的答案");
                return "";
            }
        }
        return result.toString();
    }


    public void setCorrectAnswer(boolean  isAnswer){
        this.isShowCorrectAnswer = isAnswer;
        notifyDataSetChanged();
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {

        void onItemClick(SelectBean bean);
    }
}
