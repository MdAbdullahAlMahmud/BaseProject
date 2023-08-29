package com.mkrlabs.dashboard.ui.dashboard.topic.sub_topic

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mkrlabs.common.core.base.BaseFragment
import com.mkrlabs.dashboard.DashboardActivity
import com.mkrlabs.dashboard.DashboardHomeViewModel
import com.mkrlabs.dashboard.data.model.request.SubTopicRequest
import com.mkrlabs.dashboard.data.model.response.SubTopicItem
import com.mkrlabs.dashboard.data.model.response.TopicItem
import com.mkrlabs.dashboard.databinding.FragmentSubTopicBinding
import com.mkrlabs.dashboard.ui.dashboard.adapter.SubTopicAdapter
import com.mkrlabs.dashboard.ui.dashboard.topic.TopicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubTopicFragment : BaseFragment<TopicViewModel, FragmentSubTopicBinding>() {

    override val mViewModel: TopicViewModel by viewModels()
    val sharedViewModel: DashboardHomeViewModel by activityViewModels()

    override fun getViewBinding(): FragmentSubTopicBinding = FragmentSubTopicBinding.inflate(layoutInflater)


    private var subTopicAdapter : SubTopicAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        callOnInit()
        setObserver()
    }
    private fun callOnInit(){
        val  subTopicRequest = SubTopicRequest(sharedViewModel.topicItem?.cid)
        mViewModel.getSubTopicList(subTopicRequest)
    }
    private fun initView(){
        initAdapter()
    }
    private fun initAdapter(){
        subTopicAdapter = SubTopicAdapter(this::subTopicItemClickListener)
        mViewBinding.subTopicRV.adapter = subTopicAdapter
    }
    private fun subTopicItemClickListener(topicItem: SubTopicItem){

    }
    private fun setObserver(){
        mViewModel.subTopicList.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                subTopicAdapter?.submitList(it)
            }
        })
    }
    override fun setDefaultProperties() {
        val  activity = requireActivity()
        if (activity is DashboardActivity){
            activity.setActionBarTitle(sharedViewModel.topicItem?.category_name)
            activity.showBackButton()
            activity.hideBottomNavBar()
        }

    }
}