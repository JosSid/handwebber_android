package com.jossidfactory.handwebber.screen.advertisement.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jossidfactory.handwebber.domain.advertisement.model.AdvertisementModel
import com.jossidfactory.handwebber.domain.advertisement.usecase.GetAdvertisementsListUseCase
import kotlinx.coroutines.launch

class AdvertisementsListViewModel(
    private val getAdvertisementsListUseCase: GetAdvertisementsListUseCase
): ViewModel() {

    private val advertisementListState = AdvertisementsListState()

    private val _state = MutableLiveData<AdvertisementsListState>()
    val state: LiveData<AdvertisementsListState> = _state

    var filteredAds = mutableListOf<AdvertisementModel>()
        private set

    init {
        getAdvertisementsList()
    }

    private fun getAdvertisementsList(){
        _state.value = advertisementListState

        viewModelScope.launch {
            try {
                val response = getAdvertisementsListUseCase.invoke()
                filteredAds = response.result.toMutableList()
                _state.value = _state.value?.copy(
                    advertisements = filteredAds
                )

            }catch (e: Throwable){
                println("")
            }
        }

    }

    fun onQueryChange(query: String) {
        _state.value = _state.value?.copy(
            query = query
        )
        if (query.isNotEmpty()) {
            _state.value = _state.value?.copy(
                advertisements = filteredAds.filter { beer -> beer.name.lowercase().contains(query
                    .lowercase())
                }
            )
        } else {
            _state.value = _state.value?.copy(
                advertisements = filteredAds
            )
        }
    }

}