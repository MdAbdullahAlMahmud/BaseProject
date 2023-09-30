package com.mkrlabs.dashboard.ui.dashboard.webview

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import com.mkrlabs.common.core.base.BaseFragment
import com.mkrlabs.dashboard.DashboardActivity
import com.mkrlabs.dashboard.R
import com.mkrlabs.dashboard.databinding.FragmentWebViewBinding


class WebViewFragment : BaseFragment<WebViewViewModel,FragmentWebViewBinding>() {
    override val mViewModel: WebViewViewModel by viewModels()

    override fun getViewBinding(): FragmentWebViewBinding  = FragmentWebViewBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setWebViewUrl()
    }

    fun setWebViewUrl(){

        arguments?.let {
            var url = it.getString("URL")
            mViewBinding.miscWebView.loadUrl(url.toString())
        }

    }
    fun initView(){
        mViewBinding.miscWebView.getSettings().setJavaScriptEnabled(true);
        mViewBinding.miscWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null) {
                    view?.loadUrl(url)
                }
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                mViewModel.showLoader()
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                mViewModel.hideLoader()
                super.onPageFinished(view, url)
            }

        }
    }

    override fun setDefaultProperties() {
       val activity = requireActivity()

        if (activity is DashboardActivity){
            activity.setActionBarTitle("Miscellaneous")
            activity.hideBottomNavBar()
            activity.showBackButton()
        }
        mViewBinding.miscWebView.setBackgroundColor(Color.TRANSPARENT)

    }


}