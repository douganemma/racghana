package com.racghana.mobile.di

import com.racghana.mobile.data.api.ApiClient
import com.racghana.mobile.data.api.ApiService
import com.racghana.mobile.data.api.createService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return createService(ApiService::class.java)
    }
}
