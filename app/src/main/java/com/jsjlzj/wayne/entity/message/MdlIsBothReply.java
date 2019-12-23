package com.jsjlzj.wayne.entity.message;

public class MdlIsBothReply {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public class DataBean{

        /**
         * bothReply : 是否双方回复过:0:否;1:是
         * positionId : 职位ID
         * workHopeId : 求职意向ID
         */

        private int bothReply;
        private String positionId;
        private String workHopeId;

        public int getBothReply() {
            return bothReply;
        }

        public void setBothReply(int bothReply) {
            this.bothReply = bothReply;
        }

        public String getPositionId() {
            return positionId;
        }

        public void setPositionId(String positionId) {
            this.positionId = positionId;
        }

        public String getWorkHopeId() {
            return workHopeId;
        }

        public void setWorkHopeId(String workHopeId) {
            this.workHopeId = workHopeId;
        }
    }
}
