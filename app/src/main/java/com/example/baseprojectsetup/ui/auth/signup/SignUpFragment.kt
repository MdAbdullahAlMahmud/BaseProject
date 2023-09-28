package com.example.baseprojectsetup.ui.auth.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.baseprojectsetup.R
import com.example.baseprojectsetup.data.model.request.RegistrationRequest
import com.mkrlabs.common.core.base.BaseFragment
import com.example.baseprojectsetup.databinding.FragmentLoginBinding
import com.example.baseprojectsetup.databinding.FragmentSignUpBinding
import com.example.baseprojectsetup.ui.MainActivity
import com.mkrlabs.common.core.base.interfaces.CommunicatorImpl
import com.mkrlabs.common.core.base.utils.AppConstant.IS_AUTHENTICATED
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
        setObserver()

    }

    private fun  setObserver(){
        mViewModel.registrationResponse.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let {
                if (it.userEmail?.isNotEmpty() == true){
                    setBooleanPreferenceData(IS_AUTHENTICATED,true)
                    CommunicatorImpl.callback?.gotoDashboard()
                }
            }
        })
    }
    private fun setOnClickListener(){
        mViewBinding.start.setOnClickListener {

            if (validateInput()){
               mViewModel.requestRegistration(registrationBody())
            }
        }
        mViewBinding.loginLl.setOnClickListener{
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

    }

    fun registrationBody() : RegistrationRequest = RegistrationRequest().apply {
        name = mViewBinding.name.text.toString()
        email = mViewBinding.email.text.toString()
        phone = mViewBinding.phone.text.toString()
        password = mViewBinding.password.text.toString()
        deviceId = getDeviceUniqueId()

    }


    fun validateInput() : Boolean{
        var isValid = true
        if (mViewBinding.name.text.isEmpty()){
            mViewBinding.name.error = "required"
            isValid = false
        }
        if (mViewBinding.email.text.isEmpty()){
            mViewBinding.email.error = "required"
            isValid = false
        }
        if (mViewBinding.phone.text.isEmpty()){
            mViewBinding.phone.error = "required"
            isValid = false
        }
        if (mViewBinding.password.text.isEmpty()){
            mViewBinding.password.error = "required"
            isValid = false
        }

        return isValid
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