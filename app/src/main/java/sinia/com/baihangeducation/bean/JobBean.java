package sinia.com.baihangeducation.bean;

import java.util.List;

/**
 * Created by 忧郁的眼神 on 2016/7/28.
 */
public class JobBean {


    /**
     * isSuccessful : 0
     * state : 0
     * items : [{"positionType":"制造","items2":[{"positionSmall":"生产文员"},{"positionSmall":"工艺师"}]},{"positionType":"销售行政","items2":[{"positionSmall":"商务经理"}]}]
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

    public class ItemsEntity {
        /**
         * positionType : 制造
         * items2 : [{"positionSmall":"生产文员"},{"positionSmall":"工艺师"}]
         */
        private String positionType;
        private List<Items2Entity> items2;

        public void setPositionType(String positionType) {
            this.positionType = positionType;
        }

        public void setItems2(List<Items2Entity> items2) {
            this.items2 = items2;
        }

        public String getPositionType() {
            return positionType;
        }

        public List<Items2Entity> getItems2() {
            return items2;
        }

        public class Items2Entity {
            /**
             * positionSmall : 生产文员
             */
            private String positionSmall;

            public void setPositionSmall(String positionSmall) {
                this.positionSmall = positionSmall;
            }

            public String getPositionSmall() {
                return positionSmall;
            }
        }
    }
}
