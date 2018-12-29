package com.chetuhui.lcj.chezhubao_x.model;

import java.util.List;

public class PopularBean {

    /**
     * total : 0
     * data : [{"id":2,"createTime":"2018-11-25","createBy":"2222","updateTime":null,"updateBy":"23233","version":1,"title":"互助平台有风险","content":"互助平台有风险吗","likeNum":2,"noLikeNum":3,"state":1,"imgurl":null,"questionOrder":1}]
     * code : 0
     * msg : 成功
     * pageNum : 0
     */

    private int total;
    private String code;
    private String msg;
    private int pageNum;
    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * createTime : 2018-11-25
         * createBy : 2222
         * updateTime : null
         * updateBy : 23233
         * version : 1
         * title : 互助平台有风险
         * content : 互助平台有风险吗
         * likeNum : 2
         * noLikeNum : 3
         * state : 1
         * imgurl : null
         * questionOrder : 1
         */

        private int id;
        private String createTime;
        private String createBy;
        private Object updateTime;
        private String updateBy;
        private int version;
        private String title;
        private String content;
        private int likeNum;
        private int noLikeNum;
        private int state;
        private Object imgurl;
        private int questionOrder;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }

        public int getNoLikeNum() {
            return noLikeNum;
        }

        public void setNoLikeNum(int noLikeNum) {
            this.noLikeNum = noLikeNum;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public Object getImgurl() {
            return imgurl;
        }

        public void setImgurl(Object imgurl) {
            this.imgurl = imgurl;
        }

        public int getQuestionOrder() {
            return questionOrder;
        }

        public void setQuestionOrder(int questionOrder) {
            this.questionOrder = questionOrder;
        }
    }
}