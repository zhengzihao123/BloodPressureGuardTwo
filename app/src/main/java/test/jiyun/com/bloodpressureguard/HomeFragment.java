package test.jiyun.com.bloodpressureguard;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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

import static android.app.Activity.RESULT_OK;


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
    private boolean isLogin;
    private View view;
    private PopupWindow popupWindow;
    private Button paizhao, xiangceng, quxiao;
    private static int RESULT_LOAD_IMAGE = 1;


    @Override
    protected int ViewID() {
        return R.layout.fragment_homepager;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        getPopu();

    }

    @Override
    protected void loadData() {
//        //
        Glide.with(HomeFragment.this).load(UserUtils.getUSERImage()).placeholder(R.drawable.login_icon_account).into(TouXiangImage);
        UserNameTextView.setText(UserUtils.getUSERNAME());


//


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
                if (isLogin) {

                    popupWindow.showAtLocation(getActivity().findViewById(R.id.cehua), Gravity.BOTTOM, 0, 0);


                } else {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.UserName_TextView:

                break;
            case R.id.My_Add:
                if (isLogin) {

                    ToastUtils.showShortToast("哈哈哈");

                } else {
                    Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent1);
                }
                break;
            case R.id.My_Collection:
                if (isLogin) {
                    Intent intent1 = new Intent(getActivity(), My_Collection.class);
                    startActivity(intent1);
                } else {
                    Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent1);
                }
                break;
            case R.id.My_Data:
                if (isLogin) {
                    Intent intent1 = new Intent(getActivity(), My_Data.class);
                    startActivity(intent1);
                } else {
                    Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent1);
                }
                break;
            case R.id.My_Message:
                if (isLogin) {
                    Intent intent1 = new Intent(getActivity(), My_Message.class);
                    startActivity(intent1);
                } else {
                    Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent1);
                }
                break;
            case R.id.My_Set:
                if (isLogin) {
                    Intent intent1 = new Intent(getActivity(), My_Set.class);
                    startActivity(intent1);
                } else {
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
        editor.putString(KeyUtils.USERNAME, mySetBean.getAccounts().get(0).getAccountstr());
        editor.commit();
    }

    private void getPopu() {
        view = View.inflate(getActivity(), R.layout.show_pupu, null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(Color.WHITE);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setAnimationStyle(R.style.Popupwindow);
        paizhao = (Button) view.findViewById(R.id.PaiZhao);
        xiangceng = (Button) view.findViewById(R.id.XiangCeng);
        quxiao = (Button) view.findViewById(R.id.QuXiao);
        paizhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShortToast("打开相机~~");
                Intent intent = new Intent(); //调用照相机
                intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
                startActivity(intent);
                popupWindow.dismiss();

            }
        });
        xiangceng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShortToast("我继承了一个DEMO");
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);//调用系统相册
                popupWindow.dismiss();
            }
        });
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //获取图片路径
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getActivity().getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            Bitmap bm = BitmapFactory.decodeFile(imagePath);
           TouXiangImage.setImageBitmap(bm);
            c.close();

        }
    }




}
