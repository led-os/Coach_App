package com.jsjlzj.wayne.entity.shopping;

import java.util.List;

/**
 * @ClassName: MineOrderPageBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/17 14:11
 */
public class MineOrderPageBean {

    /**
     * data : {"pageNo":"当前页","pageSize":"每页大小","result":[{"aftersaleType":"售后使用字段（维权类型 0，退货；1换货）","createTime":"下单时间","discountAmount":"单个商品优惠金额","isEva":"是否评价#0,未评价|NO;1,已评价|YES","name":"名称","orderCode":"订单号","payAmount":"实付总额","payCode":"支付交易号","productCount":"数量","productPic":"图片地址","productPrice":"单价（分单位需要转换下元 /100）","productSpec":"规格","receiverAddress":"收货地址","receiverName":"收货人","receiverPhone":"收货电话","sendTime":"发货时间","showStatus":"状态（看数字）：0,待支付|WAITPAY;1,已取消|CANCLEED;2,待发货|WAITSEND;3,待收货|WAITRECEIVED;4,待评价|RECEIVED;5,已完成|FINISH;6,交易关闭|CLOSE8","skuId":"商品ID","totalDiscountAmount":"总订单优惠金额（分单位需要转换下元 /100）"}],"totalCount":"总记录数"}
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
         * result : [{"aftersaleType":"售后使用字段（维权类型 0，退货；1换货）","createTime":"下单时间","discountAmount":"单个商品优惠金额","isEva":"是否评价#0,未评价|NO;1,已评价|YES","name":"名称","orderCode":"订单号","payAmount":"实付总额","payCode":"支付交易号","productCount":"数量","productPic":"图片地址","productPrice":"单价（分单位需要转换下元 /100）","productSpec":"规格","receiverAddress":"收货地址","receiverName":"收货人","receiverPhone":"收货电话","sendTime":"发货时间","showStatus":"状态（看数字）：0,待支付|WAITPAY;1,已取消|CANCLEED;2,待发货|WAITSEND;3,待收货|WAITRECEIVED;4,待评价|RECEIVED;5,已完成|FINISH;6,交易关闭|CLOSE8","skuId":"商品ID","totalDiscountAmount":"总订单优惠金额（分单位需要转换下元 /100）"}]
         * totalCount : 总记录数
         */

        private int pageNo;
        private int pageSize;
        private int totalCount;
        private List<MineOrderBean> result;

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

        public List<MineOrderBean> getResult() {
            return result;
        }

        public void setResult(List<MineOrderBean> result) {
            this.result = result;
        }


    }
}
