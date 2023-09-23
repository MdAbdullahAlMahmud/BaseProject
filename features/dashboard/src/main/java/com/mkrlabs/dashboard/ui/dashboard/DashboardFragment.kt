package com.mkrlabs.dashboard.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mkrlabs.common.core.base.BaseFragment
import com.mkrlabs.dashboard.DashboardActivity
import com.mkrlabs.dashboard.DashboardHomeViewModel
import com.mkrlabs.dashboard.R
import com.mkrlabs.dashboard.data.model.FeatureItem
import com.mkrlabs.dashboard.data.model.enums.Categories
import com.mkrlabs.dashboard.databinding.FragmentDashboardHomeBinding
import com.mkrlabs.dashboard.ui.dashboard.adapter.FeatureListAdapter
import com.mkrlabs.dashboard.data.model.enums.Features
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<DashboardViewModel, FragmentDashboardHomeBinding>() {

    override val mViewModel: DashboardViewModel by viewModels()
    private val sharedViewModel : DashboardHomeViewModel by  activityViewModels()
    override fun getViewBinding(): FragmentDashboardHomeBinding  = FragmentDashboardHomeBinding.inflate(layoutInflater)


    private var qsBankAdapter : FeatureListAdapter? = null
    private var onusilonAdapter : FeatureListAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAdapter()
        setClickListener()
        setObserver()

    }


    private fun initAdapter(){
        qsBankAdapter = FeatureListAdapter(this::qsBankItemClickListener)
        mViewBinding.rvQsBank.adapter = qsBankAdapter

        onusilonAdapter = FeatureListAdapter(this::qsBankItemClickListener)
        mViewBinding.onusilonRV.adapter = onusilonAdapter


    }

    private fun setObserver(){
        mViewModel.qsBankTopicList.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                qsBankAdapter?.submitList(it)
            }
        })

         mViewModel.onusilonTopicList.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                onusilonAdapter?.submitList(it)
            }
        })





    }
    private fun setClickListener(){

    }

    private fun qsBankItemClickListener(item: FeatureItem){
        sharedViewModel.featureItem = item

        when(item.featureCode){

            Features.TOPIC_VITTIK_JOB_SOLUTIONS.code ->{
               /* sharedViewModel.step = 3
                sharedViewModel.isPDF = true
                sharedViewModel.topicId = Categories.CAT_TOPIC_VITTIK_JOB_SOLUTIONS.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)*/


            }

            Features.BCS_PRELIMINARY.code ->{
                sharedViewModel.step = 2
                sharedViewModel.isPDF = true
                sharedViewModel.topicId = Categories.CAT_BCS_PRELIMINARY.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)
            }

            Features.BANK_NIYOG.code ->{
                sharedViewModel.step = 2
                sharedViewModel.isPDF = true
                sharedViewModel.topicId = Categories.CAT_BANK_NIYOG.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)
            }

            Features.NINE_TEN_GRADE.code ->{
                sharedViewModel.step = 2
                sharedViewModel.isPDF = true
                sharedViewModel.topicId = Categories.CAT_NINE_TEN_GRADE.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)
            }

            Features.PRIMARY_AND_NTRCA.code ->{
                sharedViewModel.step = 2
                sharedViewModel.isPDF = true
                sharedViewModel.topicId = Categories.CAT_PRIMARY_AND_NTRCA.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)

            }

            Features.ELEVEN_TWENTY_GRADE.code ->{
                sharedViewModel.step = 2
                sharedViewModel.isPDF = true
                sharedViewModel.topicId = Categories.CAT_ELEVEN_TWENTY_GRADE.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)
            }

            Features.TOPIC_VITTIK_ONUSILON.code ->{
                sharedViewModel.step = 3
                sharedViewModel.isPDF = false
                sharedViewModel.topicId = Categories.CAT_TOPIC_VITTIK_ONUSILON.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)
            }

            Features.TOPIC_VITTIK_PORIKKHA.code -> {
                sharedViewModel.step = 2
                sharedViewModel.isPDF = false
                sharedViewModel.topicId = Categories.CAT_TOPIC_VITTIK_PORIKKHA.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)
            }

            Features.BISOY_VITTIK_PORIKKHA.code -> {
                sharedViewModel.step = 2
                sharedViewModel.isPDF = false
                sharedViewModel.topicId = Categories.CAT_BISOY_VITTIK_PORIKKHA.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)
            }



        }



    }
    private fun initView(){
        mViewModel.getQsBankTopicList()
        mViewModel.getOnusilonTopicList()

    }
    override fun setDefaultProperties() {
        val  activity = requireActivity()
        if (activity is DashboardActivity){
            activity.hideBackButton()
            activity.setActionBarTitle("Hello Abdullah")
        }
    }
}
