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

public class UserBus {
    private MySet mySetBean;

    public UserBus(MySet mySetBean) {
        this.mySetBean = mySetBean;
    }

    public MySet getMySetBean() {
        return mySetBean;
    }

    public void setMySetBean(MySet mySetBean) {
        this.mySetBean = mySetBean;
    }
}
