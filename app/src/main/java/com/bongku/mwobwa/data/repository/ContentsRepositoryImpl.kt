package com.bongku.mwobwa.data.repository

import com.bongku.mwobwa.BuildConfig
import com.bongku.mwobwa.data.api.ContentsService
import com.bongku.mwobwa.data.entity.ContentsEntity
import com.bongku.mwobwa.data.entity.TvContentsEntity
import javax.inject.Inject

class ContentsRepositoryImpl @Inject constructor(
    private val contentsApi: ContentsService
) : ContentsRepository {
    override suspend fun getMovieContents(
        page: Int,
        withProvider: Int,
        watchRegion: String
    ): ContentsEntity {
        return contentsApi.getMovieContents(
            api_key = BuildConfig.TMDB_API_KEY,
            with_provider = withProvider,
            page = page,
            watch_region = watchRegion
        )
    }

    override suspend fun getSearchMovieContents(
        name: String,
        page: Int
    ): ContentsEntity {
        return contentsApi.getSearchMovieContents(
            api_key = BuildConfig.TMDB_API_KEY,
            name = name,
            page = page
        )
    }

    override suspend fun getTvContents(
        page: Int,
        withProvider: Int,
        watchRegion: String
    ): TvContentsEntity {
        return contentsApi.getTvContents(
            api_key = BuildConfig.TMDB_API_KEY,
            with_provider = withProvider,
            page = page,
            watch_region = watchRegion
        )
    }

    override suspend fun getSearchTvContents(
        name: String,
        page: Int
    ): TvContentsEntity {
        return contentsApi.getSearchTvContents(
            api_key = BuildConfig.TMDB_API_KEY,
            name = name,
            page = page
        )
    }
}