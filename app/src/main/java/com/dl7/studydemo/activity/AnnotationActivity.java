package com.dl7.studydemo.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.dl7.androidlib.activity.BaseActivity;
import com.dl7.studydemo.R;
import com.dl7.studydemo.annotatation.AnnotationUtil;
import com.dl7.studydemo.annotatation.FieldInfo;
import com.dl7.studydemo.annotatation.MethodInfo;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AnnotationActivity extends BaseActivity {

    @Bind(R.id.tv_content)
    TextView mTvContent;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
        ButterKnife.bind(this);

        initToolBar(mToolbar, true, "注解");
        testRuntimeAnnotation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_annotation, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_testClass:

                return true;
            case R.id.item_testRunTime:
                testRuntimeAnnotation();
                return true;
            case R.id.item_testSource:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void testRuntimeAnnotation() {
        Class<?> cls = AnnotationUtil.class;
        Method[] methods = cls.getMethods();
        StringBuffer sb = new StringBuffer();
        for (Method method : methods) {
            MethodInfo methodInfo = method.getAnnotation(MethodInfo.class);
            if (methodInfo != null) {
                sb.append(Modifier.toString(method.getModifiers())).append(" ")
                        .append(method.getReturnType().getSimpleName()).append(" ")
                        .append(method.getName()).append("\n");
                sb.append("author: ").append(methodInfo.author()).append("\n");
                sb.append("data: ").append(methodInfo.data()).append("\n");
                sb.append("version: ").append(methodInfo.version()).append("\n");
            }
        }

        Field[] fields = cls.getFields();
        sb.append("\n");
        for (Field field : fields) {
            FieldInfo fieldInfo = field.getAnnotation(FieldInfo.class);
            Logger.w("Field" + field.getName());
            if (fieldInfo != null) {
                sb.append(Modifier.toString(field.getModifiers())).append(" ")
                        .append(field.getType().getSimpleName()).append(" ")
                        .append(field.getName()).append("\n");
                sb.append("value: ").append(Arrays.toString(fieldInfo.value())).append("\n");
            }
        }

        mTvContent.setText(sb.toString());
        mToolbar.setSubtitle("运行时注解");
    }

}
