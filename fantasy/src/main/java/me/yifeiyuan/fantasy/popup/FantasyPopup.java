package me.yifeiyuan.fantasy.popup;

import android.app.Activity;
import android.widget.PopupWindow;
/**
 * Created by mingjue on 2019-07-03.
 */
public class FantasyPopup implements IFantasyPopup {

    private FantasyPopupWindow popup;
    private Activity activity;

    private FantasyPopup(Activity activity) {
        this.popup = new FantasyPopupWindow(activity);
        this.activity = activity;
    }

    public static FantasyPopup create(Activity activity) {
        return new FantasyPopup(activity);
    }

    @Override
    public void setAnimationStyle(final int animationStyle) {
        popup.setAnimationStyle(animationStyle);
    }

    @Override
    public void setSize(final int width, final int height) {
        popup.setWidth(width);
        popup.setHeight(height);
    }

    @Override
    public void setWidth(final int width) {
        popup.setWidth(width);
    }

    @Override
    public void setHeight(final int height) {
        popup.setHeight(height);
    }

    @Override
    public void setDuration(final long duration) {
        popup.setDuration(duration);
    }

    @Override
    public PopupWindow getPopupWindow() {
        return popup;
    }

    interface LifecycleCallback {

        void onPopupCreated(PopupWindow popupWindow);
    }
}
