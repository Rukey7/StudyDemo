package com.dl7.studydemo.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.dl7.androidlib.activity.BaseActivity;
import com.dl7.studydemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReflectionActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.content)
    TextView mContent;


    private int i = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflection);
        ButterKnife.bind(this);

        initViews();
    }

    private void initViews() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("反射");
    }
}
