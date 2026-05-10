package com.example.letssopt.presentation.profile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.profile.component.UserProfileItem
import com.example.letssopt.presentation.profile.model.UserItemUiModel
import com.example.letssopt.presentation.profile.state.ProfileSideEffect
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun UserListRoute(
    paddingValues: PaddingValues,
    viewModel: ProfileViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchUserList()
    }

    LaunchedEffect(viewModel) {
        viewModel.sideEffect.collect { effect ->
            if (effect is ProfileSideEffect.ShowToast) {
                Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
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
            UserListScreen(
                paddingValues = paddingValues,
                userList = state.data.userListState
            )
        }

        else -> Unit
    }
}

@Composable
private fun UserListScreen(
    paddingValues: PaddingValues,
    userList: ImmutableList<UserItemUiModel>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LETSSOPTTheme.colors.background)
            .padding(paddingValues)
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "친구들",
            style = LETSSOPTTheme.typography.bold.h2,
            color = LETSSOPTTheme.colors.textPrimary,
            modifier = Modifier.padding(top = 70.dp, bottom = 38.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(userList, key = { it.id }) { user ->
                UserProfileItem(user = user)
            }
        }
    }
}

@Preview
@Composable
private fun UserListScreenPreview() {
    LETSSOPTTheme {
        UserListScreen(
            paddingValues = PaddingValues(),
            userList = persistentListOf()
        )
    }
}