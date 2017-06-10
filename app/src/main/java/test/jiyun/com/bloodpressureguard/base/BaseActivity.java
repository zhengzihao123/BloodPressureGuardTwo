package test.jiyun.com.bloodpressureguard.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import butterknife.ButterKnife;
import test.jiyun.com.bloodpressureguard.App;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        ButterKnife.bind(this);
        App.activity = this;
        initView();
        loadData();
        listener();
    }

    public void setData() {


    }

    //指定布局
    protected abstract int layoutId();

    //初始化数据
    protected abstract void initView();


    //加载数据
    protected abstract void loadData();

    //加载监听
    protected abstract void listener();

    @Override
    protected void onResume() {
        super.onResume();
        App.activity = this;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
