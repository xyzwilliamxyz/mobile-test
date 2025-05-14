package com.example.qrcodegenerator.data.remote.api

import com.example.qrcodegenerator.data.remote.response.SeedResponse
import retrofit2.Response
import retrofit2.http.GET

interface QRCodeApi {

    @GET("seed")
    suspend fun getSeed(): Response<SeedResponse>
}