package sinia.com.baihangeducation.bean;

import java.io.Serializable;

/**
 * Created by 忧郁的眼神 on 2016/7/29.
 */
public class FundDetailBean implements Serializable {

    private int isSuccessful;
    private int state;
    private String fundContent;
    private String price;
    private String imageUrl;

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

    public String getFundContent() {
        return fundContent;
    }

    public void setFundContent(String fundContent) {
        this.fundContent = fundContent;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
