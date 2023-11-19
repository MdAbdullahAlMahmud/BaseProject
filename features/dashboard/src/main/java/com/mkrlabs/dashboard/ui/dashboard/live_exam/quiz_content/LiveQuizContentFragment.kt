package com.mkrlabs.dashboard.ui.dashboard.live_exam.quiz_content

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mkrlabs.common.R
import com.mkrlabs.common.core.base.BaseFragment
import com.mkrlabs.common.core.base.data.model.response.LiveQuizResponseItem
import com.mkrlabs.common.core.base.interfaces.CommunicatorImpl
import com.mkrlabs.dashboard.DashboardActivity
import com.mkrlabs.dashboard.DashboardHomeViewModel
import com.mkrlabs.dashboard.data.model.request.QuizRequestItem
import com.mkrlabs.dashboard.data.model.response.LiveQuizItem
import com.mkrlabs.dashboard.databinding.FragmentLiveQuizContentBinding
import com.mkrlabs.dashboard.ui.dashboard.live_exam.LiveQuizViewModel
import com.mkrlabs.dashboard.ui.dashboard.live_exam.adapter.LiveQuizAdapter
import com.mkrlabs.dashboard.utils.DateFormatter
import com.mkrlabs.dashboard.utils.DateFormatter.examDateAndTodaysDateIsSame
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LiveQuizContentFragment : BaseFragment<LiveQuizViewModel, FragmentLiveQuizContentBinding>() {

    override val mViewModel: LiveQuizViewModel by viewModels()
     val sharedViewModel: DashboardHomeViewModel by activityViewModels()
    override fun getViewBinding(): FragmentLiveQuizContentBinding = FragmentLiveQuizContentBinding.inflate(layoutInflater)

    private var isUpcoming: Boolean = true;

    private var liveQuizResponseList = ArrayList<LiveQuizResponseItem>()

    private val  liveListAdapter : LiveQuizAdapter = LiveQuizAdapter(
        this::onItemClicked ,
        this::medhaTalikaClicked,
        this::syllabusClickListener,
        this::folafolClick
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getInitialData()
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
                upcomingQuiz()
            }
        }

        mViewBinding.btnArchived.setOnClickListener {
            if(isUpcoming) {

                selectLimitTitleBackground(it)
                resetLimitTitleBackground(mViewBinding.btnUpcoming)
                isUpcoming=false
                archiveQuiz()
            }
        }




    }

    fun upcomingQuiz(){
        var upcomingList = java.util.ArrayList<LiveQuizResponseItem>()
        liveQuizResponseList.forEach {
            if (DateFormatter.afterToday(it.exam_date?:"") || examDateAndTodaysDateIsSame(it.exam_date?:"")){
                upcomingList.add(it)
            }
        }
        liveListAdapter?.submitList(upcomingList)

    }




    fun archiveQuiz(){
        var archiveList = java.util.ArrayList<LiveQuizResponseItem>()
        liveQuizResponseList.forEach {
            if (DateFormatter.beforeToday(it.exam_date?:"")){
                archiveList.add(it)
            }
        }
        liveListAdapter?.submitList(archiveList)

    }



    fun medhaTalikaClicked(item: LiveQuizResponseItem){
        sharedViewModel.quizItem = item
        findNavController().navigate(com.mkrlabs.dashboard.R.id.action_liveQuizContentFragment_to_leaderBoardFragment)
    }

    fun syllabusClickListener(item: LiveQuizResponseItem){
        showCustomDialog(
            title = "Syllabus",
            message = item.syllabus.toString(),
            negativeButtonEnabled = false,
            positiveText = "Okay"
        )
    }
    fun folafolClick(item: LiveQuizResponseItem , isPublished : Boolean){
        if (!isPublished){
            showCustomDialog(
                message = "Result is not published yet",
                negativeButtonEnabled = false,
                positiveText = "Okay"
            )
        }else{

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
                liveQuizResponseList.addAll(it)
                upcomingQuiz()

                //liveListAdapter.submitList(it)
            }
        })
    }

    private fun getInitialData(){
        //mViewModel.requestLiveQuizList()


        val quizRequestItem = QuizRequestItem(sharedViewModel.liveQuizDashboardItem?.cid.toString(), sub_cat_id = "0")
        mViewModel.getLiveQuizList(quizRequestItem)
    }
    private fun onItemClicked(quizResponseItem : LiveQuizResponseItem){

        Log.v("Live","live Quiz item  ---------------------${quizResponseItem.toString()}----------------------------")
        CommunicatorImpl.callback?.gotoLiveQuizActivity(quizResponseItem)


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