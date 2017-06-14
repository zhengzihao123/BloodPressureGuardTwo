package test.jiyun.com.bloodpressureguard.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;
import test.jiyun.com.bloodpressureguard.sq.Student;
import test.jiyun.com.bloodpressureguard.utils.ShuJuUtils;

/**
 * Created by 韩志军 on 2017/6/11.
 */

/*血压详情*/
public class XueYaJiLuActivity extends BaseActivity {
    @Bind(R.id.mFinsh)
    ImageView mFinsh;
    @Bind(R.id.mTitle)
    TextView mTitle;
    @Bind(R.id.mListView)
    ListView mListView;
    private MyListAdapter myListAdapter;
    private ArrayList<Student> mListShuJu;

    @Override
    protected int layoutId() {
        return R.layout.activity_xueya_xiangq;
    }

    @Override
    protected void initView() {
        mTitle.setText("所有记录的信息");
    }

    @Override
    protected void loadData() {
        mListShuJu = ShuJuUtils.getShuJu();
        if (mListShuJu.size()==0)
            return;

        Log.d("XueYaJiLuActivity", "shuJu.size():" + mListShuJu.size());
        myListAdapter = new MyListAdapter();
        mListView.setAdapter(myListAdapter);
    }

    @Override
    protected void listener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }


    @OnClick(R.id.mFinsh)
    public void onViewClicked() {
        finish();
    }

    class MyListAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return mListShuJu.size();
        }

        @Override
        public Object getItem(int position) {
            return mListShuJu.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Holder h = new Holder();
            convertView = LayoutInflater.from(XueYaJiLuActivity.this).inflate(R.layout.item_list_item, null);
            h.mShuJu = (TextView) convertView.findViewById(R.id.mShuJu);
            h.mTime = (TextView) convertView.findViewById(R.id.mTime);
            h.mDay = (TextView) convertView.findViewById(R.id.mDay);
            Student student = mListShuJu.get(position);
            h.mShuJu.setText(student.getGaoya()+"/"+student.getDiya()+"");
            h.mTime.setText(student.getTime()+"");
            h.mDay.setText(student.getId()+"月"+student.getDay()+"日"+"");
            return convertView;
        }

        class Holder {
            private TextView mShuJu, mTime, mDay;
        }
    }
}
