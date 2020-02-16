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
import com.jsjlzj.wayne.widgets.CustomGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: 猜你喜欢Adapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/1/17 15:54
 */
public class HomeLikeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_ONE = 1;
    public static final int TYPE_OTHER = 2;

    private boolean isShowBigOne = false;
    private boolean isAllOne = false;
    private boolean isShowTime = true;

    private List<String> list = new ArrayList<>();
    private Context context;

    public HomeLikeAdapter(Context context,List<String> list) {
        this.list = list;
        this.context = context;
    }

    public void setShowBigOne(boolean showBigOne) {
        isShowBigOne = showBigOne;
    }


    public void setAllOne(boolean allOne) {
        isAllOne = allOne;
    }

    public void setShowTime(boolean showTime) {
        isShowTime = showTime;
    }

    @Override
    public int getItemViewType(int position) {
        if(isAllOne){
            return TYPE_ONE;
        }
        if (position == 0 && isShowBigOne) {
            return TYPE_ONE;
        } else {
            return TYPE_OTHER;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == TYPE_ONE) {
            view = LayoutInflater.from(context).inflate(R.layout.item_home_like_one, parent, false);
            return new OneViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_home_like_other, parent, false);
            return new OtherViewHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OneViewHolder) {
            ((OneViewHolder) holder).bindView(position);
        } else if (holder instanceof OtherViewHolder) {
            ((OtherViewHolder) holder).bindView(position);
        }

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class OneViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_one)
        ImageView imgOne;
        @BindView(R.id.img_play)
        ImageView imgPlay;
        @BindView(R.id.img_play_num)
        ImageView imgPlayNum;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_play_time)
        TextView tvPlayTime;
        @BindView(R.id.img_author)
        ImageView imgAuthor;
        @BindView(R.id.tv_author)
        TextView tvAuthor;
        @BindView(R.id.tv_time)
        TextView tvTime;

        public OneViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {

        }


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
        }
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
