package com.example.letssopt.presentation.home.component.party

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.home.model.PartyUiModel

@Composable
fun HomePartyItem(
    item: PartyUiModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(196.dp)
            .background(LETSSOPTTheme.colors.surface)
    ) {
        Image(
            painter = painterResource(item.imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = item.startTime,
                style = LETSSOPTTheme.typography.regular.body2,
                color = LETSSOPTTheme.colors.primaryRed,
            )
            Text(
                text = item.tag,
                style = LETSSOPTTheme.typography.regular.body2,
                color = LETSSOPTTheme.colors.textPrimary
            )
        }
    }
}

@Preview
@Composable
private fun HomePartyItemPreview() {
    LETSSOPTTheme {
        HomePartyItem(
            item = PartyUiModel(
                imageRes = R.drawable.img_party,
                startTime = "오늘 21:13에 시작",
                tag = "# 왕과 사는 남자"
            )
        )
    }
}