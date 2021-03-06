package com.huahansoft.hhsoftlibrarykit.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.huahansoft.hhsoftlibrarykit.manager.HHSoftDefaultTopViewManager;
import com.huahansoft.hhsoftlibrarykit.manager.HHSoftLoadViewManager;
import com.huahansoft.hhsoftlibrarykit.proxy.HHSoftApplicationInterface;

public abstract class HHSoftUIBaseLoadActivity extends HHSoftBaseActivity {
    private LinearLayout contentView;
    private FrameLayout containerView;
    private HHSoftDefaultTopViewManager topViewManager;
    private HHSoftLoadViewManager loadViewManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = new LinearLayout(getPageContext());
        contentView.setOrientation(LinearLayout.VERTICAL);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        topViewManager = new HHSoftDefaultTopViewManager(this);
        contentView.addView(topViewManager.topView(), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        containerView = new FrameLayout(getPageContext());
        contentView.addView(containerView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        setContentView(contentView);
        loadViewManager = new HHSoftLoadViewManager(initLoadMode() == null ? HHSoftLoadViewManager.LoadMode.PROGRESS : initLoadMode(), containerView, () -> {
            onPageLoad();
        });
    }

    /**
     * 初始化加载模式
     *
     * @return
     */
    protected HHSoftLoadViewManager.LoadMode initLoadMode() {
        if (getApplication() instanceof HHSoftApplication) {
            HHSoftApplication application = (HHSoftApplication) getApplication();
            HHSoftApplicationInterface applicationInfo = application.applicationInfo();
            return applicationInfo.appLoadMode() == null ? HHSoftLoadViewManager.LoadMode.PROGRESS : applicationInfo.appLoadMode();
        }
        return HHSoftLoadViewManager.LoadMode.PROGRESS;
    }

    /**
     * 页面加载方法
     */
    protected abstract void onPageLoad();

    /**
     * 头部管理器
     *
     * @return
     */
    protected HHSoftDefaultTopViewManager topViewManager() {
        return topViewManager;
    }

    /**
     * 加载管理器
     *
     * @return
     */
    protected HHSoftLoadViewManager loadViewManager() {
        return loadViewManager;
    }

    /**
     * 父布局，包含头部
     *
     * @return
     */
    protected LinearLayout contentView() {
        return contentView;
    }

    /**
     * 内容布局，不包含头部
     *
     * @return
     */
    protected FrameLayout containerView() {
        return containerView;
    }
}
