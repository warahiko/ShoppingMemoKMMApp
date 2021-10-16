package io.github.warahiko.shoppingmemokmmapplication.android.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun ShoppingMemoNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
//        startDestination = Screen.Splash.route,
        startDestination = Screen.Contents.route,
    ) {
        composable(Screen.Splash.route) {
//            SplashScreen()
        }
        composable(Screen.Contents.route) {
            BottomNavHost()
        }
    }

//    LaunchedEffect(Unit) {
//        viewModel.fetchShoppingItems().join()
//        navController.navigate(Screen.Contents.route) {
//            popUpTo(Screen.Splash.route) {
//                inclusive = true
//            }
//        }
//    }
}

private sealed class Screen(
    val route: String,
) {
    object Splash : Screen("splash")
    object Contents : Screen("contents")
}
