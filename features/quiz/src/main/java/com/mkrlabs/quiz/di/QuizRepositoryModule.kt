package com.mkrlabs.quiz.di

import com.mkrlabs.quiz.data.repository.QuizRepository
import com.mkrlabs.quiz.data.repositoryImpl.QuizRepositoryImpl
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