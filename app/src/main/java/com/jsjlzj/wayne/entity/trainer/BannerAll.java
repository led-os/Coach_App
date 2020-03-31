package com.jsjlzj.wayne.entity.trainer;

import java.util.List;

/**
 * @ClassName: BannerAll
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/3/21 16:27
 */
public class BannerAll {

    /**
     * data : {"tao_learn":[{"id":1002,"url":"http://image.gokgm.com:81/20191105215339_EV3EEN7S.jpg","link":"https://element.eleme.cn/2.7/#/zh-CN/component/select","title":"淘学1","sort":1},{"id":1003,"url":"http://image.gokgm.com:81/20191105215339_EV3EEN7S.jpg","link":"https://element.eleme.cn/2.7/#/zh-CN/component/select","title":"淘学2","sort":2},{"id":1004,"url":"http://image.gokgm.com:81/20191105215339_EV3EEN7S.jpg","link":"https://element.eleme.cn/2.7/#/zh-CN/component/select","title":"淘学3","sort":3}],"index":[{"id":1000,"url":"http://image.gokgm.com:81/20191105215339_EV3EEN7S.jpg","link":"https://element.eleme.cn/2.7/#/zh-CN/component/select","title":"首页1","sort":1},{"id":1001,"url":"http://image.gokgm.com:81/20191105215339_EV3EEN7S.jpg","link":"https://element.eleme.cn/2.7/#/zh-CN/component/select","title":"首页2","sort":2},{"id":1005,"url":"http://image.gokgm.com:81/20191105215339_EV3EEN7S.jpg","link":"https://element.eleme.cn/2.7/","title":"首页3","sort":3}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<TaoLearnBean> tao_learn;
        private List<IndexBean> index;

        public List<TaoLearnBean> getTao_learn() {
            return tao_learn;
        }

        public void setTao_learn(List<TaoLearnBean> tao_learn) {
            this.tao_learn = tao_learn;
        }

        public List<IndexBean> getIndex() {
            return index;
        }

        public void setIndex(List<IndexBean> index) {
            this.index = index;
        }

        public static class TaoLearnBean {
            /**
             * id : 1002
             * url : http://image.gokgm.com:81/20191105215339_EV3EEN7S.jpg
             * link : https://element.eleme.cn/2.7/#/zh-CN/component/select
             * title : 淘学1
             * sort : 1
             */

            private int id;
            private String url;
            private String link;
            private String title;
            private int sort;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }
        }

        public static class IndexBean {
            /**
             * id : 1000
             * url : http://image.gokgm.com:81/20191105215339_EV3EEN7S.jpg
             * link : https://element.eleme.cn/2.7/#/zh-CN/component/select
             * title : 首页1
             * sort : 1
             */

            private int id;
            private String url;
            private String link;
            private String title;
            private int sort;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }
        }
    }
}
