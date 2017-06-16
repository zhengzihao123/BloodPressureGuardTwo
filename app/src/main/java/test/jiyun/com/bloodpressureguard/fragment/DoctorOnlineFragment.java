package test.jiyun.com.bloodpressureguard.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.App;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.activity.KeywordActivity;
import test.jiyun.com.bloodpressureguard.activity.ProvinceActivity;
import test.jiyun.com.bloodpressureguard.activity.QueryExpertActivity;
import test.jiyun.com.bloodpressureguard.activity.SearchItemDetail;
import test.jiyun.com.bloodpressureguard.base.BaseFragment;
import test.jiyun.com.bloodpressureguard.model.bean.HotDoctorBean;
import test.jiyun.com.bloodpressureguard.model.biz.DoctorOnline;
import test.jiyun.com.bloodpressureguard.model.biz.DoctorOnlineImpl;
import test.jiyun.com.bloodpressureguard.model.callback.ResaultCallBack;

/**
 * Created by ASUS on 2017/6/12.
 */

public class DoctorOnlineFragment extends BaseFragment {

    @Bind(R.id.online_location)
    Button onlineLocation;
    @Bind(R.id.online_province)
    RelativeLayout onlineProvince;
    @Bind(R.id.online_doctor_title)
    RelativeLayout onlineDoctorTitle;
    @Bind(R.id.online_hospital_rank)
    RelativeLayout onlineHospitalRank;
    @Bind(R.id.online_keyword)
    RelativeLayout onlineKeyword;
    @Bind(R.id.online_check)
    CheckBox onlineCheck;
    @Bind(R.id.online_query)
    Button onlineQuery;
    @Bind(R.id.online_talk_doctor)
    Button onlineTalkDoctor;
    @Bind(R.id.online_doctor_phone)
    Button onlineDoctorPhone;
    @Bind(R.id.online_replace)
    TextView onlineReplace;
    @Bind(R.id.online_hot_doctor_group)
    LinearLayout onlineHotDoctorGroup;
    ProgressDialog progressDialog;
    @Bind(R.id.online_keyword_text)
    TextView onlineKeywordText;
    @Bind(R.id.online_province_tv)
    TextView onlineProvinceTv;
    @Bind(R.id.activity_main)
    ScrollView activityMain;
    @Bind(R.id.online_title_tv)
    TextView onlineTitleTv;
    @Bind(R.id.online_rank_tv)
    TextView onlineRankTv;

    private int pageNum = 1;
    private DoctorOnline doctorOnline;
    private String titleRecord;
    private RadioGroup titleRgTop;
    private RadioGroup titleRgBottom;
    private RadioButton rgTopOne;
    private RadioButton rgTopTwo;
    private RadioButton rgTopThree;
    private RadioButton rgBottomOne;
    private RadioButton rgBottomTwo;
    private Button button;
    private PopupWindow popupWindow;
    private View view;
    private PopupWindow popupWindowTwo;
    private View viewTwo;
    private RadioGroup rankRgBottom;
    private RadioGroup rankRgTop;
    private RadioButton rankTopFour;
    private RadioButton rankTopThree;
    private RadioButton rankTopTwo;
    private RadioButton rankTopOne;
    private RadioButton rankBottomFour;
    private RadioButton rankBottomThree;
    private RadioButton rankBottomTwo;
    private RadioButton rankBottomOne;
    private String rankRecord;
    private Button buttonTwo;

    @Override
    protected int ViewID() {
        return R.layout.doctor_online_fragment;
    }

