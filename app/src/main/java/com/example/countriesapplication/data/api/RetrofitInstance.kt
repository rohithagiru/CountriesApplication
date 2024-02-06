package com.example.countriesapplication.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * RetrofitInstance to create a Retrofit Builder
 */
class RetrofitInstance {
    companion object {
        private val retrofit: Retrofit by lazy {
            Retrofit.Builder().baseUrl("https://gist.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val api: ApiService by lazy{
            retrofit.create(ApiService::class.java)
        }
    }
}