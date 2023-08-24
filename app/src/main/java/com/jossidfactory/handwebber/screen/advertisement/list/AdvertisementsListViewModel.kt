package com.jossidfactory.handwebber.screen.advertisement.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jossidfactory.handwebber.domain.advertisement.usecase.GetAdvertisementsListUseCase
import kotlinx.coroutines.launch

class AdvertisementsListViewModel(
    private val getAdvertisementsListUseCase: GetAdvertisementsListUseCase
): ViewModel() {

    private val advertisementListState = AdvertisementsListState()

    private val _state = MutableLiveData<AdvertisementsListState>()
    val state: LiveData<AdvertisementsListState> = _state

    init {
        getAdvertisementsList()
    }

    private fun getAdvertisementsList(){
        _state.value = advertisementListState

        viewModelScope.launch {
            try {
                val response = getAdvertisementsListUseCase.invoke()
                _state.value = _state.value?.copy(
                    advertisements = response.result
                )

            }catch (e: Throwable){
                println("")
            }
        }

    }


}