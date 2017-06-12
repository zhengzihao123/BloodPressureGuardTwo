package test.jiyun.com.bloodpressureguard.model.bean;

import java.util.List;

/**
 * 项目名称：城市通
 * 类描述：
 * 创建人 londn
 * 创建时间： 2017-6-11
 * 修改人：
 * 修改时间：
 * 修改内容：
 */

public class MySet {

    /**
     * state : 200
     * email :
     * pushable : 1
     * avatar : http://xs3.op.xywy.com/api.iu1.xywy.com/ucenter/20170610/b0e6e734033238bb482fe127af09928816793.jpg
     * accounts : [{"accountid":0,"accountstr":"会员116928912","sex":"女","height":"160","birthday":"0000-00-00"}]
     */

    private int state;
    private String email;
    private int pushable;
    private String avatar;
    private List<AccountsBean> accounts;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPushable() {
        return pushable;
    }

    public void setPushable(int pushable) {
        this.pushable = pushable;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<AccountsBean> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountsBean> accounts) {
        this.accounts = accounts;
    }

    public static class AccountsBean {
        /**
         * accountid : 0
         * accountstr : 会员116928912
         * sex : 女
         * height : 160
         * birthday : 0000-00-00
         */

        private int accountid;
        private String accountstr;
        private String sex;
        private String height;
        private String birthday;

        public int getAccountid() {
            return accountid;
        }

        public void setAccountid(int accountid) {
            this.accountid = accountid;
        }

        public String getAccountstr() {
            return accountstr;
        }

        public void setAccountstr(String accountstr) {
            this.accountstr = accountstr;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }
    }
}
