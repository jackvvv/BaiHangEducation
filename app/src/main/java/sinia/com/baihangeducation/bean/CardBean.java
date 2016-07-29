package sinia.com.baihangeducation.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 忧郁的眼神 on 2016/7/29.
 */
public class CardBean {


    /**
     * isSuccessful : 0
     * state : 0
     * items : [{"number":"","fundId":"2","price":300,"fundIamge":"","fundName":"800万风投"},{"number":"","fundId":"3","price":200,"fundIamge":"","fundName":"500万风投"},{"number":"","fundId":"4","price":200,"fundIamge":"","fundName":"300万风投"},{"number":"","fundId":"5","price":200,"fundIamge":"","fundName":"800万风投"}]
     */
    private int isSuccessful;
    private int state;
    private List<ItemsEntity> items;

    public void setIsSuccessful(int isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setItems(List<ItemsEntity> items) {
        this.items = items;
    }

    public int getIsSuccessful() {
        return isSuccessful;
    }

    public int getState() {
        return state;
    }

    public List<ItemsEntity> getItems() {
        return items;
    }

    public class ItemsEntity implements Serializable {
        private String certificate;

        public String getCertificate() {
            return certificate;
        }

        public void setCertificate(String certificate) {
            this.certificate = certificate;
        }
    }
}
