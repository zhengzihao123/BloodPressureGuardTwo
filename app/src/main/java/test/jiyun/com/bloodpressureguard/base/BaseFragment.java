package test.jiyun.com.bloodpressureguard.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by think on 2017/5/14.
 */

public abstract class BaseFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(layoutId(), container, false);
        return view;
    }

   public View getFragmentLayoutView(){
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
        initListener();
        loadData();
    }

    /**
     * 加载一个视图
     *
     * @return
     */
    protected abstract int layoutId();


    /**
     * 初始化控件
     */
    protected abstract void initView(View view);

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

}
