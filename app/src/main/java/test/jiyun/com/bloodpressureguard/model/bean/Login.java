package test.jiyun.com.bloodpressureguard.model.bean;

/**
 * 项目名称：城市通
 * 类描述：
 * 创建人 londn
 * 创建时间： 2017-6-10
 * 修改人：
 * 修改时间：
 * 修改内容：
 */

public class Login {

    /**
     * state : 200
     * userid : 116928912
     * isregister : 1
     * phonenum : 15103597440
     * height : 160
     * sex : 女
     * birthday : 0000-00-00
     */

    private int state;
    private String userid;
    private int isregister;
    private String phonenum;
    private int height;
    private String sex;
    private String birthday;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getIsregister() {
        return isregister;
    }

    public void setIsregister(int isregister) {
        this.isregister = isregister;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
