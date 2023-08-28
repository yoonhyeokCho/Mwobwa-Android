package com.bongku.mwobwa.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bongku.mwobwa.MwobwaApplication
import com.bongku.mwobwa.data.datastore.MwobwaDataStore
import com.bongku.mwobwa.data.entity.ContentsEntity
import com.bongku.mwobwa.data.entity.ContentsResult
import com.bongku.mwobwa.data.entity.SavedContentEntity
import com.bongku.mwobwa.data.entity.TvContentsEntity
import com.bongku.mwobwa.data.repository.ContentsRepositoryImpl
import com.bongku.mwobwa.data.repository.SavedContentRepositoryImpl
import com.bongku.mwobwa.extension.LiveDataExtension
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val contentsRepository: ContentsRepositoryImpl,
    private val savedRepository: SavedContentRepositoryImpl
) : ViewModel() {

    private val _contentsMovieResponse = LiveDataExtension<ContentsEntity>()
    val contentsMovieResponse get() = _contentsMovieResponse

    private val _contentsTvResponse = LiveDataExtension<TvContentsEntity>()
    val contentsTvResponse get() = _contentsTvResponse

    private val _ottCompany = LiveDataExtension<List<String>>()
    val ottCompany get() = _ottCompany

    var previousContents: List<ContentsResult>? = null

    private val mwobwaDataStore: MwobwaDataStore =
        MwobwaApplication.mwobwaApplication.mwobwaDataStore

    fun getContents(
        mediaType: String,
        page: Int,
        withProvider: Int,
        watchRegion: String
    ) {
        viewModelScope.launch {
            if (mediaType == "movie") {
                val response = contentsRepository.getMovieContents(
                    page,
                    withProvider,
                    watchRegion
                )
                response.results.forEach {
                    when (withProvider) {
                        2 -> {
                            it.company = "apple tv"
                        }
                        8 -> {
                            it.company = "netflix"
                        }
                        else -> {
                            it.company = "disney"
                        }
                    }
                }
                _contentsMovieResponse.value = response
            } else {
                val response = contentsRepository.getTvContents(
                    page,
                    withProvider,
                    watchRegion
                )
                response.results.forEach {
                    when (withProvider) {
                        2 -> {
                            it.company = "apple tv"
                        }
                        8 -> {
                            it.company = "netflix"
                        }
                        else -> {
                            it.company = "disney"
                        }
                    }
                }
                _contentsTvResponse.value = response
            }

        }
    }

    fun getOttCompany() = viewModelScope.launch {
        _ottCompany.value = mwobwaDataStore.getOttCompany()
    }

    fun insertRoomData(savedContentEntity: SavedContentEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            savedRepository.insertContentData(savedContentEntity)
        }
}