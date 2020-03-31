package com.jsjlzj.wayne.entity.trainer;

/**
 * @ClassName: InvitationCodeBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/3/22 17:50
 */
public class InvitationCodeBean {

    /**
     * data : {"url":"https://jh-tiyun-test.oss-cn-hangzhou.aliyuncs.com/file/100429-inviteReg.png"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * url : https://jh-tiyun-test.oss-cn-hangzhou.aliyuncs.com/file/100429-inviteReg.png
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
