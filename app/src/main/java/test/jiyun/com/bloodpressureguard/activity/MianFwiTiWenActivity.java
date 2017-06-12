package test.jiyun.com.bloodpressureguard.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by 韩志军 on 2017/6/12.
 */


/*免费提问*/
public class MianFwiTiWenActivity extends BaseActivity {
    @Bind(R.id.mTitle)
    TextView mTitle;
    @Bind(R.id.mEdiText)
    EditText mEdiText;
    @Bind(R.id.mBtnTj)
    Button mBtnTj;
    private PopupWindow mPopup;
    private View view;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mPopup.dismiss();
            Toast.makeText(MianFwiTiWenActivity.this, "您的网速有点慢", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected int layoutId() {
        return R.layout.activity_mian_fei;
    }

    @Override
    protected void initView() {
        mTitle.setText("免费提问");
        view = LayoutInflater.from(MianFwiTiWenActivity.this).inflate(R.layout.popup_item, null);
        mPopup = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopup.setBackgroundDrawable(new ColorDrawable());
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void listener() {

    }


    @OnClick(R.id.mBtnTj)
    public void onViewClicked() {
        if (mEdiText.getText().toString().isEmpty()) {
            Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mEdiText.getText().toString().length() < 10) {
            Toast.makeText(this, "不得少于10个字", Toast.LENGTH_SHORT).show();
            return;
        }
        mPopup.showAsDropDown(findViewById(R.id.yis_zx));
        handler.sendEmptyMessageDelayed(1, 1000);

    }
}
