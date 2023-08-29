package com.example.baseprojectsetup.ui.auth.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.baseprojectsetup.R
import com.mkrlabs.common.core.base.BaseFragment
import com.mkrlabs.common.core.base.interfaces.Communicator
import com.mkrlabs.common.core.base.interfaces.CommunicatorImpl
import com.example.baseprojectsetup.data.model.request.LoginRequest
import com.example.baseprojectsetup.databinding.FragmentLoginBinding
import com.example.baseprojectsetup.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment :
    com.mkrlabs.common.core.base.BaseFragment<LoginViewModel, FragmentLoginBinding>() {

    override val mViewModel: LoginViewModel by  viewModels()

    override fun getViewBinding(): FragmentLoginBinding = FragmentLoginBinding.inflate(layoutInflater)


    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val IS_AUTHENTICATED = "IS_AUTHENTICATED"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireActivity(). getSharedPreferences("PREF", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        initView()
        uiOptimization()
        setOnClickListener()
        setObserver()
    }

    private fun uiOptimization(){
        mViewBinding.email.showKeyboard(requireActivity())
    }
    private fun setObserver(){
        mViewModel.loginResponse.observe(viewLifecycleOwner, Observer {



            it.getContentIfNotHandled()?.apply {
                println("$userName   $userId")
                editor.putBoolean(IS_AUTHENTICATED,true).apply()
                CommunicatorImpl.callback?.gotoDashboard()
            }

        })
    }

    private fun setOnClickListener(){

        mViewBinding.loginBtn.setOnClickListener {
            mViewModel.isValid(mViewBinding.email, mViewBinding.password )


            if (mViewModel.isValidInput){

                mViewModel.login(getLoginRequest())
            }
        }

        mViewBinding.registerNowTv.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }


        mViewBinding.forgotPasswordTV.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

    }

    fun getLoginRequest() : LoginRequest {
        return LoginRequest().apply {
            email = mViewBinding.email.text.toString()
            password = mViewBinding.password.text.toString()
            deviceId = getDeviceUniqueId()
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