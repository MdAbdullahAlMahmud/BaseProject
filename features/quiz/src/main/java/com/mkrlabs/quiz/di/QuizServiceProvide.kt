package com.mkrlabs.quiz.di

import android.content.Context
import com.mkrlabs.common.core.base.network.NetworkFactory
import com.mkrlabs.quiz.data.services.QuizService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object QuizServiceProvide {
    @Provides
    fun provideQuizService(@ApplicationContext applicationContext: Context) : QuizService =
        NetworkFactory.createService(applicationContext,QuizService::class.java)

}