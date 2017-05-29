package com.adolf.zhl_actionbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by adolf on 2017/5/28.
 */

public class BaseActivity extends Activity {
    private FrameLayout mRootView;
    private ActionBarView mActionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        initView();
    }

    private void initView(){
        mRootView = (FrameLayout) findViewById(R.id.fl_content);
        mActionBar = (ActionBarView) findViewById(R.id.view_action_bar);
    }

    public ActionBarView getCusActionBar(){
        return mActionBar;
    }


    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID,null);
        mRootView.addView(view);
    }
}
