package sinia.com.baihangeducation.bean;

import java.io.Serializable;

/**
 * Created by 忧郁的眼神 on 2016/7/29.
 */
public class PersonalBean implements Serializable{

    /**
     * area :
     * companyType :
     * sex :
     * nowAddress :
     * telephone : 15651788424
     * userName :
     * talentType :
     * personPhone :
     * isSuccessful : 0
     * imageUrl :
     * emContact :
     * state : 0
     * age :
     * emContactPhone :
     */
    private String area;
    private String companyType;
    private String sex;
    private String nowAddress;
    private String telephone;
    private String userName;
    private String talentType;
    private String personPhone;
    private int isSuccessful;
    private String imageUrl;
    private String emContact;
    private int state;
    private String age;
    private String emContactPhone;

    public void setArea(String area) {
        this.area = area;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setNowAddress(String nowAddress) {
        this.nowAddress = nowAddress;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTalentType(String talentType) {
        this.talentType = talentType;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public void setIsSuccessful(int isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setEmContact(String emContact) {
        this.emContact = emContact;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setEmContactPhone(String emContactPhone) {
        this.emContactPhone = emContactPhone;
    }

    public String getArea() {
        return area;
    }

    public String getCompanyType() {
        return companyType;
    }

    public String getSex() {
        return sex;
    }

    public String getNowAddress() {
        return nowAddress;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getUserName() {
        return userName;
    }

    public String getTalentType() {
        return talentType;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public int getIsSuccessful() {
        return isSuccessful;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getEmContact() {
        return emContact;
    }

    public int getState() {
        return state;
    }

    public String getAge() {
        return age;
    }

    public String getEmContactPhone() {
        return emContactPhone;
    }
}
