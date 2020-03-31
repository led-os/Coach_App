package com.jsjlzj.wayne.entity.store.search;

/**
 * @ClassName: ChannelListBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/3/2 10:38
 */
public class ChannelListBean {
    /**
     * avatar : string
     * fansCount : 0
     * id : 0
     * isFollower : false
     * name : string
     */

    private String avatar;
    private int fansCount;
    private int id;
    private boolean isFollower;
    private String name;
    private String headImg;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFollower() {
        return isFollower;
    }

    public void setFollower(boolean follower) {
        isFollower = follower;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}
