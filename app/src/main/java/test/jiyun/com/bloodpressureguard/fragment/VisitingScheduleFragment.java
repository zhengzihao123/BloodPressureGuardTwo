package test.jiyun.com.bloodpressureguard.fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import test.jiyun.com.bloodpressureguard.App;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseFragment;
import test.jiyun.com.bloodpressureguard.model.bean.FreePlusDetailBean;
import test.jiyun.com.bloodpressureguard.model.biz.DoctorOnline;
import test.jiyun.com.bloodpressureguard.model.biz.DoctorOnlineImpl;
import test.jiyun.com.bloodpressureguard.model.callback.ResaultCallBack;

/**
 * Created by ASUS on 2017/6/13.
 */

public class VisitingScheduleFragment extends BaseFragment {
    @Bind(R.id.morning)
    LinearLayout morning;
    @Bind(R.id.afternoon)
    LinearLayout afternoon;
    @Bind(R.id.night)
    LinearLayout night;
    @Bind(R.id.hospital_location)
    TextView hospitalLocation;

    private DoctorOnline doctorOnline;
    private ProgressDialog progressDialog;

    @Override
    protected int ViewID() {
        return R.layout.visiting_schedule;
    }

    @Override
    protected void initView() {
        doctorOnline = DoctorOnlineImpl.getInstance();
        show();
    }

    @Override
    protected void loadData() {
        progressDialog.show();
        doctorOnline.visitingSchedule(getArguments().getString("id"), new ResaultCallBack() {
            @Override
            public void onSuccess(Object obj) {
                progressDialog.dismiss();
                FreePlusDetailBean detailBean = (FreePlusDetailBean) obj;
                List<FreePlusDetailBean.DataBean.ScheduleBean.RdtimeBean> rdtime = detailBean.getData().getSchedule().getRdtime();

                if (rdtime.size() > 0) {
                    for (FreePlusDetailBean.DataBean.ScheduleBean.RdtimeBean rdtimeBean : rdtime) {
                        int ss = Integer.parseInt(rdtimeBean.getWeek());
                        String halfday = rdtimeBean.getHalfday();
                        switch (halfday) {
                            case "1":
                                TextView childAt1 = (TextView) morning.getChildAt(ss);
                                if (rdtimeBean.equals("3"))
                                    childAt1.setText("特需");
                                else
                                    childAt1.setText("专家");

                                childAt1.setBackgroundColor(Color.parseColor("#16DCC7"));
                                break;
                            case "2":
                                TextView childAt2 = (TextView) afternoon.getChildAt(ss);
                                if (rdtimeBean.equals("3"))
                                    childAt2.setText("特需");
                                else
                                    childAt2.setText("专家");
                                childAt2.setBackgroundColor(Color.parseColor("#16DCC7"));
                                break;
                            case "3":
                                TextView childAt3 = (TextView) night.getChildAt(ss);
                                if (rdtimeBean.equals("3"))
                                    childAt3.setText("特需");
                                else
                                    childAt3.setText("专家");
                                childAt3.setBackgroundColor(Color.parseColor("#16DCC7"));
                                break;
                        }
                    }
                }
                String address = "医院地址：" + detailBean.getData().getAddress();
                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#B6B6B6"));
                SpannableString spannableString = new SpannableString(address);
                spannableString.setSpan(foregroundColorSpan, 0, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                hospitalLocation.setText(spannableString);
            }

            @Override
            public void onError(String errorMsg) {
                progressDialog.dismiss();
                showToast(errorMsg);
            }

            @Override
            public void notNet(String netData) {
                progressDialog.dismiss();
                showToast(netData);
            }

            @Override
            public void onErrorParams(String errorParams) {
                progressDialog.dismiss();
                showToast(errorParams);
            }
        }, FreePlusDetailBean.class);
    }

    @Override
    protected void listener() {

    }

    private void show() {
        progressDialog = new ProgressDialog(App.activity);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在加载中...");
        progressDialog.setCancelable(false);
    }
}
