package test.jiyun.com.bloodpressureguard.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;

import static test.jiyun.com.bloodpressureguard.R.id.mGrideLayout;

/**
 * Created by 韩志军 on 2017/6/10.
 */

/*血压资讯*/
public class XueYaZXActivity extends BaseActivity {
    @Bind(R.id.mTitle)
    TextView mTitle;
    @Bind(R.id.mGridView)
    GridView mGridView;
    @Bind(R.id.mFinsh)
    ImageView mFinsh;

    private List<Integer> mDrawableList;
    private List<String> mNameList;
    private int heightPixels;

    @Override
    protected int layoutId() {
        return R.layout.activity_xyzx;
    }


    @Override
    protected void initView() {
        int height = XueYaZXActivity.this.getResources().getDisplayMetrics().heightPixels;
        /*dp转换层px*/
        float density = XueYaZXActivity.this.getResources().getDisplayMetrics().density;
        int v = (int) (70 * density);
        heightPixels = height - v;

        mDrawableList = new ArrayList<>();
        mNameList = new ArrayList<>();

        mDrawableList.add(R.drawable.a_xyas);
        mDrawableList.add(R.drawable.b_xysp);
        mDrawableList.add(R.drawable.c_xyyf);
        mDrawableList.add(R.drawable.d_xyzl);
        mDrawableList.add(R.drawable.e_xyjc);

        mNameList.add("高血压常识");
        mNameList.add("高血压食谱");
        mNameList.add("高血压预防");
        mNameList.add("高血压治疗");
        mNameList.add("高血压检测");
        MyAdapter myAdapter = new MyAdapter();
        mGridView.setAdapter(myAdapter);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void listener() {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(XueYaZXActivity.this, XiangQingActivity.class);
                in.putExtra("title", mNameList.get(position));
                in.putExtra("flg", position + 1 + "");
                startActivity(in);
            }
        });

    }


    @OnClick(R.id.mFinsh)
    public void onViewClicked() {
        finish();
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mDrawableList.size();
        }

        @Override
        public Object getItem(int position) {
            return mNameList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = LayoutInflater.from(XueYaZXActivity.this).inflate(R.layout.item_gride, null);


            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, heightPixels / 3);
            RelativeLayout relativeLayout = (RelativeLayout) convertView.findViewById(mGrideLayout);
            relativeLayout.setLayoutParams(layoutParams);
            TextView textView = (TextView) convertView.findViewById(R.id.mGrideText);
            textView.setText(mNameList.get(position));
            Drawable drawable = getResources().getDrawable(mDrawableList.get(position));
            drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                    drawable.getMinimumHeight());
            textView.setCompoundDrawables(null, drawable, null, null);
            return convertView;
        }

    }

}
