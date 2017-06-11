package test.jiyun.com.bloodpressureguard.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;

/**
 * Created by 韩志军 on 2017/6/10.
 */

/*上传血压数据*/
public class ShuJuJiLuActivity extends BaseActivity {
    @Bind(R.id.mTitle)
    TextView mTitle;
    @Bind(R.id.mTextRQ)
    TextView mTextRQ;
    @Bind(R.id.mLayoutRQ)
    LinearLayout mLayoutRQ;
    @Bind(R.id.mTextSJ)
    TextView mTextSJ;
    @Bind(R.id.mLayoutSJ)
    LinearLayout mLayoutSJ;
    @Bind(R.id.mTextName)
    EditText mTextName;
    @Bind(R.id.mTextGY)
    EditText mTextGY;
    @Bind(R.id.mTextDY)
    EditText mTextDY;
    @Bind(R.id.mBtnSC)
    Button mBtnSC;

    @Override
    protected int layoutId() {
        return R.layout.activity_xy_sj;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {
        /*获取当前日期*/
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        mTextRQ.setText(str+"");
        /*获取当前时间*/
        long time=System.currentTimeMillis();
        Calendar mCalendar=Calendar.getInstance();
        mCalendar.setTimeInMillis(time);
        /*取得小时*/
        int mHour=mCalendar.get(Calendar.HOUR);
        /*取得分钟*/
        int mMinuts=mCalendar.get(Calendar.MINUTE);
        mTextSJ.setText(mHour+":"+mMinuts+"");
    }

    @Override
    protected void listener() {

    }


    @OnClick({R.id.mLayoutRQ, R.id.mLayoutSJ, R.id.mBtnSC})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            /*日期*/
            case R.id.mLayoutRQ:
                break;
            /*时间*/
            case R.id.mLayoutSJ:
                break;
            /*确定*/
            case R.id.mBtnSC:
                break;
        }
    }
}
