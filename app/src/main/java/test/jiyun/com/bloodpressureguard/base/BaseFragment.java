package test.jiyun.com.bloodpressureguard.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;


public abstract class BaseFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(ViewID(), container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initView(view);
        initView();
        loadData();
        listener();
    }

    /**
     * fragment隐藏显示时调用 hidden 隐藏时为false
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            onHidden();
        } else {
            updateTitleBar();
            onShow();
        }
    }

    //View视图
    protected abstract int ViewID();


    /**
     * 初始化控件
     */
    public void initView(View view) {
    };


    //初始化组件
    protected abstract void initView();

    //加载数据
    protected abstract void loadData();

    //加载监听事件
    protected abstract void listener();

    //用来更新TitleBar
    public void updateTitleBar() {
    }

    protected void onShow() {

    }

    //页面隐藏时调用
    protected void onHidden() {

    }

    //当frgamentView销毁的时候调用
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
