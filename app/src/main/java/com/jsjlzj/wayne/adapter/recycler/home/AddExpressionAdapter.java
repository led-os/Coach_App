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
import com.jsjlzj.wayne.entity.trainer.ExpressionBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: AddExpressionAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/20 9:31
 */
public class AddExpressionAdapter extends RecyclerView.Adapter<AddExpressionAdapter.ViewHolder> {

    private Context context;
    private List<ExpressionBean> list = new ArrayList<>();

    public AddExpressionAdapter(Context context) {
        this.context = context;
        initList();
    }

    private void initList() {
        this.list.add(new ExpressionBean(0,"不显示"));
        this.list.add(new ExpressionBean(R.drawable.face_01,"不忘初心"));
        this.list.add(new ExpressionBean(R.drawable.face_02,"心情不错"));
        this.list.add(new ExpressionBean(R.drawable.face_03,"太酸爽了"));
        this.list.add(new ExpressionBean(R.drawable.face_04,"保持状态"));
        this.list.add(new ExpressionBean(R.drawable.face_05,"绝不松懈"));
        this.list.add(new ExpressionBean(R.drawable.face_06,"累成狗"));
        this.list.add(new ExpressionBean(R.drawable.face_07,"放飞自我"));
        this.list.add(new ExpressionBean(R.drawable.face_08,"克服懒惰"));
        this.list.add(new ExpressionBean(R.drawable.face_09,"全靠意念"));
        this.list.add(new ExpressionBean(R.drawable.face_10,"神清气爽"));
        this.list.add(new ExpressionBean(R.drawable.face_11,"该减肥了"));
        this.list.add(new ExpressionBean(R.drawable.face_12,"神游中"));
        this.list.add(new ExpressionBean(R.drawable.face_13,"不开心"));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_all_expression, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_face)
        ImageView imgFace;
        @BindView(R.id.tv_des)
        TextView tvDes;
        private ExpressionBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            tvDes.setText(bean.getExpressionDes());
            if(bean.getExpressionRes() != 0){
                imgFace.setVisibility(View.VISIBLE);
                imgFace.setImageDrawable(ContextCompat.getDrawable(context,bean.getExpressionRes()));
            }else {
                imgFace.setVisibility(View.GONE);
            }
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(bean);
                }

            });
        }
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {

        void onItemClick(ExpressionBean str);
    }
}
