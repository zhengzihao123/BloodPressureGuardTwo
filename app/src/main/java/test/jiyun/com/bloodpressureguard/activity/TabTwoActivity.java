package test.jiyun.com.bloodpressureguard.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import butterknife.Bind;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.App;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;
import test.jiyun.com.bloodpressureguard.view.FlowLayout;

/**
 * Created by ASUS on 2017/6/14.
 */

public class TabTwoActivity extends BaseActivity {

    @Bind(R.id.Tab_layout)
    FlowLayout TabLayout;
    @Bind(R.id.Tab_layout_top)
    LinearLayout TabLayoutTop;
    @Bind(R.id.mFinsh)
    ImageView mFinsh;
    @Bind(R.id.mTitle)
    TextView mTitle;
    private List<String> data;
    private View view;
    private PopupWindow popupWindow;
    private Intent intent;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected int layoutId() {
        return R.layout.activity_tab;
    }

    @Override
    protected void initView() {
        mTitle.setText("所患疾病");
        sharedPreferences = getSharedPreferences("tabList", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        data = new ArrayList<>();
        showPopup();
        intent = getIntent();
        data.add("头晕");
        data.add("嗜睡");
        data.add("犯迷糊");
        data.add("血压忽高忽低");
        data.add("吃什么吐什么");
        data.add("不知道");
        Set<String> tabData = sharedPreferences.getStringSet("tabTwoData", new TreeSet<String>());
        if (tabData.size() > 0) {
            data.addAll(tabData);
        }

        TabLayout.setOnTagItemClickListener(new FlowLayout.OnTagItemClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) v;
                if (tv.getTag().equals("tttt")) {
                    popupWindow.showAtLocation(TabLayoutTop, Gravity.CENTER, 0, 0);
                } else {
                    intent.putExtra("textTwo", tv.getText().toString());
                    setResult(2000, intent);
                    finish();
                }
            }
        });

        TabLayout.setTags(data);
    }

    private void showPopup() {
        view = View.inflate(App.activity, R.layout.tab_view, null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        final EditText et = (EditText) view.findViewById(R.id.tab_et);
        TextView cancel = (TextView) view.findViewById(R.id.tab_cancel);
        TextView determine = (TextView) view.findViewById(R.id.tab_determine);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        determine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String etStr = et.getText().toString();
                if (!TextUtils.isEmpty(etStr)) {
                    data.add(etStr);
                    TabLayout.setTags(data);
                    Set<String> tabData = sharedPreferences.getStringSet("tabTwoData", new TreeSet<String>());
                    tabData.add(etStr);
                    editor.putStringSet("tabTwoData", tabData);
                    editor.commit();
                    et.setText("");
                } else {
                    showToast("疾病名字不能为空");
                }
                popupWindow.dismiss();
            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void listener() {

    }

    @OnClick(R.id.mFinsh)
    public void onViewClicked() {
        finish();
    }
}
