package com.example.letssopt.presentation.signin

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.core.common.extension.noRippleClickable
import com.example.letssopt.core.designsystem.component.SoptBasicButton
import com.example.letssopt.core.designsystem.component.SoptFormField
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.signin.state.SignInSideEffect
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SignInRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToMain: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = viewModel(),

    ) {
    val context = LocalContext.current

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collectLatest { effect ->
            when (effect) {
                is SignInSideEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
                is SignInSideEffect.NavigateToMain -> {
                    navigateToMain()
                }
                is SignInSideEffect.NavigateToSignUp -> {
                    navigateToSignUp()
                }
            }
        }
    }

    SignInScreen(
        paddingValues = paddingValues,
        modifier = modifier,
        onSignUpTextClick = viewModel::onSignUpTextClick,
        onSignInClick = viewModel::signIn
    )
}

@Composable
private fun SignInScreen(
    paddingValues: PaddingValues,
    onSignUpTextClick: () -> Unit,
    onSignInClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    val emailState = rememberTextFieldState("")
    val passwordState = rememberTextFieldState("")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = LETSSOPTTheme.colors.background)
            .padding(paddingValues)
            .padding(horizontal = 20.dp)
            .imePadding(),
        horizontalAlignment = Alignment.Start
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "watcha",
                style = LETSSOPTTheme.typography.bold.l1,
                color = LETSSOPTTheme.colors.primaryRed,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 60.dp)
            )

            Spacer(modifier = Modifier.height(26.dp))

            Text(
                text = "이메일로 로그인",
                style = LETSSOPTTheme.typography.bold.h2,
                color = LETSSOPTTheme.colors.textPrimary
            )

            Spacer(modifier = Modifier.height(36.dp))

            SoptFormField(
                title = "이메일",
                state = emailState,
                placeholder = "이메일 주소를 입력하세요",
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            Spacer(modifier = Modifier.height(18.dp))

            SoptFormField(
                title = "비밀번호",
                state = passwordState,
                placeholder = "비밀번호를 입력하세요",
                isPassword = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                onKeyboardAction = KeyboardActionHandler { performDefault ->
                    performDefault()
                    focusManager.clearFocus()
                }
            )
        }

        Text(
            text = "아직 계정이 없으신가요?  회원가입",
            style = LETSSOPTTheme.typography.regular.caption,
            color = LETSSOPTTheme.colors.textSecondary,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 20.dp)
                .noRippleClickable(onClick = onSignUpTextClick)
        )

        SoptBasicButton(
            title = "로그인하기",
            onClick = {
                focusManager.clearFocus()

                onSignInClick(
                    emailState.text.toString(),
                    passwordState.text.toString()
                )
            },
            modifier = Modifier.padding(bottom = 26.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    LETSSOPTTheme {
        SignInScreen(
            onSignUpTextClick = {},
            paddingValues = PaddingValues(),
            onSignInClick = { _, _ -> }
        )
    }
}