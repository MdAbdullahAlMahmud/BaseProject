package com.mkrlabs.dashboard.ui.dashboard.profile

import androidx.fragment.app.viewModels
import com.mkrlabs.common.core.base.BaseFragment
import com.mkrlabs.dashboard.DashboardActivity
import com.mkrlabs.dashboard.databinding.FragmentPprofileBinding
import com.mkrlabs.dashboard.ui.dashboard.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<DashboardViewModel, FragmentPprofileBinding>() {

    override val mViewModel: DashboardViewModel by viewModels()
    override fun getViewBinding(): FragmentPprofileBinding  = FragmentPprofileBinding.inflate(layoutInflater)
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