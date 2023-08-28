package com.bongku.mwobwa.data.api

import com.bongku.mwobwa.data.entity.ContentsEntity
import com.bongku.mwobwa.data.entity.TvContentsEntity
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ContentsService {
    @GET("discover/movie")
    suspend fun getMovieContents(
        @Query("api_key") api_key: String,
        @Query("language") language: String = "ko-KR",
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_watch_providers") with_provider: Int,
        @Query("watch_region") watch_region: String
    ): ContentsEntity

    @GET("discover/tv")
    suspend fun getTvContents(
        @Query("api_key") api_key: String,
        @Query("language") language: String = "ko-KR",
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_watch_providers") with_provider: Int,
        @Query("watch_region") watch_region: String
    ): TvContentsEntity


    @GET("search/movie")
    suspend fun getSearchMovieContents(
        @Query("api_key") api_key: String,
        @Query("query") name: String,
        @Query("language") language: String = "ko-KR",
        @Query("page") page: Int
    ): ContentsEntity

    @GET("search/tv")
    suspend fun getSearchTvContents(
        @Query("api_key") api_key: String,
        @Query("query") name: String,
        @Query("language") language: String = "ko-KR",
        @Query("page") page: Int
    ): TvContentsEntity
}