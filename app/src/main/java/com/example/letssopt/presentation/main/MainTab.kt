package com.example.letssopt.presentation.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.letssopt.R

enum class MainTab(
    @param:DrawableRes val icon: Int,
    @param:StringRes val contentDescription: Int,
) {
    MAIN(
        icon = R.drawable.ic_main,
        contentDescription = R.string.tab_main
    ),
    PURCHASE(
        icon = R.drawable.ic_purchase,
        contentDescription = R.string.tab_purchase
    ),
    WEBTOON(
        icon = R.drawable.ic_webtoon,
        contentDescription = R.string.tab_webtoon
    ),
    SEARCH(
        icon = R.drawable.ic_search,
        contentDescription = R.string.tab_search
    ),
    LIBRARY(
        icon = R.drawable.ic_library,
        contentDescription = R.string.tab_library
    )
}