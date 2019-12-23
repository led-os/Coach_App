package com.jsjlzj.wayne.entity.Login;

import android.os.Parcel;
import android.os.Parcelable;

public class MdlUser implements Parcelable {
//
    /**
     * data : {"accountType":"NONE","isStore":0,"isTrainer":0,"mobile":"18576445428","name":null,"headImg":null,"position":null,"wxId":null,"wxName":null,"province":null,"city":null,"area":null,"deviceType":"ANDROID","token":"dff549730c47bf0750b314b5a47a4f33","status":0,"lastLoginTime":1563121697607,"deviceId":"357695505645247"}
     */


    protected MdlUser(Parcel in) {
    }

    public static final Creator<MdlUser> CREATOR = new Creator<MdlUser>() {
        @Override
        public MdlUser createFromParcel(Parcel in) {
            return new MdlUser(in);
        }

        @Override
        public MdlUser[] newArray(int size) {
            return new MdlUser[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    /**
     * "accountType": "账号类型: NONE,STORE,TRAINER",
     * "area": "地区",
     * "city": "城市",
     * "deviceId": "手机唯一标识",
     * "deviceType": "设备类型(IOS,ANDROID)",
     * "headImg": "头像",
     * "isStore": "是否为门店(0:否;1:是)账号",
     * "isTrainer": "是否为教练(0:否;1:是)账号",
     * "lastLoginTime": "最后登录时间",
     * "mobile": "手机号码",
     * "name": "姓名",
     * "position": "职位",
     * "province": "省份",
     * "status": "账号状态",
     * "token": "token",
     * "wxId": "微信ID",
     * "wxName": "微信昵称"
     */
    private MdlUserBean data;

    public MdlUserBean getData() {
        return data;
    }

    public void setData(MdlUserBean data) {
        this.data = data;
    }

    public static class MdlUserBean {


        private String accountType;
        private int isStore;
        private int isTrainer;
        private String mobile;
        private String name;
        private String headImg;
        private String position;
        private String wxId;
        private String wxName;
        private String province;
        private String city;
        private String area;
        private String deviceType;
        private String token;
        private int status;
        private String lastLoginTime;
        private String deviceId;
        private int isBindWeChat;//"是否绑定微信(1:未绑定,2:已绑定)",
        private int isSetPwd;//"是否为教练(0:否;1:是)账号",
        private int storeStatus;//"门店状态 0:未新增 1:审核中 2:未审核 3:审核通过",
        private String yunXinAccount;
        private String yunXinToken;

        public String getYunXinAccount() {
            return yunXinAccount;
        }

        public void setYunXinAccount(String yunXinAccount) {
            this.yunXinAccount = yunXinAccount;
        }

        public String getYunXinToken() {
            return yunXinToken;
        }

        public void setYunXinToken(String yunXinToken) {
            this.yunXinToken = yunXinToken;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"accountType\":\"" + accountType + "\"" +
                    ", \"isStore\":" + isStore +
                    ", \"isTrainer\":" + isTrainer +
                    ", \"mobile\":\"" + mobile + "\"" +
                    ", \"name\":\"" + name + "\"" +
                    ", \"headImg\":\"" + headImg + "\"" +
                    ", \"position\":\"" + position + "\"" +
                    ", \"wxId\":\"" + wxId + "\"" +
                    ", \"wxName\":\"" + wxName + "\"" +
                    ", \"province\":\"" + province + "\"" +
                    ", \"city\":\"" + city + "\"" +
                    ", \"area\":\"" + area + "\"" +
                    ", \"deviceType\":\"" + deviceType + "\"" +
                    ", \"token\":\"" + token + "\"" +
                    ", \"status\":" + status +
                    ", \"lastLoginTime\":" + lastLoginTime +
                    ", \"deviceId\":\"" + deviceId + "\"" +
                    ", \"isBindWeChat\":" + isBindWeChat +
                    ", \"isSetPwd\":" + isSetPwd +
                    ", \"storeStatus\":" + storeStatus +
                    '}';
        }

        public int getIsBindWeChat() {
            return isBindWeChat;
        }

        public void setIsBindWeChat(int isBindWeChat) {
            this.isBindWeChat = isBindWeChat;
        }

        public int getIsSetPwd() {
            return isSetPwd;
        }

        public void setIsSetPwd(int isSetPwd) {
            this.isSetPwd = isSetPwd;
        }

        public int getStoreStatus() {
            return storeStatus;
        }

        public void setStoreStatus(int storeStatus) {
            this.storeStatus = storeStatus;
        }

        public String getAccountType() {
            return accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }

        public int getIsStore() {
            return isStore;
        }

        public void setIsStore(int isStore) {
            this.isStore = isStore;
        }

        public int getIsTrainer() {
            return isTrainer;
        }

        public void setIsTrainer(int isTrainer) {
            this.isTrainer = isTrainer;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getWxId() {
            return wxId;
        }

        public void setWxId(String wxId) {
            this.wxId = wxId;
        }

        public String getWxName() {
            return wxName;
        }

        public void setWxName(String wxName) {
            this.wxName = wxName;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(String lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

    }

    @Override
    public String toString() {
        return "{" +
                "\"data\":" + data +
                '}';
    }
}
