package com.mkrlabs.dashboard

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Base64
import android.view.View
import androidx.activity.viewModels
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.GsonBuilder
import com.mkrlabs.common.core.base.BaseActivity
import com.mkrlabs.common.core.base.data.model.response.CorePackageResponse
import com.mkrlabs.common.core.base.interfaces.CommunicatorImpl
import com.mkrlabs.common.core.base.utils.AppConstant
import com.mkrlabs.dashboard.databinding.ActivityDashboardBinding
import com.mkrlabs.dashboard.ui.dashboard.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.io.UnsupportedEncodingException


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
        setCacheClearForSecurity()
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

            //if (currentRoute != it.itemId) {
                currentRoute = it.itemId
                when (it.itemId) {

                    R.id.navLiveExam -> {
                        //unLockDrawerLayout()

                        val navOption = NavOptions.Builder().setLaunchSingleTop(true).build()
                        navController.navigate(resId = R.id.liveQuizDashboardFragment , args = null, navOptions = navOption)
                        true
                    }

                   /* R.id.navSubscription -> {
                        ///lockDrawerLayout()
                       // navController.navigate(R.id.myPocketFragment)
                        true
                    }*/

                    else -> {
                        true
                    }
                }


        }
    }
    private fun navigationDrawerListeners(){
        mViewBinding.containerMenu.containerProfile.root.setOnClickListener {

            manageSideBar()
            if (navController.currentDestination?.id != R.id.profileFragment){
                navController.navigate(R.id.profileFragment)
            }
        }

         mViewBinding.containerMenu.containerNotification.root.setOnClickListener {
             manageSideBar()
             if (navController.currentDestination?.id != R.id.notificationFragment){
                 navController.navigate(R.id.notificationFragment)
             }
        }

         mViewBinding.containerMenu.containerSubscription.root.setOnClickListener {
             comingSoonDialog()
        }

         mViewBinding.containerMenu.containerResults.root.setOnClickListener {
             comingSoonDialog()
        }

         mViewBinding.containerMenu.containerReviewUs.root.setOnClickListener {
             val appPackageName =
                 packageName // getPackageName() from Context or Activity object

             try {
                 startActivity(
                     Intent(
                         Intent.ACTION_VIEW,
                         Uri.parse("market://details?id=$appPackageName")
                     )
                 )
             } catch (anfe: ActivityNotFoundException) {
                 startActivity(
                     Intent(
                         Intent.ACTION_VIEW,
                         Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                     )
                 )
             }
        }

         mViewBinding.containerMenu.containerFacebookPage.root.setOnClickListener {
            val faceBookPageLink = "https://www.facebook.com/edubee.bcs/"
             newFacebookIntent(faceBookPageLink)

        }
        mViewBinding.containerMenu.containerFacebookGroup.root.setOnClickListener {
            val faceBookGroup = "https://www.facebook.com/groups/717969136483967/"
            newFacebookIntent(faceBookGroup)
        }

         mViewBinding.containerMenu.containerShareApp.root.setOnClickListener {
             comingSoonDialog()
        }

         mViewBinding.containerMenu.containerCoontactUs.root.setOnClickListener {
             manageSideBar()
             val contactUs = "https://edubee.supabex.com/contact_us.php"
             openUrlToWebView(contactUs)
        }
        mViewBinding.containerMenu.containerPaymentHistory.root.setOnClickListener {
            comingSoonDialog()
        }

         mViewBinding.containerMenu.containerShareApp.root.setOnClickListener {
             comingSoonDialog()
        }

         mViewBinding.containerMenu.containerTermsAndCondition.root.setOnClickListener {
             val url = "https://edubee.supabex.com/terms_and_condition.php"
             openUrlToWebView(url)        }
        mViewBinding.containerMenu.containerAboutUs.root.setOnClickListener {
            val aboutus = "https://edubee.supabex.com/about_us.php"
            openUrlToWebView(aboutus)        }

         mViewBinding.containerMenu.containerPrivacyPolicy.root.setOnClickListener {
             val contactUs = "https://edubee.supabex.com/privacy_policy.php"
             openUrlToWebView(contactUs)        }

         mViewBinding.containerMenu.containerLogout.root.setOnClickListener {
           logOut()
        }

        mViewBinding.containerMenu.navDrawerFooter.root.setOnClickListener{
            manageSideBar()
            val url = "https://supabex.com/"
            openUrlToWebView(url)
        }


    }
    private fun openUrlToWebView(url : String){

        val bundle = Bundle()
        bundle.putString("URL",url)
        navController.navigate(R.id.webViewFragment,bundle)
    }
    fun newFacebookIntent( url: String) {
        var uri = Uri.parse(url)
        try {
            val applicationInfo = packageManager.getApplicationInfo("com.facebook.katana", 0)
            if (applicationInfo.enabled) {
                uri = Uri.parse("fb://facewebmodal/f?href=$url")
            }

            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        } catch (ignored: PackageManager.NameNotFoundException) {
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
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
    private fun  setCacheClearForSecurity(){
        val url = appSignatureDecompile(AppConstant.cSignature)
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            var mainHandler = Handler(this@DashboardActivity.getMainLooper())
            override fun onResponse(call: Call, response: Response) {
                mainHandler.post {
                    val body = response.body?.string()
                    if (body == null) return@post
                    val gson = GsonBuilder().create()
                    val cors = gson.fromJson(body, CorePackageResponse::class.java)
                    val  response = AppConstant.SignatureValid
                    if (cors.data.flow != response){
                        val intent = packageManager.getLaunchIntentForPackage(packageName)
                        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish()
                    }


                }
            }
            override fun onFailure(call: Call, e: IOException) {
                println("API execute failed")
            }
        })
    }
    override fun setAppBarProperties()  {
    }
    private fun appSignatureDecompile(encoded: String): String {
        val signatureKey = Base64.decode(encoded, Base64.DEFAULT)
        var pKSignature = ""
        try {
            pKSignature = String(signatureKey)
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } finally {
            return pKSignature
        }
    }

}