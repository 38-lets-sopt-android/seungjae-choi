package com.example.letssopt.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.common.extension.noRippleClickable
import com.example.letssopt.core.common.util.SoptValidator
import com.example.letssopt.core.designsystem.component.SoptBasicButton
import com.example.letssopt.core.designsystem.component.SoptFormField
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme

class SignInActivity : ComponentActivity() {
    private var registeredEmail by mutableStateOf("")
    private var registeredPassword by mutableStateOf("")

    private val signUpLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.let { intent ->
                registeredEmail = intent.getStringExtra("email") ?: ""
                registeredPassword = intent.getStringExtra("password") ?: ""
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignInRoute(
                        modifier = Modifier.padding(innerPadding),
                        registeredEmail = registeredEmail,
                        registeredPassword = registeredPassword,
                        navigateToSignUp = {
                            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
                            signUpLauncher.launch(intent)
                        },
                        navigateToMain = {
                            val intent =
                                Intent(this@SignInActivity, MainActivity::class.java).apply {
                                    flags =
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                }
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SignInRoute(
    registeredEmail: String,
    registeredPassword: String,
    navigateToSignUp: () -> Unit,
    navigateToMain: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    SignInScreen(
        modifier = modifier,
        onSignUpTextClick = navigateToSignUp,
        onSignInClick = { email, password ->
            val errorMessage = SoptValidator.validateSignInInputs(email, password)

            if (errorMessage != null) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            } else if (email != registeredEmail || password != registeredPassword) {
                Toast.makeText(context, "아이디 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
                navigateToMain()
            }
        }
    )
}

@Composable
private fun SignInScreen(
    onSignUpTextClick: () -> Unit,
    onSignInClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val emailState = rememberTextFieldState("")
    val passwordState = rememberTextFieldState("")

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
            text = "이메일로 로그인",
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

        Spacer(modifier = Modifier.weight(1f))

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
            onSignInClick = { _, _ -> }
        )
    }
}