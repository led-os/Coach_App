package com.jsjlzj.wayne.entity.store.learn;

/**
 * @ClassName: LibraryBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/27 20:29
 */
public class LibraryBean {
    /**
     * coverImg : string
     * id : 0
     * title : string
     */

    private String coverImg;
    private int id;
    private String title;

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
