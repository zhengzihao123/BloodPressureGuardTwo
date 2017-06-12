package test.jiyun.com.bloodpressureguard.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;
import test.jiyun.com.bloodpressureguard.sq.MyManager;
import test.jiyun.com.bloodpressureguard.sq.Student;

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

    /*数据库管理*/
    private MyManager myManager;

    @Override
    protected int layoutId() {
        return R.layout.activity_xy_sj;
    }

    @Override
    protected void initView() {
        myManager = new MyManager(ShuJuJiLuActivity.this);
        mTitle.setText("数据记录");
    }

    @Override
    protected void loadData() {
        /*获取当前日期*/
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        mTextRQ.setText(str + "");

        //通过SimpleDateFormat获取24小时制时间
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String s=sdf.format(new Date());

        mTextSJ.setText(s);
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
                if (mTextName.getText().toString().isEmpty()
                        || mTextGY.getText().toString().isEmpty()
                        || mTextDY.getText().toString().isEmpty()) {
                    Toast.makeText(this, "参数不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String name = mTextName.getText().toString();
                String gaoya = mTextGY.getText().toString();
                String diya = mTextDY.getText().toString();
                String mStrDay = mTextRQ.getText().toString();
                String mStrtime = mTextSJ.getText().toString();

                Student student = new Student();

                int yue = 0;
                String[] split = mStrDay.split("-");

                /*获取当前的月份*/
                int i = Integer.parseInt(split[1]);
                if (i < 10)
                    yue = Integer.parseInt(split[1].substring(1, 2));
                else
                    yue = Integer.parseInt(split[1]);

                /*获取当前的day*/
                int day = Integer.parseInt(split[2]);

                student.setId(yue);
                student.setDay(day);
                student.setTime(mStrtime);
                student.setDiya(Integer.parseInt(diya));
                student.setGaoya(Integer.parseInt(gaoya));
                student.setName(name);

                boolean b = myManager.addStudent(student);
                if (b){
                    Toast.makeText(this, "插入数据成功", Toast.LENGTH_SHORT).show();
                    finish();
                }


                break;
        }
    }
}
