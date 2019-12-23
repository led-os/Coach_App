package com.jsjlzj.wayne.ui.store.personal.storeinfo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.store.talent.position.RecruitActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ImageViewActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {

    public static void go2this(Activity context, int position, List<String> list) {
        Intent intent = new Intent(context, ImageViewActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("list", (Serializable) list);
        context.startActivityForResult(intent, RecruitActivity.FLAG_RECRUIT_CONTENT);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_image_view;
    }
    private ViewPager viewPager;
    private TextView tvCount;
    @Override
    protected void initViewAndControl() {
        viewPager=findView(R.id.view_Pager);
        tvCount=findView(R.id.pic_count);
        int position  = getIntent().getIntExtra("position",0);
        List<String> strs= (List<String>) getIntent().getSerializableExtra("list");
        if(strs!=null&&strs.size()>0){
            initPopuWindow(position,strs);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    private void initPopuWindow(int position, final List<String> pics) {
        tvCount.setText(position+"/"+pics.size());
        List<ImageView> imageViews=new ArrayList<>();
        for (int i = 0; i < pics.size(); i++) {
            PhotoView iv=new PhotoView(this);
            imageViews.add(iv);
            iv.enable();
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
        MyViewPagerAdapter adapter=new MyViewPagerAdapter(this,imageViews,pics);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tvCount.setText(position+1+"/"+pics.size());
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public class MyViewPagerAdapter extends PagerAdapter {
        private Context context;
        private List<ImageView> list;
        private List<String> stringList;
        public MyViewPagerAdapter(Context context, List<ImageView> list,List<String> stringList) {
            this.context = context;
            this.list = list;
            this.stringList = stringList;
        }

        @Override
        public int getCount() {
            return list==null?0:list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv=list.get(position);
            String url=stringList.get(position);
            Glide.with(context).load(url).error(R.mipmap.ic_launcher).into(iv);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView(list.get(position));
        }

    }
}
