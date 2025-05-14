package com.example.qrcodegenerator.core.network.executor

import java.io.IOException

class BackendException(
    val errorType: String? = null,
    errorMessage: String? = null,
) : IOException(errorMessage)
