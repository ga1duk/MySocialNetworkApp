package com.company.mysocialnetworkapp.di

import com.company.mysocialnetworkapp.repository.PostRepository
import com.company.mysocialnetworkapp.repository.PostRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface PostRepositoryModule {
    @Binds
    @Singleton
    fun bindsPostRepositoryImpl(postRepositoryImpl: PostRepositoryImpl): PostRepository
}