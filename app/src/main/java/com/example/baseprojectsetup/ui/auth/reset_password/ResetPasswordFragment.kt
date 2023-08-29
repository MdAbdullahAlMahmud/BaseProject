package com.example.baseprojectsetup.ui.auth.reset_password

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.baseprojectsetup.R
import com.mkrlabs.common.core.base.BaseFragment
import com.example.baseprojectsetup.databinding.FragmentOTPBinding
import com.example.baseprojectsetup.databinding.FragmentResetPasswordBinding
import com.example.baseprojectsetup.ui.MainActivity
import com.example.baseprojectsetup.ui.auth.otp_verification.OTPViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPasswordFragment : com.mkrlabs.common.core.base.BaseFragment<ResetPasswordViewModel, FragmentResetPasswordBinding>() {


    override val mViewModel: ResetPasswordViewModel by viewModels()

    override fun getViewBinding(): FragmentResetPasswordBinding = FragmentResetPasswordBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    private fun initView(){
        val  activity = requireActivity()
        if (activity is MainActivity){
            activity.hideActionBar()
        }
    }
    override fun setDefaultProperties() {

    }


}