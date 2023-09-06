package com.jossidfactory.handwebber.common.domain.mappers

import com.jossidfactory.handwebber.common.domain.entity.Error
import com.jossidfactory.handwebber.common.domain.exception.EmptyListException
import com.jossidfactory.handwebber.common.domain.exception.EmptySearchException
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException


const val http404 = 404

fun Throwable.toError(): Error = when (this) {
    is IOException -> Error.Connectivity(this.message ?: "")
    is HttpException -> {
        if (this.code() == http404) {
            Error.EmptyView("${this.code()} - empty-view")
        } else {
            Error.Unknown("${code()} - ${this.message ?: " "}")
        }
    }
    is EmptyListException -> Error.EmptyView(this.message ?: "")
    is EmptySearchException -> Error.EmptySearch(this.message ?: " ")
    else -> Error.Unknown(this.message ?: "")
}

fun Throwable.logError() {
    Timber.e(this)
}