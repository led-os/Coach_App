package com.jsjlzj.wayne.entity.find;

import java.util.List;

/**
 * @ClassName: FindLessonDetailBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/24 22:44
 */
public class FindLessonDetailBean {


    /**
     * data : {"catalogList":[{"audioUrl":"音频地址","collectCount":"收藏次数","commentCount":"评论次数","content":"内容详情","coverImg":"封面","id":"ID值","likeCount":"点赞次数","playCount":"播放次数","shareCount":"分享次数","title":"标题","videoDuration":"视频时长","videoUrl":"视频地址","viewCount":"浏览次数"}],"categoryId":"类别ID","characteristic":"课程特色","coverImg":"封面","fyCoinAmount":"蜂隐币数量","id":"ID","isBuy":false,"isFree":"是否免费 1:是 0:否","isView":false,"isVip":false,"lessonCount":"课程数(目录数量)","lessonDesc":"课程简介","originPrice":"原价","price":"实际支付价格","purchaseInstructions":"购买须知","studyPersons":"学习人数","suitableCrowd":"适宜人群[{img,desc}]","suitableCrowds":[{"desc":"图片描述","img":"图片地址"}],"syllabus":"课程大纲","teacherAvatar":"导师头像","teacherDesc":"导师简介","teacherName":"导师姓名","title":"标题"}
     */

    private FindLessonBean data;

    public FindLessonBean getData() {
        return data;
    }

    public void setData(FindLessonBean data) {
        this.data = data;
    }


}
