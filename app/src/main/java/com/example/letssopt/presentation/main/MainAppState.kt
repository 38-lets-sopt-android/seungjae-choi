package com.example.letssopt.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.letssopt.core.data.AuthPreference
import com.example.letssopt.presentation.home.navigation.Home
import com.example.letssopt.presentation.signin.navigation.SignIn
import com.example.letssopt.presentation.signup.navigation.SignUp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Stable
class MainAppState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
) {
    val startDestination = if (AuthPreference.isLoggedIn()) Home else SignIn

    private val currentDestination = navController.currentBackStackEntryFlow
        .map { it.destination }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val currentTab: StateFlow<MainTab?> = currentDestination
        .map { destination ->
            MainTab.find { tab ->
                destination?.hasRoute(tab::class) == true
            }
        }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    val isBottomBarVisible: StateFlow<Boolean> = currentDestination
        .map { destination ->
            MainTab.contains { tab ->
                destination?.hasRoute(tab::class) == true
            }
        }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            navController.currentDestination?.route?.let {
                popUpTo(it) {
                    inclusive = true
                    saveState = true
                }
                restoreState = true
                launchSingleTop = true
            }
        }
        navController.navigate(tab.route, navOptions)
    }

    fun navigateUp() {
        navController.navigateUp()
    }

    fun navigateToMain() {
        navController.navigate(Home) {
            popUpTo(0) { inclusive = true }
            launchSingleTop = true
        }
    }

    fun navigateToSignUp() {
        navController.navigate(SignUp)
    }

    private val clearStackNavOptions = navOptions {
        popUpTo(0) {
            inclusive = true
        }
        launchSingleTop = true
    }

}

@Composable
fun rememberMainAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): MainAppState = remember(navController,coroutineScope) {
    MainAppState(
        navController,
        coroutineScope,
    )
}