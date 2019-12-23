package com.jsjlzj.wayne.entity.trainer;

/**
 * Describe
 * Created by Bby on 2019-09-07
 * Email bby0856@163.com
 */
public class MdlTrainer {

    /**
     * data : {}
     * msg : string
     * status : 0
     */

    private DataBean data;
    private String msg;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
    }
}
