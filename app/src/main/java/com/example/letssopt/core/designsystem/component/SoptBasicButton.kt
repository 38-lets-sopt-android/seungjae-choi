package com.example.letssopt.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.common.extension.noRippleClickable
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme

@Composable
fun SoptBasicButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(
                color = if (enabled) LETSSOPTTheme.colors.primaryRed
                else LETSSOPTTheme.colors.disabled
            )
            .noRippleClickable(
                enabled = enabled,
                onClick = onClick
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = if (enabled) LETSSOPTTheme.colors.textPrimary
            else LETSSOPTTheme.colors.placeholder,
            style = LETSSOPTTheme.typography.bold.button
        )
    }
}

@Preview
@Composable
private fun SoptBasicButtonPreview() {
    LETSSOPTTheme {
        SoptBasicButton(
            title = "로그인하기",
            onClick = {},
            enabled = true
        )
    }
}