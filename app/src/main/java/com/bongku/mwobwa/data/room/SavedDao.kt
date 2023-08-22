package com.bongku.mwobwa.data.room

import androidx.room.*
import com.bongku.mwobwa.data.entity.SavedContentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedDao {
    @Query("SELECT * FROM saved_content_table")
    fun getContent(): Flow<List<SavedContentEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertReview(reviewData: SavedContentEntity)

    @Delete
    fun deleteReview(reviewData: SavedContentEntity)
}