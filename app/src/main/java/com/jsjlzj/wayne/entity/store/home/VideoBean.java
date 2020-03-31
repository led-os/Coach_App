package com.jsjlzj.wayne.entity.store.home;

/**
 * @ClassName: VideoBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/26 11:51
 */
public class VideoBean {
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
    private int viewCount;
    private String moodLabel;
    private String videoUrl;
    private int official;

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


    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
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

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public boolean isFollower() {
        return isFollower;
    }

    public void setFollower(boolean follower) {
        isFollower = follower;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public String getMoodLabel() {
        return moodLabel;
    }

    public void setMoodLabel(String moodLabel) {
        this.moodLabel = moodLabel;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getOfficial() {
        return official;
    }

    public void setOfficial(int official) {
        this.official = official;
    }
}
