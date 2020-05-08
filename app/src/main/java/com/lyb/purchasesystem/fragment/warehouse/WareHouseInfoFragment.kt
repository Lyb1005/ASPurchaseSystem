package com.lyb.purchasesystem.fragment.warehouse

import android.content.Intent
import android.view.Gravity
import android.view.View
import android.widget.BaseAdapter
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.hjq.toast.ToastUtils
import com.kongzue.dialog.interfaces.OnDialogButtonClickListener
import com.kongzue.dialog.v3.MessageDialog
import com.lyb.purchasesystem.R
import com.lyb.purchasesystem.adapter.purchase.PurchaseListAdapter
import com.lyb.purchasesystem.bean.purchase.PurchaseBean
import com.lyb.purchasesystem.consta.Constants
import com.lyb.purchasesystem.ui.purchase.PurchaseInfoActivity
import com.lysoft.baseproject.imp.AdapterViewClickListener
import com.lysoft.baseproject.imp.BaseCallBack
import com.lysoft.baseproject.imp.LoadStatus

/**
 * ASPurchaseSystem
 * 类描述： 采购列表
 * 类传参：
 * @Author： create by Lyb on 2020-04-24 15:45
 */
class WareHouseInfoFragment(var appCompatActivity: AppCompatActivity) : AdapterViewClickListener, WareHouseListFragment<PurchaseBean>() {
    override fun onCreate() {
        super.onCreate()
        topViewManager().topView().visibility = View.GONE
        loadViewManager().changeLoadState(LoadStatus.LOADING)
        val classRadioButton = containerView().findViewById<RadioButton>(R.id.rb_filter)
        val drawerLayout = containerView().findViewById<DrawerLayout>(R.id.draw_layout)
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        classRadioButton.setOnClickListener { v ->
            drawerLayout.openDrawer(Gravity.RIGHT)
//            drawerLayout.setScrimColor(ContextCompat.getColor(pageContext,R.color.text_gray))
        }

    }

    override fun getListData(callBack: BaseCallBack) {
        var purchaseList = mutableListOf<PurchaseBean>()
        for (a in 0..30) {
            var state = "";
            if (a < 3) {
                state = a.toString()
            } else {
                state = "2"
            }
            purchaseList.add(PurchaseBean("电脑", "i7 16G", "9000", "10台", "2020-05-06", "2020-04-05", "我们需要几台电脑办公，请尽快采买，谢谢！！！", "张三", state, "李四", "2020-05-01", "你这个建议不错", "人资部"))
        }
        callBack.callBack(purchaseList)
    }

    override fun itemClickListener(position: Int) {
        val intent = Intent(pageContext, PurchaseInfoActivity::class.java)
        intent.putExtra("model", pageListData[position])
        startActivity(intent)
    }

    override fun adapterViewClick(position: Int, view: View?) {
        if (pageListData[position].purchaseDealState.equals("0")) {
            MessageDialog.show(appCompatActivity, "提示", "确认要删除该条采购申请吗？", "确定", "取消").setOnOkButtonClickListener { baseDialog, v ->
                baseDialog.doDismiss()
                true
            }.onCancelButtonClickListener = OnDialogButtonClickListener({ baseDialog, v ->
                baseDialog.doDismiss()
                true
            })
        } else {
            ToastUtils.show("已处理的申请不能删除")
        }

    }

    override fun getPageSize(): Int {
        return Constants.PAGE_SIZE
    }

    override fun instanceAdapter(list: MutableList<PurchaseBean>): BaseAdapter {
        return PurchaseListAdapter(pageContext, list, this, false)

    }

}