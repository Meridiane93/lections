package com.meridiane.test_lection.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meridiane.test_lection.domain.Repository
import com.meridiane.test_lection.dэata.UiState
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(UiState())

    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val repository = Repository()

     fun getWeather(country: String) {
        viewModelScope.launch {
            try {

                val response = if ( country.contains('.') ) {

                    repository.getCoordinateService(
                        country.substringBefore('/'),
                        country.substringAfter('/')
                    )

                } else repository.getDataService(country)

                _uiState.update { data ->
                    data.copy(
                        cod = (response.cod).toString(),
                        main = response.main,
                        name = response.name
                    )
                }

            } catch (e:Exception){
                _uiState.update { data ->
                    data.copy(
                        cod = e.message,
                        main = null,
                        name = null
                    )
                }
            }
        }

    }

    override fun onCleared() {
        viewModelScope.cancel()
    }
}


