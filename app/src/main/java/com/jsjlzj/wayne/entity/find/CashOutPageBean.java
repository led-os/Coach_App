package com.jsjlzj.wayne.entity.find;

import java.util.List;

/**
 * @ClassName: CashOutPageBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/6 22:35
 */
public class CashOutPageBean {

    /**
     * data : {"pageNo":"当前页","pageSize":"每页大小","result":[{"amount":"提现金额","balance":"本次余额","bankName":"银行名称","branchName":"支行名称","cardNo":"卡号","createTime":"申请时间","status":"状态 1:提交申请 2: 审核通过 3:审核驳回","userName":"用户姓名"}],"totalCount":"总记录数"}
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
         * result : [{"amount":"提现金额","balance":"本次余额","bankName":"银行名称","branchName":"支行名称","cardNo":"卡号","createTime":"申请时间","status":"状态 1:提交申请 2: 审核通过 3:审核驳回","userName":"用户姓名"}]
         * totalCount : 总记录数
         */

        private int pageNo;
        private String pageSize;
        private int totalCount;
        private List<ResultBean> result;

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
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
             * amount : 提现金额
             * balance : 本次余额
             * bankName : 银行名称
             * branchName : 支行名称
             * cardNo : 卡号
             * createTime : 申请时间
             * status : 状态 1:提交申请 2: 审核通过 3:审核驳回
             * userName : 用户姓名
             */

            private String amount;
            private String balance;
            private String bankName;
            private String branchName;
            private String cardNo;
            private String createTime;
            //"状态 1:提交申请 2: 审核通过 3:审核驳回"
            private int status;
            private String userName;
            private boolean isTitle;

            public ResultBean(String createTime, boolean isTitle) {
                this.createTime = createTime;
                this.isTitle = isTitle;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getBankName() {
                return bankName;
            }

            public void setBankName(String bankName) {
                this.bankName = bankName;
            }

            public String getBranchName() {
                return branchName;
            }

            public void setBranchName(String branchName) {
                this.branchName = branchName;
            }

            public String getCardNo() {
                return cardNo;
            }

            public void setCardNo(String cardNo) {
                this.cardNo = cardNo;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public boolean isTitle() {
                return isTitle;
            }

            public void setTitle(boolean title) {
                isTitle = title;
            }
        }
    }
}
