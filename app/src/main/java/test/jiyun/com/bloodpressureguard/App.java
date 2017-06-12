package test.jiyun.com.bloodpressureguard;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import test.jiyun.com.bloodpressureguard.base.BaseActivity;
import test.jiyun.com.bloodpressureguard.base.BaseFragment;


/**
 * Created by 韩志军 on 2017/6/9.
 */

public class App extends Application{
    public static BaseActivity activity;
    public static BaseFragment lastFragment;
    public static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getSharedPreferences("DATA", Context.MODE_PRIVATE);
    }
}
