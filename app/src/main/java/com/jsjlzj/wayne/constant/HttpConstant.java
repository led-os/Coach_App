package com.jsjlzj.wayne.constant;

/**
 * Created by Liu on 2018/3/16.
 */

public class HttpConstant {
    public static long UPDATA_NEWDATE = 0;
    public static final String OBSERVER_URL = "https://am.lightmes.cn/observer/?shareId=3&type=minApp";
    public static final String WEBSITE_URL = "http://www.lightmes.cn";
    public static final String UPDATA_URL = "https://www.pgyer.com/X4kA";

    public static boolean isDebug = true;
//    public static String BASE_URL = "https://api-dev.jsjlhome.com/";//测试环境
    public static String BASE_URL = "https://api.jsjlhome.com/";//线上环境


    public static String WXAPPID = "wx8bd15d5408120652";//"wx465cfff1c19d069d";
    public static String WXAPPSECET = "6f08456d4f060a895aeed4f09c5eb292";//"8659207754ddedc545fbd86cee2496d1";
    public static final String JCMES_URL = "http://app.lightmes.cn/";
    public static final String UPDATA_DATE = "2019-06-13";
    public static final String PAGE_NO = "pageNo";
    public static final String PAGE_SIZE = "pageSize";
    public static final String STATUS = "status";
    public static final String CATEGORY_ID = "categoryId";
    public static final String SORT_LABEL = "sortLabel";
    public static final String SORT_TYPE = "sortType";
    public static final String TITLE = "title";

    static {
        if (true)
            BASE_URL = BASE_URL;
        else
            BASE_URL = BASE_URL;
    }

    /**`
     * 请求成功
     */
    public static final int R_HTTP_OK = 1;//1000
    public static final int R_HTTP_ERROR_MSG = 0;//1000

    /********************************* web网页- url****************************************************/
    public static String WEB_URL_BASE = "http://h5dev.jsjlhome.com";//h5测试环境
//        public static String WEB_URL_BASE = "https://h5.jsjlhome.com";//h5正式环境

//    public static final String WEB_URL_BASE =  "http://h5.gokgm.com:81";//动态详情页
    public static final String WEB_URL_DYNAMIC_DETAIL = WEB_URL_BASE + "/#/dynamicDetail?id=";//动态详情页
    public static final String WEB_URL_COURSE_DETAIL = WEB_URL_BASE + "/#/courseDetail?id=";//课程详情页
    public static final String WEB_URL_AETICLE_DETAIL = WEB_URL_BASE + "/#/articleDetail?id=";//文章详情页
    public static final String WEB_URL_SCHOOL_DETAIL = WEB_URL_BASE + "/#/schoolDetail?id=";//学校详情页
    public static final String WEB_URL_PRODUCT_DETAIL = WEB_URL_BASE + "/#/productDetail?id=";//产品详情页
    public static final String WEB_URL_MATCH_DETAIL = WEB_URL_BASE + "/#/matchDetail?id=";//赛事详情页
    public static final String WEB_URL_USER_INFO = WEB_URL_BASE + "/#/user?id=";//用户主页
    public static final String WEB_URL_COURSE_INTRODUCE = WEB_URL_BASE + "/#/course?id=";//课程介绍
    public static final String WEB_URL_INVITATION_FRIEND = WEB_URL_BASE + "/#/share?inviteId=";//邀请好友页
    public static final String WEB_URL_PRIVATE_POLICY = WEB_URL_BASE + "/agreement/index.html?type=userPrivacy";//隐私政策
    public static final String WEB_URL_PRIVATE_POLICY_MINE = WEB_URL_BASE + "/agreement/index.html?type=userPrivacyPolicy";//隐私政策

