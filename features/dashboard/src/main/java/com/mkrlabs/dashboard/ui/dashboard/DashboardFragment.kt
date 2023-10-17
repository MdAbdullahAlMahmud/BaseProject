package com.mkrlabs.dashboard.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mkrlabs.common.core.base.BaseFragment
import com.mkrlabs.common.core.base.utils.AppConstant.USER_ID
import com.mkrlabs.dashboard.DashboardActivity
import com.mkrlabs.dashboard.DashboardHomeViewModel
import com.mkrlabs.dashboard.R
import com.mkrlabs.dashboard.data.model.FeatureItem
import com.mkrlabs.dashboard.data.model.enums.Categories
import com.mkrlabs.dashboard.databinding.FragmentDashboardHomeBinding
import com.mkrlabs.dashboard.ui.dashboard.adapter.FeatureListAdapter
import com.mkrlabs.dashboard.data.model.enums.Features
import com.mkrlabs.dashboard.data.model.enums.IndentityCode
import com.mkrlabs.dashboard.ui.dashboard.adapter.MiscellaneousAdapter
import com.mkrlabs.dashboard.utils.KeyConstant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<DashboardViewModel, FragmentDashboardHomeBinding>() {

    override val mViewModel: DashboardViewModel by viewModels()
    private val sharedViewModel : DashboardHomeViewModel by  activityViewModels()
    override fun getViewBinding(): FragmentDashboardHomeBinding  = FragmentDashboardHomeBinding.inflate(layoutInflater)


    private var qsBankAdapter : FeatureListAdapter? = null
    private var onusilonAdapter : FeatureListAdapter? = null
    private var vocabularyAdapter : FeatureListAdapter? = null
    private var learnByHeartAdapter : FeatureListAdapter? = null
    private var miscellaneousAdapter : MiscellaneousAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAdapter()
        setClickListener()
        setObserver()
        showToast("User ID : ${getStringPreferenceData(USER_ID)}")

    }


    private fun initAdapter(){
        qsBankAdapter = FeatureListAdapter(this::qsBankItemClickListener)
        mViewBinding.rvQsBank.adapter = qsBankAdapter

        onusilonAdapter = FeatureListAdapter(this::qsBankItemClickListener)
        mViewBinding.onusilonRV.adapter = onusilonAdapter

        vocabularyAdapter = FeatureListAdapter(this::qsBankItemClickListener)
        mViewBinding.vocabularyRV.adapter = vocabularyAdapter

        learnByHeartAdapter = FeatureListAdapter(this::qsBankItemClickListener)
        mViewBinding.learnByHeartRV.adapter = learnByHeartAdapter

        miscellaneousAdapter = MiscellaneousAdapter(this::qsBankItemClickListener)
        mViewBinding.miscellaneousRV.adapter = miscellaneousAdapter



    }

    private fun setObserver(){
        mViewModel.qsBankTopicList.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                qsBankAdapter?.submitList(it)
            }
        })

         mViewModel.onusilonTopicList.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                onusilonAdapter?.submitList(it)
            }
        })
        mViewModel.vocabularyTopicList.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                vocabularyAdapter?.submitList(it)
            }
        })

        mViewModel.learnByHeartTopicList.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                learnByHeartAdapter?.submitList(it)
            }
        })


         mViewModel.miscellaneousTopicList.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                miscellaneousAdapter?.submitList(it)
            }
        })












    }
    private fun setClickListener(){

    }

    private fun qsBankItemClickListener(item: FeatureItem){
        sharedViewModel.featureItem = item

        when(item.featureCode){

            Features.TOPIC_VITTIK_JOB_SOLUTIONS.code ->{
                sharedViewModel.step = 3
                sharedViewModel.isPDF = true
                sharedViewModel.topicId = Categories.PDF_TOPIC_VITTIK_JOB_SOLUTIONS.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)


            }

            Features.BCS_PRELIMINARY.code ->{
                sharedViewModel.step = 2
                sharedViewModel.isPDF = true
                sharedViewModel.topicId = Categories.PDF_BCS_PRELIMINARY.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)
            }

            Features.BANK_NIYOG.code ->{
                sharedViewModel.step = 2
                sharedViewModel.isPDF = true
                sharedViewModel.topicId = Categories.PDF_BANK_NIYOG.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)
            }

            Features.NINE_TEN_GRADE.code ->{
                sharedViewModel.step = 2
                sharedViewModel.isPDF = true
                sharedViewModel.topicId = Categories.PDF_NINE_TEN_GRADE.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)
            }

            Features.PRIMARY_AND_NTRCA.code ->{
                sharedViewModel.step = 2
                sharedViewModel.isPDF = true
                sharedViewModel.topicId = Categories.PDF_PRIMARY_AND_NTRCA.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)

            }

            Features.ELEVEN_TWENTY_GRADE.code ->{
                sharedViewModel.step = 2
                sharedViewModel.isPDF = true
                sharedViewModel.topicId = Categories.PDF_ELEVEN_TWENTY_GRADE.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)
            }

            Features.TOPIC_VITTIK_ONUSILON.code ->{
                sharedViewModel.step = 3
                sharedViewModel.isPDF = false
                sharedViewModel.topicId = IndentityCode.QuizCategory.TOPIC_VITTIK_ONUSILON.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)
            }

            Features.TOPIC_VITTIK_PORIKKHA.code -> {
                sharedViewModel.step = 2
                sharedViewModel.isPDF = false
                sharedViewModel.topicId = IndentityCode.QuizCategory.TOPIC_VITTIK_PORIKKHA.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)
            }

            Features.BISOY_VITTIK_PORIKKHA.code -> {
                sharedViewModel.step = 2
                sharedViewModel.isPDF = false
                sharedViewModel.topicId =IndentityCode.QuizCategory.BISOY_VITTIK_PORIKKHA.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)
            }


            Features.GRE_333.code ->{
                sharedViewModel.step = 2
                sharedViewModel.isPDF = true
                sharedViewModel.topicId = Categories.PDF_GRE_333.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)

            }

            Features.GRE_1000.code ->{
                sharedViewModel.step = 2
                sharedViewModel.isPDF = true
                sharedViewModel.topicId = Categories.PDF_VOCABULARY_GREE_1000.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)

            }

            Features.WORD_SMART_1.code ->{
                sharedViewModel.step = 2
                sharedViewModel.isPDF = true
                sharedViewModel.topicId = Categories.PDF_WORD_SMART_1.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)

            }

            Features.NEWSPAPER_EDITORIAL.code ->{
                sharedViewModel.step = 2
                sharedViewModel.isPDF = true
                sharedViewModel.topicId = Categories.PDF_NEWSPAPER_EDITORIAL.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)

            }

            Features.IDOMS_AND_PHRASES.code ->{
                sharedViewModel.step = 2
                sharedViewModel.isPDF = true
                sharedViewModel.topicId = Categories.PDF_IDOMS_AND_PHRASES.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)

            }

            Features.GROUP_VERB.code ->{
                sharedViewModel.step = 2
                sharedViewModel.isPDF = true
                sharedViewModel.topicId = Categories.PDF_GROUP_VERB.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)

            }

            Features.SAMPROTIK_THOTTHO.code ->{
                var bundle = Bundle()
                bundle.putString("URL",KeyConstant.SAMPROTIK_TOTTHO_WEBSITE_LINK)
                findNavController().navigate(R.id.action_dashboardFragment_to_webViewFragment,bundle)
            }

            Features.TEXTBOOK.code ->{
               showToast("Coming soon")
            }

            Features.SOMBADPOTRO.code ->{
                findNavController().navigate(R.id.action_dashboardFragment_to_sombadpotroFragment)
            }

            Features.BCS_SYLABUS_GUIDELINE.code ->{
                showToast("Coming soon")
            }

            Features.BCS_BISLESON.code ->{
                showToast("Coming soon")
            }

            Features.COURSE_ROUTINE.code ->{
                showToast("Coming soon")
            }

            Features.NIYOG_BIGGOPPTI.code ->{
                showToast("Coming soon")
            }

            Features.ANUBADOK.code ->{
                var bundle = Bundle()
                bundle.putString("URL",KeyConstant.ANUBADOK_WEBSITE_LINK)
                findNavController().navigate(R.id.action_dashboardFragment_to_webViewFragment,bundle)
            }

            Features.ITIHASER_PATAI.code ->{
                showToast("Coming soon")
            }

            Features.BANK_SYLABUS_GUIDELINE.code ->{
                showToast("Coming soon")
            }












        }



    }
    private fun initView(){
        mViewModel.getQsBankTopicList()
        mViewModel.getOnusilonTopicList()
        mViewModel.getVocabularyTopicList()
        mViewModel.getLearnByHeartTopicList()
        mViewModel.getMiscellaneousTopicListTopicList()

    }
    override fun setDefaultProperties() {
        val  activity = requireActivity()
        if (activity is DashboardActivity){
            activity.hideBackButton()
            activity.setActionBarTitle("Hello ")
            activity.showBottomNavBar()
            activity.showDrawerMenu()
        }
    }
}
