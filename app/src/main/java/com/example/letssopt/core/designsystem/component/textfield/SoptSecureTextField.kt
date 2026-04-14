package com.example.letssopt.core.designsystem.component.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme

@Composable
fun SoptSecureTextField(
    state: TextFieldState,
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
) {
    BasicSecureTextField(
        state = state,
        modifier = modifier.fillMaxWidth(),
        textStyle = LETSSOPTTheme.typography.regular.caption.copy(color = LETSSOPTTheme.colors.textPrimary),
        cursorBrush = SolidColor(LETSSOPTTheme.colors.textPrimary),
        keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Password),
        onKeyboardAction = onKeyboardAction,
        decorator = { innerTextField ->
            SoptTextFieldBox(state.text.isEmpty(), placeholder, innerTextField)
        }
    )
}

@Preview
@Composable
private fun SoptSecureTextFieldPreview() {
    val password = rememberTextFieldState("")

    SoptSecureTextField(
        state = password,
        placeholder = "비밀번호"
    )
}