    public static final String WEB_URL_NEW_COURSE_DETAIL = WEB_URL_BASE + "/#/excellentCourse?id=";//新的课程详情
    public static final String WEB_URL_NEW_COURSE_PLAY_DETAIL = WEB_URL_BASE + "/#/excellentCourseDetail?id=";//课程播放详情
    public static final String WEB_URL_NEW_TEACHER_DETAIL = WEB_URL_BASE + "/#/teacherPage?id=";//老师主页
    public static final String WEB_URL_NEW_SHOPPING_DETAIL = WEB_URL_BASE + "/#/goodsDetail?id=";//商品详情
    public static final String WEB_URL_NEW_DAY_SIGN = WEB_URL_BASE + "/#/daysign";//每日签到
    public static final String WEB_URL_NEW_POINTS = WEB_URL_BASE + "/#/points";//积分明细
    public static final String WEB_URL_NEW_MEMBER_CENTER = WEB_URL_BASE + "/#/memberCenter";//会员中心
    public static final String WEB_URL_NEW_ESHOP = WEB_URL_BASE + "/#/eshop";//积分商城
    public static final String WEB_URL_BENEFIT_INFO = WEB_URL_BASE + "#/benefitInfo";//收益说明

    public static final String WEB_URL_SCAN_SCORE  = "http://59.108.91.231/ost/queryScore.jsp";//国职考试成绩查询

    /********************************* web网页- url****************************************************/

    /**
     * 每页显示的数量
     */
    public static final String SIZE10 = "10";
    public static final String SIZE12 = "12";
    public static final String SIZE40 = "40";

    public static final int PAGE_SIZE_NUMBER = 10;
    /**
     * Token过期
     */
    public static final int R_TOKEN_EXPIRE = 2001;
    /**
     * 未登录
     */
    public static final int R_TOKEN_UN_LOGIN = 1002;

    //基础功能
    public static final String API_GET_CHECK_CODE = "jcmes-user/app/AppUserController/getIdentify";

    //获取所有字典字段
    public static final String API_DICT_GETALL="dict/getAll";
    //测试
    public static final String API_GET_TEST = "test/info";
    public static final String API_POST_TEST = "test/save";
    //云信测试
    public static final String API_YUNXIN_TEST = "https://api.netease.im/nimserver/user/update.action";
    public static final String API_YUNXIN_UPDATA = "login";
    /**
     * 聊天室
     */
    //门店：不适合
    public static final String API_MESSAGE_CV_UNSUITABLE = "im/cvUnsuitable";
    //教练 不敢兴趣
    public static final String API_MESSAGE_POSISTION_UNSUITABLE = "im/positionUninterested";
    //是否回复过
    public static final String API_MESSAGE_ISBOTHREPLY= "im/isBothReply";
    //保存普通消息
    public static final String API_MESSAGE_SAVE_MESSAGE = "im/saveCommunicateRecord";
    //保存微信号
    public static final String API_MESSAGE_SAVE_WECHAT= "im/saveWxId";

    //发送面试界面信息
    public static final String API_MESSAGE_INTERVIEWINFO= "interview/getSendInterviewInfo";


    /**
     * 用户登录
     */
    //用户登录
    public static final String API_LOGIN = "login";
    //手机验证码登录
    public static final String API_LOGIN_SMES = "loginBySmsCode";

    //修改手机号
    public static final String API_UPDATEPSD = "updatePwd";

    //通过openid查询用户
    public static final String API_LOGIN_GETOPENID = "getByOpenid";

    //上传文件
    public static final String API_UPLOAD = "upload";

    //上传视频文件
    public static final String API_UPLOAD_VIDEO = "uploadVideo";

    //获取所有地区信息
    public static final String API_GETAREAALL = "area/getAll";

    //获取验证码
    public static final String API_GET_SMES = "sendCode";

    //重置密码
    public static final String API_RESET_PWD = "resetPwd";

    //绑定微信号
    public static final String API_BIND_WECHAT = "bindWeChat";

    //微信登陆
    public static final String API_LOGIN_WX = "loginByWeChat";

    //解绑微信
    public static final String API_UNBIND_WECHAT = "untyingWeChat";

    //问题反馈
    public static final String API_QUESTION_BACK = "saveQuestionFeedback";

    //设置密码
    public static final String API_SETPWD = "setPwd";

    //修改手机号
    public static final String API_CHANGE_MOBILE = "changeMobile";

