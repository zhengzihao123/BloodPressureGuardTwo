package test.jiyun.com.bloodpressureguard.fragment;

import android.app.ProgressDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import test.jiyun.com.bloodpressureguard.App;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseFragment;
import test.jiyun.com.bloodpressureguard.model.bean.ExpertReplyBean;
import test.jiyun.com.bloodpressureguard.model.biz.DoctorOnline;
import test.jiyun.com.bloodpressureguard.model.biz.DoctorOnlineImpl;
import test.jiyun.com.bloodpressureguard.model.callback.ResaultCallBack;
import test.jiyun.com.bloodpressureguard.view.MRecyclerView;

/**
 * Created by ASUS on 2017/6/13.
 */

public class ExpertReplyFragment extends BaseFragment {
    @Bind(R.id.m_refresh_view)
    MRecyclerView mRefreshView;
    @Bind(R.id.no_more_tv)
    TextView noMoreTv;
    private ProgressDialog progressDialog;
    private int page = 1;
    private DoctorOnline doctorOnline;
    private List<ExpertReplyBean.DataBean> data;
    private MyAdapter adapter;
    private boolean loadMore;

    @Override
    protected int ViewID() {
        return R.layout.expert_reply;
    }

    @Override
    protected void initView() {
        doctorOnline = DoctorOnlineImpl.getInstance();
        show();
        data = new ArrayList<>();
        adapter = new MyAdapter();
        setAdapter(1, adapter);
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

    private void loadList() {
        progressDialog.show();
        doctorOnline.expertReply(page, getArguments().getString("expertid"), new ResaultCallBack() {

            @Override
            public void onSuccess(Object obj) {
                progressDialog.dismiss();
                if (loadMore) {
                    mRefreshView.loadMoreComplete();
                }
                ExpertReplyBean expertReplyBean = (ExpertReplyBean) obj;
                List<ExpertReplyBean.DataBean> dataBeen = expertReplyBean.getData();
                if (dataBeen.size() > 0) {
                    data.addAll(dataBeen);
                    adapter.notifyDataSetChanged();
                } else {
                    mRefreshView.setVisibility(View.GONE);
                    noMoreTv.setVisibility(View.VISIBLE);
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
        }, ExpertReplyBean.class);
    }

    private void setAdapter(int type, RecyclerView.Adapter adapter) {
        adapter.setHasStableIds(true);
        mRefreshView.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(type, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRefreshView.setLayoutManager(staggeredGridLayoutManager);
        mRefreshView.setAdapter(adapter);
    }

    private OnListStateListener listStateListener = new OnListStateListener() {
        @Override
        public void onLoadMore() {
            page++;
            loadMore = true;
            loadList();
        }

    };

    interface OnListStateListener {
        void onLoadMore();
    }

    private void show() {
        progressDialog = new ProgressDialog(App.activity);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在加载中...");
        progressDialog.setCancelable(false);
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder viewHolder = new ViewHolder(View.inflate(App.activity, R.layout.expert_reply_item, null));
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.titleTv.setText(data.get(position).getTitle());
            holder.contentTv.setText(data.get(position).getReply());
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView titleTv, contentTv;

            public ViewHolder(View itemView) {
                super(itemView);
                titleTv = (TextView) itemView.findViewById(R.id.reply_title);
                contentTv = (TextView) itemView.findViewById(R.id.reply_content);
            }
        }
    }

}
