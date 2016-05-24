package com.dl7.studydemo.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.dl7.androidlib.activity.BaseActivity;
import com.dl7.studydemo.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReflectionActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.content)
    TextView mContent;


    private static int i = 1;
    private String s = "reflection";
    private Object mObject;

    public static void test() {}



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflection);
        ButterKnife.bind(this);

        initToolBar(mToolbar, true, "反射");
        getClassObj();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reflection, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_getClassObj:
                getClassObj();
                return true;
            case R.id.item_getFieldsInfo:
                getFieldsInfo();
                return true;
            case R.id.item_getMethodsInfo:
                getMethodsInfo();
                return true;
            case R.id.item_modifyFieldValue:
                modifyFieldValue();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getClassObj() {
        //1
        Class<?> cls1 = ReflectionActivity.class;
        //2
        ReflectionActivity activity = new ReflectionActivity();
        Class<?> cls2 = activity.getClass();
        //3
        Class<?> cls3 = null;
        try {
            cls3 = Class.forName("com.dl7.studydemo.activity.ReflectionActivity");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("cls1: ").append(cls1).append("\n\n");
        sb.append("cls2: ").append(cls2).append("\n\n");
        sb.append("cls3: ").append(cls3);
        mContent.setText(sb.toString());
        mToolbar.setSubtitle("三种方式获得类对象");
    }

    private void getFieldsInfo() {
        Class<ReflectionActivity> cls = ReflectionActivity.class;
        Field[] fields = cls.getDeclaredFields();
        if (fields == null) {
            return;
        }

        StringBuffer sb = new StringBuffer();
        for (Field field : fields) {
            sb.append(Modifier.toString(field.getModifiers())).append(" ");
            sb.append(field.getType().getSimpleName()).append(" ");
            sb.append(field.getName()).append(";");
            sb.append("\n\n");
        }
        mContent.setText(sb.toString());
        mToolbar.setSubtitle("获得类的所有属性信息");
    }

    private void getMethodsInfo() {
        Class<ReflectionActivity> cls = ReflectionActivity.class;
        Method[] methods = cls.getDeclaredMethods();
        if (methods == null) {
            return;
        }

        StringBuffer sb = new StringBuffer();
        for (Method method : methods) {
            sb.append(Modifier.toString(method.getModifiers())).append(" ");
            sb.append(method.getReturnType().getSimpleName()).append(" ");
            sb.append(method.getName()).append("(");
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes != null) {
                for (int i = 0; i < parameterTypes.length; i++) {
                    Class<?> type = parameterTypes[i];
                    sb.append(type.getSimpleName());
                    if (i < parameterTypes.length - 1) {
                        sb.append(", ");
                    }
                }
            }
            sb.append(")\n\n");
        }
        mContent.setText(sb.toString());
        mToolbar.setSubtitle("获得类的所有方法信息");
    }

    private void modifyFieldValue() {

        Class<ReflectionActivity> cls = ReflectionActivity.class;
        Field[] fields = cls.getDeclaredFields();
        if (fields == null) {
            return;
        }

        StringBuffer sb = new StringBuffer();
        sb.append("获得类的所有属性信息：\n\n");
        for (Field field : fields) {
            sb.append(Modifier.toString(field.getModifiers())).append(" ");
            sb.append(field.getType().getSimpleName()).append(" ");
            sb.append(field.getName()).append(";");
            sb.append("\n\n");
        }

        try {
            Field f = cls.getDeclaredField("i");
            sb.append("属性i的默认值：i = ").append(f.getInt("i")).append("\n");
            f.set("i", 100);
            sb.append("属性i修改后的值：i = ").append(f.getInt("i")).append("\n");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        mContent.setText(sb.toString());
        mToolbar.setSubtitle("获得类的所有属性信息");
    }
}
