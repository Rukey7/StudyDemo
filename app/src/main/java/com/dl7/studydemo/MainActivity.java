package com.dl7.studydemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dl7.androidlib.activity.BaseActivity;
import com.dl7.studydemo.activity.AnnotationActivity;
import com.dl7.studydemo.activity.ReflectionActivity;
import com.example.annotation.Test;
import com.orhanobut.logger.AndroidLogTool;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Test("haha")
public class MainActivity extends BaseActivity {
    @Bind(R.id.btn_start_act)
    Button btnStartAct;
    @Bind(R.id.btn_annotation)
    Button mBtnAnnotation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Logger.init("TAG").hideThreadInfo().logTool(new AndroidLogTool());
    }

    @OnClick({R.id.btn_start_act, R.id.btn_annotation})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_act:
                startActivity(new Intent(MainActivity.this, ReflectionActivity.class));
                break;
            case R.id.btn_annotation:
                startActivity(new Intent(MainActivity.this, AnnotationActivity.class));
                break;
        }
    }
}
