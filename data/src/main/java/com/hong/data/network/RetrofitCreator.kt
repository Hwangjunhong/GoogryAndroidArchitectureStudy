package com.hong.data.network

import com.hong.data.constants.Constants.BASE_URL
import com.hong.data.constants.Constants.DEFAULT_TIME_OUT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RetrofitCreator @Inject constructor() {
    private val retrofit: Retrofit
    private val okHttpClient: OkHttpClient.Builder

    init {

        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            HttpLoggingInterceptor.Level.BODY
        }

        okHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            addInterceptor(httpLoggingInterceptor)
            addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("X-Naver-Client-Id", "baPVy6Scd6jvsqAsrrHu")
                    .addHeader("X-Naver-Client-Secret", "lCfi3Abd26")
                    .build()
                chain.proceed(request)
            }
        }

        retrofit = Retrofit.Builder()
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val service: NaverMovieApi by lazy {
        retrofit.create(NaverMovieApi::class.java)
    }
}