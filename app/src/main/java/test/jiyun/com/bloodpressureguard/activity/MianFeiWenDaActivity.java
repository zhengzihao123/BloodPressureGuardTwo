package test.jiyun.com.bloodpressureguard.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
 * 创建时间： 2017-6-14
 * 修改人：
 * 修改时间：
 * 修改内容：
 */

public class MianFeiWenDaActivity extends BaseActivity {


    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.title_text)
    TextView titleText;
    @Override
    protected int layoutId() {
        return R.layout.free_questions;
    }

    @Override
    protected void initView() {
        titleText.setText("免费提问");
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

    @OnClick({R.id.back_img, R.id.title_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                onBackPressed();
                break;
            case R.id.title_text:
                break;
        }
    }
}
