package com.example.letssopt.presentation.library.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.core.common.extension.noRippleClickable
import com.example.letssopt.core.designsystem.component.SoptContentItem
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.library.model.LibraryUiModel

@Composable
fun LibraryGridItem(
    item: LibraryUiModel,
    onDeleteClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SoptContentItem(imageRes = item.imageRes)

        Box(
            modifier = Modifier
                .padding(top = 12.dp)
                .size(24.dp)
                .clip(CircleShape)
                .background(LETSSOPTTheme.colors.textPrimary)
                .noRippleClickable { onDeleteClick(item.id) },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "삭제",
                tint = LETSSOPTTheme.colors.background,
            )
        }
    }
}

@Preview
@Composable
private fun LibraryGridItemPreview() {
    LETSSOPTTheme {
        LibraryGridItem(
            item = LibraryUiModel(1, R.drawable.img_content1),
            onDeleteClick = {}
        )
    }
}