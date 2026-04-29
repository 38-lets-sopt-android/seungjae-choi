package com.example.letssopt.presentation.library.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme

@Composable
fun LibraryEmptyView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "찜해놓은 목록이 없습니다",
            style = LETSSOPTTheme.typography.regular.body,
            color = LETSSOPTTheme.colors.textSecondary
        )
    }
}

@Preview
@Composable
private fun LibraryEmptyViewPreview() {
    LETSSOPTTheme {
        LibraryEmptyView()
    }
}