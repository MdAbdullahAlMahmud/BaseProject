package com.example.baseprojectsetup.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.baseprojectsetup.R
import com.example.baseprojectsetup.databinding.ActivityMainBinding
import com.mkrlabs.common.core.base.BaseActivity
import com.mkrlabs.common.core.base.interfaces.Communicator
import com.mkrlabs.dashboard.DashboardActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() ,
    Communicator {
    override val mViewModel: MainViewModel by viewModels()

    private lateinit var navController : NavController
    override fun getViewBinding(): ActivityMainBinding  = ActivityMainBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        initViews()
        setObserver()
    }

    fun setObserver(){
        com.mkrlabs.common.core.base.interfaces.CommunicatorImpl.setCommunicator(this)
    }
    private fun initViews(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainNavigationHostFragment) as NavHostFragment
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

    fun hideBackButton(){
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

    }

    override fun gotoDashboard(navCode: Int) {
        gotoNewActivityWithCleanAllActivity(DashboardActivity::class.java)

    }

}