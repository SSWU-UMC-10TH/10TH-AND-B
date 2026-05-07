package com.example.umc_week2.di

import com.example.umc_week2.data.repository.LocalRepositoryImpl
import com.example.umc_week2.data.repository.RemoteRepositoryImpl
import com.example.umc_week2.domain.repository.LocalRepository
import com.example.umc_week2.domain.repository.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRemoteRepository(
        impl: RemoteRepositoryImpl
    ): RemoteRepository

    @Binds
    @Singleton
    abstract fun bindLocalRepository(
        impl: LocalRepositoryImpl
    ): LocalRepository
}