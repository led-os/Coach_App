package com.jsjlzj.wayne.ui.mvp.home;

import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.home.AmoySchoolBean;
import com.jsjlzj.wayne.entity.store.home.CategoryListBean;
import com.jsjlzj.wayne.entity.store.home.CategoryPageBean;
import com.jsjlzj.wayne.entity.store.home.RecommendBean;
import com.jsjlzj.wayne.entity.store.home.VideoPageBean;
import com.jsjlzj.wayne.entity.store.learn.AnswerRecordBean;
import com.jsjlzj.wayne.entity.store.learn.ChapterListBean;
import com.jsjlzj.wayne.entity.store.learn.ChapterSubjectListBean;
import com.jsjlzj.wayne.entity.store.learn.DoneChapterBean;
import com.jsjlzj.wayne.entity.store.learn.ExamSubjectListBean;
import com.jsjlzj.wayne.entity.store.learn.LearnBean;
import com.jsjlzj.wayne.entity.store.search.SearchBean;
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

    default void getMatchSuccess(MdlBaseHttpResp<AmoySchoolBean> resp){}


    default void getAmoyListSuccess(MdlBaseHttpResp<CategoryPageBean> resp){}


    default void getMatchListSuccess(MdlBaseHttpResp<CategoryPageBean> resp){}

    default void getDriedFoodSuccess(MdlBaseHttpResp<AmoySchoolBean> resp){}

    default void getCategoryListSuccess(MdlBaseHttpResp<CategoryPageBean> resp){}

    default void getVideoListSuccess(MdlBaseHttpResp<VideoPageBean> resp){}


    default void getAllClassicSuccess(MdlBaseHttpResp<CategoryListBean> resp){}


    default void getClickZanSuccess(MdlBaseHttpResp<String> resp){}


    default void getCancelZanSuccess(MdlBaseHttpResp<String> resp){}


    default void getClickCollectSuccess(MdlBaseHttpResp<String> resp){}


    default void getCancelCollectSuccess(MdlBaseHttpResp<String> resp){}

    default void getClickFollowSuccess(MdlBaseHttpResp<String> resp){}


    default void getCancelFollowSuccess(MdlBaseHttpResp<String> resp){}


    default void getLearnDataSuccess(MdlBaseHttpResp<LearnBean> resp){}


    default void getChapterListSuccess(MdlBaseHttpResp<ChapterListBean> resp){}


    default void getChapterSubjectListSuccess(MdlBaseHttpResp<ChapterSubjectListBean> resp){}


    default void getExamSubjectListSuccess(MdlBaseHttpResp<ExamSubjectListBean> resp){}

   default void saveAnswerSuccess(MdlBaseHttpResp<String> resp){}

   default void submitExamAnswerSuccess(MdlBaseHttpResp<DoneChapterBean> resp){}

   default void getAnswerRecordListSuccess(MdlBaseHttpResp<AnswerRecordBean> resp){}

   default void getSearchDataSuccess(MdlBaseHttpResp<SearchBean> resp){}



}
