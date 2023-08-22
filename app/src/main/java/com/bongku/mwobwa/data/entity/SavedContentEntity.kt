package com.bongku.mwobwa.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_content_table")
data class SavedContentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val score: Double,
    val count: Long,
    val company: String,
    val poster_path: String
)
