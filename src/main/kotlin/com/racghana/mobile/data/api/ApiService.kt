package com.racghana.mobile.data.api

import com.racghana.mobile.data.models.*
import retrofit2.http.*

interface ApiService {
    
    // ==================== Configuration ====================
    @GET("configs")
    suspend fun getConfigs(): ConfigResponse
    
    @GET("gateways")
    suspend fun getPaymentGateways(): List<PaymentGateway>
    
    // ==================== Homepage ====================
    @GET("home-page")
    suspend fun getHomePage(): HomePageResponse
    
    // ==================== Search & Services ====================
    @GET("services")
    suspend fun getServices(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20
    ): ServicesResponse
    
    @GET("{type}/search")
    suspend fun searchByType(
        @Path("type") type: String,
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20
    ): SearchResponse
    
    @GET("{type}/detail/{id}")
    suspend fun getServiceDetail(
        @Path("type") type: String,
        @Path("id") id: String
    ): ServiceDetailResponse
    
    @GET("{type}/availability/{id}")
    suspend fun checkAvailability(
        @Path("type") type: String,
        @Path("id") id: String,
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String
    ): AvailabilityResponse
    
    @GET("{type}/filters")
    suspend fun getFilters(
        @Path("type") type: String
    ): FiltersResponse
    
    @GET("{type}/form-search")
    suspend fun getFormSearch(
        @Path("type") type: String
    ): FormSearchResponse
    
    // ==================== Authentication ====================
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): AuthResponse
    
    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): AuthResponse
    
    @POST("auth/logout")
    suspend fun logout(): Response
    
    @POST("auth/refresh")
    suspend fun refreshToken(): AuthResponse
    
    @GET("auth/me")
    suspend fun getCurrentUser(): UserResponse
    
    @POST("auth/me")
    suspend fun updateProfile(
        @Body request: UpdateProfileRequest
    ): UserResponse
    
    @POST("auth/change-password")
    suspend fun changePassword(
        @Body request: ChangePasswordRequest
    ): Response
    
    // ==================== User ====================
    @GET("user/booking-history")
    suspend fun getBookingHistory(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20
    ): BookingHistoryResponse
    
    @POST("user/wishlist")
    suspend fun toggleWishlist(
        @Body request: WishlistRequest
    ): Response
    
    @GET("user/wishlist")
    suspend fun getWishlist(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20
    ): WishlistResponse
    
    @POST("user/permanently_delete")
    suspend fun deleteAccount(): Response
    
    // ==================== Locations ====================
    @GET("locations")
    suspend fun getLocations(
        @Query("query") query: String? = null
    ): LocationsResponse
    
    @GET("location/{id}")
    suspend fun getLocationDetail(
        @Path("id") id: String
    ): LocationDetailResponse
    
    // ==================== Bookings ====================
    @POST("booking/addToCart")
    suspend fun addToCart(
        @Body request: AddToCartRequest
    ): CartResponse
    
    @POST("booking/addEnquiry")
    suspend fun addEnquiry(
        @Body request: EnquiryRequest
    ): Response
    
    @POST("booking/doCheckout")
    suspend fun checkout(
        @Body request: CheckoutRequest
    ): CheckoutResponse
    
    @GET("booking/confirm/{gateway}")
    suspend fun confirmBooking(
        @Path("gateway") gateway: String,
        @Query("reference") reference: String
    ): ConfirmationResponse
    
    @GET("booking/{code}")
    suspend fun getBookingDetail(
        @Path("code") code: String
    ): BookingDetailResponse
    
    @GET("booking/{code}/check-status")
    suspend fun checkBookingStatus(
        @Path("code") code: String
    ): BookingStatusResponse
    
    // ==================== Reviews ====================
    @POST("{type}/write-review/{id}")
    suspend fun writeReview(
        @Path("type") type: String,
        @Path("id") id: String,
        @Body request: ReviewRequest
    ): ReviewResponse
    
    // ==================== News ====================
    @GET("news")
    suspend fun getNews(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): NewsResponse
    
    @GET("news/{id}")
    suspend fun getNewsDetail(
        @Path("id") id: String
    ): NewsDetailResponse
}
