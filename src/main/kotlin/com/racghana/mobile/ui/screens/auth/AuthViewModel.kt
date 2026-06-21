package com.racghana.mobile.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.racghana.mobile.data.api.ApiService
import com.racghana.mobile.data.models.AuthResponse
import com.racghana.mobile.data.models.LoginRequest
import com.racghana.mobile.data.models.RegisterRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _loginState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val loginState: StateFlow<AuthUiState> = _loginState

    private val _registerState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val registerState: StateFlow<AuthUiState> = _registerState

    fun login(email: String, password: String, rememberMe: Boolean = false) {
        viewModelScope.launch {
            _loginState.value = AuthUiState.Loading
            try {
                val response = apiService.login(
                    LoginRequest(
                        email = email,
                        password = password,
                        remember_me = rememberMe
                    )
                )
                // Save token to secure storage
                saveToken(response.token)
                _loginState.value = AuthUiState.Success(response)
            } catch (e: Exception) {
                _loginState.value = AuthUiState.Error(e.message ?: "Login failed")
            }
        }
    }

    fun register(
        name: String,
        email: String,
        password: String,
        passwordConfirmation: String,
        phone: String? = null
    ) {
        viewModelScope.launch {
            _registerState.value = AuthUiState.Loading
            try {
                val response = apiService.register(
                    RegisterRequest(
                        name = name,
                        email = email,
                        password = password,
                        password_confirmation = passwordConfirmation,
                        phone = phone
                    )
                )
                // Save token to secure storage
                saveToken(response.token)
                _registerState.value = AuthUiState.Success(response)
            } catch (e: Exception) {
                _registerState.value = AuthUiState.Error(e.message ?: "Registration failed")
            }
        }
    }

    private fun saveToken(token: String) {
        // TODO: Implement token saving to DataStore or SharedPreferences
    }
}

sealed class AuthUiState {
    object Idle : AuthUiState()
    object Loading : AuthUiState()
    data class Success(val data: AuthResponse) : AuthUiState()
    data class Error(val message: String) : AuthUiState()
}
