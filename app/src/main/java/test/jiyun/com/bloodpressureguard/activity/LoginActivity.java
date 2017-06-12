package test.jiyun.com.bloodpressureguard.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.App;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;
import test.jiyun.com.bloodpressureguard.model.bean.Login;
import test.jiyun.com.bloodpressureguard.model.bean.MySet;
import test.jiyun.com.bloodpressureguard.model.bean.UserBus;
import test.jiyun.com.bloodpressureguard.model.callback.ResaultCallBack;
import test.jiyun.com.bloodpressureguard.model.http.RetrofitUtil;
import test.jiyun.com.bloodpressureguard.utils.HoastUtils;
import test.jiyun.com.bloodpressureguard.utils.KeyUtils;
import test.jiyun.com.bloodpressureguard.utils.ToastUtils;

/**
 * 项目名称：城市通
 * 类描述：
 * 创建人 londn
 * 创建时间： 2017-6-10
 * 修改人：
 * 修改时间：
 * 修改内容：
 */

public class LoginActivity extends BaseActivity {
    @Bind(R.id.UserName)
    EditText UserName;
    @Bind(R.id.UserPassword)
    EditText UserPassword;
    @Bind(R.id.Forgot_password)
    TextView ForgotPassword;
    @Bind(R.id.register)
    TextView register;
    @Bind(R.id.Login_Btn)
    Button LoginBtn;
    private Boolean isLogin  = true;

    @Override
    protected int layoutId() {
        return R.layout.login_activity;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void listener() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.Forgot_password, R.id.Login_Btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Forgot_password:
                break;
            case R.id.Login_Btn:
                if (TextUtils.isEmpty(UserName.getText())) {
                    ToastUtils.showShortToast("请输入用户名");
                    return;
                }
                if (TextUtils.isEmpty(UserPassword.getText())) {
                    ToastUtils.showShortToast("请输入密码");
                    return;
                }
                if (UserName.length() < 4) {
                    ToastUtils.showShortToast("用户名不合法");
                    return;
                }
                if (UserPassword.length() <= 6) {
                    ToastUtils.showShortToast("输入的密码不合法");
                    return;
                }
                String UsrerName = UserName.getText().toString().trim();
                String Usrepassword = UserPassword.getText().toString().trim();
                final Map<String, String> map = new HashMap();
                map.put("phonenum", UsrerName);
                map.put("password", Usrepassword);
                RetrofitUtil.getInstance().postRetrofit("http://api.wws.xywy.com/index.php?&tag=BloodAndroid&sign=2c19b2821ebc5306c3ac37bac5b4288b&act=kbb&fun=users&type=login\n" +
                        "\n", map, new ResaultCallBack() {
                    @Override
                    public void onSuccess(Object obj) {

                        Login loginbean = (Login) obj;
                        if (loginbean.getState() == 200) {
                            SharedPreferences.Editor edit = App.sharedPreferences.edit();
                            edit.putString(KeyUtils.USERID, loginbean.getUserid());
                            edit.putBoolean(KeyUtils.ZhuangTai,isLogin);
                            edit.putString(KeyUtils.PHONENUM,loginbean.getPhonenum());
                            edit.commit();
                            Map<String, String> map1 = new HashMap();
                            map1.put("act", "kbb");
                            map1.put("fun", "users");
                            map1.put("type", "pullAccountInfo");
                            map1.put("tag", "wjk");
                            map1.put("userid", loginbean.getUserid());
                            map1.put("sign","ee3dd4651821d3a45f4329a86d459cb7");
                            RetrofitUtil.getInstance().getRetrofit(HoastUtils.HOST, map1, new ResaultCallBack() {
                                @Override
                                public void onSuccess(Object obj) {
                                    MySet mySetBean = (MySet) obj;
                                    UserBus userBus = new UserBus(mySetBean);
                                    EventBus.getDefault().postSticky(userBus);
                                    ToastUtils.showShortToast("登录成功");
                                    finish();
                                }

                                @Override
                                public void onError(String errorMsg) {

                                }

                                @Override
                                public void notNet(String netData) {

                                }

                                @Override
                                public void onErrorParams(String errorParams) {

                                }
                            }, MySet.class);

                            finish();
                        } else {
                            ToastUtils.showShortToast("登录失败~");
                        }

                    }

                    @Override
                    public void onError(String errorMsg) {

                    }

                    @Override
                    public void notNet(String netData) {

                    }

                    @Override
                    public void onErrorParams(String errorParams) {

                    }
                }, Login.class);


                break;
        }
    }
}
