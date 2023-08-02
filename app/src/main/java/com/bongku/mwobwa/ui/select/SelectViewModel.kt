package com.bongku.mwobwa.ui.select

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bongku.mwobwa.MwobwaApplication
import com.bongku.mwobwa.data.datastore.MwobwaDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectViewModel @Inject constructor() : ViewModel() {
    private val _firstAccess = MutableLiveData<Boolean>()
    val firstAccess get() = _firstAccess

    private val mwobwaDataStore: MwobwaDataStore = MwobwaApplication.mwobwaApplication.mwobwaDataStore

    fun setFirstAccess() = viewModelScope.launch {
        mwobwaDataStore.setFirstAccess()
    }

    fun checkFirstAccess() = viewModelScope.launch {
        val accessFlag = mwobwaDataStore.getFirstAccess()
        _firstAccess.value = accessFlag
    }

}