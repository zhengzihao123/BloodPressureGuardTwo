package test.jiyun.com.bloodpressureguard.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;
import test.jiyun.com.bloodpressureguard.bean.ChangBeanTwo;
import test.jiyun.com.bloodpressureguard.model.callback.ResaultCallBack;
import test.jiyun.com.bloodpressureguard.model.http.RetrofitUtil;


/**
 * Created by 韩志军 on 2017/6/12.
 */

/*详情二级*/
public class XiangQingErActivity extends BaseActivity {

    @Bind(R.id.mTitle)
    TextView mTitle;
    @Bind(R.id.mBiaoTi)
    TextView mBiaoTi;
    @Bind(R.id.mTime)
    TextView mTime;
    @Bind(R.id.mBody)
    TextView mBody;
    private String url = "index.php";
    private Map<String, String> map;

    @Override
    protected int layoutId() {
        return R.layout.item_xiangq_er;
    }

    @Override
    protected void initView() {
        String id = getIntent().getStringExtra("id");
        mTitle.setText("文章");
        getQingQiu(id);
        Log.d("XiangQingErActivity", id);

    }

    private void getQingQiu(String id) {
        map = new HashMap<>();
        map.put("act", "zixun");
        map.put("fun", "getHealthPlazeDetail");
        map.put("version", "version2");
        map.put("tag", "zj");
        map.put("sign", "2e0d0887581be1c35794ee4c13b00cae");
        map.put("id", id);
        map.put("dir", "zhuanti_nk");
        RetrofitUtil.getInstance().getRetrofit(url, map, new ResaultCallBack() {
            @Override
            public void onSuccess(Object obj) {
                ChangBeanTwo changShiBean = (ChangBeanTwo) obj;
                ChangBeanTwo.DataBean data = changShiBean.getData();
                if (data == null)
                    return;
                Date d = new Date(Long.parseLong(data.getPubdate()) * 1000);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String time = sdf.format(d);
                mBiaoTi.setText(data.getTitle());
                mTime.setText(time);
                mBody.setText(data.getBody());
            }

            @Override
            public void onError(String errorMsg) {
                Log.d("XiangQingActivity", errorMsg);
            }

            @Override
            public void notNet(String netData) {
                Log.d("XiangQingActivity", netData);
            }

            @Override
            public void onErrorParams(String errorParams) {
                Log.d("XiangQingActivity", errorParams);
            }
        }, ChangBeanTwo.class);
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
