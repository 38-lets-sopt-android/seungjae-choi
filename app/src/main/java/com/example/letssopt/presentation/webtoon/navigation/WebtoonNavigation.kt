package com.example.letssopt.presentation.webtoon.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.letssopt.core.navigation.MainTabRoute
import com.example.letssopt.presentation.webtoon.WebtoonRoute
import kotlinx.serialization.Serializable

fun NavController.navigateWebtoon(
    navOptions: NavOptions? = null
) {
    navigate(Webtoon, navOptions)
}

fun NavGraphBuilder.webtoonGraph(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit
) {
    composable<Webtoon> {
        WebtoonRoute(
            paddingValues = paddingValues,
            navigateUp = navigateUp
        )
    }
}


@Serializable
data object Webtoon: MainTabRoute