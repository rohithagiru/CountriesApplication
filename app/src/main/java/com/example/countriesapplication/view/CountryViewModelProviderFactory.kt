package com.example.countriesapplication.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.countriesapplication.data.repository.CountriesRepository
import com.example.countriesapplication.data.repository.CountriesRepositoryImpl

class CountryViewModelProviderFactory(private val countriesRepository: CountriesRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CountriesViewModel(countriesRepository = CountriesRepositoryImpl()) as T
    }
}