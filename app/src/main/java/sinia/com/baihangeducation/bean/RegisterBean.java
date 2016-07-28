package sinia.com.baihangeducation.bean;

/**
 * Created by 忧郁的眼神 on 2016/7/28.
 */
public class RegisterBean {

    /**
     * isSuccessful : 0
     * customerId : 402880fb562fcd4f0156301fab4a000f
     * state : 0
     */
    private int isSuccessful;
    private String customerId;
    private int state;

    public void setIsSuccessful(int isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getIsSuccessful() {
        return isSuccessful;
    }

    public String getCustomerId() {
        return customerId;
    }

    public int getState() {
        return state;
    }
}
