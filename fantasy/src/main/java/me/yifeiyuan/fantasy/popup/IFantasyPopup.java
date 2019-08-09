package me.yifeiyuan.fantasy.popup;

import android.widget.PopupWindow;
/**
 * Created by 程序亦非猿 on 2019-07-03.
 */
interface IFantasyPopup {

    void setAnimationStyle(int animationStyle);

    void setSize(int width, int height);

    void setWidth(int width);

    void setHeight(int height);

    void setDuration(long duration);

    PopupWindow getPopupWindow();

}
