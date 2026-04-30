package com.example.letssopt.presentation.home.component.party

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.home.component.HomeSectionHeader
import com.example.letssopt.presentation.home.model.HomePartyUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun HomePartySection(
    partyList: ImmutableList<HomePartyUiModel>,
    modifier: Modifier = Modifier,
    showMore: Boolean = false,
    onMoreClick: () -> Unit = {}
) {
    Column(modifier = modifier) {
        HomeSectionHeader(
            showMore = showMore,
            onMoreClick = onMoreClick,
            modifier = Modifier.padding(top = 26.dp)
        ) {
            Text(
                text = "왓챠 파티",
                style = LETSSOPTTheme.typography.bold.h2,
                color = LETSSOPTTheme.colors.textPrimary
            )
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
        ) {
            items(items = partyList) {
                HomePartyItem(item = it)
            }
        }
    }
}

@Preview
@Composable
private fun HomePartySectionPreview() {
    LETSSOPTTheme {
        HomePartySection(
            partyList = listOf(
                HomePartyUiModel(
                    imageRes = R.drawable.img_party,
                    startTime = "오늘 21:13에 시작",
                    tag = "# 왕과 사는 남자"
                )
            ).toImmutableList()
        )
    }
}