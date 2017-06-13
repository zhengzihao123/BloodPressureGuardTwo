package test.jiyun.com.bloodpressureguard.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import test.jiyun.com.bloodpressureguard.R;

/**
 * Created by zhangzl
 * 描述:
 * 日期: 2017/2/23 0023.
 */

public class GifRfreshHeader extends LinearLayout {

    private LinearLayout mContainer;

    public GifRfreshHeader(Context context) {
        super(context);
        initView();
    }

    public GifRfreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public GifRfreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        // 初始情况，设置下拉刷新view高度为0
        mContainer = (LinearLayout) LayoutInflater.from(getContext()).inflate(
                R.layout.gif_refresh_header, null);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 0);
        this.setLayoutParams(lp);
        this.setPadding(0, 0, 0, 0);
        addView(mContainer, new LayoutParams(LayoutParams.MATCH_PARENT, 0));
    }

    public void refreshComplete() {
    }

    public void reset() {
    }
}
