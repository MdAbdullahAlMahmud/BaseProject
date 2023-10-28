package com.mkrlabs.dashboard.ui.dashboard.live_exam.quiz_content

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mkrlabs.common.R
import com.mkrlabs.common.core.base.BaseFragment
import com.mkrlabs.dashboard.DashboardActivity
import com.mkrlabs.dashboard.DashboardHomeViewModel
import com.mkrlabs.dashboard.data.model.response.LiveQuizItem
import com.mkrlabs.dashboard.databinding.FragmentLiveQuizContentBinding
import com.mkrlabs.dashboard.ui.dashboard.live_exam.LiveQuizViewModel
import com.mkrlabs.dashboard.ui.dashboard.live_exam.adapter.LiveQuizAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LiveQuizContentFragment : BaseFragment<LiveQuizViewModel, FragmentLiveQuizContentBinding>() {

    override val mViewModel: LiveQuizViewModel by viewModels()
     val sharedViewModel: DashboardHomeViewModel by activityViewModels()
    override fun getViewBinding(): FragmentLiveQuizContentBinding = FragmentLiveQuizContentBinding.inflate(layoutInflater)

    private var isUpcoming: Boolean = true;


    private val  liveListAdapter : LiveQuizAdapter = LiveQuizAdapter(this::onItemClicked)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getInitialData()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
        initView()
        setClickListener()

    }

    private fun setClickListener(){
        mViewBinding.btnUpcoming.setOnClickListener {
            if(!isUpcoming) {

                selectLimitTitleBackground(it)
                resetLimitTitleBackground(mViewBinding.btnArchived)
                isUpcoming=true
            }
        }
        mViewBinding.btnArchived.setOnClickListener {
            if(isUpcoming) {

                selectLimitTitleBackground(it)
                resetLimitTitleBackground(mViewBinding.btnUpcoming)
                isUpcoming=false
            }
        }
    }

    private fun resetLimitTitleBackground(view: View) {
        val background = view as AppCompatTextView

        background.setBackgroundResource(
            R.drawable.limit_left_corner_background
        )
        background.setTextColor(
            ContextCompat.getColor(
                requireContext(),
               R.color.filter_text_color
            )
        )
    }
    private fun selectLimitTitleBackground(view: View) {
        val background = view as AppCompatTextView

        background.setBackgroundResource(
            R.drawable.limit_right_corner_background
        )
        background.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.black
            )
        )
    }
    private fun initView(){
        initAdapter()
    }
    private fun initAdapter(){
        mViewBinding.liveQuizListRV.adapter = liveListAdapter
    }
    private fun setObserver(){
        mViewModel.liveQuizList.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let {
                liveListAdapter.submitList(it)
            }
        })
    }

    private fun getInitialData(){
        mViewModel.requestLiveQuizList()
    }
    private fun onItemClicked(item : LiveQuizItem){

    }
    override fun setDefaultProperties() {

        val  activity = requireActivity()
        if (activity is DashboardActivity ){
            activity.hideBottomNavBar()
            activity.showBackButton()
            activity.showDrawerMenu()
            activity.setActionBarTitle(sharedViewModel.liveQuizDashboardItem?.name)
        }
    }
}