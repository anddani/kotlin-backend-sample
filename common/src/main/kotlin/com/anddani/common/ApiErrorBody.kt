package com.anddani.common

import kotlinx.serialization.Serializable

@Serializable
data class ApiErrorBody(
    val id: String,
    val message: String,
) : JsonSerializable
