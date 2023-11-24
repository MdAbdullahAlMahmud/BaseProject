package com.mkrlabs.dashboard.ui.dashboard.pdf_preview

import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.mkrlabs.common.BuildConfig
import com.mkrlabs.common.core.base.BaseFragment
import com.mkrlabs.common.core.base.utils.AppConstant
import com.mkrlabs.dashboard.DashboardActivity
import com.mkrlabs.dashboard.DashboardHomeViewModel
import com.mkrlabs.dashboard.data.model.request.PDFItemRequest
import com.mkrlabs.dashboard.databinding.FragmentPdfPreviewBinding
import com.shockwave.pdfium.PdfDocument
import com.shockwave.pdfium.PdfDocument.Meta
import dagger.hilt.android.AndroidEntryPoint
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


@AndroidEntryPoint
class PdfPreviewFragment : BaseFragment<PdfPreviewViewModel,FragmentPdfPreviewBinding>() {

    override val mViewModel: PdfPreviewViewModel by viewModels()


    private val sharedViewModel : DashboardHomeViewModel by activityViewModels()
    val TAG : String = "PDF"

    override fun getViewBinding(): FragmentPdfPreviewBinding = FragmentPdfPreviewBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setObserver()
    }

    fun initView(){
        mViewModel.requestForPdf(PDFItemRequest(pdf_id = sharedViewModel.pdfId))
        mViewModel.showLoader()
    }

    var pageNumber = 0
    fun setObserver(){

        var BASE_URL = BuildConfig.BASE_URL +"/"+AppConstant.PDF_URL_PATH+"/"
        mViewModel.pdfContent.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let {

                val fullUrl = BASE_URL+it.pdf_url
                DownloadPdf().execute(fullUrl)

            }
        })
    }

    inner class DownloadPdf : AsyncTask<String, Void, InputStream>() {
        override fun doInBackground(vararg p0: String?): InputStream? {
             var inputSteam : InputStream? = null
            try {
                val url = URL(p0[0])

                val urlConnection = url.openConnection() as HttpURLConnection
                if (urlConnection.responseCode == 200){
                    inputSteam = BufferedInputStream(urlConnection.inputStream)
                }

            }catch (e :Exception){
                e.printStackTrace()
            }

            return  inputSteam
        }

        override fun onPostExecute(result: InputStream?) {
            mViewModel.hideLoader()
            mViewBinding.pdfView.fromStream(result).load()
        }

    }


    override fun setDefaultProperties() {
        val activity = requireActivity()
        if (activity is DashboardActivity){
            activity.setTitle(sharedViewModel.subTopicItem?.category_name)
        }
       // mViewBinding.pdfWebView.setBackgroundColor(Color.TRANSPARENT)
    }


}