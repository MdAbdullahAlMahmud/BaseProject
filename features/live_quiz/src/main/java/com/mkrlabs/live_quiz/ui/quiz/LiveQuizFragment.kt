package com.mkrlabs.live_quiz.ui.quiz

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mkrlabs.common.core.base.BaseFragment
import com.mkrlabs.live_quiz.LiveQuizActivity
import com.mkrlabs.live_quiz.R
import com.mkrlabs.live_quiz.data.model.enums.QuestionAnswer
import com.mkrlabs.live_quiz.data.model.request.QuizQuestionRequest
import com.mkrlabs.live_quiz.data.model.response.QuizQuestionItem
import com.mkrlabs.live_quiz.databinding.FragmentLiveQuizBinding
import com.mkrlabs.live_quiz.ui.QuizHomeViewModel
import com.mkrlabs.live_quiz.ui.quiz.adapter.QuizQuestionAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LiveQuizFragment : BaseFragment<QuizViewModel, FragmentLiveQuizBinding>() {



    override val mViewModel: QuizViewModel by viewModels()
    private val sharedQuizViewModel: QuizHomeViewModel by activityViewModels()
    override fun getViewBinding(): FragmentLiveQuizBinding  = FragmentLiveQuizBinding.inflate(layoutInflater)

    private var countdownTimer: CountDownTimer? = null

    private var QUIZ_DURATION = 1L
    private var adapter: QuizQuestionAdapter? = null

    private var questionList =  ArrayList<QuizQuestionItem>()
    private var subjectList = ArrayList<String>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setObserver()
        setOnClickListener()
        showToast("Live Quiz Id ${sharedQuizViewModel.quizItem?.qz_id}")

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

    private fun initSpinnerAdapter(){
        val spinnerAdapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_item, subjectList
        )
        mViewBinding.sylabusSpinner.adapter = spinnerAdapter

        mViewBinding.sylabusSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
              //showToast(list[position])

                filterData(subjectList[position], position)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    private fun filterData(subject : String , position: Int){

        showToast("Position $position")
        if (position!=0){
            var newList = ArrayList<QuizQuestionItem>()
            questionList.forEach { item ->
                if (item.subjects.toString() == subject){
                    newList.add(item)
                }
            }
            adapter?.submitList(newList)
        }else{
            adapter?.submitList(questionList)
        }


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
                questionList.addAll(data)
                separateSubjectList(data)
                adapter?.submitList(data)
                initSpinnerAdapter()

            }
        })
    }
    private fun separateSubjectList(data: List<QuizQuestionItem>) {
        var hashSet = HashSet<String>()
        hashSet.add("All")
        data.forEach {
            hashSet.add(it.subjects.toString())
        }
        subjectList.addAll(hashSet)


    }

    private fun onOptionSelected(questionItem: QuizQuestionItem, userChoice : QuestionAnswer, position : Int) {
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
                if (activity is LiveQuizActivity){
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
        findNavController().navigate(R.id.action_liveQuizFragment_to_quizResultFragment)



    }

    override fun setDefaultProperties() {
        val activity = requireActivity()
        if (activity is LiveQuizActivity) {
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