package com.company.mysocialnetworkapp.di

import android.content.Context
import com.company.mysocialnetworkapp.repository.TokenRepository
import com.company.mysocialnetworkapp.repository.TokenRepositoryPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class TokenRepositoryModule {

    @Provides
    fun provideTokenRepositoryModule(
        @ApplicationContext context: Context
    ): TokenRepository = TokenRepositoryPreferences(context)
}