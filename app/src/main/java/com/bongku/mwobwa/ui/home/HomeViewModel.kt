package com.bongku.mwobwa.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bongku.mwobwa.data.entity.ContentsEntity
import com.bongku.mwobwa.data.repository.ContentsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val contentsRepository: ContentsRepositoryImpl
) : ViewModel() {

    private val _contentsResponse = MutableLiveData<ContentsEntity>()
    val contentsResponse get() = _contentsResponse

    fun getContents(
        mediaType: String,
        includeAdult: Boolean,
        page: Int,
        withCompanies: String
    ) {
        viewModelScope.launch {
            val response = contentsRepository.getContents(
                mediaType,
                includeAdult,
                page,
                withCompanies
            )
            _contentsResponse.value = response
        }
    }
}