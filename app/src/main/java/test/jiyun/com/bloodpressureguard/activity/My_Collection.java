package test.jiyun.com.bloodpressureguard.activity;

import java.util.HashMap;
import java.util.Map;

import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;
import test.jiyun.com.bloodpressureguard.utils.UserUtils;

/**
 * 项目名称：城市通
 * 类描述：
 * 创建人 londn
 * 创建时间： 2017-6-12
 * 修改人：
 * 修改时间：
 * 修改内容：
 */

public class My_Collection extends BaseActivity {
    private int id ;
    @Override
    protected int layoutId() {
        return R.layout.my_collection;
    }

    @Override
    protected void initView() {
        id = UserUtils.getUSERID();


    }

    @Override
    protected void loadData() {
        Map<String,String > map  = new HashMap();
        map.put("xywy_userid", String.valueOf(id));
        map.put("app_id","2");
        map.put("sign","2c19b2821ebc5306c3ac37bac5b4288b");
        map.put("tag","BloodAndroid");



    }

    @Override
    protected void listener() {

    }
}
