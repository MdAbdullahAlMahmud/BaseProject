package com.mkrlabs.dashboard.ui.dashboard.live_exam.live_exam_result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mkrlabs.common.core.base.BaseFragment
import com.mkrlabs.common.core.base.utils.AppConstant
import com.mkrlabs.dashboard.DashboardActivity
import com.mkrlabs.dashboard.DashboardHomeViewModel
import com.mkrlabs.dashboard.R
import com.mkrlabs.dashboard.data.model.response.LiveQuizResultItem
import com.mkrlabs.dashboard.databinding.FragmentLeaderBoardBinding
import com.mkrlabs.dashboard.databinding.FragmentLiveResultBinding
import com.mkrlabs.dashboard.ui.dashboard.live_exam.LiveQuizViewModel
import com.mkrlabs.dashboard.ui.dashboard.live_exam.leaderboard.LeaderBoardAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LiveResultFragment : BaseFragment<LiveQuizViewModel, FragmentLiveResultBinding>() {

    override val mViewModel: LiveQuizViewModel by viewModels()
    val sharedViewModel: DashboardHomeViewModel by activityViewModels()


    override fun getViewBinding(): FragmentLiveResultBinding = FragmentLiveResultBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callOnInit()
        initView()
        setObservers()
    }

    fun  callOnInit(){
        val  userId = getStringPreferenceData(AppConstant.USER_ID)
        mViewModel.requestUserLiveQuizResult(userId,sharedViewModel.liveQuizInfo?.qz_id.toString())
    }

    fun setObservers(){

        mViewModel.userResult.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let {
                userFinalResult(it)
            }
        })
    }
    fun initView(){
        sharedViewModel.liveQuizInfo.let {

            //showToast("Cut marks ${it?.cut_marks}")
        }
    }


    fun userFinalResult(result : LiveQuizResultItem){

        val totalStudent = result.count

        val yourScore = result.right_answered + "/" + result.total_quiz_qs
        val yourRank = result.rank
        val  timeTaken = result.total_time

        try {
            val cutMark = (sharedViewModel.liveQuizInfo?.cut_marks?:0).toString().toDouble()
            val yourCutMarks = (result.wrong_ans?:0).toString().toInt() * cutMark
            mViewBinding.resultCutMarksTv.text = yourCutMarks.toString()

            mViewBinding.examGridStatistic.yourScoreDataTV.text = (result.right_answered?.toDouble()
                ?.minus(cutMark.toDouble())).toString()


        }catch (e : Exception){
            e.printStackTrace()
        }

        mViewBinding.apply {
            resultTotalStudentTv.text = totalStudent.toString() +"জন"
            resultTotalPassTv.text = result.total_pass_users +" জন"
            resultTotalFailsTv.text = result.total_fail_users +" জন"
            resultHighestMarksTv.text = "${result.total_quiz_qs}"
            examGridStatistic.timeTakenDataTV.text = timeTaken
            examGridStatistic.yourPositionTv.text = yourRank
        }
    }
    override fun setDefaultProperties() {
        val activity = requireActivity()

        if (activity is DashboardActivity){
            activity.showBackButton()
            activity.setActionBarTitle("Live Result")
        }
    }



}