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
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.home.component.HomeSectionHeader
import com.example.letssopt.presentation.home.model.PartyUiModel
import kotlinx.collections.immutable.ImmutableList

@Composable
fun HomePartySection(
    partyList: ImmutableList<PartyUiModel>,
    modifier: Modifier = Modifier,
    showMore: Boolean = false,
    onMoreClick: () -> Unit = {}
) {
    Column(modifier = modifier) {
        HomeSectionHeader(
            title = {
                Text(
                    text = "왓챠 파티",
                    style = LETSSOPTTheme.typography.bold.h2,
                    color = LETSSOPTTheme.colors.textPrimary
                )
            },
            showMore = showMore,
            onMoreClick = onMoreClick,
            modifier = Modifier.padding(top = 26.dp)
        )
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