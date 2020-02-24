package com.jsjlzj.wayne.ui.mvp.home;

import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.home.AmoySchoolBean;
import com.jsjlzj.wayne.entity.store.home.RecommendBean;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BaseView;

/**
 * @ClassName: HomeView
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/1/14 15:58
 */
public interface HomeView extends BaseView {


    default void getHomeRecommendSuccess(MdlBaseHttpResp<RecommendBean> resp){}


    default void getAmoySchoolSuccess(MdlBaseHttpResp<AmoySchoolBean> resp){}
}
