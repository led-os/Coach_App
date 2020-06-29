package com.jsjlzj.wayne.entity.find;

/**
 * @ClassName: VideoBodyBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/6/29 13:35
 */
public class VideoBodyBean {
    /**
     * title : string
     * url : string
     * videoImg : string
     */

    private String title;
    private String url;
    private String videoImg;

    public VideoBodyBean(String title, String url, String videoImg) {
        this.title = title;
        this.url = url;
        this.videoImg = videoImg;
    }

    public VideoBodyBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVideoImg() {
        return videoImg;
    }

    public void setVideoImg(String videoImg) {
        this.videoImg = videoImg;
    }
}
