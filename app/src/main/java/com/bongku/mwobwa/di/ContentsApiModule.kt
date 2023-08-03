package com.bongku.mwobwa.di

import com.bongku.mwobwa.data.api.ContentsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ContentsApiModule {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    @Singleton
    @Provides
    fun provideContentsService(retrofitBuilder: Retrofit.Builder): ContentsService =
        retrofitBuilder
            .baseUrl(BASE_URL)
            .build()
            .create(ContentsService::class.java)
}