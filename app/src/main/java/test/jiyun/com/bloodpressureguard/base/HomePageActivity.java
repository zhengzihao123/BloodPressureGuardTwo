package test.jiyun.com.bloodpressureguard.base;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.R;
//
//import static com.sunny.cst.sun_citypass.R.id.TitleBar_imageView11;
//import static com.sunny.cst.sun_citypass.R.id.TitleBar_imageView2;


/**
 * Created by 韩志军 on 2017/5/15.
 * <p>
 * 主页面
 */
//public class HomePageActivity extends BaseActivity {
//    @Bind(R.id.mDrawerLayout)
//    DrawerLayout mDrawerLayout;
//    @Bind(R.id.TouTiaoBtn)
//    RadioButton TouTiaoBtn;
//    @Bind(R.id.NaoNaoBtn)
//    RadioButton NaoNaoBtn;
//    @Bind(R.id.SheQuBtn)
//    RadioButton SheQuBtn;
//    @Bind(R.id.ShengHuoBtn)
//    RadioButton ShengHuoBtn;
//    @Bind(R.id.DiscoverBtn)
//    RadioButton DiscoverBtn;
//    @Bind(R.id.Bottom_Group)
//    RadioGroup BottomGroup;
//    @Bind(R.id.TitleBar_imageView3)
//    ImageView TitleBarImageView3;
//    @Bind(R.id.Title_Text)
//    TextView TitleText;
//
//    @Bind(TitleBar_imageView11)
//    ImageView TitleBarImageView;
//    @Bind(R.id.TitleBar_imageView1)
//    ImageView TitleBarImageView1;
//    @Bind(TitleBar_imageView2)
//    ImageView TitleBarImageView2;
//    @Bind(R.id.TitleBar)
//    RelativeLayout TitleBar;
//    @Bind(R.id.mlayout)
//    LinearLayout mlayout;
//    //用来判断监听状态
//    private int click;
//
//    /* 韩志军 主页动画说用的类*/
//    private MyGradeView linkPageGridview;
//    private View view;
//    private GVAdapter gvAdapter;
//    private List<String> mlist;
//    private List<Integer> mDList;
//    private RelativeLayout layout;
//    private Handler handler = new Handler();
//    private View headView,headTwoView;
//    private TextView headLive;
//    private TextView  mXiaoShiPin,mXiangCe,mCanCle;
//    private PopupWindow popupWindow,mPopupWindow;
//
//
//
//
//
//
//    //dffdff
//    /* 韩志军 主页动画说用的类*/
//    @Override
//    protected int layoutId() {
//        return R.layout.activity_home_page;
//    }
//
//    @Override
//    protected void initView() {
//        EventBus.getDefault().register(this);
//        Log.d("主线程中的线程名=", "" + android.os.Process.myTid());
//        headView = LayoutInflater.from(App.baseActivity).inflate(R.layout.head_activity, null);
//        headLive = (TextView) headView.findViewById(R.id.dengji_textview);
//        FragmentBuilder.getInstance(App.baseActivity).startFragment(ShouYeFragment.class).addFragment(R.id.HomePager_FrameLayout).builder();
//        initpopupWindow();
//
//    }
//
//    @Override
//    protected void loadData() {
//        if (UserUtils.getUserName().length() != 0) {
//            Glide.with(HomePageActivity.this).load(UserUtils.getUSERIMAGE())
//                    .asBitmap()
//                    .centerCrop()
//                    .into(new BitmapImageViewTarget(TitleBarImageView) {
//                        @Override
//                        protected void setResource(Bitmap resource) {
//                            RoundedBitmapDrawable circularBitmapDrawable =
//                                    RoundedBitmapDrawableFactory.create(HomePageActivity.this.getResources(), resource);
//                            circularBitmapDrawable.setCircular(true);
//                            TitleBarImageView.setImageDrawable(circularBitmapDrawable);
//                        }
//                    });
//
//        }
//    }
//
//    @Override
//    protected void listener() {
//        DrawerLayoutListener();
//        TouTiaoBtn.setChecked(true);
//        initData();
//    }
//
//
//    //跳转我要隐藏TitleBar  刘振新
//    public RelativeLayout getTitleBar() {
//        return TitleBar;
//    }
//
//    /*
//        * 监听侧拉动作 侧拉弹出 侧拉菜单
//        * */
//    private void DrawerLayoutListener() {
//        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
//            @Override
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//                CeLaUtils.slideAnim(mDrawerLayout, drawerView, slideOffset);
//            }
//
//            @Override
//            public void onDrawerOpened(View drawerView) {
//
//            }
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//
//            }
//
//            @Override
//            public void onDrawerStateChanged(int newState) {
//
//            }
//        });
//    }
//
//    int i;
//
//    @OnClick({R.id.TouTiaoBtn, R.id.NaoNaoBtn, R.id.SheQuBtn, R.id.ShengHuoBtn, R.id.DiscoverBtn, R.id.Title_Text, TitleBar_imageView2, TitleBar_imageView11})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.TouTiaoBtn:
//                FragmentBuilder.getInstance(App.baseActivity).startFragment(ShouYeFragment.class).addFragment(R.id.HomePager_FrameLayout).builder();
//                break;
//            case R.id.NaoNaoBtn:
//
//                FragmentBuilder.getInstance(App.baseActivity).startFragment(NaoNaoFragment.class).addFragment(R.id.HomePager_FrameLayout).builder();
//                break;
//            case R.id.SheQuBtn:
//                FragmentBuilder.getInstance(App.baseActivity).startFragment(CommunityFragment.class).addFragment(R.id.HomePager_FrameLayout).builder();
//                break;
//            case R.id.ShengHuoBtn:
//                FragmentBuilder.getInstance(App.baseActivity).startFragment(LifeFragment.class).addFragment(R.id.HomePager_FrameLayout).builder();
//                break;
//            case R.id.DiscoverBtn:
//                FragmentBuilder.getInstance(App.baseActivity).startFragment(FXFragment.class).addFragment(R.id.HomePager_FrameLayout).builder();
//                break;
//            /**
//             * 加载城市
//             */
//            case R.id.Title_Text:
//                switch (click) {
//                    case 0:
//                        Intent intent = new Intent(getApplicationContext(), Cityswitch_Activity.class);
//                        startActivity(intent);
//                        break;
//                }
//
//                break;
//            case TitleBar_imageView2:
//                switch (click) {
//                    case 0:
//                        Intent inten = new Intent(getApplicationContext(), MessageNotiActivity.class);
//                        startActivity(inten);
//                        break;
//                    case 2:
//                        startActivity(new Intent(HomePageActivity.this, SouSuoActivity.class));
//                        break;
//
//                }
//
//                break;
//            //头像
//            case TitleBar_imageView11:
//                        Intent intent1 = new Intent(this, LoginActivity.class);
//                        startActivity(intent1);
//
//               // if(UserUtils.getUserName().length()==0){
////               }else{
////                    ToastUtils.showShortToast("你已经登录了");
////               }
//                break;
//        }
//    }
//
//    /*  韩志军 点击加号的监听*/
//    @OnClick(R.id.TitleBar_imageView3)
//    public void onViewClicked() {
//        /*首先执行 加号的动画*/
//        switch (click) {
//            //首页
//            case 0:
//                MyAnimalUtils.pictureAnimal(TitleBarImageView3, HomePageActivity.this);
//                popupWindow.showAtLocation(findViewById(R.id.mlayout), Gravity.BOTTOM, 0, 0);
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                    /*执行popupwindow 出现的动画*/
//                        MyAnimalUtils.getGVDHB(linkPageGridview, HomePageActivity.this, false);
//                    }
//                }, 400);
//                break;
//            //闹闹
//            case 1:
//                if (mPopupWindow.isShowing()){
//                    mPopupWindow.dismiss();
//                }else {
//
//                    mPopupWindow.showAsDropDown(findViewById(R.id.HomePager_FrameLayout),0,-60,Gravity.BOTTOM);
//                }
//
//                break;
//            //社区
//            case 2:
//
//                startActivity(new Intent(this, BIanJiActivity.class));
//
//
//                break;
//            //生活
//            case 3:
//                break;
//            //发现
//            case 4:
//
//                break;
//        }
//
//    }
//
//    /* 韩志军 popupwindow 模板上面的数据集合*/
//    private void initData() {
//        mlist = listutils.getStringList();
//        mDList = listutils.getIntegerList();
//        //
//        getPopup();
//    }
//
//    /*韩志军  pouppwindow 初始化*/
//    private void getPopup() {
//        view = LayoutInflater.from(HomePageActivity.this).inflate(R.layout.ppmb, null);
//        view.getBackground().setAlpha(200);
//        layout = (RelativeLayout) view.findViewById(R.id.layout);
//        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setBackgroundDrawable(null);
//        linkPageGridview = (MyGradeView) view.findViewById(R.id.link_page_gridview);
//        gvAdapter = new GVAdapter(mDList, mlist, this);
//        linkPageGridview.setAdapter(gvAdapter);
//        popupWindow.setAnimationStyle(R.style.Animation);
//        layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                /*让每一个item先消失*/
//                MyAnimalUtils.getGVDHB(linkPageGridview, HomePageActivity.this, true);
//                /*得到一共有几个item 然后乘以每个item 消失的时间*/
//                /*获得item 消失一共用了多长时间*/
//                long i = (linkPageGridview.getChildCount() + 1) * 160;
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        /*在item 消失点后让popupWindow消失*/
//                        popupWindow.dismiss();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                /*在PopupWindow消失后 让加号执行逆时针动作*/
//                                MyAnimalUtils.pictureAnimala(TitleBarImageView3, HomePageActivity.this);
//                            }
//                        }, 100);
//                    }
//                }, i);
//            }
//        });
//    }
//
//
//
//    //EventBus传值
//    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
//    public void onEvent(UserBus userBus) {
//        UserMessage.ServerInfoBean serverInfo = userBus.getUserMessage().getServerInfo();
//        Glide.with(HomePageActivity.this).load(serverInfo.getUserFace())
//                .asBitmap()
//                .centerCrop()
//                .into(new BitmapImageViewTarget(TitleBarImageView) {
//                    @Override
//                    protected void setResource(Bitmap resource) {
//                        RoundedBitmapDrawable circularBitmapDrawable =
//                                RoundedBitmapDrawableFactory.create(HomePageActivity.this.getResources(), resource);
//                        circularBitmapDrawable.setCircular(true);
//                        TitleBarImageView.setImageDrawable(circularBitmapDrawable);
//                    }
//                });
//    }
//
//    //主界面修改titleBar的方法
//    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
//    public void onTitleBar(TitleBean titleBean) {
//        int oneImageVissable = titleBean.getOneImageVissable();
//        if (oneImageVissable == 0) {
//            TitleBarImageView2.setImageResource(titleBean.getOneImageResouse());
//        } else if (titleBean.getOneImageVissable() == 1) {
//            TitleBarImageView2.setVisibility(View.VISIBLE);
//            TitleBarImageView2.setImageResource(titleBean.getOneImageResouse());
//        } else {
//            TitleBarImageView2.setVisibility(View.GONE);
//        }
//        if(titleBean.getClick()!=0){
//            TitleBarImageView1.setVisibility(View.GONE);
//        }
//        TitleText.setText(titleBean.getTitle());
//        TitleBarImageView3.setImageResource(titleBean.getTwoImageResouse());
//        this.click = titleBean.getClick();
//    }
//    /*
//    点击闹闹页面的摄像头弹出的popupwindow
//     */
//    private  void  initpopupWindow(){
//              View  view=LayoutInflater.from(HomePageActivity.this).inflate(R.layout.popup,null);
//                mPopupWindow=new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
//                mPopupWindow.setBackgroundDrawable(new ColorDrawable());
//                mPopupWindow.setOutsideTouchable(true);
//               mXiaoShiPin= (TextView) view.findViewById(R.id.XiaoShiPin);
//                mXiangCe= (TextView) view.findViewById(R.id.XiangCeXuanZhe);
//                mCanCle= (TextView) view.findViewById(R.id.Cancle);
//               mXiaoShiPin.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent  intent=new Intent(HomePageActivity.this, XiaoShiPinActivity.class);
//                            startActivity(intent);
//                           }
//                    });
//                mXiangCe.setOnClickListener(new View.OnClickListener() {
//                       @Override
//                        public void onClick(View v) {
//                                Intent  intent=new Intent(HomePageActivity.this, FaBiaoTieZiPictureActivity.class);
//                                startActivity(intent);
//                          }
//                   });
//               mCanCle.setOnClickListener(new View.OnClickListener() {
//                        @Override public void onClick(View v) {
//                             finish();
//                            }
//                    });
//            }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        System.exit(0);
//    }
//}
//