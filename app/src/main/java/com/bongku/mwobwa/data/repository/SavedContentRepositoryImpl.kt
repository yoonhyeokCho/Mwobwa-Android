package com.bongku.mwobwa.data.repository

import com.bongku.mwobwa.data.entity.SavedContentEntity
import com.bongku.mwobwa.data.room.SavedDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SavedContentRepositoryImpl @Inject constructor(
    private val savedDao: SavedDao
) : SavedContentRepository {

    override fun getContentData(): Flow<List<SavedContentEntity>> {
        return savedDao.getContent()
    }

    override fun insertContentData(savedContentEntity: SavedContentEntity) {
        savedDao.insertReview(savedContentEntity)
    }

    override fun deleteContentData(savedContentEntity: SavedContentEntity) {
        savedDao.deleteReview(savedContentEntity)
    }
}