package sinia.com.baihangeducation.bean;

import java.util.List;

/**
 * Created by 忧郁的眼神 on 2016/7/28.
 */
public class ResumeBean {


    /**
     * isSuccessful : 0
     * state : 0
     * items : [{"resumeId":"402880fb5634eaf5015634f79fd20035","city":"南京","sex":2,"name":"出","birth":"1960-01-01","telephone":"13337704256","email":"1013630093@qq.com"}]
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
         * resumeId : 402880fb5634eaf5015634f79fd20035
         * city : 南京
         * sex : 2
         * name : 出
         * birth : 1960-01-01
         * telephone : 13337704256
         * email : 1013630093@qq.com
         */
        private String resumeId;
        private String city;
        private int sex;
        private String name;
        private String birth;
        private String telephone;
        private String email;

        public void setResumeId(String resumeId) {
            this.resumeId = resumeId;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getResumeId() {
            return resumeId;
        }

        public String getCity() {
            return city;
        }

        public int getSex() {
            return sex;
        }

        public String getName() {
            return name;
        }

        public String getBirth() {
            return birth;
        }

        public String getTelephone() {
            return telephone;
        }

        public String getEmail() {
            return email;
        }
    }
}
