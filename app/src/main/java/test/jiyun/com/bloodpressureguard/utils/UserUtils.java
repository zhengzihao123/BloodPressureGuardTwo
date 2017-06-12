package test.jiyun.com.bloodpressureguard.utils;

import test.jiyun.com.bloodpressureguard.App;

/**
 * 项目名称：城市通
 * 类描述：
 * 创建人 londn
 * 创建时间： 2017-6-11
 * 修改人：
 * 修改时间：
 * 修改内容：
 */

public class UserUtils {
    //用户id获取
    public static  int getUSERID(){
        return App.sharedPreferences.getInt(KeyUtils.USERID,0);
    }
    //用户touxaing获取
    public static  String getUSERImage(){
        return App.sharedPreferences.getString(KeyUtils.USERIMAGE,"");
    }
    //用户姓名
    public static  String  getUSERNAME(){
        return App.sharedPreferences.getString(KeyUtils.USERNAME,"");
    }
    //状态
    public  static  boolean getZhaungTai(){
        return  App.sharedPreferences.getBoolean(KeyUtils.ZhuangTai,false);
    }
    //用户手机号
    public static  String  getUSERPHONENUM(){
        return App.sharedPreferences.getString(KeyUtils.PHONENUM,"");
    }
}
