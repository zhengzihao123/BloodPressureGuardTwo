package test.jiyun.com.bloodpressureguard.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
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
    @Bind(R.id.mFinsh)
    ImageView mFinsh;

    private DatePickerDialog DateDialog;
    private TimePickerDialog TimeDialog;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int mintune;

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
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String s = sdf.format(new Date());

        mTextSJ.setText(s);
    }

    @Override
    protected void listener() {
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);       //获取年月日时分秒
        month = cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        day = cal.get(Calendar.DAY_OF_MONTH);//获取到日
        hour = cal.get(Calendar.HOUR_OF_DAY);//获取到小时 24
        mintune = cal.get(Calendar.MINUTE);
        String s = String.valueOf(month + 1);
        if (s.length() == 1) {
            s = "0" + s;
        }
        String day1 = String.valueOf(day);
        if (day1.length() == 1) {
            day1 = "0" + day1;
        }
        DateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.e("TAG", year + "---" + month + "---" + dayOfMonth);
                String s = String.valueOf(month + 1);
                if (s.length() == 1) {
                    s = "0" + s;
                }
                String day1 = String.valueOf(dayOfMonth);
                if (day1.length() == 1) {
                    day1 = "0" + day1;
                }
                mTextRQ.setText(year + "-" + s + "-" + day1);
            }
        }, year, month, day);
        TimeDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String s = String.valueOf(minute);
                if (s.length()==1)
                    s=0+s;
                String sa = String.valueOf(hourOfDay);
                if (sa.length()==1)
                    sa=0+sa;
                mTextSJ.setText(sa + ":" + s);
            }
        }, hour, mintune, true);

    }


    @OnClick({R.id.mLayoutRQ, R.id.mLayoutSJ, R.id.mBtnSC, R.id.mFinsh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            /*日期*/
            case R.id.mLayoutRQ:
                DateDialog.show();
                break;
            /*时间*/
            case R.id.mLayoutSJ:
                TimeDialog.show();
                break;
            /*确定*/
            case R.id.mBtnSC:
                if (mTextName.getText().toString().isEmpty()
                        || mTextGY.getText().toString().isEmpty()
                        || mTextDY.getText().toString().isEmpty()) {
                    Toast.makeText(this, "参数不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Integer.parseInt(mTextGY.getText().toString()) > 200 || Integer.parseInt(mTextDY.getText().toString()) > 200) {
                    Toast.makeText(this, "参数不合理，不能高于200", Toast.LENGTH_SHORT).show();
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
                if (b) {
                    Toast.makeText(this, "插入数据成功", Toast.LENGTH_SHORT).show();
                    finish();
                }


                break;
            case R.id.mFinsh:
                finish();
                break;
        }
    }


}
