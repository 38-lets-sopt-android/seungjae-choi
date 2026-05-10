package com.example.letssopt.presentation.profile.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme

@Composable
fun ProfileItem(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = LETSSOPTTheme.typography.bold.button,
            color = LETSSOPTTheme.colors.textPrimary
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = value,
            style = LETSSOPTTheme.typography.regular.caption,
            color = LETSSOPTTheme.colors.textSecondary
        )
    }
}

@Preview
@Composable
private fun ProfileItemPreview() {
    LETSSOPTTheme {
        ProfileItem(
            title = "아이디",
            value = "sopt_user"
        )
    }
}