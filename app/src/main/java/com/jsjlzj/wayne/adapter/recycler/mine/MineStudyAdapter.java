package com.jsjlzj.wayne.adapter.recycler.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.trainer.MineStudyBean;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: MineStudyAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/19 0:19
 */
public class MineStudyAdapter extends RecyclerView.Adapter<MineStudyAdapter.ViewHolder> {

    private Context context;
    private List<MineStudyBean.DataBean> list = new ArrayList<>();

    public MineStudyAdapter(Context context, List<MineStudyBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }


    public void setData(List<MineStudyBean.DataBean> list){
        this.list = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mine_study, parent, false);
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
        @BindView(R.id.img_pic)
        ImageView imgPic;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_course)
        TextView tvCourse;
        @BindView(R.id.rel_item)
        RelativeLayout relItem;
        private MineStudyBean.DataBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            GlidUtils.setRoundGrid(context,bean.getCoverImg(),imgPic,4);
            tvTitle.setText(bean.getTitle());
            tvCourse.setText("共"+bean.getLessonCount()+"节课");
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            relItem.setOnClickListener(clickListener);
        }


        public MyViewClickListener clickListener = new MyViewClickListener();


        private class MyViewClickListener extends OnMultiClickListener {
            @Override
            public void OnMultiClick(View view) {
                if (listener == null) {
                    return;
                }
                switch (view.getId()) {
                    case R.id.rel_item:
                        listener.onItemClick(bean);
                        break;
                    default:
                        break;
                }
            }
        }

    }


    public interface OnItemClickListener {
        void onItemClick(MineStudyBean.DataBean bean);


    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
