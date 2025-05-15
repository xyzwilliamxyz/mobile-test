package com.example.qrcodegenerator.core.network.executor

import android.content.Context
import com.example.qrcodegenerator.R
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber

class SourceExecutor(
    private val appContext: Context,
    private val moshi: Moshi,
) {

    suspend fun <T> execute(request: suspend () -> Response<T>): SourceResult<T> {
        return try {
            val result = request()
            result.toSourceResult()
        } catch (httpException: HttpException) {
            Timber.e(httpException)
            httpException.toSourceResult()
        } catch (ex: Exception) {
            Timber.e(ex)
            ex.toSourceResult()
        }
    }

    private fun <T> Throwable.toSourceResult(): SourceResult<T> {
        return SourceResult(
            isSuccessful = false,
            throwable = this,
        )
    }

    private fun <T> HttpException.toSourceResult(): SourceResult<T> {
        val headers = this.response()?.headers()?.toMultimap().orEmpty()
        val throwable = this.response()?.buildBackendException()
        return SourceResult(
            isSuccessful = false,
            code = code(),
            headers = headers,
            throwable = throwable,
        )
    }

    private fun <T> Response<T>.toSourceResult(): SourceResult<T> {
        val headers = this.headers().toMultimap()
        val throwable = if (!this.isSuccessful) this.buildBackendException() else null
        return SourceResult(
            isSuccessful = isSuccessful,
            body = body(),
            code = code(),
            headers = headers,
            throwable = throwable,
        )
    }

    private fun <T> Response<T>.buildBackendException(): BackendException {
        val errorJson = this.errorBody()?.string()
        val errorBodyDto = runCatching {
            moshi.adapter(ErrorBodyDto::class.java).fromJson(errorJson!!)
        }.getOrNull()
        return BackendException(
            errorBodyDto?.error,
            errorBodyDto?.message.orEmpty().ifEmpty { appContext.getString(R.string.generic_error_msg) },
        )
    }

    private data class ErrorBodyDto(
        @Json(name = "message")
        val message: String,
        @Json(name = "error")
        val error: String,
    )
}
