package com.gpaddy.baseandroid.di

import com.gpaddy.baseandroid.network.NetworkRequest
import com.gpaddy.baseandroid.network.VideoRequest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesNetworkRequest(): NetworkRequest {
        return NetworkRequest.create()
    }
    @Singleton
    @Provides
    fun providesVideoRequest(): VideoRequest {
        return VideoRequest.create()
    }
}