package test.jiyun.com.bloodpressureguard.activity.huan_num;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

public class Phone_Num extends BaseActivity {
    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.TextView_Num)
    TextView TextViewNum;
    @Bind(R.id.GengHuan)
    Button GengHuan;
    private String num;

    @Override
    protected int layoutId() {
        return R.layout.phone_num;
    }

    @Override
    protected void initView() {
        titleText.setText("手机号");
        num = UserUtils.getUSERPHONENUM();
        TextViewNum.setText(num);

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

    @OnClick({R.id.back_img, R.id.title_text, R.id.TextView_Num, R.id.GengHuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                onBackPressed();
                break;
            case R.id.title_text:
                break;
            case R.id.TextView_Num:
                break;
            case R.id.GengHuan:
                Intent intent = new Intent(getApplicationContext(),YanZheng_Ma.class);
                startActivity(intent);
                break;
        }
    }
}
