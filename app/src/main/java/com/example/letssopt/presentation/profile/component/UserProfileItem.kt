package com.example.letssopt.presentation.profile.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.profile.model.UserItemUiModel

@Composable
fun UserProfileItem(
    user: UserItemUiModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = LETSSOPTTheme.colors.textPrimary
            )
            .padding(
                start = 68.dp,
                top = 10.dp,
                bottom = 10.dp,
                end = 34.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(
                text = user.id.toString(),
                style = LETSSOPTTheme.typography.bold.button,
                color = LETSSOPTTheme.colors.textPrimary,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = user.name,
                style = LETSSOPTTheme.typography.regular.caption,
                color = LETSSOPTTheme.colors.textSecondary,
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = user.part,
            style = LETSSOPTTheme.typography.regular.caption,
            color = LETSSOPTTheme.colors.textPrimary,
        )
    }
}

@Preview
@Composable
private fun UserProfileItemPreview(
) {
    LETSSOPTTheme {
        UserProfileItem(
            user = UserItemUiModel(
                id = 1,
                name = "이름",
                part = "안드로이드"
            )
        )
    }
}