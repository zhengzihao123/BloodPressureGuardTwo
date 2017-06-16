package test.jiyun.com.bloodpressureguard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.activity.huan_num.Huan_PhoneNum;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;

/**
 * 项目名称：城市通
 * 类描述：
 * 创建人 londn
 * 创建时间： 2017-6-11
 * 修改人：
 * 修改时间：
 * 修改内容：
 */

public class My_Set extends BaseActivity {
    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.ZhangHu)
    RelativeLayout ZhangHu;
    @Bind(R.id.Clear)
    RelativeLayout Clear;
    @Bind(R.id.GuanYu)
    RelativeLayout GuanYu;

    @Override
    protected int layoutId() {
        return R.layout.my_set;
    }

    @Override
    protected void initView() {
        titleText.setText("设置");

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

    @OnClick({R.id.back_img, R.id.title_text, R.id.ZhangHu, R.id.Clear, R.id.GuanYu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                onBackPressed();
                break;
            case R.id.title_text:
                break;
            case R.id.ZhangHu:
                Intent intent = new Intent(getApplicationContext(),Huan_PhoneNum.class);
                startActivity(intent);
                break;
            case R.id.Clear:
                break;
            case R.id.GuanYu:
                Intent intent1 = new Intent(this,XueYaWeiShiActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
