package com.jossidfactory.handwebber.screen.advertisement.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jossidfactory.handwebber.data.AdvertisementDataService
import com.jossidfactory.handwebber.data.dto.AdvertisementDto
import kotlinx.coroutines.launch

class AdvertisementsListViewModel(
    private val advertisementDataService: AdvertisementDataService
): ViewModel() {

    private val advertisementListState = AdvertisementsListState()

    private val _state = MutableLiveData<AdvertisementsListState>()
    val state: LiveData<AdvertisementsListState> = _state

    private var advertisements = mutableListOf<AdvertisementDto>()

    init {
        getAdvertisementsList()
    }

    private fun getAdvertisementsList(){
        _state.value = advertisementListState

        viewModelScope.launch {
            try {
                val response = advertisementDataService.getAdvertisements()
                _state.value = _state.value?.copy(
                    advertisements = response.results
                )

            }catch (e: Throwable){
                println("")
            }
        }

    }


}