package com.jsjlzj.wayne.ui.mvp.home;

import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.Login.MdlUpload;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.find.CashOutPageBean;
import com.jsjlzj.wayne.entity.find.CurrencyBean;
import com.jsjlzj.wayne.entity.find.CurrencyDetailPageBean;
import com.jsjlzj.wayne.entity.find.FindLessonPageBean;
import com.jsjlzj.wayne.entity.find.JiFenPageBean;
import com.jsjlzj.wayne.entity.find.MineProfitBean;
import com.jsjlzj.wayne.entity.find.OptimizationData1Bean;
import com.jsjlzj.wayne.entity.find.OptimizationData2Bean;
import com.jsjlzj.wayne.entity.shopping.BankCardItemBean;
import com.jsjlzj.wayne.entity.shopping.BankCardListBean;
import com.jsjlzj.wayne.entity.shopping.CommitOrderBean;

import com.jsjlzj.wayne.entity.shopping.LocationListBean;
import com.jsjlzj.wayne.entity.shopping.MineCouponBean;
import com.jsjlzj.wayne.entity.shopping.MineOrderPageBean;
import com.jsjlzj.wayne.entity.shopping.PayResultBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingCarBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingPageBean;
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
    //上传文件
    default void showUpload(MdlBaseHttpResp<MdlUpload> resp) {
    }

    default void getHomeRecommendSuccess(MdlBaseHttpResp<RecommendBean> resp) {
    }


    default void getAmoySchoolSuccess(MdlBaseHttpResp<AmoySchoolBean> resp) {
    }

    default void getMatchSuccess(MdlBaseHttpResp<AmoySchoolBean> resp) {
    }


    default void getAmoyListSuccess(MdlBaseHttpResp<CategoryPageBean> resp) {
    }


    default void getMatchListSuccess(MdlBaseHttpResp<CategoryPageBean> resp) {
    }

    default void getDriedFoodSuccess(MdlBaseHttpResp<AmoySchoolBean> resp) {
    }

    default void getCategoryListSuccess(MdlBaseHttpResp<CategoryPageBean> resp) {
    }

    default void getVideoListSuccess(MdlBaseHttpResp<VideoPageBean> resp) {
    }


    default void getAllClassicSuccess(MdlBaseHttpResp<CategoryListBean> resp) {
    }


    default void getClickZanSuccess(MdlBaseHttpResp<DataBean> resp) {
    }


    default void getMessageSuccess(MdlBaseHttpResp<DataBean> resp) {
    }

    default void deleteDynamicSuccess(MdlBaseHttpResp<DataBean> resp) {
    }


    default void getCancelZanSuccess(MdlBaseHttpResp<DataBean> resp) {
    }


    default void requestSuccess(MdlBaseHttpResp<DataBean> resp) {
    }


    default void getClickCollectSuccess(MdlBaseHttpResp<DataBean> resp) {
    }


    default void getCancelCollectSuccess(MdlBaseHttpResp<DataBean> resp) {
    }

    default void getClickFollowSuccess(MdlBaseHttpResp<DataBean> resp) {
    }


    default void getCancelFollowSuccess(MdlBaseHttpResp<DataBean> resp) {
    }


    default void getLearnDataSuccess(MdlBaseHttpResp<LearnBean> resp) {
    }


    default void getChapterListSuccess(MdlBaseHttpResp<ChapterListBean> resp) {
    }


    default void getChapterSubjectListSuccess(MdlBaseHttpResp<ChapterSubjectListBean> resp) {
    }


    default void getExamSubjectListSuccess(MdlBaseHttpResp<ExamSubjectListBean> resp) {
    }

    default void saveAnswerSuccess(MdlBaseHttpResp<String> resp) {
    }

    default void submitExamAnswerSuccess(MdlBaseHttpResp<DoneChapterBean> resp) {
    }

    default void getAnswerRecordListSuccess(MdlBaseHttpResp<AnswerRecordBean> resp) {
    }

    default void getSearchDataSuccess(MdlBaseHttpResp<SearchBean> resp) {
    }

    default void amoySignUpSuccess(MdlBaseHttpResp<DataBean> resp) {
    }

    default void publicDynamicSuccess(MdlBaseHttpResp<DataBean> resp) {
    }


    default void selectPhoto(int pos) {
    }

    default void onUploadSuccess(String filePath, int currPos) {
    }


    default void getOptimizationData1Success(MdlBaseHttpResp<OptimizationData1Bean> resp) {
    }

    default void getOptimizationData2Success(MdlBaseHttpResp<OptimizationData2Bean> resp) {
    }

    default void getCashOutListSuccess(MdlBaseHttpResp<CashOutPageBean> resp) {
    }

    default void getMineProfitSuccess(MdlBaseHttpResp<MineProfitBean> resp) {
    }

    default void getRecommendCategoryListSuccess(MdlBaseHttpResp<FindLessonPageBean> resp) {
    }


    default void getJifenListSuccess(MdlBaseHttpResp<JiFenPageBean> resp) {
    }

    default void getLocationListSuccess(MdlBaseHttpResp<LocationListBean> resp) {
    }

    default void saveLocationSuccess(MdlBaseHttpResp<DataBean> resp) {
    }

    default void getCurrencyListSuccess(MdlBaseHttpResp<CurrencyBean> resp) {
    }
  default void getCurrencyDetailListSuccess(MdlBaseHttpResp<CurrencyDetailPageBean> resp) {
    }

  default void getShoppingListSuccess(MdlBaseHttpResp<ShoppingPageBean> resp) {
    }

  default void getShoppingCarListSuccess(MdlBaseHttpResp<ShoppingCarBean> resp) {
    }

  default void getMineCouponListSuccess(MdlBaseHttpResp<MineCouponBean> resp) {
    }
  default void getEnableCouponListSuccess(MdlBaseHttpResp<MineCouponBean> resp) {
    }

  default void getBankCardListSuccess(MdlBaseHttpResp<BankCardListBean> resp) {
    }

  default void getBankCardItemSuccess(MdlBaseHttpResp<BankCardItemBean> resp) {
    }

  default void commitOrder2Success(MdlBaseHttpResp<CommitOrderBean> resp) {
    }

  default void searchPayResultSuccess(MdlBaseHttpResp<PayResultBean> resp) {
    }

  default void setPayPasswordSuccess(MdlBaseHttpResp<MdlUser> resp) {
    }

  default void getOrderListSuccess(MdlBaseHttpResp<MineOrderPageBean> resp) {
    }


}
