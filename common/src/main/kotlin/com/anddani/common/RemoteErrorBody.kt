package com.anddani.common

import kotlinx.serialization.Serializable

@Serializable
data class RemoteErrorBody(
    val errorId: Int,
)