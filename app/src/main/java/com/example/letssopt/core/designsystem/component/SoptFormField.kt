package com.example.letssopt.core.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldLineLimits // 💡 추가
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.designsystem.component.textfield.SoptBasicTextField
import com.example.letssopt.core.designsystem.component.textfield.SoptSecureTextField
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme

@Composable
fun SoptFormField(
    title: String,
    state: TextFieldState,
    placeholder: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.SingleLine
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = LETSSOPTTheme.typography.regular.caption,
            color = LETSSOPTTheme.colors.textSecondary
        )

        Spacer(modifier = Modifier.height(3.dp))

        if (isPassword) {
            SoptSecureTextField(
                state = state,
                placeholder = placeholder,
                keyboardOptions = keyboardOptions,
                onKeyboardAction = onKeyboardAction
            )
        } else {
            SoptBasicTextField(
                state = state,
                placeholder = placeholder,
                keyboardOptions = keyboardOptions,
                onKeyboardAction = onKeyboardAction,
                lineLimits = lineLimits
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SoptFormFieldPreview() {
    val state = rememberTextFieldState()

    LETSSOPTTheme {
        SoptFormField(
            title = "아이디",
            state = state,
            placeholder = "아이디를 입력해주세요"
        )
    }
}