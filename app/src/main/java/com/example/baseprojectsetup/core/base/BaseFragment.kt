package com.example.baseprojectsetup.core.base

import android.app.Activity
import android.app.Dialog
import android.content.Context
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
import com.example.baseprojectsetup.core.base.utils.LanguageConstants
import com.google.android.material.snackbar.Snackbar


/**
 * Created by Abdullah on 7/9/2023.
 */

abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding> : Fragment() {

    protected abstract val mViewModel: VM

    protected lateinit var mViewBinding: VB
    private lateinit var dialog: Dialog

    abstract fun getViewBinding(): VB
    abstract fun setLanguageTexts()
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
        setLanguageTexts()
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

    private fun setUpObservers(){
        mViewModel.showLoader.observe(viewLifecycleOwner){it->
            it.getContentIfNotHandled()?.let { isShow->
                val activity = requireActivity()
                if (activity is BaseActivity<*,*>){
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

        (requireActivity() as BaseActivity<*, *>).mViewModel.isLanguageUpdateNeeded.observe(
            viewLifecycleOwner
        ) { isUpdateNeeded ->
            if (isUpdateNeeded) {
                setLanguageTexts()
            }
        }

        mViewModel.showNotification.observe(viewLifecycleOwner){
                notification ->(requireActivity() as BaseActivity<*, *>).handleNotification(notification)

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
        positiveText: String = getLanguageText(LanguageConstants.commonLabelOk),
        negativeText: String = getLanguageText(LanguageConstants.commonLblNo),
        negativeButtonEnabled: Boolean = false,
        positiveFunction: () -> Unit = {},
        negativeFunction: () -> Unit = {},
    ) {
        val activity= requireActivity()
        if(activity is BaseActivity<*,*>){
            activity.showCustomDialog(
                title=title,
                message=message,
                positiveText=positiveText,
                negativeText=negativeText,
                negativeButtonEnabled=negativeButtonEnabled,
                positiveFunction=positiveFunction,
                negativeFunction=negativeFunction
            )
        }
    }
    fun getIpAddress(): String {
        val wifiManager =
            requireActivity().applicationContext.getSystemService(AppCompatActivity.WIFI_SERVICE) as WifiManager
        return Formatter.formatIpAddress(wifiManager.connectionInfo.ipAddress)
    }

    fun setText(view: View, key: String) {
        val activity = requireActivity()
        if (activity is BaseActivity<*, *>) {
            activity.setText(view, key)
        }
    }
    fun getLanguageText(key: String): String {
        val activity = requireActivity()
        if (activity is BaseActivity<*, *>) {
            return activity.getLanguageText(key)
        }
        return ""
    }
}
