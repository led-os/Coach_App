package com.jsjlzj.wayne.adapter.recycler.home;

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
import com.jsjlzj.wayne.entity.store.home.CategoryBean;
import com.netease.nim.uikit.common.ToastHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: DriedTypeAdapter
 * @Description: 干货类型
 * @Author: 曾海强
 * @CreateDate: 2020/2/8 11:50
 */
public class DriedTypeAdapter extends RecyclerView.Adapter<DriedTypeAdapter.ViewHolder> {

    private List<CategoryBean> list = new ArrayList<>();
    private Context context;

    public DriedTypeAdapter(Context context,List<CategoryBean> list) {
        this.list = list;
        this.context = context;
//        if(type == 0){
//            this.list.add("增肌");1004
//            this.list.add("减脂");1005
//            this.list.add("vlog");1006
//            this.list.add("励志");
//        }else if(type == 1){
//            this.list.add("训练");
//            this.list.add("营养");
//            this.list.add("资讯");
//            this.list.add("女性");
//        }else {
//            this.list.add("运动器械");
//            this.list.add("运动服务");
//            this.list.add("运动补给");
//            this.list.add("运动食品");
//        }
        this.context = context;
    }

    public void setData(List<CategoryBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DriedTypeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dried_type, parent, false);
        return new DriedTypeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DriedTypeAdapter.ViewHolder holder, int position) {
        holder.bindView(context,list,position);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_type)
        ImageView imgType;
        @BindView(R.id.tv_type)
        TextView tvType;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(Context context, List<CategoryBean> list, int pos) {
            CategoryBean bean = list.get(pos);
            String str = bean.getName();
            if("增肌".equals(str)){
                imgType.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_train_one));
            }else if("减脂".equals(str)){
                imgType.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_delete_zhi));
            }else if("vLog".equals(str)){
                imgType.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_vlog));
            }else if("励志".equals(str)){
                imgType.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_improvement));
            }else if("训练".equals(str)){
                imgType.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_train));
            }else if("营养".equals(str)){
                imgType.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_nutrition));
            }else if("资讯".equals(str)){
                imgType.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_information));
            }else if("女性".equals(str)){
                imgType.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_women));
            }else if("运动器械".equals(str)){
                imgType.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_ydqc));
            }else if("运动服务".equals(str)){
                imgType.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_ydfs));
            }else if("运动补给".equals(str)){
                imgType.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_ydbj));
            }else if("运动食品".equals(str)){
                imgType.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_ydsp));
            }
//            GlidUtils.setCircleGrid(context,str.getUrl(),imgType);
            tvType.setText(bean.getName());
            itemView.setOnClickListener(v -> {
                ToastHelper.showToast(context,bean.getName());
            });
        }
    }
}
