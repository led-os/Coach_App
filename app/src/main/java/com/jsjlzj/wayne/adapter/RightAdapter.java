package com.jsjlzj.wayne.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.store.MdlPositionType;

import java.util.List;

public class RightAdapter extends BaseAdapter {
//    private List<MdlPositionType.DataBean> list;
    private List<MdlPositionType.DataBean.PositionTypeBean> list;//by
    private Context mContext;
    private LayoutInflater inflater;
    private int selectItem=0;

    public RightAdapter(Context mContext) {
//        if(null==list){
//            list=new ArrayList<>();
//        }else {
//            this.list = list;
//        }
        this.mContext = mContext;
        inflater=LayoutInflater.from(mContext);

    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
        notifyDataSetChanged();
    }

//    public void setList(List<MdlPositionType.DataBean> list) {
//        this.list = list;
//    }

    //by
    public void setList(List<MdlPositionType.DataBean.PositionTypeBean> list) {
        this.list = list;
    }

//    public void addData(MdlPositionType.DataBean data){
//        if(data!=null){
//            list.clear();
//            list.add(data);
//            notifyDataSetChanged();
//        }
//    }

//    @Override
////    public int getCount() {
////        if(null==list||list.size()==0||list.get(selectItem).getPositionTypeList()==null||list.get(selectItem).getPositionTypeList().size()==0){
////            return 0;
////        }else{
////        return list.get(selectItem).getPositionTypeList().size();
////
////        }
////    }

    //by
  @Override
    public int getCount() {
        if(null==list||list.size()==0){
            return 0;
        }else{
        return list.size();

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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(null==convertView){
            convertView=inflater.inflate(R.layout.item_recruit_type,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tvContent.setText(((MdlPositionType.DataBean.PositionTypeBean)getItem(position)).getName());
        if(selectItem==position){
            viewHolder.tvContent.setTextColor(mContext.getResources().getColor(R.color.login_btn_l));
        }else{
            viewHolder.tvContent.setTextColor(mContext.getResources().getColor(R.color.text_gray));

        }
        return convertView;
    }

    static class ViewHolder{
        TextView tvContent;

        public ViewHolder(View view) {
            tvContent=view.findViewById(R.id.tvContent);
        }
    }
}
