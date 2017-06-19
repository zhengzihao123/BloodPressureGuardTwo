package test.jiyun.com.bloodpressureguard.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.App;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;
import test.jiyun.com.bloodpressureguard.utils.WheelView;

/**
 * Created by ASUS on 2017/6/14.
 */

public class DiseaseActivity extends BaseActivity {
    @Bind(R.id.disease_one)
    RelativeLayout diseaseOne;
    @Bind(R.id.disease_two)
    RelativeLayout diseaseTwo;
    @Bind(R.id.disease_three)
    RelativeLayout diseaseThree;
    @Bind(R.id.disease_finish)
    Button diseaseFinish;
    @Bind(R.id.disease_push)
    Button diseasePush;
    @Bind(R.id.disease_tv_one)
    TextView diseaseTvOne;
    @Bind(R.id.disease_tv_two)
    TextView diseaseTvTwo;
    @Bind(R.id.disease_tv_three)
    TextView diseaseTvThree;
    @Bind(R.id.disease_top)
    LinearLayout diseaseTop;
    @Bind(R.id.mFinsh)
    ImageView mFinsh;
    @Bind(R.id.mTitle)
    TextView mTitle;
    private PopupWindow popupWindow;
    private int index;
    private WheelView wheelView;

    @Override
    protected int layoutId() {
        return R.layout.disease_detail;
    }

    @Override
    protected void initView() {
        mTitle.setText("所患疾病");
        showPopup();
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void listener() {

    }

    @OnClick({R.id.disease_one, R.id.disease_two, R.id.disease_three, R.id.disease_finish, R.id.disease_push, R.id.mFinsh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.disease_one:
                Intent intent = new Intent(App.activity, TabActivity.class);
                startActivityForResult(intent, 100);
                break;
            case R.id.disease_two:
                Intent intentTwo = new Intent(App.activity, TabTwoActivity.class);
                startActivityForResult(intentTwo, 200);
                break;
            case R.id.disease_three:
                wheelView.setSeletion(index);
                popupWindow.showAtLocation(diseaseTop, Gravity.CENTER, 0, 0);
                break;
            case R.id.disease_finish:
                finish();
                break;
            case R.id.disease_push:
                showToast("暂时不能上传");
                break;
            case R.id.mFinsh:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 1000) {
            diseaseTvOne.setText(data.getStringExtra("text"));
        } else if (requestCode == 200 && resultCode == 2000) {
            diseaseTvTwo.setText(data.getStringExtra("textTwo"));
        }
    }

    private void showPopup() {
        View view = View.inflate(App.activity, R.layout.wheel_view_two, null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0xb0000000));
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        wheelView = (WheelView) view.findViewById(R.id.disease_wheel_view);
        Button cancelBtn = (Button) view.findViewById(R.id.disease_cancel);
        Button determineBtn = (Button) view.findViewById(R.id.disease_determine);
        List<String> data = new ArrayList<>();
        data.add("诊断");
        data.add("治疗");
        data.add("复诊");
        wheelView.setItems(data);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        determineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diseaseTvThree.setText(wheelView.getSeletedItem());
                index = wheelView.getSeletedIndex();
                popupWindow.dismiss();
            }
        });
    }
}
