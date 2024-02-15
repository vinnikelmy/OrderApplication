package com.example.orderapplication.core.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.orderapplication.order_feature.presentation.OrderChooseDelivererScreen
import com.example.orderapplication.order_feature.presentation.OrderChooseProductsScreen
import com.example.orderapplication.order_feature.presentation.OrderScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = ScreenRoutes.OrderScreen.route
    ){
        composable(ScreenRoutes.OrderScreen.route) {
            OrderScreen(navController = navController)
        }

        composable(ScreenRoutes.OrderChooseDelivererScreen.route) {
            OrderChooseDelivererScreen(navController = navController)
        }

        composable(ScreenRoutes.OrderChooseProductsScreen.route+"/{delivererId}"){
            OrderChooseProductsScreen(
                navController = navController,
                delivererId = it.arguments?.getString("delivererId")
            )
        }
    }
}

sealed class ScreenRoutes(val route: String){
    object OrderScreen: ScreenRoutes("order_screen")
    object OrderChooseDelivererScreen: ScreenRoutes("order_choose_deliverer_screen")
    object OrderChooseProductsScreen: ScreenRoutes("order_choose_products_screen")
}