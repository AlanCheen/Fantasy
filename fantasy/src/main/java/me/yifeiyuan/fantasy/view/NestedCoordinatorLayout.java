package me.yifeiyuan.fantasy.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.NestedScrollingChild2;
import android.support.v4.view.NestedScrollingChildHelper;
import android.util.AttributeSet;
import android.view.View;
/**
 * Created by 程序亦非猿 on 2019-09-29.
 */
public class NestedCoordinatorLayout extends CoordinatorLayout implements NestedScrollingChild2 {

    private NestedScrollingChildHelper mChildHelper;

    public NestedCoordinatorLayout(Context context) {
        super(context);
        this.init();
    }

    public NestedCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public NestedCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    protected void init() {
        this.mChildHelper = new NestedScrollingChildHelper(this);
        this.setNestedScrollingEnabled(true);
        this.setMotionEventSplittingEnabled(false);
    }

    public boolean isNestedScrollingEnabled() {
        return this.mChildHelper.isNestedScrollingEnabled();
    }

    public void setNestedScrollingEnabled(boolean enabled) {
        this.mChildHelper.setNestedScrollingEnabled(enabled);
    }

    public boolean hasNestedScrollingParent() {
        return this.mChildHelper.hasNestedScrollingParent();
    }

    public boolean hasNestedScrollingParent(int type) {
        return this.mChildHelper.hasNestedScrollingParent(type);
    }

    public boolean onStartNestedScroll(View child, View target, int axes, int type) {
        boolean superResult = super.onStartNestedScroll(child, target, axes, type);
        return this.startNestedScroll(axes, type) || superResult;
    }

    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        boolean superResult = super.onStartNestedScroll(child, target, nestedScrollAxes);
        return this.startNestedScroll(nestedScrollAxes) || superResult;
    }

    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed, int type) {
        this.dispatchNestedPreScroll(dx, dy, consumed, (int[]) null);
        if (consumed[1] == 0) {
            super.onNestedPreScroll(target, dx, dy, consumed, type);
        }

    }

    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        this.dispatchNestedPreScroll(dx, dy, consumed, (int[]) null);
        if (consumed[1] == 0) {
            super.onNestedPreScroll(target, dx, dy, consumed);
        }

    }

    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
        this.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, (int[]) null, type);
    }

    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        this.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, (int[]) null);
    }

    public void onStopNestedScroll(View target, int type) {
        super.onStopNestedScroll(target, type);
        this.stopNestedScroll(type);
    }

    public void onStopNestedScroll(View target) {
        super.onStopNestedScroll(target);
        this.stopNestedScroll();
    }

    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        boolean superResult = super.onNestedPreFling(target, velocityX, velocityY);
        return this.dispatchNestedPreFling(velocityX, velocityY) || superResult;
    }

    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        boolean superResult = super.onNestedFling(target, velocityX, velocityY, consumed);
        return this.dispatchNestedFling(velocityX, velocityY, consumed) || superResult;
    }

    public boolean startNestedScroll(int axes, int type) {
        return this.mChildHelper.startNestedScroll(axes, type);
    }

    public boolean startNestedScroll(int axes) {
        return this.mChildHelper.startNestedScroll(axes);
    }

    public void stopNestedScroll() {
        this.mChildHelper.stopNestedScroll();
    }

    public void stopNestedScroll(int type) {
        this.mChildHelper.stopNestedScroll(type);
    }

    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type) {
        return this.mChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type);
    }

    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow) {
        return this.mChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow) {
        return this.mChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow, int type) {
        return this.mChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type);
    }

    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return this.mChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }

    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return this.mChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    public boolean canScrollVertically(int direction) {
        if (direction < 0 && this.getChildCount() != 0) {
            View first = this.getChildAt(0);
            return first.getTop() < 0 ? true : first.canScrollVertically(direction);
        } else {
            return super.canScrollVertically(direction);
        }
    }
}