package com.gpaddy.baseandroid.di

import com.gpaddy.baseandroid.network.NetworkRequest
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
    fun providesAudioRequest(): NetworkRequest {
        return NetworkRequest.create()
    }
}