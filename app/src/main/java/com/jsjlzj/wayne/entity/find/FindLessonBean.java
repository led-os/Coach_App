package com.jsjlzj.wayne.entity.find;

/**
 * @ClassName: FindLessonBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/3 11:37
 */
public class FindLessonBean {

    /**
     * categoryId : 类别ID
     * coverImg : 封面
     * id : ID
     * isFree : 是否免费 1:是 0:否
     * lessonCount : 课程数(目录数量)
     * lessonDesc : 课程简介
     * originPrice : 原价
     * price : 实际支付价格
     * studyPersons : 学习人数
     * teacherAvatar : 导师头像
     * teacherDesc : 导师简介
     * teacherName : 导师姓名
     * title : 标题
     */

    private String categoryId;
    private String coverImg;
    private String id;
    private String isFree;
    private int lessonCount;
    private String lessonDesc;
    private String originPrice;
    private float price;
    private int studyPersons;
    private String teacherAvatar;
    private String teacherDesc;
    private String teacherName;
    private String title;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsFree() {
        return isFree;
    }

    public void setIsFree(String isFree) {
        this.isFree = isFree;
    }

    public int getLessonCount() {
        return lessonCount;
    }

    public void setLessonCount(int lessonCount) {
        this.lessonCount = lessonCount;
    }

    public String getLessonDesc() {
        return lessonDesc;
    }

    public void setLessonDesc(String lessonDesc) {
        this.lessonDesc = lessonDesc;
    }

    public String getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(String originPrice) {
        this.originPrice = originPrice;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStudyPersons() {
        return studyPersons;
    }

    public void setStudyPersons(int studyPersons) {
        this.studyPersons = studyPersons;
    }

    public String getTeacherAvatar() {
        return teacherAvatar;
    }

    public void setTeacherAvatar(String teacherAvatar) {
        this.teacherAvatar = teacherAvatar;
    }

    public String getTeacherDesc() {
        return teacherDesc;
    }

    public void setTeacherDesc(String teacherDesc) {
        this.teacherDesc = teacherDesc;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
