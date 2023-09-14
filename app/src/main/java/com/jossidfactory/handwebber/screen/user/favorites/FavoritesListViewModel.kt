package com.jossidfactory.handwebber.screen.user.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jossidfactory.handwebber.common.domain.mappers.logError
import com.jossidfactory.handwebber.common.domain.mappers.toError
import com.jossidfactory.handwebber.domain.advertisement.model.AdvertisementModel
import com.jossidfactory.handwebber.domain.advertisement.usecase.GetAdvertisementByIdUseCase
import com.jossidfactory.handwebber.domain.user.usecase.GetLoggedUserUseCase
import kotlinx.coroutines.launch

class FavoritesListViewModel(
    private val getAdvertisementByIdUseCase: GetAdvertisementByIdUseCase,
    private val getLoggedUserUseCase: GetLoggedUserUseCase
): ViewModel() {

    private val favoritesListState = FavoritesListState()

    private val _state = MutableLiveData<FavoritesListState>()
    val state: LiveData<FavoritesListState> = _state

    init {
        getFavorites()
    }

    fun getFavorites() {
        _state.value = favoritesListState
        viewModelScope.launch {
            try {
                val user = getLoggedUserUseCase.invoke()
                val favorites = mutableListOf<AdvertisementModel>()
                user?.subscriptions?.forEach {
                    val advertisement = it?.let { it1 -> getAdvertisementByIdUseCase.invoke(it1) }
                    if (advertisement != null) {
                        favorites.add(advertisement.result)
                    }
                }
                _state.value = _state.value?.copy(
                    advertisements = favorites
                )

            } catch (e: Throwable) {
                _state.value = _state.value?.copy(
                    isError = e.toError()
                )
                e.logError()
            }
        }
    }
}