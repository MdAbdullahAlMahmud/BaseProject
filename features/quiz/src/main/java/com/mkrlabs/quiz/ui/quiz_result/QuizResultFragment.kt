package com.mkrlabs.quiz.ui.quiz_result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mkrlabs.common.core.base.BaseFragment
import com.mkrlabs.quiz.QuizActivity
import com.mkrlabs.quiz.R
import com.mkrlabs.quiz.databinding.FragmentQuizResultBinding
import com.mkrlabs.quiz.ui.QuizHomeViewModel
import com.mkrlabs.quiz.ui.quiz.QuizViewModel
import com.mkrlabs.quiz.ui.quiz_result.adapter.QuizResultPreviewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizResultFragment : BaseFragment<QuizViewModel,FragmentQuizResultBinding>() {

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
        sharedViewModel.questionAnswerList?.let {
            mViewModel.userQuizResult(it)
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

        if (activity is QuizActivity){
            activity.showBackButton()
            activity.setActionBarTitle("Review")
            activity.hideTimerTv()
        }
    }
}