package com.mkrlabs.dashboard.di

import com.mkrlabs.dashboard.data.repository.TopicRepository
import com.mkrlabs.dashboard.data.repositoryImpl.TopicRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class TopicRepositoryModule {

    @Binds
    abstract fun provideTopicRepository(topicRepositoryImpl: TopicRepositoryImpl) : TopicRepository
}