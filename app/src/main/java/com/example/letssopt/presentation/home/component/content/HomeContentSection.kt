package com.example.letssopt.presentation.home.component.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.designsystem.component.SoptContentItem
import com.example.letssopt.presentation.home.component.HomeSectionHeader
import kotlinx.collections.immutable.ImmutableList

@Composable
fun HomeContentSection(
    title: @Composable () -> Unit,
    contentList: ImmutableList<Int>,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    showMore: Boolean = false,
    onMoreClick: () -> Unit = {}
) {
    Column(modifier = modifier) {
        HomeSectionHeader(
            title = title,
            subtitle = subtitle,
            showMore = showMore,
            onMoreClick = onMoreClick,
            modifier = Modifier.padding(top = 26.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(13.dp),
            modifier = Modifier.padding(top = 6.dp)
        ) {
            items(items = contentList) {
                SoptContentItem(imageRes = it)
            }
        }
    }
}