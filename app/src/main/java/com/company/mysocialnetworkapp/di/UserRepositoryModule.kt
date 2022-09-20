package com.company.mysocialnetworkapp.di

import com.company.mysocialnetworkapp.repository.UserRepository
import com.company.mysocialnetworkapp.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface UserRepositoryModule {

    @Binds
    @Singleton
    fun provideUserRepositoryImpl(userRepositoryImpl: UserRepositoryImpl): UserRepository
}