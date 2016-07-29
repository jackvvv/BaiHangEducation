package sinia.com.baihangeducation.bean;

import java.util.List;

/**
 * Created by 忧郁的眼神 on 2016/7/29.
 */
public class MyChuangYeBean {

    private int isSuccessful;
    private int state;
    private List<ItemsEntity> items;

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

    public List<ItemsEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemsEntity> items) {
        this.items = items;
    }

    public class ItemsEntity {
        private String orderId;
        private String fundId;
        private String fundName;
        private String buyNum;
        private String imageUrl;
        private String createTime;
        private String fundContent;
        private String realMoney;
        private String bigNum;
        private String money;//一份基金价格，
        private String price;
        private String state;//1.已支付2.未支付3.已领取4.未领取

        public String getFundId() {
            return fundId;
        }

        public void setFundId(String fundId) {
            this.fundId = fundId;
        }

        public String getFundName() {
            return fundName;
        }

        public void setFundName(String fundName) {
            this.fundName = fundName;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getBuyNum() {
            return buyNum;
        }

        public void setBuyNum(String buyNum) {
            this.buyNum = buyNum;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getFundContent() {
            return fundContent;
        }

        public void setFundContent(String fundContent) {
            this.fundContent = fundContent;
        }

        public String getRealMoney() {
            return realMoney;
        }

        public void setRealMoney(String realMoney) {
            this.realMoney = realMoney;
        }

        public String getBigNum() {
            return bigNum;
        }

        public void setBigNum(String bigNum) {
            this.bigNum = bigNum;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
