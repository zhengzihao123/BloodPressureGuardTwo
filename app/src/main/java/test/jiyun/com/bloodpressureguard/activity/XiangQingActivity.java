package test.jiyun.com.bloodpressureguard.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.androidkun.callback.PullToRefreshListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;
import test.jiyun.com.bloodpressureguard.bean.ChangShiBean;
import test.jiyun.com.bloodpressureguard.model.callback.ResaultCallBack;
import test.jiyun.com.bloodpressureguard.model.http.RetrofitUtil;

/**
 * Created by 韩志军 on 2017/6/12.
 */

/*日常知识 详情*/
public class XiangQingActivity extends BaseActivity {
    @Bind(R.id.mTitle)
    TextView mTitle;
    @Bind(R.id.mListView)
    PullToRefreshRecyclerView mListView;
    @Bind(R.id.mShow)
    LinearLayout mShow;
    @Bind(R.id.mFinsh)
    ImageView mFinsh;

    private String url = "index.php";
    private Map<String, String> map;
    private List<ChangShiBean.DataBean> mList;
    private MyAdapter myAdapter;
    private String type;
    private String dir;

    @Override
    protected int layoutId() {
        return R.layout.activity_xiangqing;
    }

    @Override
    protected void initView() {
        String title = getIntent().getStringExtra("title");
        mTitle.setText(title);
        int posstion = Integer.parseInt(getIntent().getStringExtra("flg"));
        mList = new ArrayList<>();
        mListView.setLayoutManager(new LinearLayoutManager(XiangQingActivity.this));
        myAdapter = new MyAdapter(XiangQingActivity.this, R.layout.list_item, mList);
        mListView.setAdapter(myAdapter);
        mListView.setPullRefreshEnabled(false);
        mListView.setLoadingMoreEnabled(true);
        Log.d("XiangQingActivity", "posstion:" + posstion);
        switch (posstion) {
            case 1:
                type = "18031";
                dir = "zhuanti_nk";
                break;
            case 2:
                type = "7938";
                dir = "zhuzhan_ys";
                break;
            case 3:
                type = "18033";
                dir = "zhuanti_nk";
                break;
            case 4:
                type = "18035";
                dir = "zhuanti_nk";
                break;
            case 5:
                type = "18032";
                dir = "zhuanti_nk";
                break;
        }
        getQingQiu();

    }

    @Override
    protected void loadData() {
    }

    @Override
    protected void listener() {
        mListView.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                mListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mListView.setLoadMoreComplete();
                        myAdapter.notifyDataSetChanged();

                    }
                }, 1200);
            }
        });
    }

    private void getQingQiu() {
        map = new HashMap<>();
        map.put("act", "zixun");
        map.put("fun", "getHealthPlazeList");
        map.put("version", "version2");
        map.put("tag", "zj");
        map.put("sign", "2e0d0887581be1c35794ee4c13b00cae");
        map.put("typeid", type);
        map.put("dir", dir);
        RetrofitUtil.getInstance().getRetrofit(url, map, new ResaultCallBack() {
            @Override
            public void onSuccess(Object obj) {
                ChangShiBean changShiBean = (ChangShiBean) obj;
                List<ChangShiBean.DataBean> data = changShiBean.getData();
                if (data.size() == 0)
                    return;
                mList.addAll(data);
                Log.d("XiangQingActivity", "mList.size():" + mList.size());
                myAdapter.notifyDataSetChanged();
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
        }, ChangShiBean.class);
    }


    @OnClick(R.id.mFinsh)
    public void onViewClicked() {
        finish();
    }


    class MyAdapter extends BaseAdapter<ChangShiBean.DataBean> {

        public MyAdapter(Context context, int layoutId, List<ChangShiBean.DataBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, final ChangShiBean.DataBean dataBean) {
            Date d = new Date(Long.parseLong(dataBean.getPubdate()) * 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String time = sdf.format(d);
            holder.setText(R.id.mBiaoTi, dataBean.getTitle());
            holder.setText(R.id.mBody, dataBean.getDescription());
            holder.setText(R.id.mTime, time);
            holder.setOnclickListener(R.id.mTiaoZ, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(XiangQingActivity.this, XiangQingErActivity.class);
                    in.putExtra("id", dataBean.getId());
                    startActivity(in);
                }
            });
        }
    }


}
