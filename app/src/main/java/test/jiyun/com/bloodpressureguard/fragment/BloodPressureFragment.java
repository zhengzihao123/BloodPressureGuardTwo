package test.jiyun.com.bloodpressureguard.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.activity.ShuJuJiLuActivity;
import test.jiyun.com.bloodpressureguard.activity.XueYaJiLuActivity;
import test.jiyun.com.bloodpressureguard.activity.XueYaZXActivity;
import test.jiyun.com.bloodpressureguard.base.BaseFragment;
import test.jiyun.com.bloodpressureguard.view.MyStatisticsView;
import test.jiyun.com.bloodpressureguard.view.MyStatisticsViewThree;
import test.jiyun.com.bloodpressureguard.view.MyStatisticsViewTwo;
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
    @Bind(R.id.mBtnB)
    RadioButton mBtnB;
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
    @Bind(R.id.mViewTwo)
    MyStatisticsViewTwo mViewTwo;
    @Bind(R.id.mViewThree)
    MyStatisticsViewThree mViewThree;
    @Bind(R.id.mViewFour)
    MyStatisticsViewTwoFour mViewFour;

    private List mGaoYa;/*高压集合*/
    private List mDiYa;/*低压集合*/
    private List mTime;

    @Override
    protected int ViewID() {
        return R.layout.fragment_blood_pressure;
    }

    @Override
    protected void initView() {
        mGaoYa = new ArrayList();
        mDiYa = new ArrayList();
        mTime = new ArrayList();
        mViewOneShow();
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void listener() {
        /*默认第一个按钮是点击的*/
        mBtnA.setChecked(true);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "点击成功", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @OnClick({R.id.mView, R.id.mViewTwo, R.id.mViewThree, R.id.mViewFour,R.id.imageView, R.id.mBtnA, R.id.mBtnB, R.id.mBtnC, R.id.mBtnD, R.id.mTextWen, R.id.mTextZX, R.id.mTextTX})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            /*上传血压数据*/
            case R.id.imageView:
                Intent in = new Intent(getActivity(), ShuJuJiLuActivity.class);
                getActivity().startActivity(in);
                break;
            case R.id.mBtnA:
                mViewOneShow();
                break;
            case R.id.mBtnB:
                mGaoYa = new ArrayList();
                mDiYa = new ArrayList();
                mTime = new ArrayList();
                mGaoYa.add(160);
                mDiYa.add(100);
                mTime.add(10);
                mViewTwo.setShuJu(mGaoYa, mDiYa, mTime);
                getMyView(mViewTwo);
                break;
            case R.id.mBtnC:
                mGaoYa = new ArrayList();
                mDiYa = new ArrayList();
                mTime = new ArrayList();
                mGaoYa.add(160);
                mDiYa.add(100);
                mTime.add(20);
                getMyView(mViewThree);
                mViewThree.setShuJu(mGaoYa, mDiYa, mTime);
                break;
            case R.id.mBtnD:
                mGaoYa = new ArrayList();
                mDiYa = new ArrayList();
                mTime = new ArrayList();
                mGaoYa.add(160);
                mDiYa.add(100);
                mTime.add(10);
                mViewFour.setShuJu(mGaoYa, mDiYa, mTime);
                getMyView(mViewFour);
                break;
            /*问yishen*/
            case R.id.mTextWen:
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
            case R.id.mViewTwo:
                break;
            case R.id.mViewThree:
                break;
            case R.id.mViewFour:
                break;
        }
    }

    private void mViewOneShow() {
        mGaoYa.add(160);
        mGaoYa.add(140);
        mGaoYa.add(170);
        mDiYa.add(70);
        mDiYa.add(80);
        mDiYa.add(90);
        mTime.add(4);
        mTime.add(14);
        mTime.add(22);
        getMyView(mView);
        mView.setShuJu(mGaoYa, mDiYa, mTime);
    }

    /*控制每个view 的显示和消失*/
    protected void getMyView(View view) {
        mView.setVisibility(View.GONE);
        mViewTwo.setVisibility(View.GONE);
        mViewThree.setVisibility(View.GONE);
        mViewFour.setVisibility(View.GONE);
        view.setVisibility(View.VISIBLE);
    }


    public void getTiaoZhuang() {
        Intent in=new Intent(getActivity(), XueYaJiLuActivity.class);
        Bundle bun=new Bundle();
        startActivity(in);
    }
}
