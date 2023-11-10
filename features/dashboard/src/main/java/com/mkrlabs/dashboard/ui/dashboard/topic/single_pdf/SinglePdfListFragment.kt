package com.mkrlabs.dashboard.ui.dashboard.topic.single_pdf

import android.os.Bundle
import android.util.Log
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
import com.mkrlabs.dashboard.data.model.request.SubTopicRequest
import com.mkrlabs.dashboard.data.model.response.SinglePdfCatItem
import com.mkrlabs.dashboard.data.model.response.SubTopicItem
import com.mkrlabs.dashboard.data.model.response.TopicItem
import com.mkrlabs.dashboard.databinding.FragmentSinglePdfListBinding
import com.mkrlabs.dashboard.databinding.FragmentTopicBinding
import com.mkrlabs.dashboard.ui.dashboard.adapter.SinglePdfCatAdapter
import com.mkrlabs.dashboard.ui.dashboard.adapter.SubTopicAdapter
import com.mkrlabs.dashboard.ui.dashboard.adapter.TopicAdapter
import com.mkrlabs.dashboard.ui.dashboard.topic.TopicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SinglePdfListFragment : BaseFragment<TopicViewModel, FragmentSinglePdfListBinding>() {

    override val mViewModel: TopicViewModel by viewModels()
    private val  sharedViewModel : DashboardHomeViewModel by activityViewModels()

    override fun getViewBinding(): FragmentSinglePdfListBinding = FragmentSinglePdfListBinding.inflate(layoutInflater)


    private var topicAdapter : SinglePdfCatAdapter? = null
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
        val sid = sharedViewModel.topicItem?.cid.toString()
        mViewModel.getSinglePDFTopicList(sid)

    }
    private fun initAdapter(){
        topicAdapter = SinglePdfCatAdapter (this::topicItemClickListener)
        mViewBinding.topicRV.adapter = topicAdapter
    }
    private fun topicItemClickListener(topicItem: SinglePdfCatItem){
        mViewModel.requestForPdfId(topicItem.mid, topicItem.cid)
    }
    private fun setObserver(){
        mViewModel.singleTopicList.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                topicAdapter?.submitList(it)
            }
        })

        mViewModel.pdfContent.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let {
                //mViewModel.requestForSinglePDFUrl(it.id.toString())
                sharedViewModel.pdfId = it.id
                findNavController().navigate(R.id.action_singlePdfListFragment_to_pdfPreviewFragment)

            }
        })

        /*mViewModel.pdfUrlResponse.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let {
                showToast("Pdf Url  ${it.pdf_url}")

            }
        })*/


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