package com.example.baseprojectsetup.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.baseprojectsetup.R
import com.example.baseprojectsetup.core.base.BaseActivity
import com.example.baseprojectsetup.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>() {

    override val mViewModel: HomeViewModel by viewModels()

    override fun getViewBinding(): ActivityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
    override fun setLanguageTexts() {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
    }

    override fun setActionBarTitle(title: String?) {
        TODO("Not yet implemented")
    }

    override fun setActionBar() {
        TODO("Not yet implemented")
    }

    override fun hideActionBar() {
        TODO("Not yet implemented")
    }

    override fun onBackPressListener() {
        TODO("Not yet implemented")
    }
}