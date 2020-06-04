package com.jsjlzj.wayne.adapter.recycler.find;

import android.content.Context;
import android.graphics.Paint;
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
import com.jsjlzj.wayne.utils.DateUtil;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: FreeExperAdapter
 * @Description: 免费体验适配器
 * @Author: 曾海强
 * @CreateDate: 2020/4/19 20:05
 */
public class FreeExperAdapter extends RecyclerView.Adapter<FreeExperAdapter.ViewHolder> {

    private Context context;
    private List<FindLessonBean> list = new ArrayList<>();

    public FreeExperAdapter(Context context, List<FindLessonBean> list) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_free_exper, parent, false);
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
        @BindView(R.id.img_url)
        ImageView imgUrl;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_number)
        TextView tvNumber;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        FindLessonBean findLessonBean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            findLessonBean = list.get(pos);
            GlidUtils.setRoundGrid(context,findLessonBean.getCoverImg(),imgUrl,4);
            tvTitle.setText(findLessonBean.getTitle());
            tvNumber.setText(DateUtil.getNumByInteger(findLessonBean.getPlayCount())+"次播放量");
            tvPrice.setText(findLessonBean.getOriginPrice());
            tvPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//中划线
            tvPrice.getPaint().setAntiAlias(true); //去掉锯齿
            itemView.setOnClickListener(v -> {
                WebViewContainerActivity.go2this(context,findLessonBean.getTitle(), HttpConstant.WEB_URL_NEW_COURSE_DETAIL+findLessonBean.getId(),
                        WebViewContainerFragment.TYPE_NEW_COURSE_DETAIL);
//                if (listener != null) {
//                    listener.onItemClick(findLessonBean);
//                }
            });
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

