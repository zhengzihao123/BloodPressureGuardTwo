package test.jiyun.com.bloodpressureguard.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import test.jiyun.com.bloodpressureguard.App;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;
import test.jiyun.com.bloodpressureguard.model.bean.QueryExpertBean;
import test.jiyun.com.bloodpressureguard.model.biz.DoctorOnline;
import test.jiyun.com.bloodpressureguard.model.biz.DoctorOnlineImpl;
import test.jiyun.com.bloodpressureguard.model.callback.ResaultCallBack;
import test.jiyun.com.bloodpressureguard.view.MRecyclerView;

public class QueryExpertActivity extends BaseActivity {
    @Bind(R.id.m_refresh_view)
    MRecyclerView mRefreshView;
    private List<QueryExpertBean.DataBean> data;
    private int page, check;
    private DoctorOnline doctorOnline;
    private MyAdapter adapter;
    private String province, title, keyword;
    private ProgressDialog progressDialog;
    private boolean loadMore = false;
    private boolean isFirst = true;

    @Override
    protected int layoutId() {
        return R.layout.search_result_listview;
    }

    @Override
    protected void initView() {
        doctorOnline = DoctorOnlineImpl.getInstance();
        page = 1;
        Intent intent = getIntent();
        province = intent.getStringExtra("onlineProvinceTv");
        title = intent.getStringExtra("onlineTitleTv");
        keyword = intent.getStringExtra("onlineKeywordText");
        check = intent.getIntExtra("onlineCheck", 0);
        show();
        data = new ArrayList<>();
        adapter = new MyAdapter();


    }

    @Override
    protected void loadData() {
        loadList();
    }

    @Override
    protected void listener() {
        mRefreshView.setLoadingListener(new MRecyclerView.LoadingListener() {
            @Override
            public void onLoadMore() {
                listStateListener.onLoadMore();
            }
        });
    }

    private OnListStateListener listStateListener = new OnListStateListener() {
        @Override
        public void onLoadMore() {
            page++;
            loadMore = true;
            loadList();
        }

    };

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder viewHolder = new ViewHolder(View.inflate(App.activity, R.layout.search_result_item, null));
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            Glide.with(App.activity)
                    .load(data.get(position).getApp_image())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.head)
                    .into(holder.headIv);
            holder.nameTv.setText(data.get(position).getName());
            holder.expertTv.setText(data.get(position).getTeach());
            holder.locationTv.setText(data.get(position).getHospital());
            holder.typeTv.setText(data.get(position).getDepart());
            holder.contentTv.setText(data.get(position).getGoodat());
            holder.plusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(App.activity, FreeplusActivity.class);
                    intent.putExtra("expert_id", data.get(position).getExpert_id());
                    intent.putExtra("id", data.get(position).getDocument_id());
                    startActivity(intent);
                }
            });
            holder.itemRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(App.activity, SearchItemDetail.class);
                    intent.putExtra("imgUrl", data.get(position).getApp_image());
                    intent.putExtra("name", data.get(position).getName());
                    intent.putExtra("expert", data.get(position).getTeach());
                    intent.putExtra("location", data.get(position).getHospital());
                    intent.putExtra("type", data.get(position).getDepart());
                    intent.putExtra("title", data.get(position).getTitle());
                    intent.putExtra("expert_id", data.get(position).getExpert_id());
                    intent.putExtra("doctor_id", data.get(position).getDocument_id());
                    intent.putExtra("content", data.get(position).getGoodat());
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView headIv;
            private TextView nameTv, expertTv, locationTv, typeTv, contentTv;
            private Button plusBtn;
            private RelativeLayout itemRL;

            ViewHolder(View itemView) {
                super(itemView);
                itemRL = (RelativeLayout) itemView.findViewById(R.id.search_item);
                headIv = (ImageView) itemView.findViewById(R.id.search_item_head);
                nameTv = (TextView) itemView.findViewById(R.id.search_item_name);
                expertTv = (TextView) itemView.findViewById(R.id.search_item_expert);
                locationTv = (TextView) itemView.findViewById(R.id.search_item_location);
                typeTv = (TextView) itemView.findViewById(R.id.search_item_type);
                contentTv = (TextView) itemView.findViewById(R.id.search_item_content);
                plusBtn = (Button) itemView.findViewById(R.id.search_plus_button);
            }
        }
    }

    private void loadList() {
        progressDialog.show();
        doctorOnline.queryExpert(page, province, title, keyword, check, new ResaultCallBack() {

            @Override
            public void onSuccess(Object obj) {
                progressDialog.dismiss();
                if (loadMore) {
                    mRefreshView.loadMoreComplete();
                }
                QueryExpertBean queryExpertBean = (QueryExpertBean) obj;
                List<QueryExpertBean.DataBean> dataBeen = queryExpertBean.getData();
                if (dataBeen.size() > 0) {
                    data.addAll(dataBeen);
                    if (isFirst) {
                        isFirst = false;
                        setAdapter(1, adapter);
                    }
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
        }, QueryExpertBean.class);
    }

    private void show() {
        progressDialog = new ProgressDialog(App.activity);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在加载中...");
        progressDialog.setCancelable(false);
    }

    private void setAdapter(int type, RecyclerView.Adapter adapter) {
        adapter.setHasStableIds(true);
        mRefreshView.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(type, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRefreshView.setLayoutManager(staggeredGridLayoutManager);
        mRefreshView.setAdapter(adapter);
    }

    interface OnListStateListener {
        void onLoadMore();
    }
}
