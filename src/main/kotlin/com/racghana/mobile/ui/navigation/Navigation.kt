package com.racghana.mobile.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.racghana.mobile.ui.screens.home.HomeScreen
import com.racghana.mobile.ui.screens.auth.LoginScreen
import com.racghana.mobile.ui.screens.auth.RegisterScreen
import com.racghana.mobile.ui.screens.search.SearchScreen
import com.racghana.mobile.ui.screens.booking.BookingDetailScreen
import com.racghana.mobile.ui.screens.profile.ProfileScreen

seal class NavDestination(val route: String) {
    object Home : NavDestination("home")
    object Login : NavDestination("login")
    object Register : NavDestination("register")
    object Search : NavDestination("search/{type}") {
        fun createRoute(type: String) = "search/$type"
    }
    object BookingDetail : NavDestination("booking/{id}") {
        fun createRoute(id: String) = "booking/$id"
    }
    object Profile : NavDestination("profile")
}

@Composable
fun RacGhanaNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = NavDestination.Home.route
    ) {
        composable(NavDestination.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(NavDestination.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(NavDestination.Register.route) {
            RegisterScreen(navController = navController)
        }
        composable(NavDestination.Search.route) { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type") ?: "car"
            SearchScreen(type = type, navController = navController)
        }
        composable(NavDestination.BookingDetail.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            BookingDetailScreen(id = id, navController = navController)
        }
        composable(NavDestination.Profile.route) {
            ProfileScreen(navController = navController)
        }
    }
}
