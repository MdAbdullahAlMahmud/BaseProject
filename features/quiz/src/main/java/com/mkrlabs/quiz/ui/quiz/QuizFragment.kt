package com.mkrlabs.quiz.ui.quiz

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mkrlabs.common.core.base.BaseFragment
import com.mkrlabs.quiz.QuizActivity
import com.mkrlabs.quiz.data.model.request.QuizQuestionRequest
import com.mkrlabs.quiz.data.model.response.QuizQuestionItem
import com.mkrlabs.quiz.databinding.FragmentQuizBinding
import com.mkrlabs.quiz.ui.QuizHomeViewModel
import com.mkrlabs.quiz.ui.quiz.adapter.QuizQuestionAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizFragment : BaseFragment<QuizViewModel, FragmentQuizBinding>() {
    override val mViewModel: QuizViewModel by viewModels()
    private val sharedQuizViewModel: QuizHomeViewModel by activityViewModels()
    override fun getViewBinding(): FragmentQuizBinding = FragmentQuizBinding.inflate(layoutInflater)
    private var adapter: QuizQuestionAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setObserver()

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
                adapter?.submitList(data)

            }
        })
    }

    private fun onOptionSelected(questionItem: QuizQuestionItem) {

    }

    override fun setDefaultProperties() {
        val activity = requireActivity()
        if (activity is QuizActivity) {
            activity.hideBackButton()
            activity.setActionBarTitle(sharedQuizViewModel.quizItem?.quiz_title)
            activity.showTimerTv()
        }

    }

}