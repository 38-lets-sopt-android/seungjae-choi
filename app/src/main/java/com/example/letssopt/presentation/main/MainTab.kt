package com.example.letssopt.presentation.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.letssopt.R
import com.example.letssopt.core.navigation.MainTabRoute
import com.example.letssopt.core.navigation.Route
import com.example.letssopt.presentation.home.navigation.Home
import com.example.letssopt.presentation.library.navigation.Library
import com.example.letssopt.presentation.purchase.navigation.Purchase
import com.example.letssopt.presentation.search.navigation.Search
import com.example.letssopt.presentation.webtoon.navigation.Webtoon

enum class MainTab(
    @param:DrawableRes val icon: Int,
    @param:StringRes val contentDescription: Int,
    val route: MainTabRoute,
    ) {
    MAIN(
        icon = R.drawable.ic_main,
        contentDescription = R.string.tab_main,
        route = Home
    ),
    PURCHASE(
        icon = R.drawable.ic_purchase,
        contentDescription = R.string.tab_purchase,
        route = Purchase
    ),
    WEBTOON(
        icon = R.drawable.ic_webtoon,
        contentDescription = R.string.tab_webtoon,
        route = Webtoon
    ),
    SEARCH(
        icon = R.drawable.ic_search,
        contentDescription = R.string.tab_search,
        route = Search
    ),
    LIBRARY(
        icon = R.drawable.ic_library,
        contentDescription = R.string.tab_library,
        route = Library
    );


    companion object {
        fun find(predicate: (MainTabRoute) -> Boolean): MainTab? {
            return MainTab.entries.find { predicate(it.route) }
        }

        fun contains(predicate: (Route) -> Boolean): Boolean {
            return MainTab.entries.map { it.route }.any { predicate(it) }
        }
    }
}