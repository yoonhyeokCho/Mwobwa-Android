package com.bongku.mwobwa.data.repository

import com.bongku.mwobwa.data.entity.SavedContentEntity
import kotlinx.coroutines.flow.Flow

interface SavedContentRepository {

    fun getContentData(): Flow<List<SavedContentEntity>>

    fun insertContentData(savedContentEntity: SavedContentEntity)

    fun deleteContentData(savedContentEntity: SavedContentEntity)
}