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
import timber.log.Timber

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
                _state.value = _state.value!!.copy(
                    advertisement = response.result
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
                Timber.d(userUpdated.toString())
                Timber.d(id)
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