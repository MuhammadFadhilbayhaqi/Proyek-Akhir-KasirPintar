package org.d3ifcool.tokopintar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import org.d3ifcool.tokopintar.ui.screen.authentication.LoginScreen
import org.d3ifcool.tokopintar.ui.screen.authentication.RegisterScreen
import org.d3ifcool.tokopintar.ui.screen.dashboard.HomeScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    // Periksa status login pengguna
    val isLoggedIn = FirebaseAuth.getInstance().currentUser != null
    val startDestination = if (isLoggedIn) "home" else "login"

    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate("register")
                },
                onGoogleLoginClick = { /* Implement Google login here, if needed */ },
                onForgotPasswordClick = { /* Implement forgot password functionality here, if needed */ }
            )
        }

        composable("register") {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                },
                onLoginClick = {
                    navController.navigate("login")
                }
            )
        }

        composable("home") {
            HomeScreen(
                onLogoutClick = {
                    FirebaseAuth.getInstance().signOut()
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
    }
}

