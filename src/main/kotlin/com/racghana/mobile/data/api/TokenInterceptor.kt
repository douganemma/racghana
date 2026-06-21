package com.racghana.mobile.data.api

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        
        // Get token from DataStore or SharedPreferences
        val token = getAuthToken() // Implement this based on your auth storage
        
        val requestBuilder = originalRequest.newBuilder()
        
        if (token.isNotEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }
        
        requestBuilder.addHeader("Accept", "application/json")
        
        return chain.proceed(requestBuilder.build())
    }
    
    private fun getAuthToken(): String {
        // TODO: Implement token retrieval from secure storage
        return ""
    }
}