    //常见问题列表
    public static final String API_FAQ_LIST = "getFAQList";

    /**
     * 门店用户
     */
    //切换身份
    public static final String API_SWITCHIDENTITY = "account/switchIdentity";
    //保存门店认证信息
    public static final String API_SAVESTOREAUTH = "saveStoreAuth";
    //保存门店用户个人信息
    public static final String API_SAVESTOREUSERINFO = "saveStoreUserInfo";
    //查询门店用户个人信息
    public static final String API_ACCOUNTINFO = "account/info";



    /**
     * 门店  门店模块
     */
    //获取俱乐部信息
    public static final String API_GETSTOREINFO = "store/getStoreInfo";
    //设置支付密码
    public static final String API_SET_PAY_PASSWARD = "setPayPwd";
    //俱乐部/我的数据
    public static final String API_GETMYINFO = "store/myself";

    //默认的公司福利列表
    public static final String API_GETSYSTEMCOMPANYBENEFITS = "store/getSystemCompanyBenefits";
    //保存品牌logo
    public static final String API_SAVEBRANDLOGO = "store/saveBrandLogo";
    //保存公司福利
    public static final String API_SAVECOMPANYBENEFITS = "store/saveCompanyBenefits";
    //保存俱乐部照片
    public static final String API_SAVECOMPANYIMAGE = "store/saveCompanyImage";
    //保存俱乐部介绍
    public static final String API_SAVECOMPANYPROFILE = "store/saveCompanyProfile";
    //保存俱乐部地址
    public static final String API_SAVESTOREADDRESS = "store/saveStoreAddress";
    //保存上班时间
    public static final String API_SAVEWORKTIME = "store/saveWorkTime";

    /**
     * 门店  职位模块
     */
    //更改职位状态(关闭或发布)
    public static final String API_CHANGEPOSITIONSTATUS = "store/position/changePositionStatus";
    //获取职位详细信息
    public static final String API_GETPOSITIONDETAIL = "store/position/getPositionDetail";
    //获取招聘类型和职位类型
    public static final String API_GETPOSITIONTYPE = "store/position/getPositionType";
    //已发布的职位类型列表
    public static final String API_GETPUBLISHPOSITIONTYPELIST = "store/position/getPublishPositionTypeList";
    //获取平台默认的技能要求
    public static final String API_GETSYSTEMSKILLREQUIRED = "store/position/getSystemSkillRequired";
    //根据状态查询职位列表
    public static final String API_QUERYBYSTATUS = "store/position/queryByStatus";
    //保存职位信息
    public static final String API_SAVEPOSITION = "store/position/savePosition";


    /**
     * 门店  简历模块
     */


    //达人简历详情
    public static final String API_DETAILCV = "store/cv/detailCV";

    //沟通过列表
    public static final String API_COMMCVC = "store/cv/getCVCommunicateList";

    //收藏达人列表
    public static final String API_GETCVCLIKE = "store/cv/getCVLikeList";

    //保存达人沟通记录
    public static final String API_SAVECOMMCVC = "store/cv/saveCVCommunicate";

    //收藏达人
    public static final String API_SAVECVCLIKE = "store/cv/saveCVLike";

    //取消收藏达人
    public static final String API_CANCELCVC = "store/cv/cancelCVLike";

    //
    //搜索达人简历
    public static final String API_SEARCHCV = "store/cv/searchCV";
    //
    //门店端是否需要完善信息
    public static final String API_STORE_FINISH_INFO = "store/isNeedPerfect";

    /**
     * 教练端-简历模块
     * 接口:创建达人简历,简历详情,职位类型列表,达人-我的
     */

