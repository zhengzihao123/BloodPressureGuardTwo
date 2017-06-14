package test.jiyun.com.bloodpressureguard.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.App;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;
import test.jiyun.com.bloodpressureguard.fragment.BloodPressureFragment;
import test.jiyun.com.bloodpressureguard.fragment.DoctorOnlineFragment;
import test.jiyun.com.bloodpressureguard.model.budler.FragmentBuilder;
import test.jiyun.com.bloodpressureguard.utils.ViewHelper;

/*主页面*/
public class MainActivity extends BaseActivity {

    @Bind(R.id.mTitle)
    TextView mTitle;
    @Bind(R.id.mTextOne)
    TextView mTextOne;
    @Bind(R.id.mTextTwo)
    TextView mTextTwo;
    @Bind(R.id.mDrawerLayout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.mLayoutA)
    LinearLayout mLayoutA;
    @Bind(R.id.mTitleImage)
    ImageView mTitleImage;
    @Bind(R.id.mTitleLayout)
    LinearLayout mTitleLayout;
    @Bind(R.id.Frament_Layout)
    FrameLayout FramentLayout;

    /*下方按钮选中的集合*/
    private List<Drawable> mDrawableYesList;
    /*下方按钮未选中的集合*/
    private List<Drawable> mDrawableNoList;


    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initShuJu();
        mTitleImage.setVisibility(View.VISIBLE);
    }


    @Override
    protected void loadData() {
        FragmentBuilder.getInstance(App.activity).startFragment(DoctorOnlineFragment.class).addFragment(R.id.Frament_Layout).builder();
    }

    @Override
    protected void listener() {
        /*监听侧拉动作 侧拉弹出 侧拉菜单*/
        DrawerLayoutListener();

    }

    @OnClick({R.id.mTextOne, R.id.mTextTwo, R.id.mTitleImage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            /*医生在线按钮*/
            case R.id.mTextOne:
                getController(0);
                mTitle.setText("医生在线");
                FragmentBuilder.getInstance(App.activity).startFragment(DoctorOnlineFragment.class).addFragment(R.id.Frament_Layout).builder();
                break;
            /*血压管理按钮*/
            case R.id.mTextTwo:
                getController(1);
                mTitle.setText("血压管理");
                FragmentBuilder.getInstance(MainActivity.this).startFragment(BloodPressureFragment.class).addFragment(R.id.Frament_Layout).builder();
                break;
            /*个人中心按钮*/
            case R.id.mTitleImage:
                mDrawerLayout.openDrawer(Gravity.LEFT);
                break;
        }
    }

    /*数据初始化*/
    private void initShuJu() {
        mDrawableYesList = new ArrayList<>();
        mDrawableNoList = new ArrayList<>();
        mDrawableYesList.add(getResources().getDrawable(R.drawable.doctor_head_press));
        mDrawableYesList.add(getResources().getDrawable(R.drawable.blood_manger_press));
        mDrawableNoList.add(getResources().getDrawable(R.drawable.blood_manger_normal));
        mDrawableNoList.add(getResources().getDrawable(R.drawable.doctor_head_normal));
        getController(0);
    }

    /*下方按钮的 自定义选择器 */
    public void getController(int shu) {
        switch (shu) {
            case 0:
                mTextOne.setTextColor(Color.parseColor("#4CBF5B"));
                mTextTwo.setTextColor(Color.parseColor("#666666"));

                Drawable drawable = mDrawableYesList.get(shu);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());

                Drawable drawable1 = mDrawableNoList.get(shu);
                drawable1.setBounds(0, 0, drawable1.getMinimumWidth(),
                        drawable1.getMinimumHeight());
                mTextOne.setCompoundDrawables(null, drawable, null, null);
                mTextTwo.setCompoundDrawables(null, drawable1, null, null);
                break;
            case 1:
                mTextOne.setTextColor(Color.parseColor("#666666"));
                mTextTwo.setTextColor(Color.parseColor("#4CBF5B"));

                Drawable drawable2 = mDrawableNoList.get(shu);
                drawable2.setBounds(0, 0, drawable2.getMinimumWidth(),
                        drawable2.getMinimumHeight());

                Drawable drawable3 = mDrawableYesList.get(shu);
                drawable3.setBounds(0, 0, drawable3.getMinimumWidth(),
                        drawable3.getMinimumHeight());
                mTextOne.setCompoundDrawables(null, drawable2, null, null);
                mTextTwo.setCompoundDrawables(null, drawable3, null, null);
                break;
        }
    }

    /*
     * 监听侧拉动作 侧拉弹出 侧拉菜单
     * */
    private void DrawerLayoutListener() {
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                float slide = 0.7f;
                int leftSize = (int) (slideOffset * 100);
                View mContent = mDrawerLayout.getChildAt(0);
                float Scale = 0.5f * (1.0f + slideOffset);
                /*左侧平滑*/
                drawerView.setAlpha(Scale);
                ViewHelper.setTranslationX(mContent, drawerView.getMeasuredWidth() * slideOffset);
                mContent.invalidate();

                if (slideOffset > slide) {
                    mTitleLayout.setBackgroundColor(Color.parseColor("#ffffff"));
                    mTitleImage.setImageResource(R.drawable.persional_press);
                } else {
                    mTitleLayout.setBackgroundColor(Color.parseColor("#4CBF5B"));
                    mTitleImage.setImageResource(R.drawable.persional_normal);
                }
                /*设置个人中心按钮的移动*/
                mTitleImage.setPadding(leftSize, 0, 0, 0);
                Log.d("MainActivity", "leftSize:" + leftSize);


                /*右侧平滑*/
                /*drawerView.setAlpha(Scale);
                ViewHelper.setTranslationX(mContent, -drawerView.getMeasuredWidth() * slideOffset);
                mContent.invalidate();*/

                /*3D滑动效果*/
                /*CeLaUtils.slideAnim(mDrawerLayout, drawerView, slideOffset);*/
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
