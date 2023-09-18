package com.mkrlabs.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.mkrlabs.common.core.base.BaseActivity
import com.mkrlabs.common.core.base.data.model.response.QuizResponseItem
import com.mkrlabs.common.core.base.utils.IntentExtras
import com.mkrlabs.quiz.databinding.ActivityQuizActivityBinding
import com.mkrlabs.quiz.ui.QuizHomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizActivity : BaseActivity<QuizHomeViewModel, ActivityQuizActivityBinding>() {

    override val mViewModel: QuizHomeViewModel by viewModels()

    override fun getViewBinding(): ActivityQuizActivityBinding =
        ActivityQuizActivityBinding.inflate(layoutInflater)

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.quizNavigationHostFragment) as NavHostFragment

        navController = navHostFragment.navController

        if (intent.extras?.getSerializable(IntentExtras.QUIZ_ITEM) != null) {
            mViewModel.quizItem = intent.getSerializableExtra(IntentExtras.QUIZ_ITEM) as QuizResponseItem
        }

    }

    override fun setAppBarProperties() {
    }

    override fun setActionBarTitle(title: String?) {
        mViewBinding.quizAppBar.apply {
            tvTitle.text = title
        }
    }

    override fun setActionBar() {
        mViewBinding.quizAppBar.root.visibility = View.VISIBLE
        mViewBinding.quizAppBar.tvTitle.visibility = View.VISIBLE
        mViewBinding.quizAppBar.backCV.visibility = View.GONE
    }

    override fun hideActionBar() {
        mViewBinding.quizAppBar.root.visibility = View.GONE
    }

    override fun onBackPressListener() {
        mViewBinding.quizAppBar.ivBack.setOnClickListener {

            when(navController.currentDestination?.id){

                R.id.quizResultFragment ->{
                    finish()
                }

            }
        }
    }

    override fun onBackPressed() = when(navController.currentDestination?.id){

        R.id.quizResultFragment ->{
            finish()
        }
        else ->super.onBackPressed()

    }

    override fun menuPressListener() {
    }

    fun showBackButton() {
        mViewBinding.quizAppBar.backCV.visibility = View.VISIBLE
    }

    fun hideBackButton() {
        mViewBinding.quizAppBar.backCV.visibility = View.GONE
    }

    fun showTimerTv() {
        mViewBinding.quizAppBar.timeLL.visibility = View.VISIBLE

    }

    fun setCountdown(countDown : String ){
        mViewBinding.quizAppBar.quizTimer.text = countDown

    }

    fun hideTimerTv() {
        mViewBinding.quizAppBar.timeLL.visibility = View.GONE

    }


}