package test.jiyun.com.bloodpressureguard.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import test.jiyun.com.bloodpressureguard.App;

/**
 * Created by ASUS on 2017/6/9.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.activity = this;
        setContentView(layoutId());
        fragmentManager = getSupportFragmentManager();
        initView();
        initData();
        initListener();
        loadData();
    }

    /**
     * 返回View对象
     */

    protected abstract int layoutId();

    /**
     * 初始化资源Id
     */
    protected abstract void initView();

    /**
     * 初始化对象
     */
    protected abstract void initData();

    /**
     * 初始化监听
     */
    protected abstract void initListener();


    /**
     * 加载数据
     */
    protected abstract void loadData();

    @Override
    protected void onResume() {
        super.onResume();
        App.activity = this;

    }

}
