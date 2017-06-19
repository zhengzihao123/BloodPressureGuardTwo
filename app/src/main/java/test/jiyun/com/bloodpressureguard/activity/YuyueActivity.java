package test.jiyun.com.bloodpressureguard.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.App;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;

/**
 * Created by ASUS on 2017/6/14.
 */

public class YuyueActivity extends BaseActivity {
    @Bind(R.id.plus_name)
    EditText plusName;
    @Bind(R.id.plus_province)
    EditText plusProvince;
    @Bind(R.id.plus_ip)
    EditText plusIp;
    @Bind(R.id.plus_phone_number)
    EditText plusPhoneNumber;
    @Bind(R.id.plus_btn)
    Button plusBtn;
    @Bind(R.id.mFinsh)
    ImageView mFinsh;
    @Bind(R.id.mTitle)
    TextView mTitle;

    @Override
    protected int layoutId() {
        return R.layout.plus_detail;
    }

    @Override
    protected void initView() {
        mTitle.setText("加号信息");
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void listener() {

    }

    @OnClick({R.id.mFinsh, R.id.plus_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mFinsh:
                finish();
                break;
            case R.id.plus_btn:
                if (TextUtils.isEmpty(plusName.getText().toString())) {
                    showToast("名字不能为空~");
                    return;
                }
                if (TextUtils.isEmpty(plusProvince.getText().toString())) {
                    showToast("省份不能为空~");
                    return;
                }
                if (TextUtils.isEmpty(plusIp.getText().toString())) {
                    showToast("身份证不能为空~");
                    return;
                }
                if (TextUtils.isEmpty(plusPhoneNumber.getText().toString())) {
                    showToast("电话不能为空~");
                    return;
                }
                String ss = "\\d{17}[\\d|x]";
                boolean matches = plusIp.getText().toString().matches(ss);
                if (!matches) {
                    showToast("身份证号码输入不正确~");
                    return;
                }
                String ss1 = "(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7}";
                boolean matches1 = plusPhoneNumber.getText().toString().matches(ss1);
                if (!matches1) {
                    showToast("手机号码输入不正确~");
                    return;
                }

                Intent intent = new Intent(App.activity, DiseaseActivity.class);
                startActivity(intent);
                break;
        }
    }


}
