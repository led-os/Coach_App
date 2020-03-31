package com.jsjlzj.wayne.entity.trainer;

import java.util.List;

/**
 * @ClassName: SignUpPageBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/3/22 18:03
 */
public class SignUpPageBean {

    /**
     * data : {"pageNo":"当前页","pageSize":"每页大小","result":[{"bizId":"业务ID(课程id或赛事id)","createTime":"报名时间","module":"模块编码 淘学:tao_learn, 赛事:sport_event","sportEvent":{"coverImg":"封面图片","endTime":"结束时间","enrollCount":"报名数","id":0,"name":"名称","startTime":"开始时间","status":"状态: 1:未开始 2:进行中 3:已结束"},"taoLearn":{"content":"课程内容","enrollCount":"报名人数","id":0,"isEnroll":false,"learnTime":"学习时间","lessonImg":"课程图片","name":"名称","orientCrowd":"面向人群"}}],"totalCount":"总记录数"}
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
         * result : [{"bizId":"业务ID(课程id或赛事id)","createTime":"报名时间","module":"模块编码 淘学:tao_learn, 赛事:sport_event","sportEvent":{"coverImg":"封面图片","endTime":"结束时间","enrollCount":"报名数","id":0,"name":"名称","startTime":"开始时间","status":"状态: 1:未开始 2:进行中 3:已结束"},"taoLearn":{"content":"课程内容","enrollCount":"报名人数","id":0,"isEnroll":false,"learnTime":"学习时间","lessonImg":"课程图片","name":"名称","orientCrowd":"面向人群"}}]
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
             * bizId : 业务ID(课程id或赛事id)
             * createTime : 报名时间
             * module : 模块编码 淘学:tao_learn, 赛事:sport_event
             * sportEvent : {"coverImg":"封面图片","endTime":"结束时间","enrollCount":"报名数","id":0,"name":"名称","startTime":"开始时间","status":"状态: 1:未开始 2:进行中 3:已结束"}
             * taoLearn : {"content":"课程内容","enrollCount":"报名人数","id":0,"isEnroll":false,"learnTime":"学习时间","lessonImg":"课程图片","name":"名称","orientCrowd":"面向人群"}
             */

            private String bizId;
            private String createTime;
            private String module;
            private SportEventBean sportEvent;
            private TaoLearnBean taoLearn;

            public String getBizId() {
                return bizId;
            }

            public void setBizId(String bizId) {
                this.bizId = bizId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getModule() {
                return module;
            }

            public void setModule(String module) {
                this.module = module;
            }

            public SportEventBean getSportEvent() {
                return sportEvent;
            }

            public void setSportEvent(SportEventBean sportEvent) {
                this.sportEvent = sportEvent;
            }

            public TaoLearnBean getTaoLearn() {
                return taoLearn;
            }

            public void setTaoLearn(TaoLearnBean taoLearn) {
                this.taoLearn = taoLearn;
            }

            public static class SportEventBean {
                /**
                 * coverImg : 封面图片
                 * endTime : 结束时间
                 * enrollCount : 报名数
                 * id : 0
                 * name : 名称
                 * startTime : 开始时间
                 * status : 状态: 1:未开始 2:进行中 3:已结束
                 */

                private String coverImg;
                private String endTime;
                private String enrollCount;
                private int id;
                private String name;
                private String startTime;
                private int status;

                public String getCoverImg() {
                    return coverImg;
                }

                public void setCoverImg(String coverImg) {
                    this.coverImg = coverImg;
                }

                public String getEndTime() {
                    return endTime;
                }

                public void setEndTime(String endTime) {
                    this.endTime = endTime;
                }

                public String getEnrollCount() {
                    return enrollCount;
                }

                public void setEnrollCount(String enrollCount) {
                    this.enrollCount = enrollCount;
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

                public String getStartTime() {
                    return startTime;
                }

                public void setStartTime(String startTime) {
                    this.startTime = startTime;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }
            }

            public static class TaoLearnBean {
                /**
                 * content : 课程内容
                 * enrollCount : 报名人数
                 * id : 0
                 * isEnroll : false
                 * learnTime : 学习时间
                 * lessonImg : 课程图片
                 * name : 名称
                 * orientCrowd : 面向人群
                 */

                private String content;
                private String enrollCount;
                private int id;
                private boolean isEnroll;
                private String learnTime;
                private String lessonImg;
                private String name;
                private String orientCrowd;

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getEnrollCount() {
                    return enrollCount;
                }

                public void setEnrollCount(String enrollCount) {
                    this.enrollCount = enrollCount;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public boolean isIsEnroll() {
                    return isEnroll;
                }

                public void setIsEnroll(boolean isEnroll) {
                    this.isEnroll = isEnroll;
                }

                public String getLearnTime() {
                    return learnTime;
                }

                public void setLearnTime(String learnTime) {
                    this.learnTime = learnTime;
                }

                public String getLessonImg() {
                    return lessonImg;
                }

                public void setLessonImg(String lessonImg) {
                    this.lessonImg = lessonImg;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getOrientCrowd() {
                    return orientCrowd;
                }

                public void setOrientCrowd(String orientCrowd) {
                    this.orientCrowd = orientCrowd;
                }
            }
        }
    }
}
