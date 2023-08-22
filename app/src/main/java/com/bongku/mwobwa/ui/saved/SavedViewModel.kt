package com.bongku.mwobwa.ui.saved

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bongku.mwobwa.data.entity.SavedContentEntity
import com.bongku.mwobwa.data.repository.SavedContentRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val savedRepository: SavedContentRepositoryImpl
) : ViewModel() {

    private val _roomData = MutableLiveData<List<SavedContentEntity>>()
    val roomData get() = _roomData

    fun getRoomData() = viewModelScope.launch(Dispatchers.IO) {
        savedRepository.getContentData().collect { data ->
            _roomData.postValue(data)
        }
    }

    fun deleteRoomData(savedContentEntity: SavedContentEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            savedRepository.deleteContentData(savedContentEntity)
        }
}