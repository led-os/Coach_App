package com.jsjlzj.wayne.entity.store.search;

import com.jsjlzj.wayne.entity.store.home.CategoryBean;
import com.jsjlzj.wayne.entity.store.home.VideoBean;

import java.util.List;

/**
 * @ClassName: SearchBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/3/2 10:23
 */
public class SearchBean {

    /**
     * data : {"channelList":[{"avatar":"string","fansCount":0,"id":0,"isFollower":false,"name":"string"}],"informationList":[{"channelAvatar":"string","channelId":0,"channelName":"string","coverImg":"string","createTime":"string","id":0,"name":"string","viewCount":0}],"productList":[{"coverImg":"string","id":0,"name":"string","place":"string","price":"string"}],"sportEventList":[{"coverImg":"封面图片","endTime":"结束时间","enrollCount":"报名数","id":0,"name":"名称","startTime":"开始时间","status":"状态: 1:未开始 2:进行中 3:已结束"}],"taoLearnList":[{"content":"课程内容","enrollCount":"报名人数","id":0,"learnTime":"学习时间","lessonImg":"课程图片","name":"名称","orientCrowd":"面向人群"}],"videoList":[{"channelAvatar":"string","channelId":0,"channelName":"string","collectCount":"收藏次数","commentCount":"评论次数","coverImg":"string","createTime":"string","id":0,"isCollect":false,"isFollower":false,"isLike":false,"likeCount":"点赞次数","name":"string","official":0,"playCount":"播放次数","videoDuration":0}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ChannelListBean> channelList;
        private List<VideoBean> informationList;
        private List<CategoryBean> productList;
        private List<CategoryBean> sportEventList;
        private List<TaoLearnListBean> taoLearnList;
        private List<VideoBean> videoList;

        public List<ChannelListBean> getChannelList() {
            return channelList;
        }

        public void setChannelList(List<ChannelListBean> channelList) {
            this.channelList = channelList;
        }

        public List<VideoBean> getInformationList() {
            return informationList;
        }

        public void setInformationList(List<VideoBean> informationList) {
            this.informationList = informationList;
        }

        public List<CategoryBean> getProductList() {
            return productList;
        }

        public void setProductList(List<CategoryBean> productList) {
            this.productList = productList;
        }

        public List<CategoryBean> getSportEventList() {
            return sportEventList;
        }

        public void setSportEventList(List<CategoryBean> sportEventList) {
            this.sportEventList = sportEventList;
        }

        public List<TaoLearnListBean> getTaoLearnList() {
            return taoLearnList;
        }

        public void setTaoLearnList(List<TaoLearnListBean> taoLearnList) {
            this.taoLearnList = taoLearnList;
        }

        public List<VideoBean> getVideoList() {
            return videoList;
        }

        public void setVideoList(List<VideoBean> videoList) {
            this.videoList = videoList;
        }
    }
}
