package test.jiyun.com.bloodpressureguard.activity.huan_num;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;
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

public class Huan_PhoneNum extends BaseActivity {
    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.Phone_Num)
    RelativeLayout PhoneNum;
    @Bind(R.id.MiMA)
    RelativeLayout MiMA;
    @Bind(R.id.TuiChu_DengLu)
    Button TuiChuDengLu;
    @Bind(R.id.phone_num)
    TextView phoneNum;
    private String phone_num;

    @Override
    protected int layoutId() {
        return R.layout.zhanghu;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {
        titleText.setText("账户");
        phone_num = UserUtils.getUSERPHONENUM();
        phoneNum.setText(phone_num);


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

    @OnClick({R.id.back_img, R.id.title_text, R.id.Phone_Num, R.id.MiMA, R.id.TuiChu_DengLu,R.id.phone_num})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                onBackPressed();
                break;
            case R.id.title_text:
                break;
            case R.id.Phone_Num:
                Intent intent = new Intent(getApplicationContext(),Phone_Num.class);
                startActivity(intent);
                break;
            case R.id.MiMA:
                break;
            case R.id.TuiChu_DengLu:
                SharedPreferences sp = getSharedPreferences("DATA", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();
                ToastUtils.showShortToast("已经退出登录！");

                finish();

                break;
            case R.id.phone_num:
                break;
        }
    }


}
