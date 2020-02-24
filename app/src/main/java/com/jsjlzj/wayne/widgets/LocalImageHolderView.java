package com.jsjlzj.wayne.widgets;

import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.store.home.BannerBean;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.utils.GlidUtils;

/**
 *
 * @author zenghaiqiang
 * @date 19/09/26
 * 本地图片Holder例子
 */
public class LocalImageHolderView extends Holder<BannerBean> {
    private ImageView imageView;

    public LocalImageHolderView(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        imageView =itemView.findViewById(R.id.ivPost);
    }

    @Override
    public void updateUI(BannerBean data) {
        GlidUtils.setCircleGrid(MyApp.ME.getApplicationContext(),data.getUrl(),imageView);
    }
}
