package test.jiyun.com.bloodpressureguard.fragment;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.activity.ShuJuJiLuActivity;
import test.jiyun.com.bloodpressureguard.activity.WenYishengActivity;
import test.jiyun.com.bloodpressureguard.activity.XueYaJiLuActivity;
import test.jiyun.com.bloodpressureguard.activity.XueYaZXActivity;
import test.jiyun.com.bloodpressureguard.activity.ZiDongActivity;
import test.jiyun.com.bloodpressureguard.base.BaseFragment;
import test.jiyun.com.bloodpressureguard.sq.MyManager;
import test.jiyun.com.bloodpressureguard.sq.Student;
import test.jiyun.com.bloodpressureguard.view.MyStatisticsView;
import test.jiyun.com.bloodpressureguard.view.MyStatisticsViewThree;
import test.jiyun.com.bloodpressureguard.view.MyStatisticsViewTwoFour;


/**
 * Created by 韩志军 on 2017/6/9.
 */

/*血压管理Fragment*/
public class BloodPressureFragment extends BaseFragment {
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.mBtnA)
    RadioButton mBtnA;
    @Bind(R.id.mBtnC)
    RadioButton mBtnC;
    @Bind(R.id.mBtnD)
    RadioButton mBtnD;
    @Bind(R.id.mView)
    MyStatisticsView mView;
    @Bind(R.id.mTextWen)
    TextView mTextWen;
    @Bind(R.id.mTextZX)
    TextView mTextZX;
    @Bind(R.id.mTextTX)
    TextView mTextTX;
    @Bind(R.id.mViewThree)
    MyStatisticsViewThree mViewThree;
    @Bind(R.id.mViewFour)
    MyStatisticsViewTwoFour mViewFour;
    @Bind(R.id.mLL)
    LinearLayout mLL;

    private List mGaoYa;/*高压集合*/
    private List mDiYa;/*低压集合*/
    private List mTime;
    private MyManager myManager;

    private PopupWindow mPopup;
    private TextView mTextSD, mTextZD;
    private RelativeLayout mRelativeLayout;


    @Override
    protected int ViewID() {
        return R.layout.fragment_blood_pressure;
    }

    @Override
    protected void initView() {
        myManager = new MyManager(getActivity());
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void listener() {
        /*默认第一个按钮是点击的*/
        mBtnA.setChecked(true);

        getMyPopup();

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBtnA.isChecked()) {
            mViewOneShow();
        }
    }

    @OnClick({R.id.mView, R.id.mViewThree, R.id.mViewFour, R.id.imageView, R.id.mBtnA, R.id.mBtnC, R.id.mBtnD, R.id.mTextWen, R.id.mTextZX, R.id.mTextTX})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            /*上传血压数据*/
            case R.id.imageView:
                mPopup.showAtLocation(mLL, 0, 0, Gravity.CENTER);
                break;
            case R.id.mBtnA:
                mViewOneShow();
                break;
            case R.id.mBtnC:
                mViewThreeShow();
                break;
            case R.id.mBtnD:
                mViewFourShow();
                break;
            /*医生*/
            case R.id.mTextWen:
                Intent inWen = new Intent(getActivity(), WenYishengActivity.class);
                getActivity().startActivity(inWen);
                break;
            /*资讯*/
            case R.id.mTextZX:
                Intent inZX = new Intent(getActivity(), XueYaZXActivity.class);
                getActivity().startActivity(inZX);
                break;
            /*提醒*/
            case R.id.mTextTX:
                break;
            case R.id.mView:
                getTiaoZhuang();
                break;
            case R.id.mViewThree:
                getTiaoZhuang();
                break;
            case R.id.mViewFour:
                getTiaoZhuang();
                break;
        }
    }

    /*查询某一天的数据*/
    private void mViewOneShow() {
        mGaoYa = new ArrayList();
        mDiYa = new ArrayList();
        mTime = new ArrayList();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        int yue = 0;
        String[] split = str.split("-");
          /*获取当前的月份*/
        int i = Integer.parseInt(split[0]);
        if (i < 10)
            yue = Integer.parseInt(split[0].substring(1, 2));
        else
            yue = Integer.parseInt(split[0]);

                /*获取当前的day*/
        int day = Integer.parseInt(split[1]);
        Log.d("BloodPressureFragment", "day:" + day);
        ArrayList<Student> ri = myManager.getRi(yue, day);
        if (ri.size() <= 0)
            return;
        Student student = ri.get(ri.size() - 1);
        mGaoYa.add(student.getGaoya());
        mDiYa.add(student.getDiya());
        String time = student.getTime();
        String[] split1 = time.split(":");
        int a = Integer.parseInt(split1[0]);
        if (a < 10) {
            String substring = split1[0].substring(1, 2);
            a = Integer.parseInt(substring);
        }
        mTime.add(a);

        getMyView(mView);
        mView.setShuJu(mGaoYa, mDiYa, mTime);

    }

    /*查询某月的数据*/
    private void mViewThreeShow() {
        mGaoYa = new ArrayList();
        mDiYa = new ArrayList();
        mTime = new ArrayList();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        int yue = 0;
        String[] split = str.split("-");
          /*获取当前的月份*/
        int i = Integer.parseInt(split[0]);
        if (i < 10)
            yue = Integer.parseInt(split[0].substring(1, 2));
        else
            yue = Integer.parseInt(split[0]);

                /*获取当前的day*/
        int day = Integer.parseInt(split[1]);
        ArrayList<Student> ri = myManager.getYue(yue);
        if (ri.size() <= 0)
            return;
        List<Integer> list = new ArrayList();
        for (Student student : ri) {
            int day2 = student.getDay();
            Log.d("BloodPressureFragment", "day2:" + day2);
            if (day2 > (day - 6) && day2 < (day + 2)) {

                list.add(day2);
                Log.d("BloodPressureFragment", "day2:AAA" + day2);
            }

        }

        /*进行从小到大排序*/
        Collections.sort(list);
        for (int i1 = 0; i1 < list.size(); i1++) {
            ArrayList<Student> riA = myManager.getRiA(list.get(i1));
            Student student1 = riA.get(riA.size() - 1);
            mGaoYa.add(student1.getGaoya());
            mDiYa.add(student1.getDiya());
            int day1 = student1.getDay();
            mTime.add(day1);
            getMyView(mViewThree);
            mViewThree.setShuJu(mGaoYa, mDiYa, mTime);
        }


    }

    /*查询某年的某月的数据*/
    private void mViewFourShow() {
        mGaoYa = new ArrayList();
        mDiYa = new ArrayList();
        mTime = new ArrayList();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        int yue = 0;
        String[] split = str.split("-");
          /*获取当前的月份*/
        int i = Integer.parseInt(split[0]);
        if (i < 10)
            yue = Integer.parseInt(split[0].substring(1, 2));
        else
            yue = Integer.parseInt(split[0]);

                /*获取当前的day*/
        int day = Integer.parseInt(split[1]);
        ArrayList<Student> ri = myManager.getAllStudent();
        if (ri.size() <= 0)
            return;
        Log.d("BloodPressureFragment", "ri.get(0).getDay():" + ri.get(0).getDay());
        for (Student student : ri) {
            int id = student.getId();
            Log.d("BloodPressureFragment", "id:" + id);
            ArrayList<Student> riA = myManager.getYueA(id);
            Student student1 = riA.get(riA.size() - 1);
            mGaoYa.add(student1.getGaoya());
            mDiYa.add(student1.getDiya());
            int day1 = student1.getId();
            Log.d("BloodPressureFragment", "day1:" + day1);
            mTime.add(day1);
        }
        getMyView(mViewFour);
        mViewFour.setShuJu(mGaoYa, mDiYa, mTime);
    }

    /*控制每个view 的显示和消失*/
    protected void getMyView(View view) {
        mView.setVisibility(View.GONE);
        mViewThree.setVisibility(View.GONE);
        mViewFour.setVisibility(View.GONE);
        view.setVisibility(View.VISIBLE);
    }


    public void getTiaoZhuang() {
        Intent in = new Intent(getActivity(), XueYaJiLuActivity.class);
        startActivity(in);
    }

    public void getMyPopup() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.popup_item_shuju, null);
        mPopup = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mPopup.setOutsideTouchable(true);
        mPopup.setBackgroundDrawable(new ColorDrawable());
        mTextSD = (TextView) view.findViewById(R.id.mTextSD);
        /*手动录入数据*/
        mTextSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ShuJuJiLuActivity.class);
                getActivity().startActivity(in);
            }
        });
        mTextZD = (TextView) view.findViewById(R.id.mTextZD);
        /*自动测量*/
        mTextZD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ZiDongActivity.class);
                getActivity().startActivity(in);
            }
        });
        mRelativeLayout = (RelativeLayout) view.findViewById(R.id.mXiaoS);
        mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopup.dismiss();
            }
        });

    }


}
