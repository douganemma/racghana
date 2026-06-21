# RacGhana Android App

A modern Android app for booking cars and other services through the RacGhana platform.

## Features

✅ **Authentication**
- User login and registration
- Secure token management
- Password change functionality

✅ **Search & Browse**
- Search for cars by location, date, and filters
- Browse featured services
- Service details with images and reviews

✅ **Bookings**
- Check service availability
- Add services to cart
- Secure checkout with multiple payment gateways
- Booking confirmation and history

✅ **User Profile**
- View and edit profile information
- Booking history
- Wishlist management

✅ **Reviews**
- Write reviews for services
- View ratings and testimonials

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM with ViewModel and StateFlow
- **Networking**: Retrofit + OkHttp
- **Serialization**: Kotlinx Serialization
- **Dependency Injection**: Dagger Hilt
- **Database**: Room (for offline caching)
- **Image Loading**: Coil
- **Navigation**: Compose Navigation

## Project Structure

```
src/main/
├── kotlin/com/racghana/mobile/
│   ├── MainActivity.kt
│   ├── data/
│   │   ├── api/
│   │   │   ├── ApiClient.kt
│   │   │   ├── ApiService.kt
│   │   │   └── TokenInterceptor.kt
│   │   └── models/
│   │       └── ApiModels.kt
│   ├── di/
│   │   └── ApiModule.kt
│   └── ui/
│       ├── navigation/
│       │   └── Navigation.kt
│       ├── screens/
│       │   ├── home/
│       │   ├── auth/
│       │   ├── search/
│       │   ├── booking/
│       │   └── profile/
│       └── theme/
│           ├── Theme.kt
│           ├── Color.kt
│           └── Typography.kt
└── AndroidManifest.xml
```

## API Endpoints

The app integrates with the RacGhana API:

**Base URL**: `https://racghana.com/api/`

### Configuration
- `GET /configs` - Site configuration
- `GET /gateways` - Payment gateways

### Services
- `GET /services` - List all services
- `GET /{type}/search` - Search by type
- `GET /{type}/detail/{id}` - Service details
- `GET /{type}/availability/{id}` - Check availability
- `GET /{type}/filters` - Get filters

### Authentication
- `POST /auth/login` - User login
- `POST /auth/register` - User registration
- `GET /auth/me` - Get current user
- `POST /auth/change-password` - Change password
- `POST /auth/logout` - User logout

### Bookings
- `POST /booking/addToCart` - Add to cart
- `POST /booking/doCheckout` - Checkout
- `GET /booking/{code}` - Booking details
- `GET /booking/{code}/check-status` - Check status

### User
- `GET /user/booking-history` - Booking history
- `GET /user/wishlist` - User wishlist
- `POST /user/wishlist` - Add/remove from wishlist

### Reviews & Ratings
- `POST /{type}/write-review/{id}` - Write review

## Setup Instructions

### Prerequisites
- Android Studio (Giraffe or later)
- SDK minimum API level 24
- Gradle 8.0+

### Installation

1. Clone the repository
```bash
git clone https://github.com/douganemma/racghana.git
cd racghana
```

2. Open in Android Studio
```bash
open -a "Android Studio" .
```

3. Sync Gradle files
- File → Sync Now

4. Update API Configuration
- Edit `src/main/kotlin/com/racghana/mobile/data/api/ApiClient.kt`
- Set your RacGhana API base URL

5. Run the app
- Select a device or emulator
- Click Run → Run 'app'

## Configuration

### API Base URL
Edit `ApiClient.kt` to configure your API endpoint:
```kotlin
private const val BASE_URL = "https://racghana.com/api/"
```

### Authentication Token Storage
Implement token storage in `TokenInterceptor.kt` and `AuthViewModel.kt`:
```kotlin
private fun getAuthToken(): String {
    // TODO: Retrieve from DataStore or SharedPreferences
}

private fun saveToken(token: String) {
    // TODO: Save to DataStore or SharedPreferences
}
```

## Building for Release

### Generate Release APK
```bash
./gradlew assembleRelease
```

The APK will be available at:
```
app/build/outputs/apk/release/app-release.apk
```

### Generate App Bundle
```bash
./gradlew bundleRelease
```

## Testing

### Run Unit Tests
```bash
./gradlew test
```

### Run Instrumented Tests
```bash
./gradlew connectedAndroidTest
```

## Troubleshooting

### Network Issues
- Ensure `android.permission.INTERNET` is declared in AndroidManifest.xml
- Check API endpoint configuration in ApiClient.kt
- Verify network connectivity

### Authentication Issues
- Ensure token is properly saved after login
- Verify TokenInterceptor is adding Authorization header
- Check token expiration and refresh mechanism

### Compose Errors
- Update Jetpack Compose version in build.gradle.kts
- Clear build cache: `./gradlew clean`

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For support, contact the RacGhana development team or create an issue in the repository.

## Roadmap

- [ ] Offline support with Room database
- [ ] Push notifications for booking updates
- [ ] Map integration for location selection
- [ ] Payment gateway integration (Stripe, Paystack, etc.)
- [ ] Advanced filters and sorting
- [ ] Multi-language support
- [ ] Dark mode optimization
- [ ] Widget support

## Credits

Developed for RacGhana Car Booking Platform
