package com.mkrlabs.dashboard.ui.dashboard.nav_drawer.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mkrlabs.common.core.base.BaseFragment
import com.mkrlabs.dashboard.DashboardActivity
import com.mkrlabs.dashboard.R
import com.mkrlabs.dashboard.data.model.response.NotificationItem
import com.mkrlabs.dashboard.databinding.FragmentNotificationBinding
import com.mkrlabs.dashboard.ui.dashboard.DashboardViewModel
import com.mkrlabs.dashboard.ui.dashboard.nav_drawer.notification.adapter.NotificationAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NotificationFragment : BaseFragment<DashboardViewModel, FragmentNotificationBinding>() {

    override fun getViewBinding(): FragmentNotificationBinding =FragmentNotificationBinding.inflate(layoutInflater)
    override val mViewModel: DashboardViewModel by  viewModels()


    private val notificationAdapter : NotificationAdapter = NotificationAdapter(this::onItemClick)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callOnInit()
        setObserver()
    }

    fun callOnInit(){
        mViewBinding.notificationRV.adapter = notificationAdapter
        mViewModel.requestForNotificationList()
    }
    fun setObserver(){
        mViewModel.notificationList.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let {
                notificationAdapter.submitList(it)
            }
        })
    }

    private fun onItemClick(item : NotificationItem){

    }

    override fun setDefaultProperties() {
        val activity = requireActivity()
        if (activity is DashboardActivity){
            activity.setActionBarTitle("Notification")
            activity.hideDrawerMenu()
            activity.showBackButton()
            activity.hideBottomNavBar()
        }
    }
}