package test.jiyun.com.bloodpressureguard.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.App;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;
import test.jiyun.com.bloodpressureguard.model.bean.FreePlusDetailBean;
import test.jiyun.com.bloodpressureguard.model.biz.DoctorOnline;
import test.jiyun.com.bloodpressureguard.model.biz.DoctorOnlineImpl;
import test.jiyun.com.bloodpressureguard.model.callback.ResaultCallBack;

/**
 * Created by ASUS on 2017/6/13.
 */

public class FreeplusActivity extends BaseActivity {
    @Bind(R.id.plus_head)
    ImageView plusHead;
    @Bind(R.id.plus_name)
    TextView plusName;
    @Bind(R.id.plus_expert)
    TextView plusExpert;
    @Bind(R.id.plus_location)
    TextView plusLocation;
    @Bind(R.id.plus_type)
    TextView plusType;
    @Bind(R.id.plus_offer)
    TextView plusOffer;
    @Bind(R.id.plus_content)
    TextView plusContent;
    @Bind(R.id.plus_content_all)
    TextView searchContentAll;
    @Bind(R.id.plus_listview)
    ListView plusListView;
    @Bind(R.id.mFinsh)
    ImageView mFinsh;
    @Bind(R.id.mTitle)
    TextView mTitle;
    private String expertId, doctorId;
    private DoctorOnline doctorOnline;
    private ProgressDialog progressDialog;
    private List<FreePlusDetailBean.DataBean.ScheduleBean.RdtimeBean> list;
    private MyAdapter adapter;

    @Override
    protected int layoutId() {
        return R.layout.free_plus_detail;
    }

    @Override
    protected void initView() {
        mTitle.setText("预约专家");
        expertId = getIntent().getStringExtra("expert_id");
        doctorId = getIntent().getStringExtra("id");
        doctorOnline = DoctorOnlineImpl.getInstance();
        show();
        list = new ArrayList<>();
        adapter = new MyAdapter();
        View view = View.inflate(App.activity, R.layout.plus_detail_head_item, null);
        plusListView.addHeaderView(view);
        plusListView.setAdapter(adapter);
    }

    @Override
    protected void loadData() {
        progressDialog.show();
        doctorOnline.freePlusDetail(expertId, doctorId, new ResaultCallBack() {
            @Override
            public void onSuccess(Object obj) {
                progressDialog.dismiss();
                FreePlusDetailBean plusDetailBean = (FreePlusDetailBean) obj;
                FreePlusDetailBean.DataBean data = plusDetailBean.getData();
                if (!TextUtils.isEmpty(data.getPhoto()))
                    Glide.with(App.activity)
                            .load(data.getPhoto())
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.head)
                            .into(plusHead);
                plusName.setText(data.getName());
                plusExpert.setText(data.getTeach());
                plusLocation.setText(data.getHospital());
                plusType.setText(data.getDepart());
                plusOffer.setText(data.getTitle());
                plusContent.setText(data.getGoodat());
                List<FreePlusDetailBean.DataBean.ScheduleBean.RdtimeBean> rdtime = data.getSchedule().getRdtime();
                if (rdtime.size() > 0) {
                    list.addAll(rdtime);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String errorMsg) {
                progressDialog.dismiss();
                showToast(errorMsg);
            }

            @Override
            public void notNet(String netData) {
                progressDialog.dismiss();
                showToast(netData);
            }

            @Override
            public void onErrorParams(String errorParams) {
                progressDialog.dismiss();
                showToast(errorParams);
            }
        }, FreePlusDetailBean.class);
    }

    @Override
    protected void listener() {

    }

    private void show() {
        progressDialog = new ProgressDialog(App.activity);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在加载中...");
        progressDialog.setCancelable(false);
    }

    @OnClick(R.id.mFinsh)
    public void onViewClicked() {
        finish();
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(App.activity, R.layout.plus_detail_list_item, null);
                holder.morningLL = (LinearLayout) convertView.findViewById(R.id.plus_item_morning);
                holder.afternoonLL = (LinearLayout) convertView.findViewById(R.id.plus_item_afternoon);
                holder.textDataTop = (TextView) convertView.findViewById(R.id.plus_item_data_top);
                holder.textDataBottom = (TextView) convertView.findViewById(R.id.plus_item_data_bottom);
                holder.textMorningTop = (TextView) convertView.findViewById(R.id.plus_item_morning_top);
                holder.textMorningBottom = (TextView) convertView.findViewById(R.id.plus_item_morning_bottom);
                holder.textAfternoonTop = (TextView) convertView.findViewById(R.id.plus_item_afternoon_top);
                holder.textAfternoonBottom = (TextView) convertView.findViewById(R.id.plus_item_afternoon_bottom);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            String title = list.get(position).getTitle();
            String month = title.substring(5, 7);
            String day = title.substring(8, 10);
            String data = title.substring(11, 14);
            String timeType = title.substring(15, 17);
            if (month.startsWith("0")) {
                month = month.substring(1);
            }
            data = data.replace("星期", "周");
            String dataTv = month + "月" + day + "日";
            holder.textDataTop.setText(dataTv);
            holder.textDataBottom.setText(data);
            switch (timeType) {
                case "上午":
                    holder.textMorningTop.setText(list.get(position).getMsg());
                    holder.textMorningBottom.setText(list.get(position).getSurplus());
                    holder.morningLL.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(App.activity, YuyueActivity.class);
                            startActivity(intent);
                        }
                    });
                    break;
                case "下午":
                    holder.textAfternoonTop.setText(list.get(position).getMsg());
                    holder.textAfternoonBottom.setText(list.get(position).getSurplus());
                    holder.afternoonLL.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(App.activity, YuyueActivity.class);
                            startActivity(intent);
                        }
                    });
                    break;
            }
            return convertView;
        }

        class ViewHolder {
            private LinearLayout morningLL, afternoonLL;
            private TextView textDataTop, textDataBottom, textMorningTop, textMorningBottom, textAfternoonTop, textAfternoonBottom;
        }
    }
}
