package com.racghana.mobile.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.racghana.mobile.data.api.ApiService
import com.racghana.mobile.data.models.HomePageResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _homePageState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val homePageState: StateFlow<HomeUiState> = _homePageState

    init {
        loadHomePage()
    }

    private fun loadHomePage() {
        viewModelScope.launch {
            try {
                val response = apiService.getHomePage()
                _homePageState.value = HomeUiState.Success(response)
            } catch (e: Exception) {
                _homePageState.value = HomeUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val data: HomePageResponse) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}
