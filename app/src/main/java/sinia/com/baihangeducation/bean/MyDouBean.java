package sinia.com.baihangeducation.bean;

import java.util.List;

/**
 * Created by 忧郁的眼神 on 2016/7/29.
 */
public class MyDouBean {


    /**
     * isSuccessful : 0
     * state : 0
     * dou : 0
     * items : []
     */
    private int isSuccessful;
    private int state;
    private String dou;
    private List<Beans> items;

    public int getIsSuccessful() {
        return isSuccessful;
    }

    public void setIsSuccessful(int isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getDou() {
        return dou;
    }

    public void setDou(String dou) {
        this.dou = dou;
    }

    public List<Beans> getItems() {
        return items;
    }

    public void setItems(List<Beans> items) {
        this.items = items;
    }

    public class Beans {
        private String money;
        private String createTime;
        private String dou;
        private String changeType;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDou() {
            return dou;
        }

        public void setDou(String dou) {
            this.dou = dou;
        }

        public String getChangeType() {
            return changeType;
        }

        public void setChangeType(String changeType) {
            this.changeType = changeType;
        }
    }
}
