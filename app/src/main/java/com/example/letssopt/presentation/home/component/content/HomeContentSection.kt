package com.example.letssopt.presentation.home.component.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.core.designsystem.component.SoptContentItem
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.home.component.HomeSectionHeader
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun HomeContentSection(
    contentList: ImmutableList<Int>,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    showMore: Boolean = false,
    onMoreClick: () -> Unit = {},
    title: @Composable () -> Unit
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

@Preview
@Composable
private fun HomeContentSectionPreview() {
    LETSSOPTTheme {
        HomeContentSection(
            title = {},
            contentList = listOf(
                R.drawable.img_content1,
                R.drawable.img_content2,
                R.drawable.img_content3
            ).toImmutableList(),
        )
    }
}