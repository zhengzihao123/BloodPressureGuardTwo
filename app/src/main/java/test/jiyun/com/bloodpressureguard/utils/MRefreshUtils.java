package test.jiyun.com.bloodpressureguard.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.view.MRecyclerView;

/**
 * Created by zhangzl
 * 描述:
 * 日期: 2017/2/24 0024.
 */

public class MRefreshUtils {
    private Context context;
    private MRecyclerView mRecyclerView;

    private boolean isTran = false;

    public MRefreshUtils(Context context, LinearLayout layout, OnListStateListener stateListener) {
        this.context = context;
        this.onListStateListener = stateListener;
        mRecyclerView = (MRecyclerView) layout.findViewById(R.id.m_refresh_view);
        mRecyclerView.setLoadingListener(new MRecyclerView.LoadingListener() {

            @Override
            public void onLoadMore() {
                if (onListStateListener != null) {
                    onListStateListener.onLoadMore();
                }
            }
        });
    }

    public void requestData(Object result) {
        mRecyclerView.loadMoreComplete();
        if (onListStateListener != null) {
            onListStateListener.onSuccess(false, result);
        }
    }

    public void setAdapter(int type, RecyclerView.Adapter adapter) {
        adapter.setHasStableIds(true);
        mRecyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(type, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    /**
     * 没有更多数据了
     *
     * @param isAll
     */
    public void setNoMore(boolean isAll) {
        mRecyclerView.setNoMore(isAll);
    }

    /**
     * 加载更多加载完毕
     */
    public void loadMoreComplete() {
        mRecyclerView.loadMoreComplete();
    }

    private OnListStateListener onListStateListener;

    public void setOnListStateListener(OnListStateListener onListStateListener) {
        this.onListStateListener = onListStateListener;
    }

    /**
     * 提示信息
     */
    private void showToast(String toast) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }

    public interface OnListStateListener {
        void onLoadMore();

        void onSuccess(boolean Refresh, Object result);

        void onFailure(boolean Refresh, Object result);

    }


}
