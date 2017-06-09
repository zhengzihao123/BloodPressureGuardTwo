package test.jiyun.com.bloodpressureguard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import test.jiyun.com.bloodpressureguard.base.BaseFragment;


/**
 * Created by 韩志军 on 2017/6/9.
 */

/*侧拉菜单*/
public class HomeFragment extends BaseFragment {
    @Bind(R.id.mLayout)
    LinearLayout mLayout;
    private int width;


    @Override
    protected int ViewID() {
        return R.layout.fragment_homepager;
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void listener() {

    }

}
