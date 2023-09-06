package com.jossidfactory.handwebber.screen.advertisement.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jossidfactory.handwebber.common.domain.exception.EmptySearchException
import com.jossidfactory.handwebber.common.domain.mappers.logError
import com.jossidfactory.handwebber.common.domain.mappers.toError
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
        _state.value = _state.value?.copy(
            isError = null
        )

        viewModelScope.launch {
            try {
                val response = getAdvertisementsListUseCase.invoke()
                filteredAds = response.result.toMutableList()
                _state.value = _state.value?.copy(
                    advertisements = filteredAds
                )

            }catch (e: Throwable){
                _state.value = _state.value?.copy(
                    isError = e.toError()
                )
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
                val ads = filteredAds.filter { beer ->
                    beer.name.lowercase().contains(
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
            getAdvertisementsList()
        }
    }
}