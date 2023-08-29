package com.example.baseprojectsetup.di

import com.example.baseprojectsetup.data.repository.AuthRepository
import com.example.baseprojectsetup.data.repositorylmpl.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class AuthRepositoryModule {

    @Binds
    abstract fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl) : AuthRepository
}