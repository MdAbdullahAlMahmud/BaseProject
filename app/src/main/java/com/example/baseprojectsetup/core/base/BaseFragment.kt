package com.example.baseprojectsetup.core.base

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.wifi.WifiManager
import android.os.Bundle
import android.text.format.Formatter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import com.example.baseprojectsetup.R
import com.example.baseprojectsetup.core.base.utils.AppConstant
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Date


/**
 * Created by Abdullah on 7/9/2023.
 */

abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding> : Fragment() {

    protected abstract val mViewModel: VM

    protected lateinit var mViewBinding: VB
    private lateinit var dialog: Dialog

    abstract fun getViewBinding(): VB
    private lateinit var fm: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = Dialog(requireContext())
        fm = requireActivity().supportFragmentManager
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewBinding = getViewBinding()
        return mViewBinding.root
    }

    override fun onResume() {
        super.onResume()
        hideKeyboard(requireContext(), requireView())
    }

    protected fun hideKeyboard(context: Context, view: View) {
        val imm: InputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
    }

    private fun setUpObservers() {
        mViewModel.showLoader.observe(viewLifecycleOwner) { it ->
            it.getContentIfNotHandled()?.let { isShow ->
                val activity = requireActivity()
                if (activity is BaseActivity<*, *>) {
                    activity.manageLoader(isShow)
                }
            }
        }

        mViewModel.showMessage.observe(viewLifecycleOwner) { message ->
            message.getContentIfNotHandled()?.let {
                if (!it.isNullOrEmpty()) {
                    showCustomDialog(message = it)
                }
            }
        }


        mViewModel.showNotification.observe(viewLifecycleOwner) { notification ->
            (requireActivity() as BaseActivity<*, *>).handleNotification(notification)

        }

        mViewModel.showGifLoader.observe(viewLifecycleOwner) { isShow ->
            if (isShow) {
                showGifLoader()
            } else {
                hideGifLoader()
            }
        }


    }

    fun showSnackbar(message: String) {
        if (message.isNotEmpty())
            Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
    }

    fun showToast(message: String) {
        if (message.isNotEmpty())
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showGifLoader() {
        if (Loader.isVisible)
            Loader.dismiss()

        GifLoader.isCancelable = false
        GifLoader.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.LoadingDialogTheme)
        GifLoader.show(requireActivity().supportFragmentManager, "loader")
    }

    private fun hideGifLoader() {
        if (GifLoader.isVisible)
            GifLoader.dismiss()
    }

    fun showCustomDialog(
        title: String = "",
        message: String,
        positiveText: String = "Ok",
        negativeText: String = "No",
        negativeButtonEnabled: Boolean = false,
        positiveFunction: () -> Unit = {},
        negativeFunction: () -> Unit = {},
    ) {
        val activity = requireActivity()
        if (activity is BaseActivity<*, *>) {
            activity.showCustomDialog(
                title = title,
                message = message,
                positiveText = positiveText,
                negativeText = negativeText,
                negativeButtonEnabled = negativeButtonEnabled,
                positiveFunction = positiveFunction,
                negativeFunction = negativeFunction
            )
        }
    }

    fun getIpAddress(): String {
        val wifiManager =
            requireActivity().applicationContext.getSystemService(AppCompatActivity.WIFI_SERVICE) as WifiManager
        return Formatter.formatIpAddress(wifiManager.connectionInfo.ipAddress)
    }

    protected fun getStringPreferenceData(key: String): String {
        val activity = requireActivity()
        if (activity is BaseActivity<*, *>) {
            return activity.getStringPreferenceData(key)
        }
        return ""
    }

    fun setStringPreferenceData(key : String, value : String){
        val  shred_pref = requireActivity().applicationContext?.getSharedPreferences(
            AppConstant.PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )
        val editor = shred_pref?.edit()
        editor?.putString(key,value)
        editor?.apply()
    }

    fun getCurrentLayoutAsBitmap(view: View): Bitmap {
        val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) bgDrawable.draw(canvas)
        else canvas.drawColor(Color.WHITE)
        view.draw(canvas)
        return returnedBitmap
    }

    protected fun getCurrentDate(format: String? = "yyyy-MM-dd") : String{
        val format = SimpleDateFormat(format)
        return format.format(Date())
    }

    protected fun getCurrentTime() : String{
        val sdf = SimpleDateFormat("HH:mm:ss")
        return sdf.format(Date())
    }

}
