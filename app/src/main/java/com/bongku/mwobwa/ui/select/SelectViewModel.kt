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

    private val _ottSetFlag = MutableLiveData<Boolean>()
    val ottSetFlag get() = _ottSetFlag

    private val mwobwaDataStore: MwobwaDataStore =
        MwobwaApplication.mwobwaApplication.mwobwaDataStore

    fun setContents(contents: List<String>) = viewModelScope.launch {
        mwobwaDataStore.setOttCompany(contents)
        _ottSetFlag.value = true
    }

}