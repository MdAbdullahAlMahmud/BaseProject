package com.mkrlabs.dashboard.ui.dashboard.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mkrlabs.common.core.base.BaseFragment
import com.mkrlabs.common.core.base.utils.AppConstant
import com.mkrlabs.dashboard.DashboardActivity
import com.mkrlabs.dashboard.data.model.request.UserRequest
import com.mkrlabs.dashboard.data.model.response.User
import com.mkrlabs.dashboard.databinding.FragmentPprofileBinding
import com.mkrlabs.dashboard.ui.dashboard.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<DashboardViewModel, FragmentPprofileBinding>() {

    override val mViewModel: DashboardViewModel by viewModels()
    override fun getViewBinding(): FragmentPprofileBinding  = FragmentPprofileBinding.inflate(layoutInflater)

    private var userInfo : User? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setObserver()
        setOnClickListener()
    }

    fun setObserver(){
        mViewModel.userInfo.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let {
                setUserPofileInfo(it)
                userInfo = it

            }
        })

        mViewModel.userUpdate.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let {
                showCustomDialog(message = it.msg.toString())
            }
        })
    }

    fun setUserPofileInfo(user: User){
        mViewBinding.apply {
            userNameTv.setText(user.userName.toString())
            userEmailTv.setText(user.userEmail.toString())
            userMobileTv.setText(user.userPhone.toString())
        }

    }
    fun initView(){
        mViewModel.getUserInfo(getStringPreferenceData(AppConstant.USER_ID))
    }
    fun setOnClickListener(){
        mViewBinding.containerBtnSave.setOnClickListener {
            checkAnythingForUpdate()

        }
    }
    fun checkAnythingForUpdate(){
        var isUpdateAble = false
        val request = UserRequest()
        request.id = getStringPreferenceData(AppConstant.USER_ID)
        request.name = mViewBinding.userNameTv.text.toString()
        request.phone = mViewBinding.userMobileTv.text.toString()
        mViewModel.requestForUserInfoUpdate(request)
    }
    override fun setDefaultProperties() {
        val activity = requireActivity()
        if (activity is DashboardActivity){
            activity.showBackButton()
            activity.hideBottomNavBar()
            activity.hideDrawerMenu()
            activity.setActionBarTitle("Profile")
        }

    }
}