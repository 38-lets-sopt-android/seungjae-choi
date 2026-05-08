package com.example.letssopt.presentation.profile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.core.common.util.UiState
import com.example.letssopt.core.designsystem.component.SoptBasicButton
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.profile.component.ProfileItem
import com.example.letssopt.presentation.profile.model.MyInfoUiModel
import com.example.letssopt.presentation.profile.state.ProfileSideEffect
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProfileRoute(
    paddingValues: PaddingValues,
    navigateToUserList: () -> Unit,
    viewModel: ProfileViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchMyInfo()
    }

    LaunchedEffect(viewModel) {
        viewModel.sideEffect.collectLatest { effect ->
            when (effect) {
                is ProfileSideEffect.NavigateToUserList -> navigateToUserList()
                is ProfileSideEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    when (val state = uiState) {
        is UiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = LETSSOPTTheme.colors.primaryRed)
            }
        }

        is UiState.Success -> {
            ProfileScreen(
                paddingValues = paddingValues,
                myInfo = state.data.myInfoState,
                onNavigateToUserList = viewModel::onNavigateToUserList
            )
        }

        else -> Unit
    }
}

@Composable
private fun ProfileScreen(
    paddingValues: PaddingValues,
    myInfo: MyInfoUiModel,
    onNavigateToUserList: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LETSSOPTTheme.colors.background)
            .padding(paddingValues)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Text(
            text = "프로필",
            style = LETSSOPTTheme.typography.bold.h2,
            color = LETSSOPTTheme.colors.textPrimary,
            modifier = Modifier.padding(top = 70.dp, bottom = 38.dp)
        )

        ProfileItem(
            title = "아이디",
            value = myInfo.loginId
        )

        ProfileItem(
            title = "이름",
            value = myInfo.name
        )

        ProfileItem(
            title = "이메일",
            value = myInfo.email
        )

        ProfileItem(
            title = "나이",
            value = myInfo.age
        )

        ProfileItem(
            title = "파트",
            value = myInfo.part
        )

        Spacer(modifier = Modifier.height(30.dp))

        SoptBasicButton(
            title = "다른 유저들 보러가기",
            onClick = onNavigateToUserList
        )
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    LETSSOPTTheme {
        ProfileScreen(
            paddingValues = PaddingValues(),
            myInfo = MyInfoUiModel(),
            onNavigateToUserList = {}
        )
    }
}