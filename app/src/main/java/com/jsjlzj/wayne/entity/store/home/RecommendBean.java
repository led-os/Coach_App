package com.jsjlzj.wayne.entity.store.home;

import java.util.List;

/**
 * @ClassName: RecommendBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/24 10:38
 */
public class RecommendBean {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public class DataBean {
        private List<BannerBean> banner;
        private List<CategoryBean> category;
        private List<LessonBean> lesson;
        private List<VideoBean> video;
        private List<InformationBean> information;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<CategoryBean> getCategory() {
            return category;
        }

        public void setCategory(List<CategoryBean> category) {
            this.category = category;
        }

        public List<LessonBean> getLesson() {
            return lesson;
        }

        public void setLesson(List<LessonBean> lesson) {
            this.lesson = lesson;
        }

        public List<VideoBean> getVideo() {
            return video;
        }

        public void setVideo(List<VideoBean> video) {
            this.video = video;
        }

        public List<InformationBean> getInformation() {
            return information;
        }

        public void setInformation(List<InformationBean> information) {
            this.information = information;
        }

    }

    public static class LessonBean {
        /**
         * id : 1001
         * coverImg : http://image.gokgm.com:81/20191105215339_EV3EEN7S.jpg
         * title : 34433434
         * videoDuration : 103
         * playCount : 33
         * createTime : 2020-01-02
         */

        private int id;
        private String coverImg;
        private String title;
        private int videoDuration;
        private int playCount;
        private String createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCoverImg() {
            return coverImg;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getVideoDuration() {
            return videoDuration;
        }

        public void setVideoDuration(int videoDuration) {
            this.videoDuration = videoDuration;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }



    public static class VideoBean {
        /**
         * id : 1005
         * coverImg : http://image.gokgm.com:81/20191105215339_EV3EEN7S.jpg
         * name : 2323233333333
         * videoDuration : 0
         * playCount : 4
         * commentCount : 0
         * collectCount : 78
         * isCollect : false
         * likeCount : 5
         * isLike : false
         * createTime : 2019-12-31
         * channelAvatar : https://jh-tiyun-test.oss-cn-hangzhou.aliyuncs.com/file/20200219105742028_Y2P3AB6U.png
         * channelName : 招猫
         * channelId : 100000
         * isFollower : true
         */

        private int id;
        private String coverImg;
        private String name;
        private int videoDuration;
        private int playCount;
        private int commentCount;
        private int collectCount;
        private boolean isCollect;
        private int likeCount;
        private boolean isLike;
        private String createTime;
        private String channelAvatar;
        private String channelName;
        private int channelId;
        private boolean isFollower;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCoverImg() {
            return coverImg;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getVideoDuration() {
            return videoDuration;
        }

        public void setVideoDuration(int videoDuration) {
            this.videoDuration = videoDuration;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getCollectCount() {
            return collectCount;
        }

        public void setCollectCount(int collectCount) {
            this.collectCount = collectCount;
        }

        public boolean isIsCollect() {
            return isCollect;
        }

        public void setIsCollect(boolean isCollect) {
            this.isCollect = isCollect;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public boolean isIsLike() {
            return isLike;
        }

        public void setIsLike(boolean isLike) {
            this.isLike = isLike;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getChannelAvatar() {
            return channelAvatar;
        }

        public void setChannelAvatar(String channelAvatar) {
            this.channelAvatar = channelAvatar;
        }

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public int getChannelId() {
            return channelId;
        }

        public void setChannelId(int channelId) {
            this.channelId = channelId;
        }

        public boolean isIsFollower() {
            return isFollower;
        }

        public void setIsFollower(boolean isFollower) {
            this.isFollower = isFollower;
        }
    }


    public static class InformationBean {
        /**
         * id : 1007
         * coverImg : http://image.gokgm.com:81/20191105215339_EV3EEN7S.jpg
         * name : 营养说明文章
         * viewCount : 23
         * createTime : 2019-12-31 12:06
         * channelAvatar : https://jh-tiyun-test.oss-cn-hangzhou.aliyuncs.com/file/20200219105742028_Y2P3AB6U.png
         * channelName : 招猫
         * channelId : 100000
         */

        private int id;
        private String coverImg;
        private String name;
        private int viewCount;
        private String createTime;
        private String channelAvatar;
        private String channelName;
        private int channelId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCoverImg() {
            return coverImg;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getChannelAvatar() {
            return channelAvatar;
        }

        public void setChannelAvatar(String channelAvatar) {
            this.channelAvatar = channelAvatar;
        }

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public int getChannelId() {
            return channelId;
        }

        public void setChannelId(int channelId) {
            this.channelId = channelId;
        }
    }

}
