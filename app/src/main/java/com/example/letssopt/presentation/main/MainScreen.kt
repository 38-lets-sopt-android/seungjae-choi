package com.example.letssopt.presentation.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.letssopt.core.designsystem.component.SoptTopBar
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.home.HomeRoute
import com.example.letssopt.presentation.library.LibraryRoute
import com.example.letssopt.presentation.main.component.MainBottomBar
import com.example.letssopt.presentation.purchase.PurchaseScreen
import com.example.letssopt.presentation.search.SearchScreen
import com.example.letssopt.presentation.webtoon.WebtoonScreen
import kotlinx.collections.immutable.toImmutableList

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var currentTab by remember { mutableStateOf(MainTab.MAIN) }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding(),
        bottomBar = {
            MainBottomBar(
                isVisible = true,
                tabs = MainTab.entries.toImmutableList(),
                currentTab = currentTab,
                onTabSelected = { currentTab = it }
            )
        },
        topBar = {
            when (currentTab) {
                MainTab.MAIN -> SoptTopBar()
                else -> {}
            }
        },

    ) { innerPadding ->
        when (currentTab) {
            MainTab.MAIN -> HomeRoute(paddingValues = innerPadding)
            MainTab.PURCHASE -> PurchaseScreen(paddingValues = innerPadding)
            MainTab.WEBTOON -> WebtoonScreen(paddingValues = innerPadding)
            MainTab.SEARCH -> SearchScreen(paddingValues = innerPadding)
            MainTab.LIBRARY -> LibraryRoute(paddingValues = innerPadding)
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