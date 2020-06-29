package com.jsjlzj.wayne.entity.store.learn;

/**
 * @ClassName: VideoTeacherBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/6/29 21:17
 */
public class VideoTeacherBean {


    /**
     * coverImg : 封面图片
     * videoDuration : 视频时长(单位为秒)
     * videoUrl : 视频地址
     */

    private String coverImg;
    private String videoDuration;
    private String videoUrl;


    public VideoTeacherBean(String videoDuration, String videoUrl,String coverImg) {
        this.coverImg = coverImg;
        this.videoDuration = videoDuration;
        this.videoUrl = videoUrl;
    }

    public VideoTeacherBean() {
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(String videoDuration) {
        this.videoDuration = videoDuration;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
