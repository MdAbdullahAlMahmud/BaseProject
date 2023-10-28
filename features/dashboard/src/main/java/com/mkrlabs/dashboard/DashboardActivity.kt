package com.mkrlabs.dashboard

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mkrlabs.common.core.base.BaseActivity
import com.mkrlabs.common.core.base.interfaces.CommunicatorImpl
import com.mkrlabs.common.core.base.utils.AppConstant
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
        setNavDraweMenuItem()
        mViewBinding.actionBar.drawerMenuIcon.setOnClickListener {
            manageSideBar()
        }
    }

    private fun setNavDraweMenuItem(){
        mViewBinding.containerMenu.containerProfile.tvItemLevel.text = "Profile"
        mViewBinding.containerMenu.containerNotification.tvItemLevel.text = "Notification"
        mViewBinding.containerMenu.containerSubscription.tvItemLevel.text = "Subscription"
        mViewBinding.containerMenu.containerResults.tvItemLevel.text = "Rasults"
        mViewBinding.containerMenu.containerReviewUs.tvItemLevel.text = "Review Us"
        mViewBinding.containerMenu.containerFacebookPage.tvItemLevel.text = "Facebook Page"
        mViewBinding.containerMenu.containerFacebookGroup.tvItemLevel.text = "Facebook Group"
        mViewBinding.containerMenu.containerShareApp.tvItemLevel.text = "Share App"
        mViewBinding.containerMenu.containerCoontactUs.tvItemLevel.text = "Contact Us"
        mViewBinding.containerMenu.containerPaymentHistory.tvItemLevel.text = "Payment History"
        mViewBinding.containerMenu.containerTermsAndCondition.tvItemLevel.text = "Terms & Conditions"
        mViewBinding.containerMenu.containerAboutUs.tvItemLevel.text = "About Us"
        mViewBinding.containerMenu.containerPrivacyPolicy.tvItemLevel.text = "Privacy Policy"
        mViewBinding.containerMenu.containerLogout.tvItemLevel.text = "Log Out"
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

                        val navOption = NavOptions.Builder().setLaunchSingleTop(true).build()
                        navController.navigate(resId = R.id.liveQuizDashboardFragment , args = null, navOptions = navOption)
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
        mViewBinding.containerMenu.containerProfile.root.setOnClickListener {
            manageSideBar()
            navController.navigate(R.id.action_dashboardFragment_to_profileFragment)
        }

         mViewBinding.containerMenu.containerNotification.root.setOnClickListener {
             comingSoonDialog()
        }

         mViewBinding.containerMenu.containerSubscription.root.setOnClickListener {
             comingSoonDialog()
        }

         mViewBinding.containerMenu.containerResults.root.setOnClickListener {
             comingSoonDialog()
        }

         mViewBinding.containerMenu.containerReviewUs.root.setOnClickListener {
             comingSoonDialog()
        }

         mViewBinding.containerMenu.containerFacebookPage.root.setOnClickListener {
             comingSoonDialog()
        }
        mViewBinding.containerMenu.containerFacebookGroup.root.setOnClickListener {
            comingSoonDialog()
        }

         mViewBinding.containerMenu.containerShareApp.root.setOnClickListener {
             comingSoonDialog()
        }

         mViewBinding.containerMenu.containerCoontactUs.root.setOnClickListener {
             comingSoonDialog()
        }
        mViewBinding.containerMenu.containerPaymentHistory.root.setOnClickListener {
            comingSoonDialog()
        }

         mViewBinding.containerMenu.containerShareApp.root.setOnClickListener {
             comingSoonDialog()
        }

         mViewBinding.containerMenu.containerTermsAndCondition.root.setOnClickListener {
             comingSoonDialog()
        }
        mViewBinding.containerMenu.containerAboutUs.root.setOnClickListener {
            comingSoonDialog()
        }

         mViewBinding.containerMenu.containerPrivacyPolicy.root.setOnClickListener {
             comingSoonDialog()
        }

         mViewBinding.containerMenu.containerLogout.root.setOnClickListener {
           logOut()
        }


    }

    private fun  logOut(){
        showCustomDialog(
            title = "Logout",
            message = "Do you want to logout ?",
            positiveButtonEnabled = true,
            negativeButtonEnabled = true,
            positiveText = "Yes",
            negativeText = "Cancel",
            positiveFunction = {
                setBooleanPreferenceData(AppConstant.IS_AUTHENTICATED,false)
                CommunicatorImpl.callback?.authenticationFailed("You have been logout")
            },
            negativeFunction = {
                dialog.dismiss()
            }
        )
    }


    private fun comingSoonDialog(){
        showCustomDialog(
            message = "Features Coming soon."
        )
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

    }
     fun hideDrawerMenu(){
         mViewBinding.actionBar.drawerMenuIcon.visibility = View.GONE
     }
     fun showDrawerMenu(){
         mViewBinding.actionBar.drawerMenuIcon.visibility = View.VISIBLE
     }

    fun lockDrawerLayout() {
        mViewBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }


    fun unLockDrawerLayout() {
        mViewBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }
     fun manageSideBar() {
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