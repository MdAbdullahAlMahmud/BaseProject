package com.mkrlabs.live_quiz.ui.quiz_result

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mkrlabs.common.core.base.BaseFragment
import com.mkrlabs.common.core.base.utils.AppConstant
import com.mkrlabs.live_quiz.LiveQuizActivity
import com.mkrlabs.live_quiz.databinding.FragmentQuizResultBinding
import com.mkrlabs.live_quiz.ui.QuizHomeViewModel
import com.mkrlabs.live_quiz.ui.quiz.QuizViewModel
import com.mkrlabs.live_quiz.ui.quiz_result.adapter.QuizResultPreviewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizResultFragment : BaseFragment<QuizViewModel, FragmentQuizResultBinding>() {

    override val mViewModel: QuizViewModel by viewModels()
    val sharedViewModel : QuizHomeViewModel by  activityViewModels()


    override fun getViewBinding(): FragmentQuizResultBinding = FragmentQuizResultBinding.inflate(layoutInflater)

    private var adapter : QuizResultPreviewAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setObserver()

    }

    private fun initView(){
        initAdapter()
        parseQuizData()

    }
    private fun parseQuizData(){
        val USER_ID = getStringPreferenceData(AppConstant.USER_ID)
        sharedViewModel.questionAnswerList?.let {
            sharedViewModel.timePassesInMillsLong?.let { it1 ->
                mViewModel.userQuizResult(it1,it,USER_ID,sharedViewModel.quizItem?.qz_id.toString(),sharedViewModel.quizItem?.quiz_time.toString()) }?:kotlin.run {
                mViewModel.userQuizResult(0,it,"0","0","0")
            }
        }

    }

    private fun initAdapter(){
        adapter = QuizResultPreviewAdapter()
        mViewBinding.resultPreviewRV.adapter = adapter
        sharedViewModel.questionAnswerList?.let { adapter?.submitList(it) }

    }
    private fun setObserver(){

        mViewModel.quizUserMetaData.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let {
                mViewBinding.examGridStatistic.timeTakenDataTV.text = it.timeTaken
                mViewBinding.examGridStatistic.yourScoreDataTV.text = "${it.correctAns}/${it.totalQs}"
                mViewBinding.examGridStatistic.topScoreDataTV.text = "${it.topScoreQs}/${it.totalQs}"

                mViewBinding.totalQuestionDataTv.text = it.totalQs
                mViewBinding.totalCorrectAnsDataTv.text = it.correctAns
                mViewBinding.totalWrongAnsDataTv.text = it.wrongAns
                mViewBinding.totalUnAnsDataTv.text = it.unAns

            }
        })

    }





    override fun setDefaultProperties() {
        val activity = requireActivity()

        if (activity is LiveQuizActivity){
            activity.showBackButton()
            activity.setActionBarTitle("Review")
            activity.hideTimerTv()
        }
    }
}