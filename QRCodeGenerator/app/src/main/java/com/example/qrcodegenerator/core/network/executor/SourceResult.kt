package com.example.qrcodegenerator.core.network.executor

/** Encapsulates an outcome from data a source. */
data class SourceResult<T>(
    val isSuccessful: Boolean,
    val body: T? = null,
    val code: Int? = null,
    val headers: Map<String, List<String>> = mapOf(),
    val throwable: Throwable? = null,
) {

    /** Try to get [body], if it is null an [IllegalStateException] will be thrown. */
    fun getBodyOrThrow(): T {
        val exception = throwable
            ?: IllegalStateException("Could not retrieve body from SourceResult.")
        return if (isSuccessful) {
            body ?: throw exception
        } else {
            throw exception
        }
    }

    /** Invoke [onResult] when [isSuccessful] is true. */
    fun onSuccess(onResult: (T) -> Unit): SourceResult<T> {
        if (isSuccessful) onResult(getBodyOrThrow())
        return this
    }

    /** Invoke [onResult] when [isSuccessful] is false. */
    fun onError(onResult: (Throwable?) -> Unit): SourceResult<T> {
        if (!isSuccessful) onResult(throwable)
        return this
    }
}
