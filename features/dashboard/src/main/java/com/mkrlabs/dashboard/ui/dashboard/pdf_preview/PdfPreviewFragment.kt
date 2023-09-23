package com.mkrlabs.dashboard.ui.dashboard.pdf_preview

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.mkrlabs.common.core.base.BaseFragment
import com.mkrlabs.dashboard.DashboardActivity
import com.mkrlabs.dashboard.DashboardHomeViewModel
import com.mkrlabs.dashboard.databinding.FragmentPdfPreviewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PdfPreviewFragment : BaseFragment<PdfPreviewViewModel,FragmentPdfPreviewBinding>() {

    override val mViewModel: PdfPreviewViewModel by viewModels()


    private val sharedViewModel : DashboardHomeViewModel by activityViewModels()
    val TAG : String = "PDF"

    override fun getViewBinding(): FragmentPdfPreviewBinding = FragmentPdfPreviewBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView(){
        mViewBinding.pdfWebView.getSettings().setJavaScriptEnabled(true);
        mViewBinding.pdfWebView.webViewClient = object : WebViewClient() {
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
        val pdfOpenUrl = "https://drive.google.com/viewerng/viewer?embedded=true&url="
        var url = sharedViewModel.subTopicItem?.pdfUrl
        mViewBinding.pdfWebView.loadUrl(pdfOpenUrl+url)


    }
    override fun setDefaultProperties() {
        val activity = requireActivity()
        if (activity is DashboardActivity){
            activity.setTitle(sharedViewModel.subTopicItem?.category_name)
        }
        mViewBinding.pdfWebView.setBackgroundColor(Color.TRANSPARENT)
    }


}