package com.jsjlzj.wayne.adapter.recycler.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.find.FindLessonBean;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: DayStudyAdapter
 * @Description: 每日一学适配
 * @Author: 曾海强
 * @CreateDate: 2020/4/19 20:04
 */
public class DayStudyAdapter extends RecyclerView.Adapter<DayStudyAdapter.ViewHolder> {


    private Context context;
    private List<FindLessonBean> hotList = new ArrayList<>();
    private List<FindLessonBean> hotListeningList = new ArrayList<>();

    /**
     *
     * @param context  上下文
     * @param hotList  热门课程榜
     * @param hotListeningList 热门听课榜
     */
    public DayStudyAdapter(Context context, List<FindLessonBean> hotList, List<FindLessonBean>  hotListeningList) {
        this.context = context;
        this.hotList.addAll(hotList);
        this.hotListeningList.addAll(hotListeningList);
    }


    public void setData(List<FindLessonBean> hotList, List<FindLessonBean>  hotListeningList) {
        this.hotList.clear();
        this.hotListeningList.clear();
        this.hotList.addAll(hotList);
        this.hotListeningList.addAll(hotListeningList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_day_study, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_top)
        ImageView imgTop;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.rel_bottom)
        RelativeLayout relBottom;
        @BindView(R.id.view_cover)
        View viewCover;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.img_url)
        ImageView imgUrl;
        @BindView(R.id.rel_content)
        RelativeLayout relContent;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {


            if(pos == 0){
                viewCover.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_solid_807a9aaf_5));
                tvType.setText("热门课程榜");
                relContent.setBackground(ContextCompat.getDrawable(context,R.color.color_9FACB2));
                relBottom.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_solid_6f8a97_6));
                tvName.setText(getTopListText(hotList));
                if(hotList != null && hotList.size() > 0){
                    GlidUtils.setRoundGrid(context,hotList.get(0).getCoverImg(),imgTop,5);
                    GlidUtils.setRoundGrid(context,hotList.get(0).getCoverImg(),imgUrl,5);
                }
            }else if(pos == 1){
                viewCover.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_solid_80ceca6f_5));
                tvType.setText("热门听课榜");
                relContent.setBackground(ContextCompat.getDrawable(context,R.color.color_b2a37d));
                relBottom.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_solid_ccb98a_6));
                tvName.setText(getTopListText(hotListeningList));
                if(hotListeningList != null && hotListeningList.size() > 0){
                    GlidUtils.setRoundGrid(context,hotListeningList.get(0).getCoverImg(),imgTop,5);
                    GlidUtils.setRoundGrid(context,hotListeningList.get(0).getCoverImg(),imgUrl,5);
                }
            }

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick("",pos);
                }

            });
        }
    }


    private String getTopListText(List<FindLessonBean> list){
        if(list != null && list.size() > 0){
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0;i < list.size() ; i++) {
                FindLessonBean bean = list.get(i);
                stringBuilder.append(i+1);
                stringBuilder.append(".");
                stringBuilder.append(bean.getTitle());
                stringBuilder.append("\n");
            }
            return stringBuilder.substring(0,stringBuilder.length() - 1);
        }
        return "";
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {

        void onItemClick(String bean,int pos);
    }
}
