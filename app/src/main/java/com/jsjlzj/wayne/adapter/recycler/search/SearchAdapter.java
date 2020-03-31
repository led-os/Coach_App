package com.jsjlzj.wayne.adapter.recycler.search;

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
import com.jsjlzj.wayne.entity.store.home.VideoBean;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.utils.DateUtil;
import com.jsjlzj.wayne.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: SearchAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/10 10:50
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {


    private Context context;
    private List<VideoBean> list = new ArrayList<>();
    private boolean isShowDelete = false;

    public SearchAdapter(Context context, List<VideoBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setShowDelete(boolean showDelete) {
        isShowDelete = showDelete;
    }

    public void setData(List<VideoBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_dynamic, parent, false);
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

        @BindView(R.id.img_dynamic)
        ImageView imgDynamic;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.img_head)
        ImageView imgHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.img_collect)
        ImageView imgCollect;
        @BindView(R.id.tv_collect)
        TextView tvCollect;
        @BindView(R.id.rel_item)
        RelativeLayout relItem;
        @BindView(R.id.img_face)
        ImageView imgFace;
        @BindView(R.id.img_delete)
        ImageView imgDelete;
        private VideoBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            GlidUtils.setRoundGrid(context,bean.getCoverImg(),imgDynamic,2);
            GlidUtils.setCircleGrid(context,bean.getChannelAvatar(),imgHead);
            tvTitle.setText(bean.getName());
            tvName.setText(bean.getChannelName());
            tvCollect.setText(DateUtil.getNumByInteger(bean.getCollectCount()));
            if(bean.isCollect()){
                imgCollect.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.collected));
            }else {
                imgCollect.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.uncollected));
            }
            if(isShowDelete){
                imgDelete.setVisibility(View.VISIBLE);
            }else {
                imgDelete.setVisibility(View.GONE);
            }

            setFacePic(imgFace,bean.getMoodLabel());
            relItem.setOnClickListener(clickListener);
            imgHead.setOnClickListener(clickListener);
            tvName.setOnClickListener(clickListener);
            imgCollect.setOnClickListener(clickListener);
            tvCollect.setOnClickListener(clickListener);
            imgDelete.setOnClickListener(clickListener);
        }


        public MyViewClickListener clickListener = new MyViewClickListener();


        private class MyViewClickListener extends OnMultiClickListener {
            @Override
            public void OnMultiClick(View view) {
                if(listener == null) {
                    return;
                }
                switch (view.getId()) {
                    case R.id.rel_item:
                        listener.onItemClick(bean);
                        break;
                    case R.id.tv_name:
                    case R.id.img_head:
                        listener.onHearClick(bean);
                        break;
                    case R.id.img_collect:
                    case R.id.tv_collect:
                        if(bean.isCollect()){
                            bean.setCollect(false);
                            bean.setCollectCount(bean.getCollectCount() - 1);
                            tvCollect.setText(DateUtil.getNumByInteger(bean.getCollectCount()));
                            imgCollect.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.uncollected));
                        }else {
                            bean.setCollectCount(bean.getCollectCount() + 1);
                            tvCollect.setText(DateUtil.getNumByInteger(bean.getCollectCount()));
                            imgCollect.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.collected));
                            bean.setCollect(true);
                        }
                        listener.onFavoriteClick(bean);
                        break;
                    case R.id.img_delete:
                        listener.onDeleteClick(bean);
                        break;
                    default:
                        break;
                }
            }
        }

    }


    private void setFacePic(ImageView imgFace,String moodLabel){
        switch (moodLabel){
            case "1":
                imgFace.setVisibility(View.VISIBLE);
                imgFace.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.face_01));
                break;
            case "2":
                imgFace.setVisibility(View.VISIBLE);
                imgFace.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.face_02));
                break;
            case "3":
                imgFace.setVisibility(View.VISIBLE);
                imgFace.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.face_03));
                break;
            case "4":
                imgFace.setVisibility(View.VISIBLE);
                imgFace.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.face_04));
                break;
            case "5":
                imgFace.setVisibility(View.VISIBLE);
                imgFace.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.face_05));
                break;
            case "6":
                imgFace.setVisibility(View.VISIBLE);
                imgFace.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.face_06));
                break;
            case "7":
                imgFace.setVisibility(View.VISIBLE);
                imgFace.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.face_07));
                break;
            case "8":
                imgFace.setVisibility(View.VISIBLE);
                imgFace.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.face_08));
                break;
            case "9":
                imgFace.setVisibility(View.VISIBLE);
                imgFace.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.face_09));
                break;
            case "10":
                imgFace.setVisibility(View.VISIBLE);
                imgFace.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.face_10));
                break;
            case "11":
                imgFace.setVisibility(View.VISIBLE);
                imgFace.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.face_11));
                break;
            case "12":
                imgFace.setVisibility(View.VISIBLE);
                imgFace.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.face_12));
                break;
            case "13":
                imgFace.setVisibility(View.VISIBLE);
                imgFace.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.face_13));
                break;
                default:
                    imgFace.setVisibility(View.GONE);
                    break;

        }

    }


    public interface OnSearchItemClickListener {
        void onItemClick(VideoBean bean);

        void onHearClick(VideoBean bean);

        void onFavoriteClick(VideoBean bean);

        void onDeleteClick(VideoBean bean);

    }

    private OnSearchItemClickListener listener;

    public void setListener(OnSearchItemClickListener listener) {
        this.listener = listener;
    }
}
