package com.example.umc_week2

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "https://reqres.in/api/"
    private const val API_KEY = "reqres_b5086b85872e498eabc29475d07a010e"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-api-key", API_KEY)
                .build()

            chain.proceed(request)
        }
        .addInterceptor(loggingInterceptor)
        .build()

    val reqresService: ReqresService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ReqresService::class.java)
}