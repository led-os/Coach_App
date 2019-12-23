package com.jsjlzj.wayne.entity.Login;

public class MdlUpload {

    /**
     * path : 文件保存路径
     * sort : 顺序字段
     * url : http://47.97.126.0:8000/20190728205840_XOULWHS9.png
     */
    public DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public class DataBean{
    private String path;
    private String sort;
    private String url;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "{" +
                "\"path\":\"" + path + "\"" +
                ", \"sort\":\"" + sort + "\"" +
                ", \"url\":\"" + url + "\"" +
                '}';
    }
    }
}