    //删除教育经历
    public static final String API_DELETEEDUCATIONEXPERIENCET = "trainer/cv/deleteEducationExperience";
    //删除工作经历
    public static final String API_DELETEWORKEXPERIENCET = "trainer/cv/deleteWorkExperience";
    //删除求职期望
    public static final String API_DELETEWORKHOPET= "trainer/cv/deleteWorkHope";
    //简历详情
    public static final String API_GETDETAILT = "trainer/cv/getDetail";
    //获取招聘类型和职位类型
    public static final String API_GETPOSITIONTYPET = "trainer/cv/getPositionType";
    //求职意向
    public static final String API_GETWORKHOPELISTT= "trainer/cv/getWorkHopeList";
    //达人-我的
    public static final String API_MYSELFT = "trainer/cv/myself";
    //保存个人优势
    public static final String API_SAVEADBANTAGE = "trainer/cv/saveAdvantage";
    //保存证书照片
    public static final String API_SAVECERTIFICATEPHOTOST = "trainer/cv/saveCertificatePhotos";
    //保存简历个人信息
    public static final String API_SAVECVBASEINFOT = "trainer/cv/saveCvBaseInfo";
    //保存教育经历
    public static final String API_SAVEEDUCATIONEXPERIENCET = "trainer/cv/saveEducationExperience";
    //保存生活照片
    public static final String API_SAVELIFEPHOTOST = "trainer/cv/saveLifePhotos";
    //保存视频教学
    public static final String API_SAVE_VIDEO_TEACH = "trainer/cv/saveTeachVideo";
    //保存工作经历
    public static final String API_SAVEWORKEXPERIENCET = "trainer/cv/saveWorkExperience";
    //保存求职期望
    public static final String API_SAVEWORKHOPET = "trainer/cv/saveWorkHope";
    //保存求职状态
    public static final String API_SAVEWORKSATUST = "trainer/cv/saveWorkStatus";

    //已添加的求职期望类型列表
    public static final String API_GETPOSITIONTYPELIST = "trainer/position/getPositionTypeList";
    //是否完善资料
    public static final String API_IS_FINISH_INFO = "trainer/cv/isNeedPerfect";

    //取消职位收藏
    public static final String API_CANCELPOSITIONLIKE = "trainer/position/cancelPositionLike";

    //取消收藏俱乐部
    public static final String API_CANCELSTORELIKE = "trainer/position/cancelStoreInfoLike";

    //职位详情信息
    public static final String API_POSITIONDETAIL = "trainer/position/detail";

    //沟通过的职位列表
    public static final String API_GETPOSITIONCOMMLIST = "trainer/position/getPositionCommunicateList";

    //收藏过的职位列表
    public static final String API_GETPOSITIONLIKELIST = "trainer/position/getPositionLikeList";

    //公司详情信息
    public static final String API_GETSTOREDETAIL = "trainer/position/getStoreDetail";

    //获取公司职位信息
    public static final String API_GETSTOREPOSITIONLIST = "trainer/position/getStorePositionList";

    //收藏职位
    public static final String API_SAVEPOSITIONLIKE = "trainer/position/savePositionLike";

    //收藏俱乐部
    public static final String API_SAVESTOREINFOLIKE = "trainer/position/saveStoreInfoLike";

    //搜索职位信息
    public static final String API_SEARCH = "trainer/position/search";

    //收藏俱乐部列表
    public static final String API_GETSTORELIKELIST = "trainer/position/selectStoreInfoLikePage";

    //职位举报
    public static final String API_TIPOFF = "trainer/position/tipOff";

    //保存地址搜索记录
    public static final String API_SAVESEARCH="address/saveSearch";

    //获取地址搜索记录
    public static final String API_GETSEARCH="address/getSearchHistory";

    //面试邀请列表（教练端）
    public static final String API_INTERVIEWTRAINER="interview/selectInterviewPageByTrainer";

    //面试邀请列表（门店端）
    public static final String API_INTERVIEWSTORE="interview/selectInterviewPageByStore";

    //面试详情
    public static final String API_INTERVIEWDETAIL="interview/getInterviewDetail";

    //门店取消面试
    public static final String API_INTERVIEWCANCEL="interview/storeCancelInterview";

    //教练更改面试状态
    public static final String API_INTERVIEWSTATUS="interview/trainerChangeInterviewStatus";

    //发送面试邀请
    public static final String API_INTERVIEWInvite="interview/sendInterviewInvite";

