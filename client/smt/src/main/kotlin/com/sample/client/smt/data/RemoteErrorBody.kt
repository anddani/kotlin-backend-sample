package com.sample.client.smt.data

import kotlinx.serialization.Serializable

@Serializable
data class RemoteErrorBody(
    val errorId: Int,
)