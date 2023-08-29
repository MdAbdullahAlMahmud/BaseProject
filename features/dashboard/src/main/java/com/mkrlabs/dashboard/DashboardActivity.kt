package com.mkrlabs.dashboard

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mkrlabs.common.core.base.BaseActivity
import com.mkrlabs.dashboard.databinding.ActivityDashboardBinding
import com.mkrlabs.dashboard.ui.dashboard.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity<DashboardViewModel, ActivityDashboardBinding>() {

    override val mViewModel: DashboardViewModel by viewModels()


    override fun getViewBinding(): ActivityDashboardBinding  = ActivityDashboardBinding.inflate(layoutInflater)


    private lateinit var navController: NavController
    private lateinit var bottomNav: BottomNavigationView
    private var currentRoute: Int = -99

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.dashboardNavHostFragment) as NavHostFragment

        navController = navHostFragment.navController
        bottomNav = mViewBinding.containerBottomNav.bottomNavigationView
        bottomNav.itemIconTintList = null
        initView()
        setListener()


    }


    private fun initView(){
        mViewBinding.actionBar.drawerMenuIcon.setOnClickListener {
            openSideBar()
        }


    }

    private fun setListener(){
        setBottomNavSelectListener()
        navigationDrawerListeners()
    }

    private fun setBottomNavSelectListener(){
        bottomNav.setOnItemSelectedListener {

            if (currentRoute != it.itemId) {
                currentRoute = it.itemId
                when (it.itemId) {

                    R.id.navLiveExam -> {
                        unLockDrawerLayout()
                        //navController.navigate(R.id.dashboardFragment)
                        true
                    }

                    R.id.navSubscription -> {
                        lockDrawerLayout()
                       // navController.navigate(R.id.myPocketFragment)
                        true
                    }

                    else -> {
                        true
                    }
                }

            } else {
                false
            }
        }
    }
    private fun navigationDrawerListeners(){

    }
    override fun setActionBarTitle(title: String?) {
        mViewBinding.actionBar.apply {
            tvTitle.text = title
        }

    }

    override fun setActionBar() {
        mViewBinding.actionBar.root.visibility = View.VISIBLE
        mViewBinding.actionBar.tvTitle.visibility = View.VISIBLE
        mViewBinding.actionBar.backCV.visibility = View.GONE
    }

    override fun hideActionBar() {
        mViewBinding.actionBar.root.visibility = View.GONE

    }

    override fun onBackPressListener() {
        mViewBinding.actionBar.backCV.setOnClickListener {
            if (mViewBinding.drawerLayout.isDrawerOpen(mViewBinding.containerMenu.containerNavDrawer)) {
                mViewBinding.drawerLayout.closeDrawer(mViewBinding.containerMenu.containerNavDrawer)
            } /*else if (navController.currentDestination?.id in listOf(
                    R.id.navLiveExam, R.id.navSubscription
                )
            ) {
                navController.navigateUp()
            } else if (supportFragmentManager.backStackEntryCount == 0 && navController.currentDestination?.id != R.id.dashboardFragment) {
                bottomNav.selectedItemId = R.id.dashboardNavHostFragment
            } else if (supportFragmentManager.backStackEntryCount > 0) {
                navController.navigateUp()
            }*/ else {
                /*showCustomDialog(
                    message = "Do you want to exit?" ,
                    positiveText =  "Exit" ,
                    negativeText = "Cancel",
                    negativeButtonEnabled = true,
                    positiveFunction = {
                        finishAffinity()
                        finish()
                    }
                )*/
                navController.navigateUp()
            }
        }
    }


    override fun menuPressListener() {
        showToast("Initialed")

    }


    fun lockDrawerLayout() {
        mViewBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }


    fun unLockDrawerLayout() {
        mViewBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }
     fun openSideBar() {
            if (mViewBinding.drawerLayout.isDrawerOpen(mViewBinding.containerMenu.containerNavDrawer)) {
                mViewBinding.drawerLayout.closeDrawer(mViewBinding.containerMenu.containerNavDrawer)
            } else {
                mViewBinding.drawerLayout.openDrawer(mViewBinding.containerMenu.containerNavDrawer)
            }
    }

    fun showBackButton() {
        mViewBinding.actionBar.backCV.visibility = View.VISIBLE
    }
    fun hideBackButton() {
        mViewBinding.actionBar.backCV.visibility = View.GONE
    }

    fun hideBottomNavBar() {
        mViewBinding.containerBottomNav.root.visibility = View.GONE
    }

    fun showBottomNavBar() {
        mViewBinding.containerBottomNav.root.visibility = View.VISIBLE
    }
    override fun setAppBarProperties() {
    }
}