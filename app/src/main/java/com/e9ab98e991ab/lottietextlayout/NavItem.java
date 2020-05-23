package com.e9ab98e991ab.lottietextlayout;

import androidx.annotation.ColorRes;
import androidx.annotation.StringRes;

/**
 * @author gaoxin 2019/12/18 上午 11:46
 * @version V1.0.0
 * @name NavItem
 * @mail godfeer@aliyun.com
 * @description  TODO
 */
public class NavItem {
    private int titleResId;
    private int iconResId;
    private int textColor;
    private int selectedTextColor;

    public NavItem(@StringRes int titleResId, int iconResId, @ColorRes int textColor ) {
        this.titleResId = titleResId;
        this.iconResId = iconResId;
        this.textColor = textColor;
    }
    public NavItem(@StringRes int titleResId, int iconResId, @ColorRes int textColor , @ColorRes int selectedTextColor ) {
        this.titleResId = titleResId;
        this.iconResId = iconResId;
        this.textColor = textColor;
        this.selectedTextColor = selectedTextColor;
    }
    //获取标题资源ID
    int getTitleResId() {
        return titleResId;
    }

    //获取图标资源ID
    int getIconResId() {
        return iconResId;
    }
    //获取当前资源值
    int getTextColor() {
        return textColor;
    }
    //获取默认选中颜色
    int getSelectedTextColor(){return selectedTextColor;}
}
