package io.github.warahiko.shoppingmemokmmapplication.android.ui.shoppingitem

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.warahiko.shoppingmemokmmapplication.android.ui.shoppingitem.list.ShoppingItemListScreen

@Composable
fun ShoppingItemScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.ShoppingItems.route) {
        composable(Screen.ShoppingItems.route) {
            ShoppingItemListScreen(
//                onClickAddButton = { navController.navigate(Screen.Add.route) },
//                onEdit = { navController.navigate(Screen.Edit.actualRoute(it.id.toString())) },
            )
        }
        composable(Screen.Add.route) {
//            AddShoppingItemScreen(
//                onBack = { navController.popBackStack() },
//            )
        }
        composable(Screen.Edit.route) { backStackEntry ->
//            val itemId = backStackEntry.arguments?.getString(Screen.Edit.itemIdKey)
//                ?: run {
//                    navController.popBackStack()
//                    return@composable
//                }
//            EditShoppingItemScreen(
//                defaultShoppingItemId = itemId,
//                onBack = { navController.popBackStack() },
//            )
        }
    }
}

private sealed class Screen(
    val route: String,
) {
    object ShoppingItems : Screen("shopping-item")
    object Add : Screen("shopping-item/add")
    object Edit : Screen("shopping-item/edit/{itemId}") {
        const val itemIdKey = "itemId"
        fun actualRoute(itemId: String): String = "shopping-item/edit/${itemId}"
    }
}
