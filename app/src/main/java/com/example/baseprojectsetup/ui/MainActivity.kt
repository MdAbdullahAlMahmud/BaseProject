package com.example.baseprojectsetup.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.baseprojectsetup.R
import com.example.baseprojectsetup.databinding.ActivityMainBinding
import com.mkrlabs.common.core.base.BaseActivity
import com.mkrlabs.common.core.base.data.model.response.QuizResponseItem
import com.mkrlabs.common.core.base.interfaces.Communicator
import com.mkrlabs.common.core.base.utils.AppConstant
import com.mkrlabs.common.core.base.utils.IntentExtras
import com.mkrlabs.dashboard.DashboardActivity
import com.mkrlabs.quiz.QuizActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(),
    Communicator {
    override val mViewModel: MainViewModel by viewModels()

    private lateinit var navController: NavController
    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        setObserver()
        setInitialScreenProperties()
        initViews()
    }
    fun setInitialScreenProperties() {
        if (isAuthenticated()) {
            val mainIntent = Intent(this, DashboardActivity::class.java)
            startActivity(mainIntent)
            finish()
        }
    }


    fun isAuthenticated(): Boolean {
        return getBooleanPreferenceData(AppConstant.IS_AUTHENTICATED)
    }
    fun setObserver() {
        com.mkrlabs.common.core.base.interfaces.CommunicatorImpl.setCommunicator(this)
    }

    private fun initViews() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainNavigationHostFragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun setActionBarTitle(title: String?) {
        mViewBinding.mainAppBar.apply {
            tvTitle.text = title
        }
    }

    override fun setActionBar() {
        mViewBinding.mainAppBar.root.visibility = View.VISIBLE
    }

    override fun hideActionBar() {
        mViewBinding.mainAppBar.root.visibility = View.GONE
    }

    fun hideBackButton() {
        mViewBinding.mainAppBar.apply {
            ivBack.visibility = View.INVISIBLE
        }
    }

    override fun onBackPressListener() {
        mViewBinding.mainAppBar.ivBack.setOnClickListener {
            navController.navigateUp()
        }
    }

    override fun setAppBarProperties() {
    }

    override fun menuPressListener() {

    }

    override fun authenticationFailed(message: String?) {
        logOutUser()

    }

    override fun gotoDashboard(navCode: Int) {
        gotoNewActivityWithCleanAllActivity(DashboardActivity::class.java)

    }

    override fun gotoQuizActivity(quizItem: QuizResponseItem?) {
        val intent = Intent(this, QuizActivity::class.java)
        intent.putExtra(IntentExtras.QUIZ_ITEM, quizItem)
        startActivity(intent)


    }



}