    @Override
    protected void initView() {
        doctorOnline = DoctorOnlineImpl.getInstance();
        show();
        showPopupOne();
        showPopupTwo();
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void listener() {
        loadList();
    }

    private void loadList() {
        progressDialog.show();
        doctorOnline.hotDoctor(pageNum, new ResaultCallBack() {
            @Override
            public void onSuccess(Object obj) {
                onlineHotDoctorGroup.setVisibility(View.VISIBLE);
                progressDialog.dismiss();
                HotDoctorBean hotDoctorBean = (HotDoctorBean) obj;
                final List<HotDoctorBean.DataBean> data = hotDoctorBean.getData();
                if (data.size() > 0) {
                    for (int i = 0; i < data.size(); i++) {
                        final int num = i;
                        RelativeLayout view = (RelativeLayout) onlineHotDoctorGroup.getChildAt(i);
                        TextView text = (TextView) view.getChildAt(0);
                        ImageView image = (ImageView) view.getChildAt(1);
                        text.setText(data.get(i).getName());
                        Glide.with(App.activity)
                                .load(data.get(i).getApp_image())
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(image);
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(App.activity, SearchItemDetail.class);
                                intent.putExtra("imgUrl", data.get(num).getApp_image());
                                intent.putExtra("name", data.get(num).getName());
                                intent.putExtra("expert", data.get(num).getTeach());
                                intent.putExtra("location", data.get(num).getHospital());
                                intent.putExtra("type", data.get(num).getDepart());
                                intent.putExtra("title", data.get(num).getTitle());
                                intent.putExtra("expert_id", data.get(num).getExpert_id());
                                intent.putExtra("doctor_id", data.get(num).getDocument_id());
                                intent.putExtra("content", data.get(num).getGoodat());
                                startActivity(intent);
                            }
                        });
                    }
                }
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
                showToast(errorParams);
            }
        }, HotDoctorBean.class);
    }

    @OnClick({R.id.online_location, R.id.online_province, R.id.online_doctor_title, R.id.online_hospital_rank, R.id.online_keyword, R.id.online_query, R.id.online_talk_doctor, R.id.online_doctor_phone, R.id.online_replace})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.online_location:
                break;
            case R.id.online_province:
                Intent intent = new Intent(App.activity, ProvinceActivity.class);
                startActivityForResult(intent, 100);
                break;
            case R.id.online_doctor_title:
                popupWindow.showAtLocation(App.activity.findViewById(R.id.mLayoutA), Gravity.BOTTOM, 0, 0);
                break;
            case R.id.online_hospital_rank:
                popupWindowTwo.showAtLocation(App.activity.findViewById(R.id.mLayoutA), Gravity.BOTTOM, 0, 0);
                break;
            case R.id.online_keyword:
                Intent intent3 = new Intent(App.activity, KeywordActivity.class);
                startActivityForResult(intent3, 200);
                break;
            case R.id.online_query:
                Intent intent4 = new Intent(App.activity, QueryExpertActivity.class);
                String s1 = onlineProvinceTv.getText().toString();
                if (s1.equals("省份") || s1.equals("不限"))
                    s1 = "";
                intent4.putExtra("onlineProvinceTv", s1);
                String s2 = onlineTitleTv.getText().toString();
                if (s2.equals("不限"))
                    s2 = "";
                intent4.putExtra("onlineTitleTv", s2);
//                String s3 = onlineRankTv.getText().toString();
//                intent4.putExtra("onlineRankTv", s3);
                String s4 = onlineKeywordText.getText().toString();
                if (s4.equals("不限"))
                    s4 = "";
                intent4.putExtra("onlineKeywordText", s4);
                int plus;
                if (onlineCheck.isChecked()) {
                    plus = 1;
                } else {
                    plus = 0;
                }
                intent4.putExtra("onlineCheck", plus);
                startActivity(intent4);
                break;
            case R.id.online_talk_doctor:
                break;
            case R.id.online_doctor_phone:
                break;
            case R.id.online_replace:
                pageNum++;
                loadList();
