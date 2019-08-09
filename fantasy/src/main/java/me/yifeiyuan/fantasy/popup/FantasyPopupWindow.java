package me.yifeiyuan.fantasy.popup;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import java.lang.ref.WeakReference;
/**
 * Created by 程序亦非猿 on 2019-07-03.
 */
@RestrictTo(Scope.LIBRARY)
public class FantasyPopupWindow extends PopupWindow {

    private static final float DEFAULT_ALPHA = 0.5f;

    private static final int AUTO_DISMISS = 843;

    private Activity activity;

    private float alpha = DEFAULT_ALPHA;

    /**
     * 展示时间
     */
    private long duration;

    private FHandler handler;

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

        handler = new FHandler(this);
    }

    @Override
    public void showAsDropDown(final View anchor) {
        if (isActivityAlive()) {
            doPreShow();
            super.showAsDropDown(anchor);
        }
    }

    @Override
    public void showAsDropDown(final View anchor, final int xoff, final int yoff) {
        if (isActivityAlive()) {
            doPreShow();
            super.showAsDropDown(anchor, xoff, yoff);
        }
    }

    @Override
    public void showAsDropDown(final View anchor, final int xoff, final int yoff, final int gravity) {
        if (isActivityAlive()) {
            doPreShow();
            super.showAsDropDown(anchor, xoff, yoff, gravity);
        }
    }

    @Override
    public void showAtLocation(final View parent, final int gravity, final int x, final int y) {
        if (isActivityAlive()) {
            doPreShow();
            super.showAtLocation(parent, gravity, x, y);
        }
    }

    private void doPreShow() {
        setWindowAlpha(alpha);
        dismissAfter(duration);
    }

    @Override
    public void dismiss() {
        release();
        super.dismiss();
    }

    private void dismissAfter(long delay) {
        handler.removeMessages(AUTO_DISMISS);
        if (delay >= 0) {
            handler.sendEmptyMessageDelayed(AUTO_DISMISS, delay);
        }
    }

    private void release() {
        handler.removeCallbacksAndMessages(null);
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

    public void setDuration(final long duration) {
        this.duration = duration;
    }

    private static class FHandler extends Handler {

        WeakReference<FantasyPopupWindow> ref;

        FHandler(FantasyPopupWindow pop) {
            ref = new WeakReference<>(pop);
        }

        @Override
        public void handleMessage(final Message msg) {
            super.handleMessage(msg);
            FantasyPopupWindow pop = ref.get();
            if (pop != null) {
                pop.handleMessage(msg);
            }
        }
    }

    private void handleMessage(final Message msg) {
        if (AUTO_DISMISS == msg.what) {
            dismiss();
        }
    }
}
