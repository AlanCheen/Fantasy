package me.yifeiyuan.fantasy.popup;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
/**
 * Created by 程序亦非猿 on 2019-07-03.
 */
public class FantasyPopupWindow extends PopupWindow {

    private Activity activity;

    private static final float DEFAULT_ALPHA = 0.5f;

    private float alpha = DEFAULT_ALPHA;

    public FantasyPopupWindow(final Activity activity) {
        super(activity);
        this.activity = activity;
        init();
    }

    private void init() {
        setFocusable(true);
        setOutsideTouchable(true);
        // 6.0 以下有个 bug就是点击 window 区域外不会消失的 bug,
        // 必须要设置一个背景，并且并不会影响你的背景
        ColorDrawable colorDrawable = new ColorDrawable(0x00000000);
        setBackgroundDrawable(colorDrawable);
    }

    @Override
    public void showAsDropDown(final View anchor) {
        if (isActivityAlive()) {
            setWindowAlpha(alpha);
            super.showAsDropDown(anchor);
        }
    }

    @Override
    public void showAsDropDown(final View anchor, final int xoff, final int yoff) {
        if (isActivityAlive()) {
            setWindowAlpha(alpha);
            super.showAsDropDown(anchor, xoff, yoff);
        }
    }

    @Override
    public void showAsDropDown(final View anchor, final int xoff, final int yoff, final int gravity) {
        if (isActivityAlive()) {
            setWindowAlpha(alpha);
            super.showAsDropDown(anchor, xoff, yoff, gravity);
        }
    }

    @Override
    public void showAtLocation(final View parent, final int gravity, final int x, final int y) {
        if (isActivityAlive()) {
            setWindowAlpha(alpha);
            super.showAtLocation(parent, gravity, x, y);
        }
    }

    private boolean isActivityAlive() {
        if (null == activity || activity.isFinishing() || activity.isDestroyed()) {
            return false;
        }
        return true;
    }

    private void setWindowAlpha(float alpha) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = alpha;
        if (alpha == 1) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        } else {
            //Bug fix for HuaWei
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
        window.setAttributes(lp);
    }
}
