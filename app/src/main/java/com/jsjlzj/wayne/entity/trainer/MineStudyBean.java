package com.jsjlzj.wayne.entity.trainer;

import java.util.List;

/**
 * @ClassName: MineStudyBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/3/22 14:27
 */
public class MineStudyBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * coverImg : string
         * id : 0
         * lessonCount : 0
         * progress : 0
         * title : string
         */

        private String coverImg;
        private int id;
        private int lessonCount;
        private int progress;
        private String title;

        public String getCoverImg() {
            return coverImg;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLessonCount() {
            return lessonCount;
        }

        public void setLessonCount(int lessonCount) {
            this.lessonCount = lessonCount;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
