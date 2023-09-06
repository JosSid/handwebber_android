package com.jossidfactory.handwebber.common.domain.entity

sealed class Error(val message: String) {
    class EmptyView(message: String) : Error(message)
    class EmptySearch(message: String) : Error(message)
    class Connectivity(message: String) : Error(message)
    class Unknown(message: String) : Error(message)
}