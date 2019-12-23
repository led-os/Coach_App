package com.jsjlzj.wayne.entity.trainer;

public class MdlInterviewMessage {

    /**
     * ext : {"sHeadImg":"http://47.97.126.0:8000/20190910013028_ZI8ZMEW8.png","tHeadImg":"http://47.97.126.0:8000/20190910012538_SPXFG8YO.png","storeName":"小胡先森健身俱乐部","id":1020}
     * type : InterviewNotice
     */

    private ExtBean ext;
    private String type;

    public ExtBean getExt() {
        return ext;
    }

    public void setExt(ExtBean ext) {
        this.ext = ext;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class ExtBean {
        /**
         * sHeadImg : http://47.97.126.0:8000/20190910013028_ZI8ZMEW8.png
         * tHeadImg : http://47.97.126.0:8000/20190910012538_SPXFG8YO.png
         * storeName : 小胡先森健身俱乐部
         * id : 1020
         */

        private String sHeadImg;
        private String tHeadImg;
        private String storeName;
        private int id;

        public String getSHeadImg() {
            return sHeadImg;
        }

        public void setSHeadImg(String sHeadImg) {
            this.sHeadImg = sHeadImg;
        }

        public String getTHeadImg() {
            return tHeadImg;
        }

        public void setTHeadImg(String tHeadImg) {
            this.tHeadImg = tHeadImg;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
