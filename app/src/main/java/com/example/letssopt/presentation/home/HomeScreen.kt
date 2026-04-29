package com.example.letssopt.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.letssopt.presentation.home.component.banner.HomeBannerSection
import com.example.letssopt.presentation.home.component.content.HomeContentSection
import com.example.letssopt.presentation.home.component.party.HomePartySection
import com.example.letssopt.presentation.home.state.HomeUiState
import kotlinx.collections.immutable.toImmutableList

@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        paddingValues = paddingValues,
        uiState = uiState
    )
}

@Composable
private fun HomeScreen(
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
            HomeBannerSection(bannerList = uiState.bannerList)
        }

        // 왓고리즘
        item {
            HomeContentSection(
                subtitle = "예능부터 드라마까지!",
                contentList = uiState.hotList,
                showMore = true
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_watgorizm),
                    contentDescription = "왓고리즘"
                )
            }
        }

        // 공개 예정 콘텐츠
        item {
            HomeContentSection(
                contentList = uiState.upcomingList,
                showMore = true
            ) {
                Text(
                    text = "공개 예정 콘텐츠",
                    style = LETSSOPTTheme.typography.bold.h2,
                    color = LETSSOPTTheme.colors.textPrimary
                )
            }
        }

        // 왓챠 파티
        item {
            HomePartySection(
                partyList = uiState.partyList,
                showMore = true
            )
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