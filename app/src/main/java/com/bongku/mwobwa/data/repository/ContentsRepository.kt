package com.bongku.mwobwa.data.repository

import com.bongku.mwobwa.data.entity.ContentsEntity

interface ContentsRepository {
    suspend fun getContents(
        mediaType: String,
        includeAdult: Boolean,
        page: Int,
        withCompanies: String
    ): ContentsEntity
}