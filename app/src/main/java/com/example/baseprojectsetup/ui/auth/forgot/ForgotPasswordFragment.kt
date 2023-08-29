package com.example.baseprojectsetup.ui.auth.forgot

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
import com.example.baseprojectsetup.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ForgotPasswordFragment : com.mkrlabs.common.core.base.BaseFragment<ForgotPasswordViewModel, FragmentForgotPasswordBinding>() {

    override val mViewModel: ForgotPasswordViewModel by viewModels()


    override fun getViewBinding(): FragmentForgotPasswordBinding = FragmentForgotPasswordBinding.inflate(layoutInflater)

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
        mViewBinding.forgotPasswordConfirmButton.setOnClickListener {
            findNavController().navigate(R.id.action_forgotPasswordFragment_to_OTPFragment)
        }
    }

    override fun setDefaultProperties() {

    }
}