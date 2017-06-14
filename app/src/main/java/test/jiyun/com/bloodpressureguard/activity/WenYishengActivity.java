package test.jiyun.com.bloodpressureguard.activity;

import android.content.Intent;
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
 * Created by 韩志军 on 2017/6/12.
 */

/*问医生*/
public class WenYishengActivity extends BaseActivity {
    @Bind(R.id.mTitle)
    TextView mTitle;
    @Bind(R.id.mTiWen)
    TextView mTiWen;
    @Bind(R.id.mFinsh)
    ImageView mFinsh;

    @Override
    protected int layoutId() {
        return R.layout.activity_wen_yis;
    }

    @Override
    protected void initView() {
        mTitle.setText("问医生");

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void listener() {

    }


    @OnClick({R.id.mFinsh, R.id.mTiWen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mFinsh:
                finish();
                break;
            case R.id.mTiWen:
                Intent in = new Intent(WenYishengActivity.this, MianFwiTiWenActivity.class);
                startActivity(in);
                break;
        }
    }

}
