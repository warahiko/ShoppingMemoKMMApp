package io.github.warahiko.shoppingmemokmmapplication.android.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.warahiko.shoppingmemokmmapplication.android.ui.splash.SplashScreen

@Composable
fun ShoppingMemoNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(onNext = {
                navController.navigate(Screen.Contents.route) {
                    popUpTo(Screen.Splash.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(Screen.Contents.route) {
            BottomNavHost()
        }
    }
}

private sealed class Screen(
    val route: String,
) {
    object Splash : Screen("splash")
    object Contents : Screen("contents")
}
