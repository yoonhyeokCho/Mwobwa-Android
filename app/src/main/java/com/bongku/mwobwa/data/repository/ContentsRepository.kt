package com.bongku.mwobwa.data.repository

import com.bongku.mwobwa.data.entity.ContentsEntity
import com.bongku.mwobwa.data.entity.TvContentsEntity

interface ContentsRepository {
    suspend fun getMovieContents(
        page: Int,
        withProvider: Int,
        watchRegion: String
    ): ContentsEntity

    suspend fun getTvContents(
        page: Int,
        withProvider: Int,
        watchRegion: String
    ): TvContentsEntity

    suspend fun getSearchMovieContents(
        name: String,
        page: Int,
    ): ContentsEntity

    suspend fun getSearchTvContents(
        name: String,
        page: Int,
    ): TvContentsEntity
}