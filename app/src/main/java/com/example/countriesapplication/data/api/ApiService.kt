package com.example.countriesapplication.data.api

import com.example.countriesapplication.data.model.CountriesInfo
import retrofit2.Response
import retrofit2.http.GET

/**
 * Service class that holds API Path for Countries Data.
 */
interface ApiService {

    @GET("peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun getCountries(): Response<CountriesInfo>
}