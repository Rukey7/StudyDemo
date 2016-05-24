package com.dl7.commonlib.helper;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dl7.commonlib.views.xrecyclerview.DividerItemDecoration;
import com.dl7.commonlib.views.xrecyclerview.ProgressStyle;
import com.dl7.commonlib.views.xrecyclerview.XRecyclerView;


/**
 * Created by long on 2016/3/30.
 * 视图帮助类
 */
public class ViewHelper {
    /**
     * 配置RecyclerView
     * @param view
     */
    public static void initRecyclerView(Context context, RecyclerView view, boolean isDivided) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        view.setHasFixedSize(true);
        view.setLayoutManager(layoutManager);
        view.setItemAnimator(new DefaultItemAnimator());
        if (isDivided) {
            view.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        }
    }

    /**
     * 配置XRecyclerView
     * @param context
     * @param view
     * @param listener
     */
    public static void initXRecyclerView(Context context, XRecyclerView view, boolean isDivided, XRecyclerView.LoadingListener listener) {
        initRecyclerView(context, view, isDivided);

        view.setPullRefreshEnabled(false);
        view.setLoadingMoreEnabled(true);
        view.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        view.setLoadingListener(listener);
    }
}
