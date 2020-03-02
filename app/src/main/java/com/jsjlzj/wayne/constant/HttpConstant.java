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
    public static String BASE_URL = "http://47.97.126.0:8090/";
//    public static String BASE_URL = "http://api.gokgm.com:81/";
    public static String WXAPPID = "wx465cfff1c19d069d";
    public static String WXAPPSECET = "8659207754ddedc545fbd86cee2496d1";
    public static final String JCMES_URL = "http://app.lightmes.cn/";
    public static final String UPDATA_DATE = "2019-06-13";
    public static final String PAGE_NO = "pageNo";
    public static final String PAGE_SIZE = "pageSize";

    static {
        if (true)
            BASE_URL = BASE_URL;
        else
            BASE_URL = BASE_URL;
    }

    /**
     * `
     * 请求成功
     */
    public static final int R_HTTP_OK = 1;//1000
    public static final int R_HTTP_ERROR_MSG = 0;//1000

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
    //保存工作经历
    public static final String API_SAVEWORKEXPERIENCET = "trainer/cv/saveWorkExperience";
    //保存求职期望
    public static final String API_SAVEWORKHOPET = "trainer/cv/saveWorkHope";
    //保存求职状态
    public static final String API_SAVEWORKSATUST = "trainer/cv/saveWorkStatus";

    //已添加的求职期望类型列表
    public static final String API_GETPOSITIONTYPELIST = "trainer/position/getPositionTypeList";

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
    public static final String API_ADD_FOLLOW = "channel/add";
    //取消关注
    public static final String API_CANCEL_FOLLOW = "channel/cancel";
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



}
