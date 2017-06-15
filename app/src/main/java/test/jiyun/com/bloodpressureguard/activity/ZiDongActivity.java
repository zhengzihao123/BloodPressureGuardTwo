package test.jiyun.com.bloodpressureguard.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;

/**
 * Created by 韩志军 on 2017/6/15.
 */

public class ZiDongActivity extends BaseActivity {
    @Bind(R.id.mOpenBluetooth)
    TextView mOpenBluetooth;
    @Bind(R.id.mStartCL)
    TextView mStartCL;

    @Override
    protected int layoutId() {
        return R.layout.zi_dong_activity;
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



    @OnClick({R.id.mOpenBluetooth, R.id.mStartCL})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            /*打开蓝牙*/
            case R.id.mOpenBluetooth:
                break;
            /*开始测试*/
            case R.id.mStartCL:
                break;
        }
    }
}
