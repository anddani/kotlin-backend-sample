package com.sample.client.smt

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import io.ktor.client.call.*
import io.ktor.client.statement.*

suspend inline fun <reified T, reified U> wrapApiCallToResult(call: () -> HttpResponse): Result<T, ApiError<U>> {
    val result = try {
        call()
    } catch (e: Exception) {
        e.printStackTrace()
        println("Fail")
        return Err(ApiError.UnexpectedError as ApiError<U>)
    }
    return when (result.status.value) {
        in 200..299 -> try {
            Ok(result.body<T>())
        } catch (e: Exception) {
            e.printStackTrace()
            println("Class ${T::class.java}")
            Err(ApiError.UnexpectedError as ApiError<U>)
        }

        in 400..499 -> Err(ApiError.ClientError(
            statusCode = result.status.value,
            statusMessage = result.status.description,
            body = result.body() as U
        ))

        in 500..599 -> Err(ApiError.ServerError(
            statusCode = result.status.value,
            statusMessage = result.status.description,
        ) as ApiError<U>)

        else -> Err(ApiError.UnexpectedError as ApiError<U>)
    }
}
