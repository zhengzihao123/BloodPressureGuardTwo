package test.jiyun.com.bloodpressureguard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.activity.LoginActivity;
import test.jiyun.com.bloodpressureguard.activity.My_Collection;
import test.jiyun.com.bloodpressureguard.activity.My_Data;
import test.jiyun.com.bloodpressureguard.activity.My_Message;
import test.jiyun.com.bloodpressureguard.activity.My_Set;
import test.jiyun.com.bloodpressureguard.base.BaseFragment;
import test.jiyun.com.bloodpressureguard.model.bean.MySet;
import test.jiyun.com.bloodpressureguard.model.bean.UserBus;
import test.jiyun.com.bloodpressureguard.utils.KeyUtils;
import test.jiyun.com.bloodpressureguard.utils.RoundImageView;
import test.jiyun.com.bloodpressureguard.utils.ToastUtils;
import test.jiyun.com.bloodpressureguard.utils.UserUtils;


/**
 * Created by 韩志军 on 2017/6/9.
 */

/*侧拉菜单*/
public class HomeFragment extends BaseFragment {
    @Bind(R.id.mLayout)
    LinearLayout mLayout;
    @Bind(R.id.TouXiang_Image)
    RoundImageView TouXiangImage;
    @Bind(R.id.UserName_TextView)
    TextView UserNameTextView;
    @Bind(R.id.My_Add)
    LinearLayout MyAdd;
    @Bind(R.id.My_Collection)
    LinearLayout MyCollection;
    @Bind(R.id.My_Data)
    LinearLayout MyData;
    @Bind(R.id.My_Message)
    LinearLayout MyMessage;
    @Bind(R.id.My_Set)
    LinearLayout MySet;
    private int width;
    private boolean isLogin ;


    @Override
    protected int ViewID() {
        return R.layout.fragment_homepager;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);


    }

    @Override
    protected void loadData() {
        Glide.with(HomeFragment.this).load(UserUtils.getUSERImage()).placeholder(R.drawable.login_icon_account).into(TouXiangImage);
        UserNameTextView.setText(UserUtils.getUSERNAME());


    }

    @Override
    public void onResume() {
        super.onResume();
        isLogin = UserUtils.getZhaungTai();

    }

    @Override
    protected void listener() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.TouXiang_Image, R.id.UserName_TextView, R.id.My_Add, R.id.My_Collection, R.id.My_Data, R.id.My_Message, R.id.My_Set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.TouXiang_Image:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.UserName_TextView:

                break;
            case R.id.My_Add:
                if (isLogin){
                    ToastUtils.showShortToast("add");
                }else {
                    Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent1);
                }
                break;
            case R.id.My_Collection:
                if (isLogin){
                    Intent intent1 = new Intent(getActivity(), My_Collection.class);
                    startActivity(intent1);
                }else {
                    Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent1);
                }
                break;
            case R.id.My_Data:
                if (isLogin){
                    Intent intent1 = new Intent(getActivity(), My_Data.class);
                    startActivity(intent1);
                }else {
                    Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent1);
                }
                break;
            case R.id.My_Message:
                if (isLogin){
                    Intent intent1 = new Intent(getActivity(), My_Message.class);
                    startActivity(intent1);
                }else {
                    Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent1);
                }
                break;
            case R.id.My_Set:
                if (isLogin){
                    Intent intent1 = new Intent(getActivity(), My_Set.class);
                    startActivity(intent1);
                }else {
                    Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent1);
                }
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onUserent(UserBus userBus) {
        ToastUtils.showShortToast("hahhaa");
        MySet mySetBean = userBus.getMySetBean();
        Glide.with(HomeFragment.this).load(mySetBean.getAvatar())
                .asBitmap()
                .centerCrop()
                .into(new BitmapImageViewTarget(TouXiangImage) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(HomeFragment.this.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        TouXiangImage.setImageDrawable(circularBitmapDrawable);
                    }
                });
        UserNameTextView.setText(mySetBean.getAccounts().get(0).getAccountstr());
        SharedPreferences.Editor editor = App.sharedPreferences.edit();
        editor.putString(KeyUtils.USERNAME,mySetBean.getAccounts().get(0).getAccountstr());
        editor.commit();
    }
}
