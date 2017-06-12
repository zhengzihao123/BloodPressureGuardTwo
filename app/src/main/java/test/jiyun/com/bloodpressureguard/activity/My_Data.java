package test.jiyun.com.bloodpressureguard.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;
import test.jiyun.com.bloodpressureguard.utils.ToastUtils;
import test.jiyun.com.bloodpressureguard.utils.WheelView;

/**
 * 项目名称：城市通
 * 类描述：
 * 创建人 londn
 * 创建时间： 2017-6-12
 * 修改人：
 * 修改时间：
 * 修改内容：
 */

public class My_Data extends BaseActivity {
    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.Name)
    RelativeLayout Name;
    @Bind(R.id.Sex)
    RelativeLayout Sex;
    @Bind(R.id.Height)
    RelativeLayout Height;
    @Bind(R.id.Weight)
    RelativeLayout Weight;
    @Bind(R.id.Birthday)
    RelativeLayout Birthday;
    @Bind(R.id.Sex_Nv)
    TextView SexNv;
    @Bind(R.id.height)
    TextView height;
    @Bind(R.id.Birthday_Day)
    TextView BirthdayDay;

    @Bind(R.id.weight)
    TextView weight;
    @Bind(R.id.XingMing)
    TextView XingMing;

//index.php?act=kbb&fun=resetProperty&tag=BloodAndroid&sign=2c19b2821ebc5306c3ac37bac5b4288b


    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String[] PLANETS = new String[]{"男", "女"};

    private static final String[] PLANETSA = new String[]{"60", "70", "80", "80", "100", "110", "120", "130", "140", "150", "160", "170", "180", "190", "200"};

    private static final String[] PLANETSB = new String[]{"10", "20", "30", "40", "50", "60", "70", "80", "90", "100"};




    private Calendar cal;
    private int year, month, day;


    @Override
    protected int layoutId() {
        return R.layout.people_data;
    }

    @Override
    protected void initView() {
        titleText.setText("个人资料");
        getDate();


    }

    @Override
    protected void loadData() {
        Map<String,String > map = new HashMap();




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

    @OnClick({R.id.back_img, R.id.title_text, R.id.Name, R.id.Sex, R.id.Height, R.id.Weight, R.id.Birthday})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                onBackPressed();
                break;
            case R.id.title_text:
                break;
            case R.id.Name:
                showDialog_Layout(this);
                break;


            case R.id.Sex:
                View outerView = LayoutInflater.from(this).inflate(R.layout.wheel_view, null);
                final WheelView wv = (WheelView) outerView.findViewById(R.id.WheelView);
                wv.setOffset(1);
                wv.setItems(Arrays.asList(PLANETS));
                wv.setSeletion(1);
                wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                    @Override
                    public void onSelected(int selectedIndex, String item) {
                        Log.d(TAG, "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
                    }
                });

                new AlertDialog.Builder(this)
                        .setTitle("选择性别：")
                        .setView(outerView)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String item = wv.getSeletedItem();
                                SexNv.setText(item);
                                ToastUtils.showShortToast("修改成功~");
                            }
                        })
                        .show();

                break;
            case R.id.Height:
                View outerView1 = LayoutInflater.from(this).inflate(R.layout.wheel_view, null);
                final WheelView wv1 = (WheelView) outerView1.findViewById(R.id.WheelView);
                wv1.setOffset(1);
                wv1.setItems(Arrays.asList(PLANETSA));
                wv1.setSeletion(1);
                wv1.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                    @Override
                    public void onSelected(int selectedIndex, String item) {
                        Log.d(TAG, "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
                    }
                });

                new AlertDialog.Builder(this)
                        .setTitle("选择身高：")
                        .setView(outerView1)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String item = wv1.getSeletedItem();
                                height.setText(item);
                                ToastUtils.showShortToast("修改成功~");
                            }
                        })
                        .show();


                break;
            case R.id.Weight:

                View outerView2 = LayoutInflater.from(this).inflate(R.layout.wheel_view, null);
                final WheelView wv2 = (WheelView) outerView2.findViewById(R.id.WheelView);
                wv2.setOffset(1);
                wv2.setItems(Arrays.asList(PLANETSB));
                wv2.setSeletion(1);
                wv2.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                    @Override
                    public void onSelected(int selectedIndex, String item) {
                        Log.d(TAG, "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
                    }
                });

                new AlertDialog.Builder(this)
                        .setTitle("选择体重：")
                        .setView(outerView2)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String item = wv2.getSeletedItem();
                                weight.setText(item);
                                ToastUtils.showShortToast("修改成功~");
                            }
                        })
                        .show();

                break;
            case R.id.Birthday:
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        BirthdayDay.setText(year + "-" + (++month) + "-" + day);      //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                    }
                };
                //在Activity中newSpinner是我把mContext传入，但是出了Android.view.WindowManager$BadTokenException: Unable to add window -- token null is not for an application这个错误，
                //解决  只有Activity可以添加窗口
                DatePickerDialog dialog = new DatePickerDialog(My_Data.this, 0, listener, year, month, day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();

                break;
        }
    }

    //获取当前日期
    private void getDate() {
        cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);       //获取年月日时分秒
        Log.i("wxy", "year" + year);
        month = cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        day = cal.get(Calendar.DAY_OF_MONTH);
    }

    //显示基于Layout的AlertDialog
    private void showDialog_Layout(Context context) {
        LayoutInflater inflater = LayoutInflater.from(this);
        final View textEntryView = inflater.inflate(
                R.layout.dialoglayout, null);
        final EditText edtInput = (EditText) textEntryView.findViewById(R.id.EditText_View);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle("修改姓名");
        builder.setView(textEntryView);
        builder.setPositiveButton("确认",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        setTitle(edtInput.getText());
                        XingMing.setText(edtInput.getText());
                        ToastUtils.showShortToast("修改成功~");
                    }
                });
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        setTitle("");
                    }
                });
        builder.show();
    }

}


