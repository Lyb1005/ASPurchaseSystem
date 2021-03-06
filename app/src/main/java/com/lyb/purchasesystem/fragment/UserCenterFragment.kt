package com.lyb.purchasesystem.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.hjq.toast.ToastUtils
import com.kongzue.dialog.interfaces.OnDialogButtonClickListener
import com.kongzue.dialog.v3.MessageDialog
import com.linchaolong.android.imagepicker.ImagePicker
import com.lyb.purchasesystem.R
import com.lyb.purchasesystem.ui.clapatwill.MineClapAtWillListActivity
import com.lyb.purchasesystem.ui.clapatwill.WaitMineDealClapAtWillListActivity
import com.lyb.purchasesystem.ui.classroom.ClassRoomAppointmentActivity
import com.lyb.purchasesystem.ui.suggestions.MineSuggestionsActivity
import com.lyb.purchasesystem.ui.user.LoginActivity
import com.lyb.purchasesystem.ui.user.MsgListActivity
import com.lyb.purchasesystem.ui.user.UserEditPwdActivity
import com.lysoft.baseproject.activity.BaseUIFragment
import com.lysoft.baseproject.clipview.ClipImageActivity
import com.lysoft.baseproject.utils.FileUtils
import kotlinx.android.synthetic.main.frag_user_center.*
import kotlinx.android.synthetic.main.frag_user_center.view.*


/**
 * ASPurchaseSystem
 * 类描述：
 * 类传参：
 *
 * @Author： create by Lyb on 2020-04-10 14:39
 */
class UserCenterFragment(val parentActivity: AppCompatActivity) : View.OnClickListener, BaseUIFragment() {
    lateinit var imagePicker: ImagePicker


    override fun onCreate() {
        topViewManager().backTextView().visibility = View.GONE
        topViewManager().titleTextView().text = "个人中心"
        val view = View.inflate(pageContext, R.layout.frag_user_center, null)
        containerView().addView(view)
        containerView().img_head.setOnClickListener(this)
        containerView().tv_name.setOnClickListener(this)
        containerView().tv_user_camera.setOnClickListener(this)
        containerView().tv_user_wait_deal.setOnClickListener(this)
//        var userInfo = UserInfoUtils.getUserInfo(context)
//        containerView().tv_name.text = (userInfo?.username)
        containerView().tv_name.text = "张三"
        containerView().tv_logout.setOnClickListener(this)
        containerView().tv_user_center_edit_pwd.setOnClickListener(this)
        containerView().tv_user_center_msg.setOnClickListener(this)
        containerView().tv_user_suggestion.setOnClickListener(this)
        containerView().tv_user_appointment.setOnClickListener(this)
        val versionName = parentActivity.packageManager.getPackageInfo(parentActivity.packageName, 0).versionName
        val versionNum = "v " + versionName;
        containerView().tv_soft_version.text = versionNum


    }


    override fun onClick(v: View?) {
        when (v?.id) {
            img_head.id -> {
                imagePicker = ImagePicker()
                imagePicker.setTitle("设置头像")
                imagePicker.setCropImage(false)
                imagePicker.startChooser(this, object : ImagePicker.Callback() {
                    override fun onPickImage(imageUri: Uri?) {
                        Log.i("Lyb", "onPickImage===")
                        ClipImageActivity.goToClipActivity(this@UserCenterFragment, imageUri)
                    }
                })
            }
            tv_name.id -> (ToastUtils.show("名字"))
            //我的预约
            tv_user_appointment.id -> {
                val intent = Intent(pageContext, ClassRoomAppointmentActivity::class.java)
                intent.putExtra("type", 2)
                startActivity(intent)
            }
            //我的意见
            tv_user_suggestion.id -> startActivity(Intent(pageContext, MineSuggestionsActivity::class.java))
            //我的随手拍
            tv_user_camera.id -> startActivity(Intent(pageContext, MineClapAtWillListActivity::class.java))
            //待我处理
            tv_user_wait_deal.id -> startActivity(Intent(pageContext, WaitMineDealClapAtWillListActivity::class.java))
            //系统消息
            tv_user_center_msg.id -> startActivity(Intent(pageContext, MsgListActivity::class.java))
            //修改密码
            tv_user_center_edit_pwd.id -> startActivity(Intent(pageContext, UserEditPwdActivity::class.java))
            //退出登录
            tv_logout.id -> outLogin()


        }
    }

    /**
     * 退出登录
     */
    private fun outLogin() {
        MessageDialog.show(parentActivity, "提示", "确认要退出登录吗？", "确定", "取消").setOnOkButtonClickListener({ baseDialog, v ->
            baseDialog.doDismiss()
//            UserInfoUtils.loginOut(pageContext)
            startActivity(Intent(pageContext, LoginActivity::class.java))
            parentActivity.finish()
            true
        }).onCancelButtonClickListener = OnDialogButtonClickListener { baseDialog, v ->
            baseDialog.doDismiss()
            true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                200 -> imagePicker.onActivityResult(this, requestCode, resultCode, data)
                //拿裁剪后的图片
                ClipImageActivity.REQ_CLIP_AVATAR -> {
                    val data1 = data?.data
                    val realFilePathFromUri = FileUtils.getRealFilePathFromUri(pageContext, data1)
                    Log.i("Lybb", "realFilePathFromUri==" + realFilePathFromUri)
                    Glide.with(this).load(data1).error(R.drawable.default_head_circle).centerCrop().circleCrop().into(containerView().img_head)
                }
            }
        }
    }
}

