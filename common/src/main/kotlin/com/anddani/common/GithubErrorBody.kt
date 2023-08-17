package com.anddani.common

import kotlinx.serialization.Serializable

@Serializable
data class GithubErrorBody(
    val errorId: Int,
)