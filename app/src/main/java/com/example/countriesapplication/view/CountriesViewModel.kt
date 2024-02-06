package com.example.countriesapplication.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesapplication.data.model.CountriesInfo
import com.example.countriesapplication.data.repository.CountriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

/**
 * CountriesViewModels handles the business logic which makes a network call from repository and
 * post it to liveData.
 */
class CountriesViewModel(
    val countriesRepository: CountriesRepository
    ): ViewModel() {

    private val _uiState = MutableStateFlow<CountriesState>(value = CountriesState.Loading(true))
    val uiState: StateFlow<CountriesState> = _uiState.asStateFlow()

    init {
        fetchCountriesData()
    }

    /**
     * This function is called to fetch the countries data
     */
    private fun fetchCountriesData() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.emit(CountriesState.Loading(true))
            try {
                val result = countriesRepository.getCountries()
                _uiState.emit(CountriesState.Loading(false))
                _uiState.emit(CountriesState.Success(data = result))

            } catch (e: Exception) {
                _uiState.emit(CountriesState.Error)
            }
        }
    }
}

sealed class CountriesState {
    data class Success(val data: Response<CountriesInfo>?) : CountriesState()
    data class Loading(val isLoading: Boolean): CountriesState()
    object Error : CountriesState()
}