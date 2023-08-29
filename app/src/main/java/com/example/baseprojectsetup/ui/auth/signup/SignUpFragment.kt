package com.example.baseprojectsetup.ui.auth.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.baseprojectsetup.R
import com.mkrlabs.common.core.base.BaseFragment
import com.example.baseprojectsetup.databinding.FragmentLoginBinding
import com.example.baseprojectsetup.databinding.FragmentSignUpBinding
import com.example.baseprojectsetup.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class SignUpFragment : BaseFragment<SignUpViewModel, FragmentSignUpBinding>() {

    override val mViewModel: SignUpViewModel by viewModels()

    override fun getViewBinding(): FragmentSignUpBinding  = FragmentSignUpBinding.inflate(layoutInflater)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()

    }

    private fun setOnClickListener(){
        mViewBinding.loginLl.setOnClickListener{
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

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