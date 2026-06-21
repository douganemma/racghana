package com.racghana.mobile.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.racghana.mobile.data.api.ApiService
import com.racghana.mobile.data.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _profileState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val profileState: StateFlow<ProfileUiState> = _profileState

    fun loadProfile() {
        viewModelScope.launch {
            try {
                val response = apiService.getCurrentUser()
                _profileState.value = ProfileUiState.Success(response.data)
            } catch (e: Exception) {
                _profileState.value = ProfileUiState.Error(e.message ?: "Load failed")
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                apiService.logout()
                // Clear token and navigate to login
            } catch (e: Exception) {
                _profileState.value = ProfileUiState.Error(e.message ?: "Logout failed")
            }
        }
    }
}

sealed class ProfileUiState {
    object Loading : ProfileUiState()
    data class Success(val user: User) : ProfileUiState()
    data class Error(val message: String) : ProfileUiState()
}
