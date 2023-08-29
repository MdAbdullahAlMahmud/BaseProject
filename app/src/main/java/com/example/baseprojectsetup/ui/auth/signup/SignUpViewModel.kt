package com.example.baseprojectsetup.ui.auth.signup

import com.mkrlabs.common.core.base.BaseViewModel
import com.example.baseprojectsetup.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel  @Inject constructor(
    private val authRepository: AuthRepository
) : com.mkrlabs.common.core.base.BaseViewModel() {


}