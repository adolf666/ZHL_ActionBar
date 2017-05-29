package com.adolf.zhl_actionbar;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by adolf on 2017/5/27.
 */

public class ActionBarView extends ViewGroup {
    private LinearLayout mLeftContainerLayout;
    private LinearLayout mRightContainerLayout;
    private TextView mTitleTv;
    private ImageView mLeftBackButton;

    public ActionBarView(Context context) {
        super(context);
        init();
    }

    public ActionBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ActionBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_action_bar,this);
        mLeftContainerLayout = (LinearLayout) view.findViewById(R.id.ll_left_contain);
        mRightContainerLayout = (LinearLayout) view.findViewById(R.id.ll_right_contain);
        mTitleTv = (TextView) view.findViewById(R.id.tv_title);
        initLeftBackButton(view);
    }

    private void initLeftBackButton(View view){
        mLeftBackButton = (ImageView) view.findViewById(R.id.iv_back);
        mLeftBackButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });
    }

    public void resteBackButtonListener(OnClickListener listener){
        if (listener != null){
            mLeftBackButton.setOnClickListener(listener);
        }
    }

    private void hideOrShowBackButton(int show){
        mLeftBackButton.setVisibility((show == View.VISIBLE || show == View.GONE || show == View.INVISIBLE) ? show :View.VISIBLE);
    }

    public void hideBackButton(){
        hideOrShowBackButton(View.GONE);
    }

    public void showBackButton(){
        hideOrShowBackButton(View.VISIBLE);
    }

    private void hideOrShowActionBar(int show){
        setVisibility((show == View.VISIBLE || show == View.GONE || show == View.INVISIBLE) ? show :View.VISIBLE);
    }

    public void hideActionBar(){
        hideOrShowActionBar(View.GONE);
    }

    public void showActionBar(){
        hideOrShowActionBar(View.VISIBLE);
    }

    public void setTitle(String title){
        mTitleTv.setText(title);
    }

    public void addRightButton(String text, Drawable backgroundDrawable,int resId,OnClickListener listener){
        View rightView;
        if (TextUtils.isEmpty(text)){
            ImageView rightIv = new ImageView(getContext());
            rightIv.setImageResource(resId < 0 ? 0 : resId);
            rightIv.setImageDrawable(backgroundDrawable);
            rightView = rightIv;
        }else {
            TextView rightTv = new TextView(getContext());
            rightTv.setGravity(Gravity.CENTER);
            rightTv.setText(text);
            rightView = rightTv;
        }

        rightView.setLayoutParams(initRightLayoutParams());
        setRightActionBarPadding(rightView);
        rightView.setOnClickListener(listener);
        mRightContainerLayout.addView(rightView,mRightContainerLayout.getChildCount());
    }

    public void addRightButton(String text ,OnClickListener listener){
        addRightButton(text,null,0,listener);
    }

    public void addRightButton(Drawable backgroundDrawable,OnClickListener listener){
        addRightButton("",backgroundDrawable,0,listener);
    }

    public void addRightButton(int resId,OnClickListener listener){
        addRightButton("",null,resId,listener);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childView.layout(0,0,childView.getMeasuredWidth(),childView.getMeasuredHeight());
        }
    }

    private LinearLayout.LayoutParams initRightLayoutParams(){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        params.leftMargin = 5;
        params.rightMargin = 5;
        return params;
    }

    private void setRightActionBarPadding(View rightButton){
        rightButton.setPadding(10,0,10,0);
    }
}
