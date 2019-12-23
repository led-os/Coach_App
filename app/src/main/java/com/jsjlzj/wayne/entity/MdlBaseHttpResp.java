package com.jsjlzj.wayne.entity;

public class MdlBaseHttpResp <T> {
    public int status;
    public String msg;
    public T data;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public class Data<T>{
        public T data;

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"data\":" + data +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "{" +
                "\"status\":" + status +
                ", \"msg\":\"" + msg + "\"" +
                ", \"data\":" + data.toString() +
                '}';
    }
}
