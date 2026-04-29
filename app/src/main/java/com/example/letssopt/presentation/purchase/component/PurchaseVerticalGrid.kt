package com.example.letssopt.presentation.purchase.component

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
import com.example.letssopt.presentation.purchase.model.PurchaseUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun PurchaseVerticalGrid(
    items: ImmutableList<PurchaseUiModel>,
    onPurchaseClick: (PurchaseUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(
            items = items,
            key = { it.id }
        ) { item ->
            PurchaseGridItem(
                item = item,
                onPurchaseClick = onPurchaseClick
            )
        }
    }
}

@Preview
@Composable
private fun PurchaseVerticalGridPreview() {
    LETSSOPTTheme {
        PurchaseVerticalGrid(
            items = listOf(
                PurchaseUiModel(1, R.drawable.img_content1, "이 사람 통역 되나요"),
                PurchaseUiModel(2, R.drawable.img_content2, "이상한일5"),
                PurchaseUiModel(3, R.drawable.img_content3, "하일매리"),
                PurchaseUiModel(4, R.drawable.img_content1, "이 사람 통역 되나요"),
                PurchaseUiModel(5, R.drawable.img_content2, "이상한일5")
            ).toImmutableList(),
            onPurchaseClick = {}
        )
    }
}