package com.mkrlabs.dashboard.ui.dashboard.live_exam.leaderboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mkrlabs.common.core.base.BaseFragment
import com.mkrlabs.dashboard.DashboardActivity
import com.mkrlabs.dashboard.DashboardHomeViewModel
import com.mkrlabs.dashboard.data.model.response.LeaderBoardItem
import com.mkrlabs.dashboard.databinding.FragmentLeaderBoardBinding
import com.mkrlabs.dashboard.ui.dashboard.live_exam.LiveQuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeaderBoardFragment : BaseFragment<LiveQuizViewModel, FragmentLeaderBoardBinding>() {

    override val mViewModel: LiveQuizViewModel by viewModels()
    val sharedViewModel: DashboardHomeViewModel by activityViewModels()


    val adapter : LeaderBoardAdapter = LeaderBoardAdapter (this::onItemClicked)
    override fun getViewBinding(): FragmentLeaderBoardBinding = FragmentLeaderBoardBinding.inflate(layoutInflater)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setObservers()

    }
    fun initView(){
        mViewModel.requestLeaderBoard(sharedViewModel.quizItem?.qz_id.toString())
        initAdapter()
    }

    fun initAdapter(){
        mViewBinding.leaderBoardRV.adapter = adapter
    }
    fun setObservers(){
        mViewModel.leaderBoard.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let {
                adapter.submitList(it)
            }
        })
    }
    fun onItemClicked(leaderBoardItem: LeaderBoardItem){

    }
    override fun setDefaultProperties() {
        val activity = requireActivity()

        if (activity is DashboardActivity){
            activity.showBackButton()
            activity.setActionBarTitle("Leaderboard")
        }
    }
}