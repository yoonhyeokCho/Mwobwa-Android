package com.bongku.mwobwa.ui.search

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

    private val _searchContentsResponse = MutableLiveData<ContentsEntity>()
    val searchContentsResponse get() = _searchContentsResponse

    fun getSearchContents(
        mediaType: String,
        name: String,
        includeAdult: Boolean,
        page: Int
    ) {
        viewModelScope.launch {
            _searchContentsResponse.value = contentsRepository.getSearchContents(
                mediaType, name, includeAdult, page
            )
        }
    }
}