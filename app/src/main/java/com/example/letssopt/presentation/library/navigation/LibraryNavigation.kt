package com.example.letssopt.presentation.library.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.letssopt.core.navigation.MainTabRoute
import com.example.letssopt.presentation.library.LibraryRoute
import kotlinx.serialization.Serializable

fun NavController.navigateLibrary(
    navOptions: NavOptions? = null
) {
    navigate(Library, navOptions)
}

fun NavGraphBuilder.libraryGraph(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit
) {
    composable<Library> {
        LibraryRoute(
            paddingValues = paddingValues,
            navigateUp = navigateUp
        )
    }
}


@Serializable
data object Library: MainTabRoute