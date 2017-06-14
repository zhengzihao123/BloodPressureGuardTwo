package test.jiyun.com.bloodpressureguard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;
import test.jiyun.com.bloodpressureguard.model.bean.XiangQing;
import test.jiyun.com.bloodpressureguard.model.callback.ResaultCallBack;
import test.jiyun.com.bloodpressureguard.model.http.RetrofitUtil;

/**
 * 项目名称：城市通
 * 类描述：
 * 创建人 londn
 * 创建时间： 2017-6-13
 * 修改人：
 * 修改时间：
 * 修改内容：
 */

public class Collection_XiangQing extends BaseActivity {
    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.title_shoucang)
    TextView titleShoucang;
    @Bind(R.id.text_body)
    TextView textBody;
    private String id;
    private String url;

    @Override
    protected int layoutId() {
        return R.layout.xiangqing;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        id = intent.getStringExtra("111");
        titleText.setText("收藏详情");

        Log.i("BBBBBBBBBBBBBBB", id + "");

    }

    @Override
    protected void loadData() {
        Map<String, String> map = new HashMap();
        map.put("act", "zixun");
        map.put("fun", "getHealthPlazeDetail");

        map.put("version", "version2");

        map.put("tag", "zj");

        map.put("sign", "2e0d0887581be1c35794ee4c13b00cae");

        map.put("id", id);

        map.put("dir", "zhuzhan_ys");
        RetrofitUtil.getInstance().getRetrofit("http://api.wws.xywy.com/index.php", map, new ResaultCallBack() {
            @Override
            public void onSuccess(Object obj) {
                XiangQing xiangQingBean = (XiangQing) obj;
                XiangQing.DataBean data = xiangQingBean.getData();
                //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
//                webView.getSettings().setJavaScriptEnabled(true);
//                webView.loadUrl(url);
                if (data == null)
                    return;
                titleShoucang.setText(data.getTitle());
                textBody.setText(data.getBody());


            }

            @Override
            public void onError(String errorMsg) {

            }

            @Override
            public void notNet(String netData) {

            }

            @Override
            public void onErrorParams(String errorParams) {

            }
        }, XiangQing.class);


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

    @OnClick({R.id.back_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                onBackPressed();
                break;

        }
    }
}
