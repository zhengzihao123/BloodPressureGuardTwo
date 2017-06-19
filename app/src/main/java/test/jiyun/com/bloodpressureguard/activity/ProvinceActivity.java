package test.jiyun.com.bloodpressureguard.activity;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.App;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;

/**
 * Created by ASUS on 2017/6/12.
 */

public class ProvinceActivity extends BaseActivity {
    @Bind(R.id.province_list_view)
    ListView provinceListView;
    @Bind(R.id.mFinsh)
    ImageView mFinsh;
    @Bind(R.id.mTitle)
    TextView mTitle;
    private List<String> list;

    @Override
    protected int layoutId() {
        return R.layout.province_activity;
    }

    @Override
    protected void initView() {
        mTitle.setText("省份");
        list = new ArrayList<>();
        addList();
    }

    @Override
    protected void loadData() {
        provinceListView.setAdapter(new MyAdapter());
    }

    @Override
    protected void listener() {
        provinceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = getIntent();
                intent.putExtra("province", list.get(position));
                setResult(1000, intent);
                finish();
            }
        });
    }

    private void addList() {
        list.add("不限");
        list.add("北京市");
        list.add("山东省");
        list.add("山西省");
        list.add("河北省");
        list.add("河南省");
        list.add("天津市");
        list.add("辽宁省");
        list.add("黑龙江省");
        list.add("吉林省");
        list.add("湖北省");
        list.add("湖南省");
        list.add("上海市");
        list.add("四川省");
        list.add("重庆市");
        list.add("陕西省");
        list.add("甘肃省");
        list.add("云南省");
        list.add("新疆维吾尔自治区");
        list.add("内蒙古自治区");
        list.add("海南省");
        list.add("贵州省");
        list.add("青海省");
        list.add("广东省");
        list.add("宁夏回族自治区");
        list.add("西藏自治区");
        list.add("广西壮族自治区");
        list.add("江苏省");
        list.add("浙江省");
        list.add("安徽省");
        list.add("江西省");
        list.add("福建省");
    }

    @OnClick(R.id.mFinsh)
    public void onViewClicked() {
        finish();
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
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
                convertView = View.inflate(App.activity, R.layout.province_item, null);
                holder.text = (TextView) convertView.findViewById(R.id.province_item_tv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.text.setText(list.get(position));
            return convertView;
        }

        class ViewHolder {
            private TextView text;
        }
    }

}
