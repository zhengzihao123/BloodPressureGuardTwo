package test.jiyun.com.bloodpressureguard.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import test.jiyun.com.bloodpressureguard.App;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;
import test.jiyun.com.bloodpressureguard.fragment.ExpertReplyFragment;
import test.jiyun.com.bloodpressureguard.fragment.VisitingScheduleFragment;

/**
 * Created by ASUS on 2017/6/13.
 */

public class SearchItemDetail extends BaseActivity {
    @Bind(R.id.search_detail_head)
    ImageView searchDetailHead;
    @Bind(R.id.search_detail_name)
    TextView searchDetailName;
    @Bind(R.id.search_detail_type)
    TextView searchDetailType;
    @Bind(R.id.search_detail_location)
    TextView searchDetailLocation;
    @Bind(R.id.search_detail_expert)
    TextView searchDetailExpert;
    @Bind(R.id.search_detail_offer)
    TextView searchDetailOffer;
    @Bind(R.id.search_detail_content)
    TextView searchDetailContent;
    @Bind(R.id.search_detail_content_all)
    TextView searchDetailContentAll;
    @Bind(R.id.search_detail_tab)
    TabLayout searchDetailTab;
    @Bind(R.id.search_detail_pager)
    ViewPager searchDetailPager;

    private String imgUrl, name, expert, location, type, content, title, expertId, doctorId;
    private List<Fragment> fragments;

    @Override
    protected int layoutId() {
        return R.layout.search_item_detail;
    }

    @Override
    protected void initView() {
        imgUrl = getIntent().getStringExtra("imgUrl");
        name = getIntent().getStringExtra("name");
        expert = getIntent().getStringExtra("expert");
        location = getIntent().getStringExtra("location");
        type = getIntent().getStringExtra("type");
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        expertId = getIntent().getStringExtra("expert_id");
        doctorId = getIntent().getStringExtra("doctor_id");
        fragments = new ArrayList<>();
        Bundle bundle = new Bundle();
        bundle.putString("expertid", expertId);
        ExpertReplyFragment replyFragment = new ExpertReplyFragment();
        replyFragment.setArguments(bundle);
        Bundle bundle1 = new Bundle();
        bundle1.putString("id", doctorId);
        VisitingScheduleFragment scheduleFragment = new VisitingScheduleFragment();
        scheduleFragment.setArguments(bundle1);
        fragments.add(replyFragment);
        fragments.add(scheduleFragment);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        searchDetailTab.setupWithViewPager(searchDetailPager);
        searchDetailPager.setAdapter(adapter);
    }

    @Override
    protected void loadData() {
        if (!TextUtils.isEmpty(imgUrl))
            Glide.with(App.activity)
                    .load(imgUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.head)
                    .into(searchDetailHead);
        searchDetailName.setText(name);
        searchDetailType.setText(type);
        searchDetailLocation.setText(location);
        searchDetailExpert.setText(expert);
        searchDetailOffer.setText(title);
        searchDetailContent.setText(content);
    }

    @Override
    protected void listener() {

    }


    private class MyAdapter extends FragmentPagerAdapter {
        private String[] titles = {"专家回复", "出诊时间表", "专家经验分享"};

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return this.titles[position];
        }
    }

}
