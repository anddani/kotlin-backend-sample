package com.anddani.client.smt.data

import kotlinx.serialization.Serializable

@Serializable
data class RemoteDemon(
    val skills: Map<String, Int>,
)
