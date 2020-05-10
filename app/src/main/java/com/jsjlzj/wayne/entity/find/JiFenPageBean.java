package com.jsjlzj.wayne.entity.find;

import java.util.List;

/**
 * @ClassName: JiFenPageBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/8 23:58
 */
public class JiFenPageBean {

    /**
     * data : {"pageNo":"当前页","pageSize":"每页大小","result":[{"amount":"积分数量","signInDate":"签到时间","type":"积分来源1:签到 2:抽奖 3:连续7天签到奖励 4:连续15天签到奖励 5:连续30天签到奖励"}],"totalCount":"总记录数"}
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
         * result : [{"amount":"积分数量","signInDate":"签到时间","type":"积分来源1:签到 2:抽奖 3:连续7天签到奖励 4:连续15天签到奖励 5:连续30天签到奖励"}]
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
             * amount : 积分数量
             * signInDate : 签到时间
             * type : 积分来源1:签到 2:抽奖 3:连续7天签到奖励 4:连续15天签到奖励 5:连续30天签到奖励
             */

            private String amount;
            private String signInDate;
            private int type;

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getSignInDate() {
                return signInDate;
            }

            public void setSignInDate(String signInDate) {
                this.signInDate = signInDate;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
