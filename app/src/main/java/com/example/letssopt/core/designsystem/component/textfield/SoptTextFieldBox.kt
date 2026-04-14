package com.example.letssopt.core.designsystem.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme

@Composable
fun SoptTextFieldBox(
    isEmpty: Boolean,
    placeholder: String,
    innerTextField: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = LETSSOPTTheme.colors.surface)
            .padding(horizontal = 18.dp, vertical = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        if (isEmpty) {
            Text(
                text = placeholder,
                style = LETSSOPTTheme.typography.regular.caption,
                color = LETSSOPTTheme.colors.placeholder
            )
        }
        innerTextField()
    }
}

@Preview
@Composable
private fun SoptTextFieldBoxPreview() {
    SoptTextFieldBox(
        isEmpty = true,
        placeholder = "아이디를 입력해주세요",
        innerTextField = {}
    )
}