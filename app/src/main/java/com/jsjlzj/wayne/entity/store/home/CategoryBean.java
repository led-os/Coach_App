package com.jsjlzj.wayne.entity.store.home;

/**
 * @ClassName: CategoryBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/24 14:54
 */
public class CategoryBean {
    /**
     * id : 1015
     * url : http://image.gokgm.com:81/20191105215339_EV3EEN7S.jpg
     * name : 增肌饮食
     *     "coverImg": "string",
     *           "enrollCount": 0,
     *           "id": 0,
     *           "name": "string"
     */

    private int id;
    private String url;
    private String name;
    private String coverImg;
    private int enrollCount;
    private String startTime;
    private String endTime;
    /**"状态: 1:未开始 2:进行中 3:已结束"*/
    private int status;


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public int getEnrollCount() {
        return enrollCount;
    }

    public void setEnrollCount(int enrollCount) {
        this.enrollCount = enrollCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
