package com.jsjlzj.wayne.adapter.recycler.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.jsjlzj.wayne.R;
import com.netease.nim.uikit.common.ToastHelper;
import com.netease.nim.uikit.common.media.imagepicker.loader.GlideImageLoader;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: HomeVideoAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/1/15 15:52
 */
public class HomeVideoAdapter extends RecyclerView.Adapter<HomeVideoAdapter.ViewHolder> {

    private List<String> list = new ArrayList<>();
    private Context context;

    public HomeVideoAdapter(Context context) {
        this.list.add("增肌饮食");
        this.list.add("减脂饮食");
        this.list.add("增肌训练");
        this.list.add("减脂训练");
        this.list.add("胸部");
        this.list.add("肩部");
        this.list.add("背部");
        this.list.add("手臂");
        this.list.add("Vlog");
        this.list.add("有氧");
        this.list.add("核心");
        this.list.add("腿部");
        this.list.add("热身");
        this.list.add("拉伸");
        this.context = context;
    }

    @NonNull
    @Override
    public HomeVideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeVideoAdapter.ViewHolder holder, int position) {
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

        void bindView(Context context,List<String> list, int pos) {
            GlideImageLoader.displayAlbumThumb(imgType,  "", com.netease.nim.uikit.R.drawable.nim_image_default,2);
            String str = list.get(pos);
            tvType.setText(str);
            itemView.setOnClickListener(v -> {
                ToastHelper.showToast(context,str);
            });
        }
    }
}
