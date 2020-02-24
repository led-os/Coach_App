package com.jsjlzj.wayne.entity.store.home;

/**
 * @ClassName: BannerBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/24 11:18
 */
public class BannerBean {

    /**
     * id : 1000
     * url : http://image.gokgm.com:81/20191105215339_EV3EEN7S.jpg
     * link : https://element.eleme.cn/2.7/#/zh-CN/component/select
     * title : 首页1
     * sort : 1
     */

    private int id;
    private String url;
    private String link;
    private String title;
    private int sort;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
