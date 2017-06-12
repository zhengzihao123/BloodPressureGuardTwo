package test.jiyun.com.bloodpressureguard.activity.huan_num;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
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
import test.jiyun.com.bloodpressureguard.model.bean.Phone;
import test.jiyun.com.bloodpressureguard.model.callback.ResaultCallBack;
import test.jiyun.com.bloodpressureguard.model.http.RetrofitUtil;
import test.jiyun.com.bloodpressureguard.utils.HoastUtils;
import test.jiyun.com.bloodpressureguard.utils.ToastUtils;
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

public class YanZheng_Ma extends BaseActivity {
    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.Num_Text)
    TextView NumText;
    @Bind(R.id.edit_num)
    EditText editNum;
    @Bind(R.id.send_yanzhengma)
    Button sendYanzhengma;
    private String num;
    private boolean isSendRegist;

//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what){
//                case 100:
//                    int time = (int) msg.obj;
//
//                    if(time>0){
//                       sendYanzhengma.setText(time+"秒");
//                        Message message = new Message();
//                        message.what =100;
//                        message.obj = time-1;
//                        handler.sendMessageDelayed(message,1500);
//                    }else{
//                        isSendRegist = true;
//                        sendYanzhengma.setText("发送验证码");
//                    }
//                    break;
//            }
//
//        }
//    };

    @Override
    protected int layoutId() {
        return R.layout.yanzheng_phonenum;
    }

    @Override
    protected void initView() {

        titleText.setText("验证手机号");
        num = UserUtils.getUSERPHONENUM();
        NumText.setText("更换成功后，下次登录可以使用新的手机号，当前手机号为：" + num);
        isSendRegist = true;

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

    @OnClick({R.id.back_img, R.id.title_text, R.id.Num_Text, R.id.edit_num, R.id.send_yanzhengma})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                onBackPressed();
                break;
            case R.id.title_text:
                break;
            case R.id.Num_Text:
                break;
            case R.id.edit_num:
                break;
            case R.id.send_yanzhengma:
                if (TextUtils.isEmpty(editNum.getText())) {
                    ToastUtils.showShortToast("请输入手机号");
                    return;
                }
                editNum.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (editNum.getText().length() >= 11) {
                            sendYanzhengma.setBackgroundColor(Color.parseColor("#4CBF5B"));
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (editNum.getText().length() >= 11) {
                            sendYanzhengma.setBackgroundColor(Color.parseColor("#4CBF5B"));
                        } else {
                            sendYanzhengma.setBackgroundColor(Color.parseColor("#5f5d5d"));
                        }
                    }

                });
                //http://api.wws.xywy.com/index.php?act=sms&fun=sendCode&target=17600044200&tag=BloodAndroid&sign=2c19b2821ebc5306c3ac37bac5b4288b&type=3
                Map<String,String > map = new HashMap<>();
                map.put("act","sms");
                map.put("fun","sendCode");
                map.put("target",editNum.getText().toString().trim());
                map.put("tag","BloodAndroid");
                map.put("sign","2c19b2821ebc5306c3ac37bac5b4288b");
                map.put("type","3");
                RetrofitUtil.getInstance().getRetrofit(HoastUtils.HOST, map, new ResaultCallBack() {
                    @Override
                    public void onSuccess(Object obj) {
                        Phone phoneBean = (Phone) obj;
                       if (phoneBean.getCode()==1000){
                           ToastUtils.showShortToast("验证码已发送");
//                           Message message = new Message();
//                           message.what =100;
//                           message.obj = 120;
//                           handler.sendMessage(message);

                       }else {
                           ToastUtils.showShortToast("此手机已验证");
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
                }, Phone.class);




                break;
        }
    }
}

