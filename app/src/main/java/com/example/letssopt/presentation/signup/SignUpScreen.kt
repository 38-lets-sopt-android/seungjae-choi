package com.example.letssopt.presentation.signup

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.core.common.util.UiState
import com.example.letssopt.core.designsystem.component.SoptBasicButton
import com.example.letssopt.core.designsystem.component.SoptFormField
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.signup.model.SignUpUiModel
import com.example.letssopt.presentation.signup.state.SignUpSideEffect
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SignUpRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    viewModel: SignUpViewModel = viewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.sideEffect.collectLatest { effect ->
            when (effect) {
                is SignUpSideEffect.ShowToast ->
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                is SignUpSideEffect.NavigateToSignIn -> navigateUp()
            }
        }
    }

    SignUpScreen(
        paddingValues = paddingValues,
        isLoading = uiState is UiState.Loading,
        onSignUpClick = viewModel::signUp
    )
}

@Composable
private fun SignUpScreen(
    paddingValues: PaddingValues,
    isLoading: Boolean,
    onSignUpClick: (SignUpUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    val loginIdState = rememberTextFieldState("")
    val passwordState = rememberTextFieldState("")
    val nameState = rememberTextFieldState("")
    val emailState = rememberTextFieldState("")
    val ageState = rememberTextFieldState("")
    val partState = rememberTextFieldState("")

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
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
                    text = "회원가입",
                    style = LETSSOPTTheme.typography.bold.h2,
                    color = LETSSOPTTheme.colors.textPrimary
                )

                Spacer(modifier = Modifier.height(36.dp))

                SoptFormField(
                    title = "아이디",
                    state = loginIdState,
                    placeholder = "아이디를 입력하세요",
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(18.dp))
                SoptFormField(
                    title = "비밀번호",
                    state = passwordState,
                    placeholder = "비밀번호를 입력하세요",
                    isPassword = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(18.dp))
                SoptFormField(
                    title = "이름",
                    state = nameState,
                    placeholder = "이름을 입력하세요",
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(18.dp))
                SoptFormField(
                    title = "이메일",
                    state = emailState,
                    placeholder = "이메일 주소를 입력하세요",
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(18.dp))
                SoptFormField(
                    title = "나이",
                    state = ageState,
                    placeholder = "나이를 입력하세요",
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(18.dp))
                SoptFormField(
                    title = "파트 (iOS / 안드로이드 / 웹)",
                    state = partState,
                    placeholder = "파트를 입력하세요",
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    onKeyboardAction = KeyboardActionHandler { performDefault ->
                        performDefault()
                        focusManager.clearFocus()
                    }
                )
            }

            SoptBasicButton(
                title = "회원가입",
                enabled = loginIdState.text.isNotBlank() &&
                        passwordState.text.isNotBlank() &&
                        nameState.text.isNotBlank() &&
                        emailState.text.isNotBlank() &&
                        ageState.text.isNotBlank() &&
                        partState.text.isNotBlank(),
                onClick = {
                    focusManager.clearFocus()
                    val age = ageState.text.toString().toIntOrNull() ?: return@SoptBasicButton
                    val uiModel = SignUpUiModel(
                        loginId = loginIdState.text.toString(),
                        password = passwordState.text.toString(),
                        name = nameState.text.toString(),
                        email = emailState.text.toString(),
                        age = age,
                        part = partState.text.toString()
                    )
                    onSignUpClick(uiModel)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 26.dp)
            )
        }
    }
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = LETSSOPTTheme.colors.background.copy(alpha = 0.3f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = LETSSOPTTheme.colors.primaryRed)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    LETSSOPTTheme {
        SignUpScreen(
            paddingValues = PaddingValues(),
            isLoading = false,
            onSignUpClick = {}
        )
    }
}