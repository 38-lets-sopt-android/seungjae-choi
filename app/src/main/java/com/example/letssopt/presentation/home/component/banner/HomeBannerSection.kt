package com.example.letssopt.presentation.home.component.banner

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.home.component.HomeSectionHeader
import kotlinx.collections.immutable.ImmutableList

@Composable
fun HomeBannerSection(
    bannerList: ImmutableList<Int>,
    modifier: Modifier = Modifier
) {
    val infiniteCount = Int.MAX_VALUE
    val startIndex = infiniteCount / 2 - (infiniteCount / 2) % bannerList.size
    val pagerState = rememberPagerState(
        initialPage = startIndex,
        pageCount = { infiniteCount }
    )

    Column(modifier = modifier) {
        HomeSectionHeader(
            title = {
                Text(
                    text = "방금 막 도착한 신상 컨텐츠",
                    style = LETSSOPTTheme.typography.bold.h2,
                    color = LETSSOPTTheme.colors.textPrimary
                )
            },
            subtitle = "예능부터 드라마까지!",
            modifier = Modifier.padding(top = 24.dp, start = 3.dp)
        )
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 40.dp),
            pageSpacing = 16.dp,
            modifier = Modifier.padding(top = 24.dp)
        ) { page ->
            HomeBannerItem(imageRes = bannerList[page % bannerList.size])
        }
    }
}