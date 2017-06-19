package test.jiyun.com.bloodpressureguard.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;

/**
 * 项目名称：城市通
 * 类描述：
 * 创建人 londn
 * 创建时间： 2017-6-14
 * 修改人：
 * 修改时间：
 * 修改内容：
 */

public class MianFeiWenDaActivity extends BaseActivity {


    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.message_content_et)
    EditText messageContentEt;
    @Bind(R.id.message_rg)
    RadioGroup messageRg;
    @Bind(R.id.questions_count_text)
    TextView questionsCountText;

    @Override
    protected int layoutId() {
        return R.layout.free_questions;
    }

    @Override
    protected void initView() {
        titleText.setText("免费提问");
    }

    @Override
    protected void loadData() {
        ((RadioButton) messageRg.getChildAt(0)).setChecked(true);
    }

    @Override
    protected void listener() {
        messageContentEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                questionsCountText.setText(String.valueOf(s.length()));
            }
        });
    }


    @OnClick({R.id.back_img, R.id.title_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                onBackPressed();
                break;
            case R.id.title_text:
                break;
        }
    }

}
