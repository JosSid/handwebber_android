package com.jossidfactory.handwebber.common.domain.mappers

import com.jossidfactory.handwebber.common.domain.entity.Error
import com.jossidfactory.handwebber.common.domain.exception.EmptyListException
import com.jossidfactory.handwebber.common.domain.exception.EmptySearchException
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

const val http401 = 401
const val http404 = 404
const val http409 = 409
const val http422 = 422

fun Throwable.toError(): Error = when (this) {
    is IOException -> Error.Connectivity(this.message ?: "")
    is HttpException -> {
        when (this.code()) {
            http401 -> Error.Unauthorized("${this.code()} - unauthorized")
            http404 -> Error.EmptyView("${this.code()} - empty-view")
            http409 -> Error.Conflict("${this.code()} - mail already registered")
            http422 -> Error.Unprocesable("${this.code()} - unprocesable entity")
            else -> Error.Unknown("${this.code()} - ${this.message ?: " "}")
        }
    }
    is EmptyListException -> Error.EmptyView(this.message ?: "")
    is EmptySearchException -> Error.EmptySearch(this.message ?: " ")
    else -> Error.Unknown(this.message ?: "")
}

fun Throwable.logError() {
    Timber.e(this)
}