//                doctorOnline.hotDoctor(pageNum, new ResaultCallBack() {
//                    @Override
//                    public void onSuccess(Object obj) {
//                        progressDialog.dismiss();
//                        HotDoctorBean hotDoctorBean = (HotDoctorBean) obj;
//                        final List<HotDoctorBean.DataBean> data = hotDoctorBean.getData();
//                        if (data.size() > 0) {
//                            for (int i = 0; i < data.size(); i++) {
//                                final int num = i;
//                                RelativeLayout view = (RelativeLayout) onlineHotDoctorGroup.getChildAt(i);
//                                TextView text = (TextView) view.getChildAt(0);
//                                ImageView image = (ImageView) view.getChildAt(1);
//                                text.setText(data.get(i).getName());
//                                Glide.with(App.activity)
//                                        .load(data.get(i).getApp_image())
//                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                        .into(image);
//                                view.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        Intent intent = new Intent(App.activity, SearchItemDetail.class);
//                                        intent.putExtra("imgUrl", data.get(num).getApp_image());
//                                        intent.putExtra("name", data.get(num).getName());
//                                        intent.putExtra("expert", data.get(num).getTeach());
//                                        intent.putExtra("location", data.get(num).getHospital());
//                                        intent.putExtra("type", data.get(num).getDepart());
//                                        intent.putExtra("title", data.get(num).getTitle());
//                                        intent.putExtra("expert_id", data.get(num).getExpert_id());
//                                        intent.putExtra("doctor_id", data.get(num).getDocument_id());
//                                        intent.putExtra("content", data.get(num).getGoodat());
//                                        startActivity(intent);
//                                    }
//                                });
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onError(String errorMsg) {
//                        progressDialog.dismiss();
//                        showToast(errorMsg);
//                    }
//
//                    @Override
//                    public void notNet(String netData) {
//                        progressDialog.dismiss();
//                        showToast(netData);
//                    }
//
//                    @Override
//                    public void onErrorParams(String errorParams) {
//                        progressDialog.dismiss();
//                        showToast(errorParams);
//                    }
//                }, HotDoctorBean.class);
                break;
        }
    }

    private void showPopupTwo() {
        viewTwo = View.inflate(App.activity, R.layout.hospitalrank, null);
        popupWindowTwo = new PopupWindow(viewTwo, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        popupWindowTwo.setFocusable(true);
        popupWindowTwo.setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        popupWindowTwo.setBackgroundDrawable(dw);
        popupWindowTwo.setAnimationStyle(R.style.mypopwindow_anim_style);
        rankRgTop = (RadioGroup) viewTwo.findViewById(R.id.rank_rg_top);
        rankRgBottom = (RadioGroup) viewTwo.findViewById(R.id.rank_rg_bottom);
        rankTopOne = (RadioButton) viewTwo.findViewById(R.id.rg_top_one);
        rankTopTwo = (RadioButton) viewTwo.findViewById(R.id.rg_top_two);
        rankTopThree = (RadioButton) viewTwo.findViewById(R.id.rg_top_three);
        rankTopFour = (RadioButton) viewTwo.findViewById(R.id.rg_top_four);
        rankBottomOne = (RadioButton) viewTwo.findViewById(R.id.rg_bottom_one);
        rankBottomTwo = (RadioButton) viewTwo.findViewById(R.id.rg_bottom_two);
        rankBottomThree = (RadioButton) viewTwo.findViewById(R.id.rg_bottom_three);
        rankBottomFour = (RadioButton) viewTwo.findViewById(R.id.rg_bottom_four);
        buttonTwo = (Button) viewTwo.findViewById(R.id.rank_determine);
        rankRgTop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rg_top_one:
                        if (rankTopOne.isChecked()) {
                            rankRgBottom.clearCheck();
                            rankRecord = rankTopOne.getText().toString();
                        }
                        break;
                    case R.id.rg_top_two:
                        if (rankTopTwo.isChecked()) {
                            rankRgBottom.clearCheck();
                            rankRecord = rankTopTwo.getText().toString();
                        }
                        break;
                    case R.id.rg_top_three:
                        if (rankTopThree.isChecked()) {
                            rankRgBottom.clearCheck();
                            rankRecord = rankTopThree.getText().toString();
                        }
                        break;
                    case R.id.rg_top_four:
                        if (rankTopFour.isChecked()) {
                            rankRgBottom.clearCheck();
                            rankRecord = rankTopFour.getText().toString();
                        }
                        break;
                }
            }
        });
        rankRgBottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rg_bottom_one:
                        if (rankBottomOne.isChecked()) {
                            rankRgTop.clearCheck();
                            rankRecord = rankBottomOne.getText().toString();
                        }
                        break;
                    case R.id.rg_bottom_two:
                        if (rankBottomTwo.isChecked()) {
                            rankRgTop.clearCheck();
                            rankRecord = rankBottomTwo.getText().toString();
                        }
                        break;
                    case R.id.rg_bottom_three:
                        if (rankBottomThree.isChecked()) {
                            rankRgTop.clearCheck();
                            rankRecord = rankBottomThree.getText().toString();
                        }
                        break;
                    case R.id.rg_bottom_four:
                        if (rankBottomFour.isChecked()) {
                            rankRgTop.clearCheck();
                            rankRecord = rankBottomFour.getText().toString();
                        }
                        break;
                }
            }
        });
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onlineRankTv.setText(rankRecord);
                popupWindowTwo.dismiss();
            }
        });
    }

    private void showPopupOne() {
        view = View.inflate(App.activity, R.layout.doctortitle, null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        titleRgTop = (RadioGroup) view.findViewById(R.id.title_rg_top);
        titleRgBottom = (RadioGroup) view.findViewById(R.id.title_rg_bottom);
        rgTopOne = (RadioButton) view.findViewById(R.id.rg_top_one);
        rgTopTwo = (RadioButton) view.findViewById(R.id.rg_top_two);
        rgTopThree = (RadioButton) view.findViewById(R.id.rg_top_three);
        rgBottomOne = (RadioButton) view.findViewById(R.id.rg_bottom_one);
        rgBottomTwo = (RadioButton) view.findViewById(R.id.rg_bottom_two);
        button = (Button) view.findViewById(R.id.title_determine);
        titleRgTop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rg_top_one:
                        if (rgTopOne.isChecked()) {
                            titleRgBottom.clearCheck();
                            titleRecord = rgTopOne.getText().toString();
                        }
                        break;
                    case R.id.rg_top_two:
                        if (rgTopTwo.isChecked()) {
                            titleRgBottom.clearCheck();
                            titleRecord = rgTopTwo.getText().toString();
                        }
                        break;
                    case R.id.rg_top_three:
                        if (rgTopThree.isChecked()) {
                            titleRecord = rgTopThree.getText().toString();
                            titleRgBottom.clearCheck();
                        }
                        break;
                }
            }
        });
        titleRgBottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rg_bottom_one:
                        if (rgBottomOne.isChecked()) {
                            titleRecord = rgBottomOne.getText().toString();
                            titleRgTop.clearCheck();
                        }
                        break;
                    case R.id.rg_bottom_two:
                        if (rgBottomTwo.isChecked()) {
                            titleRecord = rgBottomTwo.getText().toString();
                            titleRgTop.clearCheck();
                        }
                        break;
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onlineTitleTv.setText(titleRecord);
                popupWindow.dismiss();
            }
        });
    }

    private void show() {
        progressDialog = new ProgressDialog(App.activity);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在加载中...");
        progressDialog.setCancelable(false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == 2000) {
            String keyword = data.getStringExtra("keyword");
            if (TextUtils.isEmpty(keyword)) {
                keyword = "";
            }
            onlineKeywordText.setText(keyword);
        } else if (requestCode == 100 && resultCode == 1000) {
            String province = data.getStringExtra("province");
            onlineProvinceTv.setText(province);
        }
    }

}
