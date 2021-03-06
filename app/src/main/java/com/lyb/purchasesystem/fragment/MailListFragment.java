package com.lyb.purchasesystem.fragment;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.hjq.toast.ToastUtils;
import com.lyb.purchasesystem.R;
import com.lyb.purchasesystem.adapter.CityAdapter;
import com.lyb.purchasesystem.bean.CityBean;
import com.lysoft.baseproject.activity.BaseUIFragment;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * 介绍：高仿微信通讯录界面
 * 头部不是HeaderView 因为头部也需要快速导航，"↑"
 * 作者：zhangxutong
 * 邮箱：mcxtzhang@163.com
 * 主页：http://blog.csdn.net/zxt0601
 * 时间： 2016/11/7.
 */
public class MailListFragment extends BaseUIFragment {
    private static final String TAG = "zxt";
    private static final String INDEX_STRING_TOP = "↑";
    private RecyclerView mRv;
    private CityAdapter mAdapter;
    private LinearLayoutManager mManager;
    private List<CityBean> mDatas = new ArrayList<>();

    private SuspensionDecoration mDecoration;

    /**
     * 右侧边栏导航区域
     */
    private IndexBar mIndexBar;

    /**
     * 显示指示器DialogText
     */
    private TextView mTvSideBarHint;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate() {
        View view = View.inflate(getPageContext(), R.layout.activity_wechat, null);
        containerView().addView(view);
        topViewManager().backTextView().setVisibility(View.GONE);
        topViewManager().titleTextView().setText("通讯录");
        mRv = view.findViewById(R.id.rv);
        swipeRefreshLayout = view.findViewById(R.id.sw_phone);
        mRv.setLayoutManager(mManager = new LinearLayoutManager(getPageContext()));

        mAdapter = new CityAdapter(getPageContext(), mDatas);
        mRv.setAdapter(mAdapter);
        mRv.addItemDecoration(mDecoration = new SuspensionDecoration(getPageContext(), mDatas));
        //如果add两个，那么按照先后顺序，依次渲染。
        //        mRv.addItemDecoration(new com.lyb.purchasesystem.decoration.DividerItemDecoration(getPageContext(), DividerItemDecoration.VERTICAL_LIST));
        //使用indexBar
        mTvSideBarHint = view.findViewById(R.id.tvSideBarHint);//HintTextView
        mIndexBar = view.findViewById(R.id.indexBar);//IndexBar

        //indexbar初始化
        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager);//设置RecyclerView的LayoutManager

        //模拟线上加载数据
        initDatas(getResources().getStringArray(R.array.provinces));
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getPageContext(), R.color.main_color));
        swipeRefreshLayout.setOnRefreshListener(() -> {
            //
            new CountDownTimer(1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    ToastUtils.show("刷新成功");
                    swipeRefreshLayout.setRefreshing(false);
                }
            }.start();
        });
    }


    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private void initDatas(final String[] data) {
        //延迟两秒 模拟加载数据中....
        getActivity().getWindow().getDecorView().postDelayed((Runnable) () -> {
            mDatas = new ArrayList<>();
            //微信的头部 也是可以右侧IndexBar导航索引的，
            // 但是它不需要被ItemDecoration设一个标题titile
//            mDatas.add((CityBean) new CityBean("新的朋友").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
//            mDatas.add((CityBean) new CityBean("群聊").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
//            mDatas.add((CityBean) new CityBean("标签").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
//            mDatas.add((CityBean) new CityBean("公众号").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
            for (int i = 0; i < data.length; i++) {
                CityBean cityBean = new CityBean();
                cityBean.setCity(data[i]);//设置城市名称
                mDatas.add(cityBean);
            }
            mAdapter.setDatas(mDatas);
            mAdapter.notifyDataSetChanged();

            mIndexBar.setmSourceDatas(mDatas)//设置数据
                    .invalidate();
            mDecoration.setmDatas(mDatas);
        }, 500);
    }

    /**
     * 更新数据源
     *
     * @param view
     */
    public void updateDatas(View view) {
        for (int i = 0; i < 5; i++) {
            mDatas.add(new CityBean("东京"));
            mDatas.add(new CityBean("大阪"));
        }

        mIndexBar.setmSourceDatas(mDatas)
                .invalidate();
        mAdapter.notifyDataSetChanged();
    }


}
