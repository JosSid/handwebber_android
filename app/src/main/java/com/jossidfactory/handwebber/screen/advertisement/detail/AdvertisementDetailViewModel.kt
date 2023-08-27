package com.jossidfactory.handwebber.screen.advertisement.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jossidfactory.handwebber.domain.advertisement.usecase.GetAdvertisementByIdUseCase
import kotlinx.coroutines.launch

class AdvertisementDetailViewModel(
    private val getAdvertisementByIdUseCase: GetAdvertisementByIdUseCase
): ViewModel() {

    private val advertisementDetailState = AdvertisementDetailState()

    private val _state = MutableLiveData<AdvertisementDetailState>()
    val state: LiveData<AdvertisementDetailState> = _state

    fun getAdvertisementById(id: String) {

        _state.value = advertisementDetailState
        viewModelScope.launch {
            try {
                val response = getAdvertisementByIdUseCase.invoke(id)
                _state.value = _state.value!!.copy(
                    advertisement = response.result
                )
            } catch (e: Throwable) {
                println("")
            }

        }
    }

}