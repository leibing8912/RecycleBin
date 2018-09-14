package com.yy.mobile.recyclebin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * @className: MainActivity
 * @classDescription: 采用recyclebin类似缓存机制复用view
 * @author: leibing
 * @email: leibing@yy.com
 * @createTime:2018/9/14
 */
public class MainActivity extends AppCompatActivity {
    // TAG
    private final static String TAG = "MainActivity";
    // 布局一索引
    private final int LAYOUT_ONE_INDEX = 0;
    // 布局二索引
    private final int LAYOUT_TWO_INDEX = 1;
    // 布局三索引
    private final int LAYOUT_THREE_INDEX = 2;
    // 子view缓存数组
    private View[] mChildView = new View[3];
    // 布局索引
    private int layoutIndex = LAYOUT_ONE_INDEX;
    // 容器
    private RbFrameLayout mContainer;
    // 各个布局控件
    private TextView oneTv;
    private TextView twoTv;
    private TextView threeTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // findView
        mContainer = (RbFrameLayout) findViewById(R.id.rfly_container);
        // onclick
        findViewById(R.id.btn_rb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutIndex++;
                if (layoutIndex > LAYOUT_THREE_INDEX) {
                    layoutIndex = LAYOUT_ONE_INDEX;
                }
                makeAndAddView(layoutIndex);
            }
        });
        // add default view
        makeAndAddView(layoutIndex);
    }

    /**
     * add child view to parent
     */
    private void makeAndAddView(int index) {
        mContainer.detachAllViewsFromParent();
        View childView = mChildView[index];
        if (childView == null) {
            Log.v(TAG, "#create new view index = " + index);
            switch (index) {
                case LAYOUT_ONE_INDEX:
                    childView = LayoutInflater.from(this).inflate(R.layout.layout_one, null);
                    oneTv = childView.findViewById(R.id.tv_one);
                    oneTv.setText("布局一创建");
                    break;
                case LAYOUT_TWO_INDEX:
                    childView = LayoutInflater.from(this).inflate(R.layout.layout_two, null);
                    twoTv = childView.findViewById(R.id.tv_two);
                    twoTv.setText("布局二创建");
                    break;
                case LAYOUT_THREE_INDEX:
                    childView = LayoutInflater.from(this).inflate(R.layout.layout_three, null);
                    threeTv = childView.findViewById(R.id.tv_three);
                    threeTv.setText("布局三创建");
                    break;
                default:
                    childView = LayoutInflater.from(this).inflate(R.layout.layout_one, null);
                    oneTv = childView.findViewById(R.id.tv_one);
                    oneTv.setText("布局一创建");
                    break;
            }
            mContainer.addView(childView);
            // 缓存view
            mChildView[index] = childView;
        } else {
            Log.v(TAG, "#reuse old view index = " + index + " childView = " + childView);
            switch (index) {
                case LAYOUT_ONE_INDEX:
                    oneTv.setText("布局一复用");
                    break;
                case LAYOUT_TWO_INDEX:
                    twoTv.setText("布局二复用");
                    break;
                case LAYOUT_THREE_INDEX:
                    threeTv.setText("布局三复用");
                    break;
                default:
                    oneTv.setText("布局一复用");
                    break;
            }
            mContainer.attachViewToParent(childView);
        }
    }

    @Override
    protected void onDestroy() {
        mChildView = null;
        super.onDestroy();
    }
}
