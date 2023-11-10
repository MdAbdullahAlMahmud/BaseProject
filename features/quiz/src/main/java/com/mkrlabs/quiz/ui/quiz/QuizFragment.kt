package com.mkrlabs.quiz.ui.quiz

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mkrlabs.common.core.base.BaseFragment
import com.mkrlabs.quiz.QuizActivity
import com.mkrlabs.quiz.R
import com.mkrlabs.quiz.data.model.enums.QuestionAnswer
import com.mkrlabs.quiz.data.model.request.QuizQuestionRequest
import com.mkrlabs.quiz.data.model.response.QuizQuestionItem
import com.mkrlabs.quiz.databinding.FragmentQuizBinding
import com.mkrlabs.quiz.ui.QuizHomeViewModel
import com.mkrlabs.quiz.ui.quiz.adapter.QuizQuestionAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlin.time.Duration.Companion.minutes

@AndroidEntryPoint
class QuizFragment : BaseFragment<QuizViewModel, FragmentQuizBinding>() {
    override val mViewModel: QuizViewModel by viewModels()
    private val sharedQuizViewModel: QuizHomeViewModel by activityViewModels()
    private var countdownTimer: CountDownTimer? = null

    private var QUIZ_DURATION = 1L
    override fun getViewBinding(): FragmentQuizBinding = FragmentQuizBinding.inflate(layoutInflater)
    private var adapter: QuizQuestionAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setObserver()
        setOnClickListener()

    }
    private fun setOnClickListener(){
        mViewBinding.quizFinishButton.setOnClickListener {
            countdownTimer?.cancel()
            storeUserAnswerAndRedirect()
        }
    }
    private fun initView() {
        callOnInit()
        initAdapter()

    }
    private fun callOnInit(){
        val quizQuestionRequest = QuizQuestionRequest(sharedQuizViewModel.quizItem?.qz_id)
        mViewModel.getQuizQuestionList(quizQuestionRequest)
    }

    private fun initAdapter() {
        adapter = QuizQuestionAdapter(this::onOptionSelected)
        mViewBinding.quizQuestionRV.adapter = adapter

    }

    private fun setObserver() {
        mViewModel.quizQuestionList.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let { data ->
                startCountdown()
                adapter?.submitList(data)

            }
        })
    }

    private fun onOptionSelected(questionItem: QuizQuestionItem, userChoice : QuestionAnswer , position : Int) {
        adapter?.updatedAnswer(position,userChoice)
    }
    private fun startCountdown() {
        val activity = requireActivity()


        sharedQuizViewModel.quizItem?.quiz_time?.let {
            val quizTimeInSec = it.toLong()
            //val quizTimeInMinutes = quizTimeInSec/ 60.0
            QUIZ_DURATION = quizTimeInSec.toLong()
        }


        val totalTimeInMillis = QUIZ_DURATION * 1000 // Total countdown time in milliseconds (e.g., 1 minute)

        var timePassed = 0L
        countdownTimer = object : CountDownTimer(totalTimeInMillis.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 60000
                val seconds = (millisUntilFinished % 60000) / 1000

                timePassed = totalTimeInMillis - millisUntilFinished
                sharedQuizViewModel.timePassesInMillsLong = timePassed
                if (activity is QuizActivity){
                    activity.setCountdown(String.format("%02d:%02d", minutes, seconds))
                }
            }

            override fun onFinish() {
                showQuizTimeOverDialog()

            }
        }

        countdownTimer?.start()
    }

    private fun showQuizTimeOverDialog(){
        showCustomDialog(
            message = "Your quiz time is over. Press Continue for quiz result",
            title = "Time Over",
            positiveText = "Continue",
            positiveFunction = {
                storeUserAnswerAndRedirect()
                //showToast("Completed")
            }
        )
    }

    private fun storeUserAnswerAndRedirect(){
        sharedQuizViewModel.questionAnswerList = adapter?.getQuestionWithAnswer()
        findNavController().navigate(R.id.action_quizFragment_to_quizResultFragment)



    }

    override fun setDefaultProperties() {
        val activity = requireActivity()
        if (activity is QuizActivity) {
            activity.hideBackButton()
            activity.setActionBarTitle(sharedQuizViewModel.quizItem?.quiz_title)
            activity.showTimerTv()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        countdownTimer?.cancel()

    }

}