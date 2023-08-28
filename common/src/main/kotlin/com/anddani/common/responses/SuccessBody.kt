package com.anddani.common.responses

import com.anddani.common.JsonSerializable

sealed interface SuccessBody

data class ObjectBody(val body: JsonSerializable) : SuccessBody
data class ListBody(val body: List<JsonSerializable>) : SuccessBody
data class StringBody(val body: String) : SuccessBody

fun JsonSerializable.toSuccessBody(): ObjectBody = let(::ObjectBody)
fun List<JsonSerializable>.toSuccessBody(): ListBody = let(::ListBody)
fun String.toSuccessBody(): StringBody = let(::StringBody)
