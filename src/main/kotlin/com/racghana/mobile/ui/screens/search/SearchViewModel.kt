package com.racghana.mobile.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.racghana.mobile.data.api.ApiService
import com.racghana.mobile.data.models.Service
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _searchState = MutableStateFlow<SearchUiState>(SearchUiState.Empty)
    val searchState: StateFlow<SearchUiState> = _searchState

    private var currentServiceType = "car"

    fun setServiceType(type: String) {
        currentServiceType = type
    }

    fun search(query: String) {
        if (query.isBlank()) {
            _searchState.value = SearchUiState.Empty
            return
        }

        viewModelScope.launch {
            _searchState.value = SearchUiState.Loading
            try {
                val response = apiService.searchByType(
                    type = currentServiceType,
                    query = query,
                    page = 1,
                    perPage = 20
                )
                
                if (response.data.isEmpty()) {
                    _searchState.value = SearchUiState.Empty
                } else {
                    _searchState.value = SearchUiState.Success(response.data)
                }
            } catch (e: Exception) {
                _searchState.value = SearchUiState.Error(e.message ?: "Search failed")
            }
        }
    }
}

sealed class SearchUiState {
    object Empty : SearchUiState()
    object Loading : SearchUiState()
    data class Success(val results: List<Service>) : SearchUiState()
    data class Error(val message: String) : SearchUiState()
}
