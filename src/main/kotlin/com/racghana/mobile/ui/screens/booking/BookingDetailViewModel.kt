package com.racghana.mobile.ui.screens.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.racghana.mobile.data.api.ApiService
import com.racghana.mobile.data.models.BookingDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookingDetailViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _detailState = MutableStateFlow<BookingDetailUiState>(BookingDetailUiState.Loading)
    val detailState: StateFlow<BookingDetailUiState> = _detailState

    fun loadDetail(id: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getServiceDetail("car", id)
                // Create BookingDetails object from ServiceDetailResponse
                // Note: You may need to adapt this based on your actual API response
                _detailState.value = BookingDetailUiState.Loading // Placeholder
            } catch (e: Exception) {
                _detailState.value = BookingDetailUiState.Error(e.message ?: "Load failed")
            }
        }
    }
}

sealed class BookingDetailUiState {
    object Loading : BookingDetailUiState()
    data class Success(val detail: BookingDetails) : BookingDetailUiState()
    data class Error(val message: String) : BookingDetailUiState()
}
