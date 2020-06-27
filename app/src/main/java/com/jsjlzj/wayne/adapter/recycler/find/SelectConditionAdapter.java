package com.jsjlzj.wayne.adapter.recycler.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.find.FindStoreConditionBean;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: SelectConditionAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/6/26 14:12
 */
public class SelectConditionAdapter extends RecyclerView.Adapter<SelectConditionAdapter.ViewHolder> {


    private List<FindStoreConditionBean.DataBean> list = new ArrayList<>();
    private Context context;
    private FindStoreConditionBean.DataBean currBean;

    public SelectConditionAdapter(Context context, List<FindStoreConditionBean.DataBean> list) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_select_condition, parent, false);
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.rv_condition)
        RecyclerView rvCondition;
        FindStoreConditionBean.DataBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(int pos) {
            bean = list.get(pos);
            tvType.setText(bean.getName());
            rvCondition.setLayoutManager(new GridLayoutManager(context,4));
            ConditionAdapter adapter = new ConditionAdapter(context,bean.getChildren(),bean.getSelectId());
            rvCondition.setAdapter(adapter);
            adapter.setListener(bean -> {
                this.bean.setSelectId(bean.getId());
            });

        }
    }

    public FindStoreConditionBean.DataBean getCurrBean() {
        return currBean;
    }


    public String getSelectCondition(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            FindStoreConditionBean.DataBean bean = list.get(i);
            if(bean.getSelectId() != -1){
                stringBuilder.append(bean.getSelectId());
                stringBuilder.append(",");
            }
        }
        if(stringBuilder.length() > 1){
            return stringBuilder.substring(0,stringBuilder.length() -1);
        }
        return "";
    }

    public void initSelectCondition(){
        for (int i = 0; i < list.size(); i++) {
            FindStoreConditionBean.DataBean bean = list.get(i);
            bean.setSelectId(-1);
        }
        notifyDataSetChanged();
    }
}
