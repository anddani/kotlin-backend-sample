package com.sample.common

import kotlinx.serialization.Serializable

@Serializable
data class RemoteErrorBody(
    val errorId: Int,
)