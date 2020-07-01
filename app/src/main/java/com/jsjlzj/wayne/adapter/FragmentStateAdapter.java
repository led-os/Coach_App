package com.jsjlzj.wayne.adapter;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.ui.store.find.PictureDetailFragment;
import com.jsjlzj.wayne.utils.imgeutils.PreviewerViewPager;

import java.util.HashMap;
import java.util.List;

/**
 * Description: java类作用描述
 * Author: 曾海强
 * CreateDate: 2019/6/3 15:44
 * @author zenghaiqiang
 */
public class FragmentStateAdapter extends FragmentStatePagerAdapter {

    private List<String> imgIds;
    private List<String> transactionIdList;
    private HashMap<Integer, PictureDetailFragment> map = new HashMap<>();
    private PreviewerViewPager vp;

    public FragmentStateAdapter(FragmentManager manager, List<String> list, PreviewerViewPager viewPager) {
        super(manager);
        this.imgIds = list;
        vp = viewPager;
    }

    @Override
    public PictureDetailFragment getItem(int position) {
        PictureDetailFragment fragment;
        if (map.containsKey(position)) {
            fragment = map.get(position);
        } else {
            fragment = new PictureDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString(ExtraConstant.EXTRA_PIC_URL, imgIds.get(position));
            fragment.setArguments(bundle);
            fragment.setViewPager(vp);
            map.put(position, fragment);
        }
        return fragment;
    }

    /**
     * 移出 Fragment
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (map.containsKey(position)) map.remove(position);
        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return imgIds == null ? 0 : imgIds.size();
    }


}
