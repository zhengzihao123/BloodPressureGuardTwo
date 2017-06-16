package test.jiyun.com.bloodpressureguard.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;

import static android.R.attr.handle;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by 韩志军 on 2017/6/15.
 */

public class ZiDongActivity extends BaseActivity {
    @Bind(R.id.mOpenBluetooth)
    TextView mOpenBluetooth;
    @Bind(R.id.mStartCL)
    TextView mStartCL;

    /*高压和低压高度*/
    @Bind(R.id.mDiYa)
    TextView mDiYa;
    @Bind(R.id.mGaoYa)
    TextView mGaoYa;

    /*高压和低压数据*/
    @Bind(R.id.mDiYaText)
    TextView mDiYaText;
    @Bind(R.id.mGaoYaText)
    TextView mGaoYaText;

    private LinearLayout.LayoutParams mGYLayoutParams;
    private LinearLayout.LayoutParams mDYLayoutParams;
    private LinearLayout.LayoutParams mLayoutParams;

    /*高低压位置*/
    private int heightG = 0;
    private int heightD = 0;
    private int width = 200;

    private int g = 0;
    private int d = 0;

    private String str="/mmHg";

    private String strG = g + str;
    private String strD = d + str;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            switch (what) {
                case 1:
                    heightG += 10;
                    heightD += 6;
                    g+=10;
                    d+=6;
                    strG=g+str;
                    strD=d+str;
                    getDongHua(width,heightG,heightD,strG,strD);

                    if (heightG > 900)
                        handler.sendEmptyMessageDelayed(2, 300);
                    else
                        handler.sendEmptyMessageDelayed(1, 30);
                    break;
                case 2:
                    Log.d("ZiDongActivity", "走了");
                    heightG -= 10;
                    heightD -= 6;
                    g-=10;
                    d-=6;
                    strG=g+str;
                    strD=d+str;
                    getDongHua(width,heightG,heightD,strG,strD);
                    if (heightG==0)
                        handler.sendEmptyMessageDelayed(3, 600);
                    else
                        handler.sendEmptyMessageDelayed(2, 200);
                    break;
                case 3:
                    Toast.makeText(ZiDongActivity.this, "测量完成", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected int layoutId() {
        return R.layout.zi_dong_activity;
    }

    @Override
    protected void initView() {
        getDongHua(width,heightG,heightD,strG,strD);
    }

    private void getDongHua(int width,int heightG,int heightD,String strG,String strD) {
        mGYLayoutParams = new LinearLayout.LayoutParams(width, heightG);
        mDYLayoutParams = new LinearLayout.LayoutParams(width, heightD);
        mLayoutParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);

        /*高低压数据*/
        mGaoYaText.setText(strG);
        mDiYaText.setText(strD);
        mGaoYaText.setLayoutParams(mLayoutParams);
        mDiYaText.setLayoutParams(mLayoutParams);
        /*高低压位置*/
        mGaoYa.setLayoutParams(mGYLayoutParams);
        mDiYa.setLayoutParams(mDYLayoutParams);
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
                handler.sendEmptyMessageDelayed(1, 300);
                break;
        }
    }

}
