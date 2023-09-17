package com.jossidfactory.handwebber.screen.advertisement.create

import com.jossidfactory.handwebber.common.domain.entity.Error

data class CreateAdvertisementState(
    val title: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val stock: Int = 1,
    val active: Boolean = true,
    val tags: List<String> = mutableListOf(),
    val custom: Boolean = false,
    val image: String = "",
    val iseError: Error? = null
)
