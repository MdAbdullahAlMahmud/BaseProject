package com.mkrlabs.dashboard.ui.dashboard.topic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mkrlabs.common.core.base.BaseFragment
import com.mkrlabs.dashboard.DashboardActivity
import com.mkrlabs.dashboard.DashboardHomeViewModel
import com.mkrlabs.dashboard.R
import com.mkrlabs.dashboard.data.model.response.SubTopicItem
import com.mkrlabs.dashboard.data.model.response.TopicItem
import com.mkrlabs.dashboard.databinding.FragmentTopicBinding
import com.mkrlabs.dashboard.ui.dashboard.adapter.FeatureListAdapter
import com.mkrlabs.dashboard.ui.dashboard.adapter.TopicAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopicFragment : BaseFragment<TopicViewModel,FragmentTopicBinding>() {

    override val mViewModel: TopicViewModel by viewModels()
    private val  sharedViewModel : DashboardHomeViewModel by activityViewModels()

    override fun getViewBinding(): FragmentTopicBinding  = FragmentTopicBinding.inflate(layoutInflater)


    private var topicAdapter : TopicAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        callOnInit()
        setObserver()
    }
    private fun initView(){
        initAdapter()

    }

    private fun callOnInit(){
        if (sharedViewModel.isPDF == true){
            mViewModel.getPDFTopicList(sharedViewModel.topicId.toString())
        }else{
            mViewModel.getTopicList(sharedViewModel.topicId.toString())

        }
    }
    private fun initAdapter(){
        topicAdapter = TopicAdapter (this::topicItemClickListener)
        mViewBinding.topicRV.adapter = topicAdapter
    }
    private fun topicItemClickListener(topicItem: TopicItem){
        sharedViewModel.topicItem = topicItem

        if(sharedViewModel.step == 3 ){
            findNavController().navigate(R.id.action_topicFragment_to_subTopicFragment)
        }/*else if (sharedViewModel.step == 2 && sharedViewModel.isPDF == true){
            findNavController().navigate(R.id.action_topicFragment_to_subTopicFragment)
        }*/ else{
            sharedViewModel.subTopicItem = SubTopicItem(
                id = "0",
                cid = topicItem.cid,
                category_name = "",
                category_image = "",
            )
            findNavController().navigate(R.id.action_topicFragment_to_quizListFragment)

        }

    }
    private fun setObserver(){
        mViewModel.topicList.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                topicAdapter?.submitList(it)
            }
        })
    }
    override fun setDefaultProperties() {
        val  activity = requireActivity()
        if (activity is DashboardActivity){
            activity.setActionBarTitle(sharedViewModel.featureItem?.featureName)
            activity.showBackButton()
            activity.hideBottomNavBar()
        }

    }
}