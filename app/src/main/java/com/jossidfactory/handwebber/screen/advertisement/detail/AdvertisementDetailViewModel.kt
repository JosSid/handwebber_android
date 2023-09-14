package com.jossidfactory.handwebber.screen.advertisement.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jossidfactory.handwebber.common.domain.mappers.logError
import com.jossidfactory.handwebber.common.domain.mappers.toError
import com.jossidfactory.handwebber.domain.advertisement.usecase.GetAdvertisementByIdUseCase
import com.jossidfactory.handwebber.domain.user.model.UpdateUserRequestModel
import com.jossidfactory.handwebber.domain.user.usecase.GetLoggedUserUseCase
import com.jossidfactory.handwebber.domain.user.usecase.UpdateUserUseCase
import kotlinx.coroutines.launch

class AdvertisementDetailViewModel(
    private val getAdvertisementByIdUseCase: GetAdvertisementByIdUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val getLoggedUserUseCase: GetLoggedUserUseCase
): ViewModel() {

    private val advertisementDetailState = AdvertisementDetailState()

    private val _state = MutableLiveData<AdvertisementDetailState>()
    val state: LiveData<AdvertisementDetailState> = _state

    fun getAdvertisementById(id: String) {

        _state.value = advertisementDetailState
        viewModelScope.launch {
            try {
                val response = getAdvertisementByIdUseCase.invoke(id)
                val user = getLoggedUserUseCase.invoke()

                _state.value = _state.value!!.copy(
                    advertisement = response.result,
                    isFavorite = user?.subscriptions?.contains(id) == true
                )
            } catch (e: Throwable) {
                _state.value = _state.value?.copy(
                    isError = e.toError()
                )
                e.logError()
            }

        }
    }

    fun handleFavoriteAdvertisement(id: String) {
        viewModelScope.launch {
            try {
                val user = getLoggedUserUseCase.invoke()
                val requestBody = UpdateUserRequestModel(
                    username = null,
                    email = null,
                    password = null,
                    subscriptions = id,
                    image = null
                )

                val userUpdated = user?.let { updateUserUseCase.invoke(it.id,requestBody) }

                if (userUpdated != null) {
                    if(userUpdated.subscriptions?.contains(id) == true) {
                        _state.value = _state.value?.copy(
                            isFavorite = true
                        )
                    } else {
                        _state.value = _state.value?.copy(
                            isFavorite = false
                        )
                    }
                }
            } catch (e: Throwable) {
                _state.value = _state.value?.copy(
                    isError = e.toError()
                )
                e.logError()
            }
        }
    }

    fun onResetError(id: String) {
        _state.value = _state.value?.copy(
            isError = null
        )
        getAdvertisementById(id)
    }

}