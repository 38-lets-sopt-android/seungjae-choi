package com.example.letssopt.presentation.home.component.banner

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.home.component.HomeSectionHeader
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay

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

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            pagerState.animateScrollToPage(pagerState.currentPage + 1)
        }
    }

    Column(modifier = modifier) {
        HomeSectionHeader(
            subtitle = "예능부터 드라마까지!",
            modifier = Modifier.padding(top = 24.dp, start = 3.dp)
        ) {
            Text(
                text = "방금 막 도착한 신상 컨텐츠",
                style = LETSSOPTTheme.typography.bold.h2,
                color = LETSSOPTTheme.colors.textPrimary
            )
        }

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

@Preview
@Composable
private fun HomeBannerSectionPreview() {
    LETSSOPTTheme {
        HomeBannerSection(
            bannerList = listOf(
                R.drawable.img_banner,
                R.drawable.img_banner,
                R.drawable.img_banner
            ).toImmutableList()
        )
    }
}