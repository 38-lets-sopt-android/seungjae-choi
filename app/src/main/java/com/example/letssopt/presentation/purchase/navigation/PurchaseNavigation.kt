package com.example.letssopt.presentation.purchase.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.letssopt.core.navigation.MainTabRoute
import com.example.letssopt.presentation.purchase.PurchaseRoute
import kotlinx.serialization.Serializable

fun NavController.navigatePurchase(
    navOptions: NavOptions? = null
) {
    navigate(Purchase, navOptions)
}

fun NavGraphBuilder.purchaseGraph(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit
) {
    composable<Purchase> {
        PurchaseRoute(
            paddingValues = paddingValues,
            navigateUp = navigateUp
        )
    }
}


@Serializable
data object Purchase: MainTabRoute