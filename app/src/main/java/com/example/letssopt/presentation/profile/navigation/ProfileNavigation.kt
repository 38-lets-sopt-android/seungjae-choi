// presentation/profile/navigation/ProfileNavigation.kt
package com.example.letssopt.presentation.profile.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.letssopt.core.navigation.Route
import com.example.letssopt.presentation.profile.ProfileRoute
import com.example.letssopt.presentation.profile.ProfileViewModel
import com.example.letssopt.presentation.profile.UserListRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToProfileGraph(
    navOptions: NavOptions? = null
) {
    navigate(ProfileGraph, navOptions)
}

fun NavGraphBuilder.profileGraph(
    paddingValues: PaddingValues,
    navController: NavController,
    navigateUp: () -> Unit
) {
    navigation<ProfileGraph>(startDestination = Profile) {

        composable<Profile> { entry ->
            val parentEntry = remember(entry) {
                navController.getBackStackEntry(ProfileGraph)
            }
            val sharedViewModel: ProfileViewModel = viewModel(parentEntry)

            ProfileRoute(
                paddingValues = paddingValues,
                viewModel = sharedViewModel,
                navigateToUserList = { navController.navigate(UserList) }
            )
        }

        composable<UserList> { entry ->
            val parentEntry = remember(entry) {
                navController.getBackStackEntry(ProfileGraph)
            }
            val sharedViewModel: ProfileViewModel = viewModel(parentEntry)

            UserListRoute(
                paddingValues = paddingValues,
                viewModel = sharedViewModel
            )
        }
    }
}

@Serializable data object ProfileGraph : Route
@Serializable data object Profile : Route
@Serializable data object UserList : Route