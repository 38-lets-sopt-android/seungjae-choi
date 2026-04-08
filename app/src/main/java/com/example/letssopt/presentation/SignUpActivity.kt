package com.example.letssopt.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.common.util.SoptValidator
import com.example.letssopt.core.designsystem.component.SoptBasicButton
import com.example.letssopt.core.designsystem.component.SoptFormField
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignUpRoute(
                        modifier = Modifier.padding(innerPadding),
                        navigateToSignIn = { email, password ->
                            val intent = Intent().apply {
                                putExtra("email", email)
                                putExtra("password", password)
                            }
                            setResult(RESULT_OK, intent)
                            finish()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SignUpRoute(
    navigateToSignIn: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    SignUpScreen(
        modifier = modifier,
        onSignUpClick = { email, password, passwordCheck ->
            val errorMessage = SoptValidator.validateSignUpInputs(email, password, passwordCheck)
            if (errorMessage != null) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show()
                navigateToSignIn(email, password)
            }
        }
    )
}

@Composable
private fun SignUpScreen(
    onSignUpClick: (String, String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val emailState = rememberTextFieldState("")
    val passwordState = rememberTextFieldState("")
    val passwordCheckState = rememberTextFieldState("")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = LETSSOPTTheme.colors.background)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.Start
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
            title = "이메일",
            state = emailState,
            placeholder = "이메일 주소를 입력하세요"
        )

        Spacer(modifier = Modifier.height(18.dp))

        SoptFormField(
            title = "비밀번호",
            state = passwordState,
            placeholder = "비밀번호를 입력하세요",
            isPassword = true
        )

        Spacer(modifier = Modifier.height(18.dp))

        SoptFormField(
            title = "비밀번호 확인",
            state = passwordCheckState,
            placeholder = "비밀번호를 다시 입력하세요",
            isPassword = true
        )

        Spacer(modifier = Modifier.weight(1f))

        SoptBasicButton(
            title = "회원가입",
            onClick = {
                onSignUpClick(
                    emailState.text.toString(),
                    passwordState.text.toString(),
                    passwordCheckState.text.toString()
                )
            },
            enabled = emailState.text.isNotBlank() && passwordState.text.isNotBlank() && passwordCheckState.text.isNotBlank(),
            modifier = Modifier.padding(bottom = 26.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    LETSSOPTTheme {
        SignUpScreen(
            onSignUpClick = { _, _, _ -> }
        )
    }
}