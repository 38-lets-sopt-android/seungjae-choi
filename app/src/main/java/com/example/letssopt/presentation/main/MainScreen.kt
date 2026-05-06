package com.example.letssopt.presentation.main

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import com.example.letssopt.core.designsystem.component.SoptTopBar
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.home.navigation.homeGraph
import com.example.letssopt.presentation.library.navigation.libraryGraph
import com.example.letssopt.presentation.main.component.MainBottomBar
import com.example.letssopt.presentation.purchase.navigation.purchaseGraph
import com.example.letssopt.presentation.search.navigation.searchGraph
import com.example.letssopt.presentation.signin.navigation.signInGraph
import com.example.letssopt.presentation.signup.navigation.signUpGraph
import com.example.letssopt.presentation.webtoon.navigation.webtoonGraph
import kotlinx.collections.immutable.toPersistentList

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    appState: MainAppState = rememberMainAppState(),
) {
    val isBottomBarVisible by appState.isBottomBarVisible.collectAsStateWithLifecycle()
    val currentTab by appState.currentTab.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding(),
        bottomBar = {
            MainBottomBar(
                isVisible = isBottomBarVisible,
                tabs = MainTab.entries.toPersistentList(),
                currentTab = currentTab,
                onTabSelected = appState::navigate
            )
        },
        topBar = {
            when (currentTab) {
                MainTab.MAIN -> SoptTopBar()
                else -> {}
            }
        },

        ) { innerPadding ->
        NavHost(
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(durationMillis = 300)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(durationMillis = 300)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(durationMillis = 300)
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(durationMillis = 300)
                )
            },
            navController = appState.navController,
            startDestination = appState.startDestination
        ) {
            signInGraph(
                paddingValues = innerPadding,
                navigateUp = appState::navigateUp,
                navigateToSignUp = appState::navigateToSignUp,
                navigateToMain = appState::navigateToMain
            )

            signUpGraph(
                paddingValues = innerPadding,
                navigateUp = appState::navigateUp,
            )

            homeGraph(
                paddingValues = innerPadding,
                navigateUp = appState::navigateUp
            )
            purchaseGraph(
                paddingValues = innerPadding,
                navigateUp = appState::navigateUp
            )
            webtoonGraph(
                paddingValues = innerPadding,
                navigateUp = appState::navigateUp
            )
            searchGraph(
                paddingValues = innerPadding,
                navigateUp = appState::navigateUp
            )
            libraryGraph(
                paddingValues = innerPadding,
                navigateUp = appState::navigateUp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    LETSSOPTTheme {
        MainScreen()
    }
}