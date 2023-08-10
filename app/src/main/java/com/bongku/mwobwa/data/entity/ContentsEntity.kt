package com.bongku.mwobwa.data.entity

import com.google.gson.annotations.SerializedName

data class ContentsEntity(
    val results: List<ContentsResult>
)

data class ContentsResult(
    val adult: Boolean,
    @SerializedName("genre_ids")
    val genreIDS: List<Long>,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Long,
    var company: String = ""
)