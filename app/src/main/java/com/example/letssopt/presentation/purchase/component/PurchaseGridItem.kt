package com.example.letssopt.presentation.purchase.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.core.common.extension.noRippleClickable
import com.example.letssopt.core.designsystem.component.SoptContentItem
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.purchase.model.PurchaseUiModel

@Composable
fun PurchaseGridItem(
    item: PurchaseUiModel,
    onPurchaseClick: (PurchaseUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Box {
            SoptContentItem(
                imageRes = item.imageRes,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 6.dp, end = 6.dp)
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(Color.Black)
                    .noRippleClickable { onPurchaseClick(item) },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_ticket),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .padding(5.dp)
                        .size(18.dp)
                )
            }
        }

        Text(
            text = item.title,
            style = LETSSOPTTheme.typography.regular.body,
            color = LETSSOPTTheme.colors.textPrimary,
            modifier = Modifier.padding(top = 6.dp),
            maxLines = 2
        )
    }
}

@Preview
@Composable
private fun PurchaseGridItemPreview() {
    LETSSOPTTheme {
        PurchaseGridItem(
            item = PurchaseUiModel(1, R.drawable.img_content1, "이 사람 통역 되나요"),
            onPurchaseClick = {}
        )
    }
}