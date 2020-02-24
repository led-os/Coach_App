package com.jsjlzj.wayne.adapter.recycler.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.store.home.RecommendBean;
import com.jsjlzj.wayne.utils.GlidUtils;
import com.jsjlzj.wayne.widgets.CustomGridLayoutManager;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: HomeInformationAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/24 14:26
 */
public class HomeInformationAdapter extends RecyclerView.Adapter<HomeInformationAdapter.OtherViewHolder> {

    public static final int TYPE_ONE = 1;
    public static final int TYPE_OTHER = 2;

    private boolean isShowTime = true;

    private List<RecommendBean.InformationBean> list = new ArrayList<>();
    private Context context;

    public HomeInformationAdapter(Context context,List<RecommendBean.InformationBean> list) {
        this.list.addAll(list);
        this.context = context;
    }


    public void setData(List<RecommendBean.InformationBean> list){
        if(list != null && list.size() > 0){
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }



    @NonNull
    @Override
    public HomeInformationAdapter.OtherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(context).inflate(R.layout.item_home_like_other, parent, false);
        return new OtherViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull OtherViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }


    class OtherViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_one)
        ImageView imgOne;
        @BindView(R.id.tv_play_time)
        TextView tvPlayTime;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.img_author)
        ImageView imgAuthor;
        @BindView(R.id.tv_author)
        TextView tvAuthor;
        @BindView(R.id.tv_play_num)
        TextView tvPlayNum;
        @BindView(R.id.tv_time)
        TextView tvTime;
        private RecommendBean.InformationBean bean;

        public OtherViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            if(isShowTime){
                tvPlayTime.setVisibility(View.VISIBLE);
            }else {
                tvPlayTime.setVisibility(View.GONE);
            }
            bean = list.get(pos);
            GlidUtils.setRoundGrid(context,bean.getCoverImg(),imgOne,2);
            tvTitle.setText(bean.getName());
            tvPlayNum.setText(bean.getViewCount()+ "次阅读");
            tvTime.setText(bean.getCreateTime());
            tvPlayTime.setVisibility(View.GONE);

            GlidUtils.setRoundGrid(context,bean.getChannelAvatar(),imgAuthor,23);
            tvAuthor.setText(bean.getChannelName());

            itemView.setOnClickListener(v -> {
                if(listener != null){
                    listener.onItemClick(bean);
                }
            });
        }
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener{

        void onItemClick(RecommendBean.InformationBean bean);
    }


    /**
     * 判断Item 所占的宽度
     *
     * @param context
     * @return
     */
    public CustomGridLayoutManager getLayoutManager(Context context) {
        CustomGridLayoutManager layoutManager = new CustomGridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (getItemViewType(position)) {
                    case TYPE_ONE:
                        return 2;
                    case TYPE_OTHER:
                    default:
                        return 1;
                }
            }
        });
        return layoutManager;
    }
}
