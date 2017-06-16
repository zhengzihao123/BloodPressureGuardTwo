package test.jiyun.com.bloodpressureguard.activity;

import android.os.Bundle;
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
import test.jiyun.com.bloodpressureguard.model.bean.Message;
import test.jiyun.com.bloodpressureguard.model.callback.ResaultCallBack;
import test.jiyun.com.bloodpressureguard.model.http.RetrofitUtil;
import test.jiyun.com.bloodpressureguard.utils.ToastUtils;
import test.jiyun.com.bloodpressureguard.utils.UserUtils;

/**
 * 项目名称：城市通
 * 类描述：
 * 创建人 londn
 * 创建时间： 2017-6-15
 * 修改人：
 * 修改时间：
 * 修改内容：
 */

public class YiJianActivity extends BaseActivity {
    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.YiJian)
    EditText YiJian;
    @Bind(R.id.FaSong)
    Button FaSong;
    private String NeiRong;
    private String id;

    @Override
    protected int layoutId() {
        return R.layout.yijian_fankui;
    }

    @Override
    protected void initView() {
        titleText.setText("意见反馈");
        id = UserUtils.getUSERID();


    }

    @Override
    protected void loadData() {


    }

    @Override
    protected void listener() {
        FaSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NeiRong = YiJian.getText().toString();
                Map<String, String> map = new HashMap<>();
                map.put("content", NeiRong);
                map.put("apptype", "10004");
                map.put("userid", id);
                RetrofitUtil.getInstance().postRetrofit("http://api.wws.xywy.com/index.php?&tag=BloodAndroid&sign=2c19b2821ebc5306c3ac37bac5b4288b&act=kbb&fun=user_feedback", map, new ResaultCallBack() {
                    @Override
                    public void onSuccess(Object obj) {
                        Message message = (Message) obj;
                        if (message.getState() == 200) {
                            ToastUtils.showShortToast("意见反馈成功~~");
                            finish();
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
                }, Message.class);

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                onBackPressed();
                break;

        }
    }
}
