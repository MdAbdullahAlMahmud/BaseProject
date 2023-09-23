package com.mkrlabs.dashboard.ui.dashboard.topic.quiz_list

import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.mkrlabs.common.core.base.BaseFragment
import com.mkrlabs.common.core.base.interfaces.CommunicatorImpl
import com.mkrlabs.dashboard.DashboardActivity
import com.mkrlabs.dashboard.DashboardHomeViewModel
import com.mkrlabs.dashboard.data.model.request.QuizRequestItem
import com.mkrlabs.common.core.base.data.model.response.QuizResponseItem
import com.mkrlabs.dashboard.R
import com.mkrlabs.dashboard.databinding.FragmentQuizListBinding
import com.mkrlabs.dashboard.ui.dashboard.adapter.QuizListAdapter
import com.mkrlabs.dashboard.ui.dashboard.topic.TopicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizListFragment : BaseFragment<TopicViewModel,FragmentQuizListBinding>() {

    override val mViewModel: TopicViewModel by viewModels()
    val sharedViewModel: DashboardHomeViewModel by activityViewModels()


    private var quizListAdapter : QuizListAdapter? = null
    override fun getViewBinding(): FragmentQuizListBinding = FragmentQuizListBinding.inflate(layoutInflater)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        callOnInit()

        setObserver()
    }

    private fun initView(){
        initAdapter()
    }

    private fun initAdapter(){
        quizListAdapter = QuizListAdapter(this::quizListItemClickListener)
        mViewBinding.quizListRV.adapter = quizListAdapter


    }
    private fun callOnInit(){
        val quizRequestItem = QuizRequestItem(cat_id = sharedViewModel.subTopicItem?.cid, sub_cat_id = sharedViewModel.subTopicItem?.id)
        mViewModel.getQuizList(quizRequestItem)
    }

    private fun setObserver(){
        mViewModel.quizList.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let {
                quizListAdapter?.submitList(it)
            }
        })
    }

    private fun quizListItemClickListener( quizResponseItem: QuizResponseItem){
        if (sharedViewModel.isPDF == true){
            findNavController().navigate(R.id.action_quizListFragment_to_pdfPreviewFragment)
        }else
            CommunicatorImpl.callback?.gotoQuizActivity(quizResponseItem)
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