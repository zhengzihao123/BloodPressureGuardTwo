package test.jiyun.com.bloodpressureguard.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.adapter.MyAdapter;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;
import test.jiyun.com.bloodpressureguard.model.bean.ShouCang;
import test.jiyun.com.bloodpressureguard.model.callback.ResaultCallBack;
import test.jiyun.com.bloodpressureguard.model.http.RetrofitUtil;
import test.jiyun.com.bloodpressureguard.utils.ToastUtils;
import test.jiyun.com.bloodpressureguard.utils.UserUtils;

/**
 * 项目名称：城市通
 * 类描述：
 * 创建人 londn
 * 创建时间： 2017-6-12
 * 修改人：
 * 修改时间：
 * 修改内容：
 */

public class My_Collection extends BaseActivity {
    @Bind(R.id.back_img)
    ImageView backImg;
    @Bind(R.id.title_text)
    TextView titleText;
    private String id;
    ProgressDialog waitingDialog;
    private List<ShouCang.DataBean> mList = new ArrayList<>();
    private MyAdapter myAdapter;
    private ListView listView;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 200) {
                waitingDialog.dismiss();
                ToastUtils.showShortToast("连接成功");
            }
        }
    };

    @Override
    protected int layoutId() {
        return R.layout.my_collection;
    }

    @Override
    protected void initView() {
        id = UserUtils.getUSERID();
        titleText.setText("我的收藏");

        listView = (ListView) findViewById(R.id.collection_listview);
        myAdapter = new MyAdapter(this, mList);
        listView.setAdapter(myAdapter);

    }

    @Override
    protected void loadData() {

        handler.sendEmptyMessageDelayed(200, 2000);
        showWaitingDialog();

        Map<String, String> map = new HashMap();
        map.put("xywy_userid", id);
        map.put("tag", "BloodAndroid");
        map.put("sign", "2c19b2821ebc5306c3ac37bac5b4288b");
        map.put("app_id", "2");
        RetrofitUtil.getInstance().postRetrofit("http://api.yun.xywy.com/index.php/app/collect/list_data/111", map, new ResaultCallBack() {
            @Override
            public void onSuccess(Object obj) {
                ShouCang shouCangbean = (ShouCang) obj;
                List<ShouCang.DataBean> dataBeen = shouCangbean.getData();
                mList.addAll(dataBeen);
                myAdapter.notifyDataSetChanged();


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
        }, ShouCang.class);


    }
//http://api.wws.xywy.com/index.php?act=zixun&fun=getHealthPlazeDetail&version=version2&tag=zj&sign=2e0d0887581be1c35794ee4c13b00cae&id=770882&dir=zhuzhan_ys
    @Override
    protected void listener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShouCang.DataBean bean = mList.get(position);
                String categoryid = bean.getCategoryid();
                Intent intent = new Intent(My_Collection.this, Collection_XiangQing.class);
                intent.putExtra("111", categoryid);
                Log.e("AAAAAAAAAAAAAAAAA",categoryid+"2443243");
                startActivity(intent);

            }
        });

    }

    private void showWaitingDialog() {
    /* 等待Dialog具有屏蔽其他控件的交互能力
     * @setCancelable 为使屏幕不可点击，设置为不可取消(false)
     * 下载等事件完成后，主动调用函数关闭该Dialog
     */
        waitingDialog =
                new ProgressDialog(My_Collection.this);
        //waitingDialog.setTitle("我是一个等待Dialog");
        waitingDialog.setMessage("等待中...");
        waitingDialog.setIndeterminate(true);
        waitingDialog.setCancelable(false);
        waitingDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
