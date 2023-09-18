package com.jossidfactory.handwebber.screen.advertisement.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateAdvertisementViewModel(): ViewModel() {
    private val createAdvertisementState = CreateAdvertisementState()

    private val _state = MutableLiveData<CreateAdvertisementState>()
    val state: LiveData<CreateAdvertisementState> = _state

    init {
        _state.value = createAdvertisementState
    }

    fun getStock(stock: Int) {
        _state.value = _state.value?.copy(
            stock = stock
        )
    }
}