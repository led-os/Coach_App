package com.jsjlzj.wayne.adapter.recycler.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.find.FindLessonBean;
import com.jsjlzj.wayne.entity.find.FindTrainerBean;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: SelectTrainAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/6/22 22:23
 */
public class SelectTrainAdapter extends RecyclerView.Adapter<SelectTrainAdapter.ViewHolder> {


    private Context context;
    private List<FindTrainerBean.DataBean> list = new ArrayList<>();
    private int selectPos = -1;

    public SelectTrainAdapter(Context context, List<FindTrainerBean.DataBean> list) {
        this.context = context;
        this.list.addAll(list);
    }


    public void setData(List<FindTrainerBean.DataBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void setSelectTrainId(int selectTrainId){
        selectPos = 0;
        List<FindTrainerBean.DataBean> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            FindTrainerBean.DataBean bean = list.get(i);
            if(bean.getId() == selectTrainId){
                newList.add(0,bean);
            }else {
                newList.add(bean);
            }
        }
        setData(newList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_select_store, parent, false);
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

        @BindView(R.id.img_store)
        ImageView imgStore;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.img_select)
        ImageView imgSelect;
        FindTrainerBean.DataBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            if(selectPos == pos){
                imgSelect.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_find_store_select));
            }else {
                imgSelect.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_find_store_no_select));
            }
            GlidUtils.setRoundGrid(context,bean.getHeadImg(),imgStore,2);
            tvName.setText(bean.getName());
            itemView.setOnClickListener(v -> {
                selectPos = 0;
                transListToFirst(pos);
                if(listener != null){
                    listener.onItemClick(bean);
                }
            });
        }
    }


    public void transListToFirst(int pos){
        List<FindTrainerBean.DataBean> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            FindTrainerBean.DataBean bean = list.get(i);
            if(pos == i){
                newList.add(0,bean);
            }else {
                newList.add(bean);
            }
        }
        setData(newList);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {

        void onItemClick(FindTrainerBean.DataBean bean);
    }
}