    //获取所有广告图
    public static final String API_GET_ALL_PIC ="trainer/index/banners";
    //获取首页分类列表（优选为第一个）
    public static final String API_GET_CATEGORY_LIST ="lms/categoryList";
    //获取商城首页数据
    public static final String API_GET_HOME_SHOPPING ="product/index";


    /************************************v2 新接口****************************************************/
    //首页推荐
    public static final String API_HOME_RECOMMEND = "trainer/index/recommend";
    //首页淘学
    public static final String API_HOME_AMOY_SCHOOL = "trainer/index/taoLearn";
    //首页赛事
    public static final String API_HOME_MATCH = "trainer/index/sportEvent";
    //淘学列表
    public static final String API_HOME_AMOY_LIST = "trainer/index/taoLearn/category/list";
    //赛事列表
    public static final String API_HOME_MATCH_LIST = "trainer/index/sportEvent/list";
    //首页干货
    public static final String API_HOME_DRIED_FOOD = "trainer/index/ganHuo";
    //首页干货列表
    public static final String API_HOME_DRIED_FOOD_LIST = "trainer/index/ganHuo/list";
    //首页资讯
    public static final String API_HOME_INFORMATION = "trainer/index/information";
    //首页资讯列表
    public static final String API_HOME_INFORMATION_LIST = "trainer/index/information/list";
    //首页产品
    public static final String API_HOME_PRODUCT = "trainer/index/product";
    //首页产品列表
    public static final String API_HOME_PRODUCT_LIST = "trainer/index/product/list";
    //首页视频分类列表
    public static final String API_HOME_ALL_CLASSIC = "trainer/index/video/category/list";
    //热门学校列表
    public static final String API_HOME_ORGANIZATION_LIST = "trainer/index/taoLearn/organization/list";
    //热门视频列表
    public static final String API_HOME_VIDEO_LIST = "trainer/index/video/list";
    //点赞
    public static final String API_ADD_ZAN = "like/add";
    //取消点赞
    public static final String API_CANCEL_ZAN = "like/cancel";
    //收藏
    public static final String API_ADD_COLLECT = "collect/add";
    //取消收藏
    public static final String API_CANCEL_COLLECT = "collect/cancel";
    //关注
    public static final String API_ADD_FOLLOW = "channel/follow";
    //取消关注
    public static final String API_CANCEL_FOLLOW = "channel/cancel";
    //删除动态
    public static final String API_DELETE_DYNAMIC = "community/del";
    //我的学习列表
    public static final String API_LEARN_LIST = "learn/myLearnList";
    //获取邀请码
    public static final String API_INVITATION_CODE = "invite/qrCode";
    //获取邀请好友列表
    public static final String API_INVITATION_LIST = "inviteList";
    //获取我的报名列表
    public static final String API_SIGN_UP_LIST = "enroll/list";
    //获取购物车数量
    public static final String API_HOME_GET_SHOPPING_NUM = "order/car/getCarNum";
    //学习数据
    public static final String API_LEARN_DATA = "learn/indexData";
    //章节列表数据
    public static final String API_CHAPTER_LIST = "learn/onlineChapterList";
    //章节题目列表数据
    public static final String API_CHAPTER_SUBJECT_LIST = "learn/onlineTopicsList";
    //保存答题记录
    public static final String API_SAVE_ANSWER_RECORD = "learn/saveAnswerRecord";
    //章节错误题目列表
    public static final String API_WRONG_SUBJECT_LIST = "learn/wrongOnlineTopicsList";
    //模拟考试-获取考试题目
    public static final String API_EXAM_SUBJECT_LIST = "learn/getTestPaperTopics";
    //模拟考试-提交答案
    public static final String API_SUBJECT_EXAM_ANSWER = "learn/submitTestPaper";
    //章节答题-完成答题
    public static final String API_DONE_ANSWER = "learn/finishAnswer";
    //模拟考试-错误回顾
    public static final String API_ERROR_RESULT = "learn/getTestPaperWrongTopics";
    //章节答题-获取当前题目id
    public static final String API_CURRENT_SUBJECT = "learn/getCurrentTopicId";
    //模拟考试-成绩列表
    public static final String API_ANSWER_RECORD = "learn/getAchievementList";
    //搜索全部
    public static final String API_SEARCH_ALL = "trainer/index/allSearch";
    //社区动态列表
    public static final String API_DYNAMIC_LIST = "community/list";
    //我的动态列表
    public static final String API_MINE_DYNAMIC_LIST = "community/myList";
    //淘学课程报名
    public static final String API_AMOY_SIGN_UP = "enroll/taoLearnEnroll";
    //赛事报名
    public static final String API_MATCH_SIGN_UP = "enroll/sportEventEnroll";
    //发布动态
    public static final String API_PUBLIC_DYNAMIC = "community/publish";
    //我的粉丝列表
    public static final String API_CHANNEL_FENSLIST = "channel/fansList";
    //我的关注列表
    public static final String API_CHANNEL_FOLLOW_LIST = "channel/followerList";
    //我的收藏 动态列表
    public static final String API_DYNAMIC_COLLECT_LIST = "collect/community/list";
    //我的收藏 视频列表
    public static final String API_VIDEO_COLLECT_LIST = "collect/video/list";
    //我的收藏 文章列表
    public static final String API_INFORMATION_COLLECT_LIST = "collect/information/list";
    //优选数据1
    public static final String API_FIND_OPTIMIZATION_DATA1 = "lms/index/data1";
    //优选数据2
    public static final String API_FIND_OPTIMIZATION_DATA2 = "lms/index/data2";
    //获取提现记录
    public static final String API_MINE_CASH_OUT_RECORD = "getCashWithdrawalPage";
    //我的收益
    public static final String API_MINE_PROFIT = "myProfit";
    //分类推荐列表更多
    public static final String API_RECOMMEND_CATEGORY_MORE = "lms/categoryRcmdLessonList";
    //免费体验更多
    public static final String API_FREE_EXPER_CATEGORY_MORE = "lms/freeExperienceList";
    //热门课程更多
    public static final String API_HOT_CATEGORY_MORE = "lms/hotLessonList";
    //热门听课更多
    public static final String API_HOT_LISTENING_CATEGORY_MORE = "lms/hotListeningLessonList";
    //减脂更多
    public static final String API_JIANZHI_CATEGORY_MORE = "lms/jianzhiLessonList";
    //运动更多
    public static final String API_MOTION_CATEGORY_MORE = "lms/yundongLessonList";
    //4们课程更多
    public static final String API_FOUR_LESSON_CATEGORY_MORE = "lms/fourLessonList";
    //搜索课程列表
    public static final String API_SEARCH_CATEGORY_MORE = "lms/searchLessonList";
    //积分明细列表
    public static final String API_JIFEN_DETAIL_LIST = "jifen/integralList";
    //获取收货地址列表
    public static final String API_LOCATION_MANAGER_LIST = "order/userAddress/getUserAddressList";

