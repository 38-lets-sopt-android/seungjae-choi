package com.example.letssopt.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                        navigateToSignIn = {}
                    )
                }
            }
        }
    }
}

@Composable
fun SignUpRoute(
    navigateToSignIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    SignUpScreen(
        modifier = modifier,
        onSignUpClick = navigateToSignIn
    )
}

@Composable
private fun SignUpScreen(
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordCheck by remember { mutableStateOf("") }

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
            value = email,
            onValueChange = { email = it },
            placeholder = "이메일 주소를 입력하세요"
        )

        Spacer(modifier = Modifier.height(18.dp))

        SoptFormField(
            title = "비밀번호",
            value = password,
            onValueChange = { password = it },
            placeholder = "비밀번호를 입력하세요",
        )

        Spacer(modifier = Modifier.height(18.dp))

        SoptFormField(
            title = "비밀번호 확인",
            value = passwordCheck,
            onValueChange = { passwordCheck = it },
            placeholder = "비밀번호를 다시 입력하세요",
        )

        Spacer(modifier = Modifier.weight(1f))

        SoptBasicButton(
            title = "회원가입",
            onClick = onSignUpClick,
            enabled = true,
            modifier = Modifier.padding(bottom = 26.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    LETSSOPTTheme {
        SignUpScreen(onSignUpClick = {})
    }
}