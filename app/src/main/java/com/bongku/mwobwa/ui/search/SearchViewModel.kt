package com.bongku.mwobwa.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bongku.mwobwa.data.entity.ContentsEntity
import com.bongku.mwobwa.data.repository.ContentsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val contentsRepository: ContentsRepositoryImpl
) : ViewModel() {

    private val _searchMovieContentsResponse = MutableLiveData<ContentsEntity>()
    val searchMovieContentsResponse get() = _searchMovieContentsResponse

    private val _searchTvContentsResponse = MutableLiveData<ContentsEntity>()
    val searchTvContentsResponse get() = _searchTvContentsResponse

    fun getSearchContents(
        mediaType: String,
        name: String,
        includeAdult: Boolean,
        page: Int
    ) {
        viewModelScope.launch {
            if (mediaType == "movie") {
                _searchMovieContentsResponse.value = contentsRepository.getSearchContents(
                    mediaType, name, includeAdult, page
                )
            } else {
                _searchTvContentsResponse.value = contentsRepository.getSearchContents(
                    mediaType, name, includeAdult, page
                )
            }
        }
    }
}