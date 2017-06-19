package test.jiyun.com.bloodpressureguard.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;

/**
 * 项目名称：城市通
 * 类描述：
 * 创建人 londn
 * 创建时间： 2017-6-15
 * 修改人：
 * 修改时间：
 * 修改内容：
 */

public class XueYaWeiShiActivity extends BaseActivity {
    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.pingFen)
    RelativeLayout pingFen;
    @Bind(R.id.YiJian_FanKui)
    RelativeLayout YiJianFanKui;

    @Override
    protected int layoutId() {
        return R.layout.guanyu_xueyaws;
    }

    @Override
    protected void initView() {
        titleText.setText("关于血压卫士");

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

    @OnClick({R.id.back_img, R.id.pingFen, R.id.YiJian_FanKui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                onBackPressed();
                break;
            case R.id.pingFen:
                Uri uri = Uri.parse("market://details?id=" + getPackageName());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.YiJian_FanKui:
                Intent intent1 = new Intent(this, YiJianActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
