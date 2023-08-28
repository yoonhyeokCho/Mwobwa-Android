package com.bongku.mwobwa.data.entity

import com.google.gson.annotations.SerializedName

data class TvContentsEntity(
    val results: List<TvContentsResult>
)

data class TvContentsResult(
    @SerializedName("genre_ids")
    val genreIDS: List<Long>,
    @SerializedName("original_name")
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val name: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Long,
    var company: String = "",
    val poster_path: String
)