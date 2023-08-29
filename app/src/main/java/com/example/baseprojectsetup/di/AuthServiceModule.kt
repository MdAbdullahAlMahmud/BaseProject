package com.example.baseprojectsetup.di

import android.content.Context
import com.mkrlabs.common.core.base.network.NetworkFactory
import com.example.baseprojectsetup.data.services.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AuthServiceModule {

    @Provides
    fun  provideAuthService(@ApplicationContext applicationContext: Context) : AuthService =
        NetworkFactory.createService(applicationContext,AuthService::class.java)
}