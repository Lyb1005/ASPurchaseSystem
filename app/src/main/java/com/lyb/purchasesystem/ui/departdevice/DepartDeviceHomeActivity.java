package com.lyb.purchasesystem.ui.departdevice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;

import androidx.annotation.Nullable;

import com.lyb.purchasesystem.R;
import com.lyb.purchasesystem.adapter.departdevice.DepartDeviceListAdapter;
import com.lyb.purchasesystem.bean.departdevice.DepartDeviceBean;
import com.lyb.purchasesystem.consta.Constants;
import com.lysoft.baseproject.activity.BaseUIListActivity;
import com.lysoft.baseproject.imp.BaseCallBack;
import com.lysoft.baseproject.imp.LoadStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * ASPurchaseSystem
 * 类描述：部门设备首页
 * 类传参：
 *
 * @Author： create by Lyb on 2020-05-25 16:12
 */
public class DepartDeviceHomeActivity extends BaseUIListActivity<DepartDeviceBean> {
    private List<DepartDeviceBean> departDeviceBeans;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View topView = View.inflate(this, R.layout.item_depart_home_top, null);
        contentView().addView(topView, 1);
        topViewManager().titleTextView().setText("部门设备");
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    @Override
    protected void getListData(BaseCallBack callBack) {
        departDeviceBeans = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            List<DepartDeviceBean> childList = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                DepartDeviceBean departDeviceBean = new DepartDeviceBean("喷墨打印器" + j, "SAMSUNG", "560", "12", j + "", null);
                childList.add(departDeviceBean);
            }
            DepartDeviceBean departDeviceBean = new DepartDeviceBean("打印机" + i, "惠普", "1000", "25", i + "", childList);
            departDeviceBeans.add(departDeviceBean);
        }
        callBack.callBack(departDeviceBeans);

    }

    @Override
    protected BaseAdapter instanceAdapter(List list) {
        return new DepartDeviceListAdapter(this, list, true);
    }

    @Override
    protected void itemClickListener(int position) {
        Intent intent = new Intent(getPageContext(), DepartDeviceSecondActivity.class);
        DepartDeviceBean departDeviceBean = getPageListData().get(position);
        intent.putExtra("model", departDeviceBean);
        startActivity(intent);
    }

    @Override
    protected int getPageSize() {
        return Constants.PAGE_SIZE;
    }

}
