package com.bongku.mwobwa.di

import com.bongku.mwobwa.data.room.SavedDao
import com.bongku.mwobwa.data.room.SavedDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SavedContentRepositoryModule {
    @Singleton
    @Provides
    fun provideSavedDao(
        reviewDatabase: SavedDatabase): SavedDao{
        return reviewDatabase.savedDao()
    }
}