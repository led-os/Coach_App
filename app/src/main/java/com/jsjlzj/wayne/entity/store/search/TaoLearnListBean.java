package com.jsjlzj.wayne.entity.store.search;

/**
 * @ClassName: TaoLearnListBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/3/2 10:40
 */
public class TaoLearnListBean {
    /**
     * content : 课程内容
     * enrollCount : 报名人数
     * id : 0
     * learnTime : 学习时间
     * lessonImg : 课程图片
     * name : 名称
     * orientCrowd : 面向人群
     */

    private String content;
    private String enrollCount;
    private int id;
    private String learnTime;
    private String lessonImg;
    private String name;
    private String orientCrowd;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEnrollCount() {
        return enrollCount;
    }

    public void setEnrollCount(String enrollCount) {
        this.enrollCount = enrollCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLearnTime() {
        return learnTime;
    }

    public void setLearnTime(String learnTime) {
        this.learnTime = learnTime;
    }

    public String getLessonImg() {
        return lessonImg;
    }

    public void setLessonImg(String lessonImg) {
        this.lessonImg = lessonImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrientCrowd() {
        return orientCrowd;
    }

    public void setOrientCrowd(String orientCrowd) {
        this.orientCrowd = orientCrowd;
    }
}
