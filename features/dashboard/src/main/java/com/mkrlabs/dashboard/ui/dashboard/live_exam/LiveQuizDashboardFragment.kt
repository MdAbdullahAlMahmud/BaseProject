package com.mkrlabs.dashboard.ui.dashboard.live_exam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mkrlabs.common.core.base.BaseFragment
import com.mkrlabs.dashboard.DashboardActivity
import com.mkrlabs.dashboard.DashboardHomeViewModel
import com.mkrlabs.dashboard.R
import com.mkrlabs.dashboard.data.model.response.LiveDashboardItem
import com.mkrlabs.dashboard.databinding.FragmentDashboardHomeBinding
import com.mkrlabs.dashboard.databinding.FragmentLiveQuizDashboardBinding
import com.mkrlabs.dashboard.ui.dashboard.DashboardViewModel
import com.mkrlabs.dashboard.ui.dashboard.adapter.LiveDashboardAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LiveQuizDashboardFragment : BaseFragment<DashboardViewModel,FragmentLiveQuizDashboardBinding>(){

    override val mViewModel: DashboardViewModel by viewModels()


    private val sharedViewModel : DashboardHomeViewModel by  activityViewModels()
    override fun getViewBinding(): FragmentLiveQuizDashboardBinding = FragmentLiveQuizDashboardBinding.inflate(layoutInflater)


    private  var liveQuizDashboardAdapter: LiveDashboardAdapter = LiveDashboardAdapter (this::onItemClicked)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialData()
        initView()
        setObserver()
    }

    private fun setObserver(){
        mViewModel.liveQuizTopicList.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let {
                liveQuizDashboardAdapter.submitList(it)
            }
        })
    }

    fun initialData(){
        mViewModel.getLiveQuizTopicListTopicList()
    }

    private fun initView(){
        mViewBinding.rvLiveQuiz.adapter = liveQuizDashboardAdapter
    }

    private fun onItemClicked(item : LiveDashboardItem){

    }
    override fun setDefaultProperties() {
        val activity = requireActivity()
        if (activity is DashboardActivity) {
            activity.setActionBarTitle("লাইভ এক্সাম কোর্স")
            activity.showBackButton()
            activity.hideBottomNavBar()
        }


    }
}