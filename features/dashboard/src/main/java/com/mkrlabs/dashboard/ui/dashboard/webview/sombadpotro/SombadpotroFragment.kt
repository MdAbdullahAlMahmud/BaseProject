package com.mkrlabs.dashboard.ui.dashboard.webview.sombadpotro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mkrlabs.common.core.base.BaseFragment
import com.mkrlabs.dashboard.DashboardActivity
import com.mkrlabs.dashboard.R
import com.mkrlabs.dashboard.databinding.FragmentSombadpotroBinding
import com.mkrlabs.dashboard.utils.KeyConstant


class SombadpotroFragment : BaseFragment<SombadpotroViewModel, FragmentSombadpotroBinding>() {

    override val mViewModel: SombadpotroViewModel by  viewModels()


    override fun getViewBinding(): FragmentSombadpotroBinding  = FragmentSombadpotroBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    fun  setOnClickListener(){
        mViewBinding.newsHolder.prothomAloIv.setOnClickListener{
            var bundle = Bundle()
            bundle.putString("URL", KeyConstant.PROTHOM_ALO_WEBSITE_LINK)
            findNavController().navigate(R.id.action_sombadpotroFragment_to_webViewFragment,bundle)
        }
         mViewBinding.newsHolder.kalerKonthoIv.setOnClickListener{
             var bundle = Bundle()
             bundle.putString("URL", KeyConstant.KALER_KONTHO_WEBSITE_LINK)
             findNavController().navigate(R.id.action_sombadpotroFragment_to_webViewFragment,bundle)
        }
         mViewBinding.newsHolder.jugantorIv.setOnClickListener{
             var bundle = Bundle()
             bundle.putString("URL", KeyConstant.JUGANTOR_WEBSITE_LINK)
             findNavController().navigate(R.id.action_sombadpotroFragment_to_webViewFragment,bundle)
        }
         mViewBinding.newsHolder.bangladeshProtidinIv.setOnClickListener{
             var bundle = Bundle()
             bundle.putString("URL", KeyConstant.BANGLADESH_PROTIDIN_WEBSITE_LINK)
             findNavController().navigate(R.id.action_sombadpotroFragment_to_webViewFragment,bundle)
        }
         mViewBinding.newsHolder.noyaDigintoIv.setOnClickListener{
             var bundle = Bundle()
             bundle.putString("URL", KeyConstant.NOYA_DIGINTO_WEBSITE_LINK)
             findNavController().navigate(R.id.action_sombadpotroFragment_to_webViewFragment,bundle)
        }
         mViewBinding.newsHolder.bonikBatraIv.setOnClickListener{
             var bundle = Bundle()
             bundle.putString("URL", KeyConstant.BONIK_BATRA_WEBSITE_LINK)
             findNavController().navigate(R.id.action_sombadpotroFragment_to_webViewFragment,bundle)
        }

         mViewBinding.newsHolder.theDailyStarIv.setOnClickListener{
             var bundle = Bundle()
             bundle.putString("URL", KeyConstant.DAILY_NEWS_WEBSITE_LINK)
             findNavController().navigate(R.id.action_sombadpotroFragment_to_webViewFragment,bundle)
        }



    }
    override fun setDefaultProperties() {
        val  activity = requireActivity()
        if (activity is DashboardActivity){
            activity.showBackButton()
            activity.setActionBarTitle("সংবাদপত্র")
            activity.hideBottomNavBar()
        }
    }
}