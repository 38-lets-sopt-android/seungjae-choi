package com.example.letssopt.presentation.library.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.library.model.LibraryUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun LibraryVerticalGrid(
    items: ImmutableList<LibraryUiModel>,
    onDeleteClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(
            items = items,
            key = { it.id }
        ) { item ->
            LibraryGridItem(
                item = item,
                onDeleteClick = onDeleteClick
            )
        }
    }
}

@Preview
@Composable
private fun LibraryVerticalGridPreview() {
    LETSSOPTTheme {
        LibraryVerticalGrid(
            items = listOf(
                LibraryUiModel(1L, R.drawable.img_content1, "이 사람 통역 되나요"),
                LibraryUiModel(2L, R.drawable.img_content2, "이상한일5"),
                LibraryUiModel(3L, R.drawable.img_content3, "하일매리")
            ).toImmutableList(),
            onDeleteClick = {}
        )
    }
}