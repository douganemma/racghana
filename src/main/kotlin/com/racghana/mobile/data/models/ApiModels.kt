package com.racghana.mobile.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// ==================== Configuration ====================
@Serializable
data class ConfigResponse(
    val site_name: String,
    val site_url: String,
    val currency: String,
    val timezone: String
)

@Serializable
data class PaymentGateway(
    val id: String,
    val name: String,
    val icon: String?,
    val is_active: Boolean
)

// ==================== Home Page ====================
@Serializable
data class HomePageResponse(
    val featured_services: List<Service>,
    val categories: List<Category>,
    val testimonials: List<Testimonial>?,
    val banners: List<Banner>?
)

@Serializable
data class Category(
    val id: String,
    val name: String,
    val icon: String?,
    val type: String
)

@Serializable
data class Banner(
    val id: String,
    val image: String,
    val title: String,
    val link: String?
)

@Serializable
data class Testimonial(
    val id: String,
    val name: String,
    val message: String,
    val rating: Float,
    val avatar: String?
)

// ==================== Services ====================
@Serializable
data class ServicesResponse(
    val data: List<Service>,
    val pagination: Pagination
)

@Serializable
data class Service(
    val id: String,
    val title: String,
    val description: String?,
    val type: String,
    val price: Double,
    val image: String?,
    val rating: Float?,
    val location: String?,
    val created_at: String?
)

@Serializable
data class SearchResponse(
    val data: List<Service>,
    val pagination: Pagination,
    val filters: Filters?
)

@Serializable
data class Filters(
    val price_range: PriceRange?,
    val locations: List<String>?,
    val ratings: List<String>?
)

@Serializable
data class PriceRange(
    val min: Double,
    val max: Double
)

@Serializable
data class ServiceDetailResponse(
    val data: ServiceDetail
)

@Serializable
data class ServiceDetail(
    val id: String,
    val title: String,
    val description: String?,
    val type: String,
    val price: Double,
    val images: List<String>?,
    val features: List<String>?,
    val rating: Float?,
    val reviews_count: Int?,
    val location: String?,
    val availability: AvailabilityInfo?
)

@Serializable
data class AvailabilityResponse(
    val available: Boolean,
    val message: String?,
    val available_dates: List<String>?
)

@Serializable
data class AvailabilityInfo(
    val available: Boolean,
    val next_available: String?
)

@Serializable
data class FiltersResponse(
    val filters: List<FilterOption>
)

@Serializable
data class FilterOption(
    val id: String,
    val name: String,
    val type: String,
    val options: List<String>
)

@Serializable
data class FormSearchResponse(
    val fields: List<FormField>
)

@Serializable
data class FormField(
    val id: String,
    val name: String,
    val type: String,
    val required: Boolean,
    val options: List<String>?
)

// ==================== Authentication ====================
@Serializable
data class LoginRequest(
    val email: String,
    val password: String,
    val remember_me: Boolean? = false
)

@Serializable
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val password_confirmation: String,
    val phone: String? = null
)

@Serializable
data class AuthResponse(
    val user: User,
    val token: String,
    val expires_in: Long?
)

@Serializable
data class User(
    val id: String,
    val name: String,
    val email: String,
    val phone: String?,
    val avatar: String?,
    val created_at: String
)

@Serializable
data class UserResponse(
    val data: User
)

@Serializable
data class UpdateProfileRequest(
    val name: String?,
    val phone: String?,
    val avatar: String?
)

@Serializable
data class ChangePasswordRequest(
    val current_password: String,
    val new_password: String,
    val new_password_confirmation: String
)

// ==================== Bookings ====================
@Serializable
data class BookingHistoryResponse(
    val data: List<BookingItem>,
    val pagination: Pagination
)

@Serializable
data class BookingItem(
    val id: String,
    val code: String,
    val service_title: String,
    val start_date: String,
    val end_date: String,
    val total_price: Double,
    val status: String,
    val created_at: String
)

@Serializable
data class AddToCartRequest(
    val service_id: String,
    val service_type: String,
    val start_date: String,
    val end_date: String,
    val quantity: Int? = 1
)

@Serializable
data class CartResponse(
    val cart_id: String,
    val items: List<CartItem>,
    val subtotal: Double,
    val tax: Double,
    val total: Double
)

@Serializable
data class CartItem(
    val id: String,
    val service_title: String,
    val price: Double,
    val quantity: Int
)

@Serializable
data class EnquiryRequest(
    val service_id: String,
    val service_type: String,
    val message: String,
    val name: String,
    val email: String,
    val phone: String
)

@Serializable
data class CheckoutRequest(
    val cart_id: String,
    val gateway: String,
    val customer_name: String,
    val customer_email: String,
    val customer_phone: String,
    val notes: String?
)

@Serializable
data class CheckoutResponse(
    val booking_code: String,
    val payment_url: String?,
    val reference: String
)

@Serializable
data class ConfirmationResponse(
    val success: Boolean,
    val booking_code: String,
    val message: String
)

@Serializable
data class BookingDetailResponse(
    val data: BookingDetails
)

@Serializable
data class BookingDetails(
    val id: String,
    val code: String,
    val service: Service,
    val start_date: String,
    val end_date: String,
    val customer_name: String,
    val customer_email: String,
    val customer_phone: String,
    val total_price: Double,
    val status: String,
    val notes: String?,
    val created_at: String,
    val updated_at: String
)

@Serializable
data class BookingStatusResponse(
    val status: String,
    val message: String
)

@Serializable
data class WishlistRequest(
    val service_id: String,
    val service_type: String,
    val action: String // "add" or "remove"
)

@Serializable
data class WishlistResponse(
    val data: List<Service>,
    val pagination: Pagination
)

// ==================== Locations ====================
@Serializable
data class LocationsResponse(
    val data: List<Location>
)

@Serializable
data class Location(
    val id: String,
    val name: String,
    val city: String,
    val country: String,
    val latitude: Double?,
    val longitude: Double?
)

@Serializable
data class LocationDetailResponse(
    val data: LocationDetail
)

@Serializable
data class LocationDetail(
    val id: String,
    val name: String,
    val description: String?,
    val city: String,
    val country: String,
    val address: String?,
    val phone: String?,
    val latitude: Double?,
    val longitude: Double?
)

// ==================== Reviews ====================
@Serializable
data class ReviewRequest(
    val rating: Int,
    val title: String,
    val comment: String
)

@Serializable
data class ReviewResponse(
    val success: Boolean,
    val message: String
)

// ==================== News ====================
@Serializable
data class NewsResponse(
    val data: List<NewsItem>,
    val pagination: Pagination
)

@Serializable
data class NewsItem(
    val id: String,
    val title: String,
    val excerpt: String?,
    val image: String?,
    val published_at: String,
    val author: String?
)

@Serializable
data class NewsDetailResponse(
    val data: NewsDetail
)

@Serializable
data class NewsDetail(
    val id: String,
    val title: String,
    val content: String,
    val image: String?,
    val published_at: String,
    val author: String?,
    val category: String?
)

// ==================== Common ====================
@Serializable
data class Pagination(
    val current_page: Int,
    val from: Int?,
    val last_page: Int,
    val per_page: Int,
    val to: Int?,
    val total: Int
)

@Serializable
data class Response(
    val success: Boolean,
    val message: String?
)
