package com.jsjlzj.wayne.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.store.MdlStoreInfo;

import java.util.List;

public class PicAdapter extends BaseAdapter {
    private final static int TYPE_ADD=1;
    private final static int TYPE_PIC=2;
    private int max=9;
    private OnAddPicClicklistener onAddPicClickListener;
    private List<MdlStoreInfo.DataBean.CompanyImagesBean> list;
    private Context mContext;
    private LayoutInflater inflater;

    public PicAdapter(Context mContext,OnAddPicClicklistener onAddPicClickListener) {
        this.mContext = mContext;
        this.onAddPicClickListener=onAddPicClickListener;
        inflater=LayoutInflater.from(mContext);
    }
    public void setMax(int max) {
        this.max = max;
    }

    public void setList(List<MdlStoreInfo.DataBean.CompanyImagesBean> list) {
        this.list = list;
    }

    public interface OnAddPicClicklistener {
        void onAddPicClick();
        void onDeletePicClick(int position);
    }


    @Override
    public int getItemViewType(int position) {
        if(isShowAddItem(position)){
            return TYPE_ADD;
        }else{
            return TYPE_PIC;
        }
    }
    private boolean isShowAddItem(int position){
        int size=list.size()==0?0:list.size();
        return position==size;
    }

    @Override
    public int getCount() {
        if(list==null) {
            return 0;
        }else{
            return list.size()<max ? list.size()+1 : list.size();

        }
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_photo_list, parent, false);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.image);
            holder.checkBox = (ImageView) convertView.findViewById(R.id.btnIcClose);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (getItemViewType(position)==TYPE_ADD){
            holder.imageView.setImageResource(R.drawable.dr_bg_photo_add);
            holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            holder.iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onAddPicClickListener.onAddPicClick();
                }
            });
//            Glide.with(mContext).load("").error(R.mipmap.camera).into(holder.iv);
            holder.checkBox.setVisibility(View.INVISIBLE);
        }else{
            holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            holder.checkBox.setVisibility(View.VISIBLE);
            String s = list.get(position).getThumbnail();
            if(!TextUtils.isEmpty(s)) {
                Glide.with(mContext).load(s).into(holder.imageView);
            }else{
                Glide.with(mContext).load(R.drawable.dr_bg_photo_add).into(holder.imageView);
            }
//            bitmap=BitmapFactory.decodeFile(list.get(position));
//            holder.iv.setImageBitmap(bitmap);

            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { ;
                    onAddPicClickListener.onDeletePicClick(position);

                }
            });
        }

        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
        ImageView checkBox;
    }

}
