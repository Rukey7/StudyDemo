package com.dl7.studydemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.dl7.androidlib.activity.BaseActivity;
import com.dl7.studydemo.activity.ReflectionActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @Bind(R.id.btn_start_act)
    Button btnStartAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_start_act)
    public void onClick() {
        startActivity(new Intent(MainActivity.this, ReflectionActivity.class));
    }
}
