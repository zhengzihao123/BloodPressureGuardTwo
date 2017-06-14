package test.jiyun.com.bloodpressureguard.activity.huan_num;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;
import test.jiyun.com.bloodpressureguard.model.bean.BangDing;
import test.jiyun.com.bloodpressureguard.model.callback.ResaultCallBack;
import test.jiyun.com.bloodpressureguard.model.http.RetrofitUtil;
import test.jiyun.com.bloodpressureguard.utils.HoastUtils;
import test.jiyun.com.bloodpressureguard.utils.ToastUtils;

/**
 * 项目名称：城市通
 * 类描述：
 * 创建人 londn
 * 创建时间： 2017-6-13
 * 修改人：
 * 修改时间：
 * 修改内容：
 */

public class BangDing_Activity extends BaseActivity {
    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.Pnum)
    TextView Pnum;
    @Bind(R.id.mEdiText_YanZhngMa)
    EditText mEdiTextYanZhngMa;
    @Bind(R.id.Button_BangDing)
    Button ButtonBangDing;
    @Bind(R.id.title_text)
    TextView titleText;
    private String num;
   private  String string;

    @Override
    protected int layoutId() {
        return R.layout.queding_yanzhengma;
    }

    @Override
    protected void initView() {
        titleText.setText("绑定手机号");
        Intent intent = getIntent();
       num =  intent.getStringExtra("aaa");
        Log.i("vvvvvvvvvvvv",num+"");
        Pnum.setText("我们已经下发验证码到这个手机号："+num);
        string = mEdiTextYanZhngMa.getText().toString().trim();




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

    @OnClick({R.id.back_img, R.id.Pnum, R.id.mEdiText_YanZhngMa, R.id.Button_BangDing})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                onBackPressed();
                break;
            case R.id.Pnum:
                break;
            case R.id.mEdiText_YanZhngMa:
                break;
            case R.id.Button_BangDing:
                Map<String,String > map = new HashMap();
                map.put("act","sms");
                map.put("fun","verifyCode");
                map.put("target",num);
                map.put("tag","BloodAndroid");
                map.put("code",string);
                map.put("yz","1");
                map.put("sign","2c19b2821ebc5306c3ac37bac5b4288b");
                map.put("type","3");
                RetrofitUtil.getInstance().getRetrofit("http://api.wws.xywy.com/index.php", map, new ResaultCallBack() {
                    @Override
                    public void onSuccess(Object obj) {
                        BangDing bangdingBean= (BangDing) obj;
                        if (bangdingBean.getCode()==10000){
                            ToastUtils.showShortToast("提交成功~");
                            finish();
                        }else {
                            ToastUtils.showShortToast("提交失败");
                        }

                    }

                    @Override
                    public void onError(String errorMsg) {
                        ToastUtils.showShortToast("请求失败");
                    }

                    @Override
                    public void notNet(String netData) {
                        ToastUtils.showShortToast("无网络连接");

                    }

                    @Override
                    public void onErrorParams(String errorParams) {
                        ToastUtils.showShortToast("参数错误");

                    }
                }, BangDing.class);

                break;
        }
    }
}
