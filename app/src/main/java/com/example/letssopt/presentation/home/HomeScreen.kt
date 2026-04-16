package com.example.letssopt.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.R
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.home.component.HomeBannerItem
import com.example.letssopt.presentation.home.component.HomeContentItem
import com.example.letssopt.presentation.home.component.HomePartyItem
import com.example.letssopt.presentation.home.component.HomeSectionHeader
import com.example.letssopt.presentation.home.state.HomeUiState
import kotlinx.collections.immutable.toImmutableList

@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        paddingValues = paddingValues,
        uiState = uiState
    )
}

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    uiState: HomeUiState
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        // 신상 컨텐츠
        item {
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
        }
        item {
            val infiniteCount = Int.MAX_VALUE
            val startIndex = infiniteCount / 2 - (infiniteCount / 2) % uiState.bannerList.size

            val pagerState = rememberPagerState(
                initialPage = startIndex,
                pageCount = { infiniteCount }
            )

            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 40.dp),
                pageSpacing = 16.dp,
                modifier = Modifier.padding(top = 24.dp)
            ) { page ->
                val actualIndex = page % uiState.bannerList.size
                HomeBannerItem(imageRes = uiState.bannerList[actualIndex])
            }
        }

        // 왓고리즘
        item {
            HomeSectionHeader(
                title = {
                    androidx.compose.foundation.Image(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_watgorizm),
                        contentDescription = "왓고리즘"
                    )
                },
                subtitle = "예능부터 드라마까지!",
                showMore = true,
                modifier = Modifier.padding(top = 26.dp)
            )
        }
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(13.dp),
                modifier = Modifier.padding(top = 6.dp)
            ) {
                items(
                    items = uiState.hotList,
                ) {
                    HomeContentItem(imageRes = it)
                }
            }
        }

        // 공개 예정 콘텐츠
        item {
            HomeSectionHeader(
                title = {
                    Text(
                        text = "공개 예정 콘텐츠",
                        style = LETSSOPTTheme.typography.bold.h2,
                        color = LETSSOPTTheme.colors.textPrimary
                    )
                },
                showMore = true,
                modifier = Modifier.padding(top = 26.dp)
            )
        }
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(13.dp),
                modifier = Modifier.padding(top = 6.dp)
            ) {
                items(
                    items = uiState.upcomingList,
                ) {
                    HomeContentItem(imageRes = it)
                }
            }
        }

        // 왓챠 파티
        item {
            HomeSectionHeader(
                title = {
                    Text(
                        text = "왓챠 파티",
                        style = LETSSOPTTheme.typography.bold.h2,
                        color = LETSSOPTTheme.colors.textPrimary
                    )
                },
                showMore = true,
                modifier = Modifier.padding(top = 26.dp)
            )
        }
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(bottom = 16.dp)
            ) {
                items(
                    items = uiState.partyList,
                ) {
                    HomePartyItem(item = it)
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    LETSSOPTTheme {
        HomeScreen(
            paddingValues = PaddingValues(0.dp),
            uiState = HomeUiState(
                bannerList = HomeUiState.dummyBannerList.toImmutableList(),
                hotList = HomeUiState.dummyContentList.toImmutableList(),
                upcomingList = HomeUiState.dummyContentList.toImmutableList(),
                partyList = HomeUiState.dummyPartyList.toImmutableList()
            )
        )
    }
}