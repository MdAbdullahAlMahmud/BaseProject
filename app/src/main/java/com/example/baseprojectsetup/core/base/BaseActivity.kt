package com.example.baseprojectsetup.core.base

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.text.format.Formatter
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.example.baseprojectsetup.R
import com.example.baseprojectsetup.core.base.utils.AppConstant
import com.example.baseprojectsetup.core.base.utils.SingleLiveEvent
import com.example.baseprojectsetup.databinding.LayoutAlertDialogBinding
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Date

abstract class BaseActivity<VM : BaseViewModel, VB : ViewBinding> : AppCompatActivity(){
    abstract val mViewModel: VM

    protected lateinit var mViewBinding: VB
    lateinit var dialog: Dialog

    abstract fun getViewBinding(): VB

    abstract fun setLanguageTexts()
    private var hasNotificationPermission: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = Dialog(this)
        mViewBinding = getViewBinding()

        setUpObservers()
        onBackPressListener()
        setNotificationPermission()
    }

    private fun setUpObservers(){
        mViewModel.showLoader.observe(this){
            it.getContentIfNotHandled()?.let { isShow->
                manageLoader(isShow)
            }
        }

        mViewModel.showMessage.observe(this) { message ->
            message.getContentIfNotHandled()?.let {
                if (!it.isNullOrEmpty()) {
                    showCustomDialog(message = it)
                }
            }
        }

        mViewModel.showNotification.observe(this) { notification ->
            handleNotification(notification)

        }

        mViewModel.isLanguageUpdateNeeded.observe(this) { isUpdateNeeded ->
            if (isUpdateNeeded) {
                setLanguageTexts()
            }
        }
    }

    fun manageLoader(isShow : Boolean){
        try {
            if (Loader.isAdded)
                Loader.dismiss()
            if (isShow) {
                this.supportFragmentManager.beginTransaction().remove(Loader).commit()
                Loader.isCancelable = false
                Loader.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.LoadingDialogTheme)
                Loader.show(this.supportFragmentManager, "loader")
            } else {
                Loader.dismiss()
            }
        } catch (_: Exception) {
        }

    }

    private fun showSnackBar(message: String) {
        runOnUiThread {
            Snackbar.make(mViewBinding.root, message, Snackbar.LENGTH_LONG).show()
        }
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    protected fun hideKeyboard() {
        val imm: InputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mViewBinding.root.windowToken, 0)
    }

    protected fun gotoNewActivity(activityClass : Class<*>){
        if (this.javaClass != activityClass){
            val intent = Intent(this,activityClass)
            startActivity(intent)
        }
    }

    protected fun gotoNewActivityWithClearActivity(activityClass : Class<*>){
        if (this.javaClass != activityClass){
            val intent = Intent(this,activityClass)
            startActivity(intent)
            finish()
        }
    }

    protected fun gotoNewActivityWithCleanAllActivity(activityClass : Class<*>){
        if (this.javaClass != activityClass){
            val intent = Intent(this,activityClass)
            intent.addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TASK
                or Intent.FLAG_ACTIVITY_CLEAR_TOP
                or Intent.FLAG_ACTIVITY_NEW_TASK
            )
            startActivity(intent)
        }
    }

    fun getCurrentDate(format: String? = "yyyy-MM-dd") : String{
        val format = SimpleDateFormat(format)
        return format.format(Date())
    }

    fun getCurrentTime() : String{
        val sdf = SimpleDateFormat("HH:mm:ss")
        return sdf.format(Date())
    }


    fun getCurrentLayoutAsBitmap(view: View) : Bitmap{
        val  returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val  canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) bgDrawable.draw(canvas)
        else canvas.drawColor(Color.WHITE)
        view.draw(canvas)
        return returnedBitmap
    }


     fun setStringPreferenceData(key : String, value : String){
        val  shred_pref = applicationContext?.getSharedPreferences(
            AppConstant.PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )
        val editor = shred_pref?.edit()
        editor?.putString(key,value)
        editor?.apply()
    }

     fun getStringPreferenceData(key : String) : String{
        val  shred_pref = applicationContext?.getSharedPreferences(
            AppConstant.PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )
        return shred_pref?.getString(key,"") ?: ""
    }





    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val networkCapabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            activeNetworkInfo?.run {
                type == ConnectivityManager.TYPE_WIFI ||
                        type == ConnectivityManager.TYPE_MOBILE ||
                        type == ConnectivityManager.TYPE_ETHERNET
            } ?: false
        }
    }

    abstract fun setActionBarTitle(title: String?)

    abstract fun setActionBar()

    abstract fun hideActionBar()

    abstract fun onBackPressListener()

    fun showCustomDialog(
        title: String = "",
        message: String,
        positiveText: String = "Yes",
        negativeText: String = "No",
        negativeButtonEnabled: Boolean = false,
        positiveButtonEnabled: Boolean = true,
        positiveFunction: () -> Unit = {},
        negativeFunction: () -> Unit = {},
    ) {
        if (dialog.isShowing) {
            return
        }
        val layoutAlertDialogBinding = LayoutAlertDialogBinding.inflate(layoutInflater)
        dialog.setCancelable(false)
        dialog.setContentView(layoutAlertDialogBinding.root)

        layoutAlertDialogBinding.tvAlertTitle.text = title
        layoutAlertDialogBinding.tvMessage.text = message
        layoutAlertDialogBinding.btnNegative.text = negativeText
        layoutAlertDialogBinding.btnPositive.text = positiveText

        layoutAlertDialogBinding.btnNegative.visibility =
            if (negativeButtonEnabled) View.VISIBLE else View.GONE

        layoutAlertDialogBinding.btnPositive.visibility =
            if (positiveButtonEnabled) View.VISIBLE else View.GONE

        layoutAlertDialogBinding.btnPositive.setOnClickListener {
            positiveFunction()
            dialog.dismiss()
        }

        layoutAlertDialogBinding.btnNegative.setOnClickListener {
            negativeFunction()
            dialog.dismiss()
        }

        dialog.window?.apply {
            setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            setBackgroundDrawableResource(android.R.color.transparent)
        }
        dialog.show()
    }


    fun getIpAddress(): String {
        val wifiManager = applicationContext.getSystemService(AppCompatActivity.WIFI_SERVICE) as WifiManager
        return Formatter.formatIpAddress(wifiManager.connectionInfo.ipAddress)
    }

    private fun setNotificationPermission(){
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
            showNotificationPermissionRationale()

        }else{
            hasNotificationPermission = true
        }
    }

    private fun showNotificationPermissionRationale(){
        if (Build.VERSION.SDK_INT >= 33) {
            notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }
    }
    private val notificationPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (!isGranted) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (Build.VERSION.SDK_INT >= 33) {
                        if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
                            showNotificationPermissionRationale()
                        } else {
//                            showSettingDialog()
                        }
                    }
                }
            } else {
                hasNotificationPermission = true

            }
        }

    fun handleNotification(notification: SingleLiveEvent<String>) {
        notification.getContentIfNotHandled()?.let {
            if (!it.isNullOrEmpty()) {

//                showNotification(it)

            }
        }
    }
}