package com.yy.mobile.recyclebin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * @className: RbFrameLayout
 * @classDescription: custom frameLayout,use recyclebin to cache view
 * @author: leibing
 * @email: leibing@yy.com
 * @createTime:2018/9/14
 */
public class RbFrameLayout extends FrameLayout {
    public RbFrameLayout(@NonNull Context context) {
        super(context);
    }

    public RbFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RbFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * detach all views from parent
     */
    public void detachAllViewsFromParent() {
        super.detachAllViewsFromParent();
    }


    /**
     * attach view to parent
     * @param child
     */
    public void attachViewToParent(View child) {
        super.attachViewToParent(child, 0, getLayoutParams());
    }
}
