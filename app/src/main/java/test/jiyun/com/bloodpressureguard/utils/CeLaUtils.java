package test.jiyun.com.bloodpressureguard.utils;

import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;


/**
 * Created by 韩志军 on 2017/5/15.
 */

public class CeLaUtils {

    public static void slideAnim(DrawerLayout drawerLayout, View drawerView, float slideOffset) {
        View contentView = drawerLayout.getChildAt(0);
        Log.d("CheLaActivity", "slideOffset——————:" + slideOffset);
        // slideOffset表示菜单项滑出来的比例，打开菜单时取值为0->1,关闭菜单时取值为1->0
        float scale = 1 - slideOffset;
        float rightScale = 0.8f + scale * 0.2f;
        float leftScale = 1 - 0.3f * scale;

        ViewHelper.setScaleX(drawerView, leftScale);
        ViewHelper.setScaleY(drawerView, leftScale);
        ViewHelper.setAlpha(drawerView, 0.6f + 0.4f * (1 - scale));
        ViewHelper.setTranslationX(contentView, drawerView.getMeasuredWidth()
                * (1 - scale));
        ViewHelper.setPivotX(contentView, 0);
        ViewHelper.setPivotY(contentView, contentView.getMeasuredHeight() / 2);
        contentView.invalidate();
        ViewHelper.setScaleX(contentView, rightScale);
        ViewHelper.setScaleY(contentView, rightScale);
    }
}
