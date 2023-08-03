package com.bongku.mwobwa.data.repository

import com.bongku.mwobwa.BuildConfig
import com.bongku.mwobwa.data.api.ContentsService
import com.bongku.mwobwa.data.entity.ContentsEntity
import javax.inject.Inject

class ContentsRepositoryImpl @Inject constructor(
    private val contentsApi: ContentsService
) : ContentsRepository {
    override suspend fun getContents(
        mediaType: String,
        includeAdult: Boolean,
        page: Int,
        withCompanies: String
    ): ContentsEntity {
        return contentsApi.getContents(
            api_key=BuildConfig.TMDB_API_KEY,
            mediaType = mediaType,
            includeAdult = includeAdult,
            page = page,
            withCompanies = withCompanies
        )
    }
}