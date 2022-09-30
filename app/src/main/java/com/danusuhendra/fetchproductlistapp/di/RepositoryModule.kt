package com.danusuhendra.fetchproductlistapp.di

import com.danusuhendra.fetchproductlistapp.network.ApiInterface
import com.danusuhendra.fetchproductlistapp.network.NetworkMapper
import com.danusuhendra.fetchproductlistapp.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(retrofit: ApiInterface, networkMapper: NetworkMapper) : MainRepository {
        return MainRepository(retrofit, networkMapper)
    }
}