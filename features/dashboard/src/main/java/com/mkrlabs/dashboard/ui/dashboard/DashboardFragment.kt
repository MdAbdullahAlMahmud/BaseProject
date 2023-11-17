package com.mkrlabs.dashboard.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mkrlabs.common.core.base.BaseFragment
import com.mkrlabs.common.core.base.utils.AppConstant
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
import com.mkrlabs.dashboard.data.model.response.TopicItem
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
        showToast("User Id ${getStringPreferenceData(USER_ID)}")
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

        mViewBinding.bcsCV.setOnClickListener {
            val  item = FeatureItem("1","BCS প্রস্তুতি","", com.mkrlabs.common.R.drawable.topic1)
            sharedViewModel.featureItem = item
            sharedViewModel.step = 3
            sharedViewModel.isPDF = true
            sharedViewModel.topicId = Categories.PDF_BCS_PROSTUTI.code
            findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)
        }

        mViewBinding.bankProstuti.setOnClickListener {
            val  item = FeatureItem("2","Bank প্রস্তুতি","", com.mkrlabs.common.R.drawable.topic1)
            sharedViewModel.featureItem = item
            sharedViewModel.step = 3
            sharedViewModel.isPDF = true
            sharedViewModel.topicId = Categories.PDF_BANK_PROSTUTI.code
            findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)
        }



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


            /**
             * Onusilon
             */

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

            Features.MODEL_TEST.code -> {
                sharedViewModel.step = 2
                sharedViewModel.isPDF = false
                sharedViewModel.topicId =IndentityCode.QuizCategory.MODEL_TEST.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)
            }

            Features.JOB_SOLUTION.code -> {
                sharedViewModel.step = 3
                sharedViewModel.isPDF = false
                sharedViewModel.topicId =IndentityCode.QuizCategory.JOB_SOLUTION.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)
            }






            //-----------------------------------------------------------------------------------------------


            /**
             * Vocabulary
             */


            Features.GRE_333.code ->{
                sharedViewModel.step = 1
                sharedViewModel.isPDF = true
                var topicItem = TopicItem(cid = Categories.PDF_GRE_333.code )
                sharedViewModel.topicItem = topicItem
                findNavController().navigate(R.id.action_dashboardFragment_to_singlePdfListFragment)

            }

            Features.GRE_1000.code ->{
                sharedViewModel.step = 1
                sharedViewModel.isPDF = true
                var topicItem = TopicItem(cid =Categories.PDF_VOCABULARY_GREE_1000.code )
                sharedViewModel.topicItem = topicItem
                findNavController().navigate(R.id.action_dashboardFragment_to_singlePdfListFragment)

            }

            Features.WORD_SMART_1.code ->{
                sharedViewModel.step = 1
                sharedViewModel.isPDF = true
                var topicItem = TopicItem(cid = Categories.PDF_WORD_SMART_1.code )
                sharedViewModel.topicItem = topicItem
                findNavController().navigate(R.id.action_dashboardFragment_to_singlePdfListFragment)

            }

             Features.WORD_SMART_2.code ->{
                sharedViewModel.step = 1
                sharedViewModel.isPDF = true
                 var topicItem = TopicItem(cid = Categories.PDF_WORD_SMART_2.code )
                 sharedViewModel.topicItem = topicItem
                 findNavController().navigate(R.id.action_dashboardFragment_to_singlePdfListFragment)

            }

             Features.MAGOOSH.code ->{
                sharedViewModel.step = 1
                sharedViewModel.isPDF = true
                 var topicItem = TopicItem(cid = Categories.PDF_MAGOOSH.code )
                 sharedViewModel.topicItem = topicItem
                 findNavController().navigate(R.id.action_dashboardFragment_to_singlePdfListFragment)

            }

            Features.MAHATTAN.code ->{
                sharedViewModel.step = 1
                sharedViewModel.isPDF = true
                var topicItem = TopicItem(cid = Categories.PDF_MANHATTAN.code )
                sharedViewModel.topicItem = topicItem
                findNavController().navigate(R.id.action_dashboardFragment_to_singlePdfListFragment)

            }

            Features.PREVIOUS_VCAB.code ->{
                sharedViewModel.step = 1
                sharedViewModel.isPDF = true
                var topicItem = TopicItem(cid = Categories.PDF_PREVIOUS_VOCABULARY.code )
                sharedViewModel.topicItem = topicItem
                findNavController().navigate(R.id.action_dashboardFragment_to_singlePdfListFragment)

            }
            Features.SPELLING.code ->{
                sharedViewModel.step = 1
                sharedViewModel.isPDF = true
                var topicItem = TopicItem(cid = Categories.PDF_SPELLING.code )
                sharedViewModel.topicItem = topicItem
                findNavController().navigate(R.id.action_dashboardFragment_to_singlePdfListFragment)

            }






            //--------------------------------------------------------------------------------------------

            /**
             * Learn by Heart
             */
            Features.NEWSPAPER_EDITORIAL.code ->{
                sharedViewModel.step = 1
                sharedViewModel.isPDF = true
                var topicItem = TopicItem(cid = Categories.PDF_NEWSPAPER_EDITORIAL.code )
                sharedViewModel.topicItem = topicItem
                findNavController().navigate(R.id.action_dashboardFragment_to_singlePdfListFragment)

            }

            Features.IDOMS_AND_PHRASES.code ->{
                sharedViewModel.step = 1
                sharedViewModel.isPDF = true
                var topicItem = TopicItem(cid = Categories.PDF_IDOMS_AND_PHRASES.code )
                sharedViewModel.topicItem = topicItem
                findNavController().navigate(R.id.action_dashboardFragment_to_singlePdfListFragment)

            }

            Features.GROUP_VERB.code ->{
                sharedViewModel.step = 1
                sharedViewModel.isPDF = true
                var topicItem = TopicItem(cid = Categories.PDF_GROUP_VERB.code )
                sharedViewModel.topicItem = topicItem
                findNavController().navigate(R.id.action_dashboardFragment_to_singlePdfListFragment)

            }


            Features.APPROPRIATE_PREPOSITION.code ->{
                sharedViewModel.step = 1
                sharedViewModel.isPDF = true
                var topicItem = TopicItem(cid = Categories.PDF_APPROPRIATE_PREPOSITION.code )
                sharedViewModel.topicItem = topicItem
                findNavController().navigate(R.id.action_dashboardFragment_to_singlePdfListFragment)

            }

            Features.ANALOGY.code ->{
                sharedViewModel.step = 1
                sharedViewModel.isPDF = true
                var topicItem = TopicItem(cid = Categories.PDF_ANALOGY.code )
                sharedViewModel.topicItem = topicItem
                findNavController().navigate(R.id.action_dashboardFragment_to_singlePdfListFragment)
            }

            Features.ONE_WORD_SUBJECT.code ->{
                sharedViewModel.step = 1
                sharedViewModel.isPDF = true
                var topicItem = TopicItem(cid = Categories.PDF_ONE_WORD_SUBSTITUTION.code )
                sharedViewModel.topicItem = topicItem
                findNavController().navigate(R.id.action_dashboardFragment_to_singlePdfListFragment)
            }

            Features.PROVERB.code ->{
                sharedViewModel.step = 1
                sharedViewModel.isPDF = true
                var topicItem = TopicItem(cid = Categories.PDF_PROVERB.code )
                sharedViewModel.topicItem = topicItem
                findNavController().navigate(R.id.action_dashboardFragment_to_singlePdfListFragment)
            }

            Features.TRANSLATION.code ->{
                sharedViewModel.step = 1
                sharedViewModel.isPDF = true
                var topicItem = TopicItem(cid = Categories.PDF_TRANSLATION.code )
                sharedViewModel.topicItem = topicItem
                findNavController().navigate(R.id.action_dashboardFragment_to_singlePdfListFragment)
            }

        // ------------------------------------------------------------------------------------------


            /**
             * Miscellaneous
             */




            Features.SAMPROTIK_THOTTHO.code ->{
                sharedViewModel.step = 2
                sharedViewModel.isPDF = true
                sharedViewModel.topicId = IndentityCode.MiscellaneousCategory.SAMPROTIK_THOTTHO.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)
            }

            Features.TEXTBOOK.code ->{
                sharedViewModel.step = 3
                sharedViewModel.isPDF = true
                sharedViewModel.topicId = IndentityCode.MiscellaneousCategory.TEXTBOOK.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)
            }

            Features.SOMBADPOTRO.code ->{
                findNavController().navigate(R.id.action_dashboardFragment_to_sombadpotroFragment)
            }

            Features.BCS_SYLABUS_GUIDELINE.code ->{
                sharedViewModel.step = 1
                sharedViewModel.isPDF = true
                var topicItem = TopicItem(cid = Categories.PDF_BCS_SYLYBUS_AND_GUIDELINE.code )
                sharedViewModel.topicItem = topicItem
                findNavController().navigate(R.id.action_dashboardFragment_to_singlePdfListFragment)
            }

            Features.BCS_BISLESON.code ->{
                sharedViewModel.step = 1
                sharedViewModel.isPDF = true
                var topicItem = TopicItem(cid = Categories.PDF_BCS_PROSNO_BISLESON.code )
                sharedViewModel.topicItem = topicItem
                findNavController().navigate(R.id.action_dashboardFragment_to_singlePdfListFragment)

            }

            Features.COURSE_ROUTINE.code ->{
                sharedViewModel.step = 1
                sharedViewModel.isPDF = true
                var topicItem = TopicItem(cid = Categories.PDF_BCS_COURSE_ROUTINE.code )
                sharedViewModel.topicItem = topicItem
                findNavController().navigate(R.id.action_dashboardFragment_to_singlePdfListFragment)
            }

            Features.NIYOG_BIGGOPPTI.code ->{
                sharedViewModel.step = 1
                sharedViewModel.isPDF = true
                var topicItem = TopicItem(cid = Categories.PDF_BCS_NIYOG_BIGOPTTI.code )
                sharedViewModel.topicItem = topicItem
                findNavController().navigate(R.id.action_dashboardFragment_to_singlePdfListFragment)
            }

            Features.ANUBADOK.code ->{
                var bundle = Bundle()
                bundle.putString("URL",KeyConstant.ANUBADOK_WEBSITE_LINK)
                findNavController().navigate(R.id.action_dashboardFragment_to_webViewFragment,bundle)
            }

            Features.ITIHASER_PATAI.code ->{
                sharedViewModel.step = 2
                sharedViewModel.isPDF = true
                sharedViewModel.topicId = IndentityCode.MiscellaneousCategory.ITIHASER_PATAI.code
                findNavController().navigate(R.id.action_dashboardFragment_to_topicFragment)
            }

            Features.BANK_SYLABUS_GUIDELINE.code ->{
                sharedViewModel.step = 1
                sharedViewModel.isPDF = true
                var topicItem = TopicItem(cid = Categories.PDF_BANK_SYLYBUS_AND_GUIDELINE.code )
                sharedViewModel.topicItem = topicItem
                findNavController().navigate(R.id.action_dashboardFragment_to_singlePdfListFragment)            }












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
