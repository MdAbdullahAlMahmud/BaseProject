package com.example.baseprojectsetup.ui.auth.otp_verification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.baseprojectsetup.R
import com.mkrlabs.common.core.base.BaseFragment
import com.example.baseprojectsetup.databinding.FragmentForgotPasswordBinding
import com.example.baseprojectsetup.databinding.FragmentOTPBinding
import com.example.baseprojectsetup.ui.MainActivity
import com.example.baseprojectsetup.ui.auth.forgot.ForgotPasswordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OTPFragment : com.mkrlabs.common.core.base.BaseFragment<OTPViewModel, FragmentOTPBinding>() {
    override val mViewModel: OTPViewModel by viewModels()

    override fun getViewBinding(): FragmentOTPBinding = FragmentOTPBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()
    }
    private fun initView(){
        val  activity = requireActivity()
        if (activity is MainActivity){
            activity.hideActionBar()
        }
    }


    private fun setOnClickListener(){
        mViewBinding.codeVerifyButton.setOnClickListener {
            findNavController().navigate(R.id.action_OTPFragment_to_resetPasswordFragment)
        }
    }
    override fun setDefaultProperties() {

    }

}