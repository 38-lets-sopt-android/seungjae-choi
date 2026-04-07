package com.example.letssopt.core.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme

@Composable
fun SoptFormField(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = LETSSOPTTheme.typography.regular.caption,
            color = LETSSOPTTheme.colors.textSecondary
        )

        Spacer(modifier = Modifier.height(3.dp))

        SoptBasicTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder,
            isPassword = isPassword,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions
        )
    }
}

@Preview
@Composable
private fun SoptFormFieldPreview() {
    SoptFormField(
        title = "아이디",
        value = "",
        onValueChange = {},
        placeholder = "아이디를 입력해주세요"
    )
}