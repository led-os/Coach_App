package com.jsjlzj.wayne.entity.trainer;

import java.util.List;

/**
 * @ClassName: InvitationBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/3/22 16:26
 */
public class InvitationBean {

    /**
     * data : {"pageNo":"当前页","pageSize":"每页大小","result":[{"createTime":"邀请时间","headImg":"头像","mobile":"手机号码","name":"姓名","type":"0:教练和俱乐部, 1:教练, 2:俱乐部","userId":"用户Id"}],"totalCount":"总记录数"}
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
         * pageNo : 当前页
         * pageSize : 每页大小
         * result : [{"createTime":"邀请时间","headImg":"头像","mobile":"手机号码","name":"姓名","type":"0:教练和俱乐部, 1:教练, 2:俱乐部","userId":"用户Id"}]
         * totalCount : 总记录数
         */

        private int pageNo;
        private int pageSize;
        private int totalCount;
        private List<ResultBean> result;

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class ResultBean {
            /**
             * createTime : 邀请时间
             * headImg : 头像
             * mobile : 手机号码
             * name : 姓名
             * type : 0:教练和俱乐部, 1:教练, 2:俱乐部
             * userId : 用户Id
             */

            private String createTime;
            private String headImg;
            private String mobile;
            private String name;
            private int type;
            private String userId;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
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

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }
        }
    }
}
