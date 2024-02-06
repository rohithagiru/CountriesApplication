package com.example.countriesapplication.data.repository

import com.example.countriesapplication.data.model.CountriesInfo
import com.example.countriesapplication.data.api.RetrofitInstance
import retrofit2.Response

/**
 * Countries Repository is used to retrieve data from its data source
 */
interface CountriesRepository {
    suspend fun getCountries(): Response<CountriesInfo>
}

/**
 * Implementation of Repository to get the data
 */
class CountriesRepositoryImpl: CountriesRepository {

    override suspend fun getCountries() = RetrofitInstance.api.getCountries()

}