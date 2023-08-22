package com.bongku.mwobwa.di

import android.content.Context
import com.bongku.mwobwa.data.room.SavedDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SavedDatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) : SavedDatabase{
        return SavedDatabase.getDatabse(context)
    }
}