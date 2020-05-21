package com.jsjlzj.wayne.entity.shopping;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: MineCouponBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/13 17:52
 */
public class MineCouponBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * amount : 优惠金额
         * endTime : 截止时间
         * minPoint : 使用门槛；0表示无门槛
         * name : 名称
         * type : 优惠卷类型；0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券 * 暂时只有: 2->购物赠券
         * useType : 使用类型：0->全场通用；1->指定分类；2->指定商品
         */
        private float amount;
        private long endTime;
        private float minPoint;
        private String name;
        private int type;
        private int useType;
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public float getMinPoint() {
            return minPoint;
        }

        public void setMinPoint(float minPoint) {
            this.minPoint = minPoint;
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

        public int getUseType() {
            return useType;
        }

        public void setUseType(int useType) {
            this.useType = useType;
        }
    }
}
