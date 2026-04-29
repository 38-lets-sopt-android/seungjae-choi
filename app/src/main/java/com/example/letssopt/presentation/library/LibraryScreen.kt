package com.example.letssopt.presentation.library

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.library.component.LibraryEmptyView
import com.example.letssopt.presentation.library.component.LibraryVerticalGrid
import com.example.letssopt.presentation.library.state.LibraryUiState
import kotlinx.collections.immutable.toImmutableList

@Composable
fun LibraryRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    viewModel: LibraryViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LibraryScreen(
        paddingValues = paddingValues,
        uiState = uiState,
        onDeleteClick = viewModel::deleteItem
    )
}

@Composable
private fun LibraryScreen(
    paddingValues: PaddingValues,
    uiState: LibraryUiState,
    onDeleteClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "찜한 목록",
            style = LETSSOPTTheme.typography.bold.h1,
            color = LETSSOPTTheme.colors.textPrimary,
            modifier = Modifier.padding(top = 70.dp)
        )

        if (uiState.items.isEmpty()) {
            LibraryEmptyView()
        } else {
            Spacer(modifier = Modifier.height(45.dp))

            LibraryVerticalGrid(
                items = uiState.items,
                onDeleteClick = onDeleteClick
            )
        }
    }
}

@Preview
@Composable
private fun LibraryScreenPreview() {
    LETSSOPTTheme {
        LibraryScreen(
            paddingValues = PaddingValues(0.dp),
            uiState = LibraryUiState(
                items = LibraryUiState.dummyItems.toImmutableList()
            ),
            onDeleteClick = {}
        )
    }
}