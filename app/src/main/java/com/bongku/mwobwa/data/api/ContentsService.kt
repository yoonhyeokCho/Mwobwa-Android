package com.bongku.mwobwa.data.api

import com.bongku.mwobwa.data.entity.ContentsEntity
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ContentsService {
    @GET("discover/{mediaType}")
    suspend fun getContents(
        @Path("mediaType") mediaType: String,
        @Query("api_key") api_key: String,
        @Query("include_adult") includeAdult: Boolean,
        @Query("language") language: String = "ko-KR",
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_companies") withCompanies: String,
    ): ContentsEntity

    @GET("search/{mediaType}")
    suspend fun getSearchContents(
        @Path("mediaType") mediaType: String,
        @Query("api_key") api_key: String,
        @Query("query") name: String,
        @Query("include_adult") includeAdult: Boolean,
        @Query("language") language: String = "ko-KR",
        @Query("page") page: Int
    ): ContentsEntity
}