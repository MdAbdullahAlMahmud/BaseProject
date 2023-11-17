package com.mkrlabs.live_quiz.di

import com.mkrlabs.live_quiz.data.repository.QuizRepository
import com.mkrlabs.live_quiz.data.repositoryImpl.QuizRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class QuizRepositoryModule {
    @Binds
    abstract fun  provideQuizRepository(quizRepositoryImpl: QuizRepositoryImpl) : QuizRepository
}