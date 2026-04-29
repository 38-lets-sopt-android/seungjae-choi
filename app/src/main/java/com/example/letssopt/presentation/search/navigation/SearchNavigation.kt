package com.example.letssopt.presentation.search.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.letssopt.core.navigation.MainTabRoute
import com.example.letssopt.presentation.search.SearchRoute
import kotlinx.serialization.Serializable

fun NavController.navigateSearch(
    navOptions: NavOptions? = null
) {
    navigate(Search, navOptions)
}

fun NavGraphBuilder.searchGraph(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit
) {
    composable<Search> {
        SearchRoute(
            paddingValues = paddingValues,
            navigateUp = navigateUp
        )
    }
}


@Serializable
data object Search: MainTabRoute