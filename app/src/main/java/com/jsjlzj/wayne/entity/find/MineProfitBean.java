package com.jsjlzj.wayne.entity.find;

/**
 * @ClassName: MineProfitBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/7 10:55
 */
public class MineProfitBean {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         *  "currentMonthEstimateIncome": "本月收益(预估收入)",
         *  "currentMonthSettlementIncome": "本月收益(结算收入)",
         *  "lastMonthSettlementIncome": "上月收益(结算收入)",
         *  "totalAmount": "收益累计金额",
         *  "withdrawableAmount": "可提现金额"
         */
        private String totalAmount;
        private String withdrawableAmount;
        private String currentMonthEstimateIncome;
        private String currentMonthSettlementIncome;
        private String lastMonthSettlementIncome;

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getWithdrawableAmount() {
            return withdrawableAmount;
        }

        public void setWithdrawableAmount(String withdrawableAmount) {
            this.withdrawableAmount = withdrawableAmount;
        }

        public String getCurrentMonthEstimateIncome() {
            return currentMonthEstimateIncome;
        }

        public void setCurrentMonthEstimateIncome(String currentMonthEstimateIncome) {
            this.currentMonthEstimateIncome = currentMonthEstimateIncome;
        }

        public String getCurrentMonthSettlementIncome() {
            return currentMonthSettlementIncome;
        }

        public void setCurrentMonthSettlementIncome(String currentMonthSettlementIncome) {
            this.currentMonthSettlementIncome = currentMonthSettlementIncome;
        }

        public String getLastMonthSettlementIncome() {
            return lastMonthSettlementIncome;
        }

        public void setLastMonthSettlementIncome(String lastMonthSettlementIncome) {
            this.lastMonthSettlementIncome = lastMonthSettlementIncome;
        }
    }
}
