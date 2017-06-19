package test.jiyun.com.bloodpressureguard.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.App;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;

/**
 * Created by ASUS on 2017/6/12.
 */

public class KeywordActivity extends BaseActivity {
    @Bind(R.id.keyword_query_btn)
    TextView keywordQueryBtn;
    @Bind(R.id.keyword_et)
    EditText keywordEt;
    @Bind(R.id.keyword_listview)
    ListView keywordListview;
    @Bind(R.id.mFinsh)
    ImageView mFinsh;
    @Bind(R.id.mTitle)
    TextView mTitle;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private List<String> data;

    @Override
    protected int layoutId() {
        return R.layout.online_keyword;
    }

    @Override
    protected void initView() {
        mTitle.setText("关键字");
        sharedPreferences = getSharedPreferences("keyword", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        data = new ArrayList<>();
        Set<String> keyword_list = sharedPreferences.getStringSet("keyword_list", new HashSet<String>());
        if (keyword_list.size() == 0) {
            keywordListview.setVisibility(View.GONE);
        } else {
            data.addAll(keyword_list);
        }
    }

    @Override
    protected void loadData() {
        keywordListview.setAdapter(new MyAdapter());
    }

    @Override
    protected void listener() {

        keywordListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                keywordEt.setText(data.get(position));
            }
        });
        mFinsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @OnClick(R.id.keyword_query_btn)
    public void onViewClicked() {
        String string = keywordEt.getText().toString();
        if (TextUtils.isEmpty(string))
            string = "";
        Set<String> keyword_list = sharedPreferences.getStringSet("keyword_list", new HashSet<String>());
        keyword_list.add(string);
        editor.putStringSet("keyword_list", keyword_list);
        editor.commit();
        Intent intent = getIntent();
        intent.putExtra("keyword", string);
        setResult(2000, intent);
        finish();
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
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
            holder.text.setText(data.get(position));
            return convertView;
        }

        class ViewHolder {
            private TextView text;
        }
    }
}
