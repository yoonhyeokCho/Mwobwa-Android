package com.bongku.mwobwa.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bongku.mwobwa.MwobwaApplication
import com.bongku.mwobwa.data.datastore.MwobwaDataStore
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

    private val _ottCompany = MutableLiveData<List<String>>()
    val ottCompany get() = _ottCompany

    private val mwobwaDataStore: MwobwaDataStore =
        MwobwaApplication.mwobwaApplication.mwobwaDataStore

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
            response.results.forEach {
                it.company = withCompanies
            }
            _contentsResponse.value = response
        }
    }

    fun getOttCompany() = viewModelScope.launch {
        _ottCompany.value = mwobwaDataStore.getOttCompany()
    }

}