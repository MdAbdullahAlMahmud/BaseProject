package com.mkrlabs.dashboard.di

import android.content.Context
import com.mkrlabs.common.core.base.network.NetworkFactory
import com.mkrlabs.dashboard.data.services.TopicService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object TopicServiceProvide {
    @Provides
    fun provideTopicService(@ApplicationContext applicationContext: Context) : TopicService =
        NetworkFactory.createService(applicationContext,TopicService::class.java)

}