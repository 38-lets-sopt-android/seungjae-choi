package com.example.letssopt.presentation.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.common.extension.noRippleClickable
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme

@Composable
fun HomeSectionHeader(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    showMore: Boolean = false,
    onMoreClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Column {
            title()

            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = LETSSOPTTheme.typography.regular.body,
                    color = LETSSOPTTheme.colors.textSecondary,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        if (showMore) {
            Text(
                text = "더보기",
                style = LETSSOPTTheme.typography.regular.body2,
                color = LETSSOPTTheme.colors.textSecondary,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .noRippleClickable(onClick = onMoreClick)
            )
        }
    }

}

@Preview
@Composable
private fun HomeSectionHeaderPreview() {
    LETSSOPTTheme {
        HomeSectionHeader(
            title = {
                Text(
                    text = "방금 막 도착한 신상 컨텐츠",
                    style = LETSSOPTTheme.typography.bold.h2,
                    color = LETSSOPTTheme.colors.textPrimary
                )
            },
            subtitle = "예능부터 드라마까지!",
            showMore = true
        )
    }
}