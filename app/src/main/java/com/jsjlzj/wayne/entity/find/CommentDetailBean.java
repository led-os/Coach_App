package com.jsjlzj.wayne.entity.find;

/**
 * @ClassName: CommentDetailBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/6/27 17:18
 */
public class CommentDetailBean {

    /**
     * data : {"content":"string","createTimeText":"string","headImg":"string","id":0,"image":"string","name":"string","storeAllScore":0,"storeEnvScore":0,"storeFacilityScore":0,"storeInfo":{"area":"string","businessDistrict":"string","city":"string","detailAddress":"string","id":0,"newStoreFlag":"string","province":"string","score":0,"storeLogo":"string","storeName":"string","storeTelephone":"string"},"storeServiceScore":0,"trainer":{"expertiseArea":"string","headImg":"string","id":0,"name":"string","trainerType":"string","workYears":0},"userId":0,"video":"string","videoImg":"string"}
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
         * content : string
         * createTimeText : string
         * headImg : string
         * id : 0
         * image : string
         * name : string
         * storeAllScore : 0
         * storeEnvScore : 0
         * storeFacilityScore : 0
         * storeInfo : {"area":"string","businessDistrict":"string","city":"string","detailAddress":"string","id":0,"newStoreFlag":"string","province":"string","score":0,"storeLogo":"string","storeName":"string","storeTelephone":"string"}
         * storeServiceScore : 0
         * trainer : {"expertiseArea":"string","headImg":"string","id":0,"name":"string","trainerType":"string","workYears":0}
         * userId : 0
         * video : string
         * videoImg : string
         */

        private String content;
        private String createTimeText;
        private String headImg;
        private int id;
        private String image;
        private String name;
        private float storeAllScore;
        private int storeEnvScore;
        private int storeFacilityScore;
        private StoreInfoBean storeInfo;
        private int storeServiceScore;
        private TrainerBean trainer;
        private int userId;
        private String video;
        private String videoImg;
        private float trainerAllScore;
        private int trainerImageScore;
        private int trainerServiceScore;
        private int trainerSpecialityScore;

        public float getTrainerAllScore() {
            return trainerAllScore;
        }

        public void setTrainerAllScore(float trainerAllScore) {
            this.trainerAllScore = trainerAllScore;
        }

        public int getTrainerImageScore() {
            return trainerImageScore;
        }

        public void setTrainerImageScore(int trainerImageScore) {
            this.trainerImageScore = trainerImageScore;
        }

        public int getTrainerServiceScore() {
            return trainerServiceScore;
        }

        public void setTrainerServiceScore(int trainerServiceScore) {
            this.trainerServiceScore = trainerServiceScore;
        }

        public int getTrainerSpecialityScore() {
            return trainerSpecialityScore;
        }

        public void setTrainerSpecialityScore(int trainerSpecialityScore) {
            this.trainerSpecialityScore = trainerSpecialityScore;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTimeText() {
            return createTimeText;
        }

        public void setCreateTimeText(String createTimeText) {
            this.createTimeText = createTimeText;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getStoreAllScore() {
            return storeAllScore;
        }

        public void setStoreAllScore(float storeAllScore) {
            this.storeAllScore = storeAllScore;
        }

        public int getStoreEnvScore() {
            return storeEnvScore;
        }

        public void setStoreEnvScore(int storeEnvScore) {
            this.storeEnvScore = storeEnvScore;
        }

        public int getStoreFacilityScore() {
            return storeFacilityScore;
        }

        public void setStoreFacilityScore(int storeFacilityScore) {
            this.storeFacilityScore = storeFacilityScore;
        }

        public StoreInfoBean getStoreInfo() {
            return storeInfo;
        }

        public void setStoreInfo(StoreInfoBean storeInfo) {
            this.storeInfo = storeInfo;
        }

        public int getStoreServiceScore() {
            return storeServiceScore;
        }

        public void setStoreServiceScore(int storeServiceScore) {
            this.storeServiceScore = storeServiceScore;
        }

        public TrainerBean getTrainer() {
            return trainer;
        }

        public void setTrainer(TrainerBean trainer) {
            this.trainer = trainer;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getVideoImg() {
            return videoImg;
        }

        public void setVideoImg(String videoImg) {
            this.videoImg = videoImg;
        }

        public static class StoreInfoBean {
            /**
             * area : string
             * businessDistrict : string
             * city : string
             * detailAddress : string
             * id : 0
             * newStoreFlag : string
             * province : string
             * score : 0
             * storeLogo : string
             * storeName : string
             * storeTelephone : string
             */

            private String area;
            private String businessDistrict;
            private String city;
            private String detailAddress;
            private int id;
            private String newStoreFlag;
            private String province;
            private float score;
            private String storeLogo;
            private String storeName;
            private String storeTelephone;

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getBusinessDistrict() {
                return businessDistrict;
            }

            public void setBusinessDistrict(String businessDistrict) {
                this.businessDistrict = businessDistrict;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDetailAddress() {
                return detailAddress;
            }

            public void setDetailAddress(String detailAddress) {
                this.detailAddress = detailAddress;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNewStoreFlag() {
                return newStoreFlag;
            }

            public void setNewStoreFlag(String newStoreFlag) {
                this.newStoreFlag = newStoreFlag;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public float getScore() {
                return score;
            }

            public void setScore(float score) {
                this.score = score;
            }

            public String getStoreLogo() {
                return storeLogo;
            }

            public void setStoreLogo(String storeLogo) {
                this.storeLogo = storeLogo;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public String getStoreTelephone() {
                return storeTelephone;
            }

            public void setStoreTelephone(String storeTelephone) {
                this.storeTelephone = storeTelephone;
            }
        }

        public static class TrainerBean {
            /**
             * expertiseArea : string
             * headImg : string
             * id : 0
             * name : string
             * trainerType : string
             * workYears : 0
             */

            private String expertiseArea;
            private String headImg;
            private int id;
            private String name;
            private String trainerType;
            private int workYears;

            public String getExpertiseArea() {
                return expertiseArea;
            }

            public void setExpertiseArea(String expertiseArea) {
                this.expertiseArea = expertiseArea;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTrainerType() {
                return trainerType;
            }

            public void setTrainerType(String trainerType) {
                this.trainerType = trainerType;
            }

            public int getWorkYears() {
                return workYears;
            }

            public void setWorkYears(int workYears) {
                this.workYears = workYears;
            }
        }
    }
}