    //保存地址
    public static final String API_SAVE_LOCATION = "order/userAddress/addUserAddress";
    //修改地址
    public static final String API_MODIFY_LOCATION = "order/userAddress/upUserAddress";
    //删除地址
    public static final String API_DELETE_LOCATION = "order/userAddress/delUserAddress";
    //结算
    public static final String API_ORDER_COMMIT = "order/settle/getSettleList";
    //提交订单
    public static final String API_ORDER_COMMIT_2 = "order/pay/orderSubmit";
    //获取蜂隐币列表
    public static final String API_CURRENCY_LIST = "fycoin/fyCoinAppleList";
    //获取蜂隐币明细列表
    public static final String API_CURRENCY_DETAIL = "fycoin/tradeDetailList";
    //组合优惠分页接口
    public static final String API_GROUP_PRODUCT = "product/getDiscountsProductList";
    //搜索商品列表
    public static final String API_SEARCH_PRODUCT = "product/getProductList";
    //最新产品列表
    public static final String API_SEARCH_NEW_PRODUCT = "product/getNewProductList";
    //热门产品列表
    public static final String API_SEARCH_HOT_PRODUCT = "product/getSaleProductList";
    //限时秒杀列表页
    public static final String API_TIME_SKILL_SHOPPING_LIST = "product/getActivityList";
    //限时秒杀提醒
    public static final String API_TIME_SKILL_HINT = "product/saveActivityProductLog";
    //购物车列表
    public static final String API_SHOPPING_CAR_LIST = "order/car/getShoppingCarList";
    //我的优惠劵列表
    public static final String API_SHOPPING_MINE_COUPON = "coupon/getMyCoupon";
    //可选优惠券列表
    public static final String API_SHOPPING_ENABLE_COUPON_LIST = "coupon/getCouponList";
    //添加购物车
    public static final String API_SHOPPING_ADD_CAR = "order/car/addShoppingCar";
    //修改商品购物车数量
    public static final String API_SHOPPING_UPDATE_BY_NUM = "order/car/upCarByNum";
    //申请成为团长
    public static final String API_MINE_APPLY_LEADER = "addApplyRegimental";
    //获取银行卡列表信息
    public static final String API_MINE_BANKCARD_LIST = "getBankCardList";
    //删除银行卡信息
    public static final String API_MINE_DELETE_BANKCARD = "delBankCardInfo";
    //根据id获取银行卡信息
    public static final String API_MINE_GET_BANKCARD = "getBankCardInfo";
    //申请提现
    public static final String API_MINE_APPLY_CASHOUT = "applyCashWithdrawal";
    //申请厂家入驻
    public static final String API_MINE_APPLY_SETTLED_IN = "applyCompany";
    //保存（修改）银行卡信息
    public static final String API_MINE_SAVE_BANKCARD = "saveBankCard";
    //将商品从购物车删除
    public static final String API_SHOPPING_ORDER_DELETE = "order/car/delCar";
    //调起支付
    public static final String API_SHOPPING_PAY_ORDER = "order/pay/orderPay";
    //获取订单列表
    public static final String API_SHOPPING_ORDER_LIST = "myOrder/getMyOrderList";
    //收益订单列表
    public static final String API_SHOPPING_ORDER_PROFIT_LIST = "getMyProfitDetailPage";
    //获取订单列表
    public static final String API_SHOPPING_AFTER_ORDER_LIST = "myOrder/getReturnOrder";
    //撤销申请
    public static final String API_ORDER_CANCEL_AFTER = "myOrder/getReturnOrderCancel";
    //订单详情
    public static final String API_ORDER_DETAIL = "myOrder/getMyOrderDetail";
    //撤销退货申请
    public static final String API_CANCEL_AFTER_SALE = "myOrder/getReturnOrderCancel";
    //提交退货申请
    public static final String API_SAVE_AFTER_SALE = "myOrder/saveAftersaleInfo";
    //退货或售后详情
    public static final String API_ORDER_AFTER_DETAIL = "myOrder/getReturnOrderDetail";
    //订单详情---取消订单
    public static final String API_ORDER_CANCEL = "myOrder/cancelOrder";
    //查询支付结果
    public static final String API_SHOPPING_SEARCH_PAY_STATE = "order/pay/orderPayQuery";
    //确定收货
    public static final String API_SHOPPING_CONFIRM_ORDER = "myOrder/confirmOrder";
    //评价
    public static final String API_SHOPPING_EVALUATE_ORDER = "myOrder/evaluateOrder";
    //查询物流信息
    public static final String API_SHOPPING_SCAN_LOGISTICS = "myOrder/getExpressInfo";
    //确认订单页面可用优惠券
    public static final String API_SHOPPING_ENABLE_COUPON = "order/settle/getSettleCoupon";
    //获取课程详情
    public static final String API_COURSER_DETAIL = "lms/lessonDetail";
    //蜂隐币购买课程
    public static final String API_COURSER_BY_CURRENCY = "lms/buyLesson";
    //获取商品详情信息
    public static final String API_SHOPPING_DETAIL = "product/getSkuProduct";
    //提交充值订单
    public static final String API_COMMIT_VIP_ORDER = "vip/account/apple/rechargeSubmit";



}
