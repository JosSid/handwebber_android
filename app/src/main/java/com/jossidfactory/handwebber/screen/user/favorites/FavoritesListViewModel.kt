package com.jossidfactory.handwebber.screen.user.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jossidfactory.handwebber.common.domain.exception.EmptySearchException
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

    private var filteredAds = mutableListOf<AdvertisementModel>()

    init {
        getFavorites()
    }

    fun getFavorites() {
        _state.value = favoritesListState
        _state.value = _state.value?.copy(
            isError = null
        )

        viewModelScope.launch {
            try {
                val user = getLoggedUserUseCase.invoke()
                user?.subscriptions?.forEach {
                    val advertisement = it?.let { id -> getAdvertisementByIdUseCase.invoke(id) }
                    if (advertisement != null) {
                        filteredAds.add(advertisement.result)
                    }
                }
                _state.value = _state.value?.copy(
                    advertisements = filteredAds
                )

            } catch (e: Throwable) {
                _state.value = _state.value?.copy(
                    isError = e.toError()
                )
                e.logError()
            }
        }
    }

    fun onQueryChange(query: String) {
        _state.value = _state.value?.copy(
            query = query,
            isError = null
        )
        try {
            if (query.isNotEmpty()) {
                val ads = filteredAds.filter { ad ->
                    ad.name.lowercase().contains(
                        query
                            .lowercase()
                    )
                }
                _state.value = _state.value?.copy(
                    advertisements = ads
                )

                if (ads.isEmpty()) {
                    throw EmptySearchException("")
                }
            } else {
                _state.value = _state.value?.copy(
                    advertisements = filteredAds
                )
            }

        } catch (e: Throwable) {
            _state.value = _state.value?.copy(
                isError = e.toError()
            )
            e.logError()
        }
    }

    fun onResetError() {
        if(state.value?.query?.isNotEmpty()!!) {
            val query = state.value!!.query
            onQueryChange(query.substring(0, query.length -1))
        }else{
            getFavorites()
        }
    }
}