package com.example.user.coalert_front;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class HomeBodyLayout extends LinearLayout {
    {
        initView();
    }

    public HomeBodyLayout(Context context) {
        super(context);
    }

    public HomeBodyLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeBodyLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public HomeBodyLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView() {
        inflate(getContext(), R.layout.layout_home, this);
    }
}
