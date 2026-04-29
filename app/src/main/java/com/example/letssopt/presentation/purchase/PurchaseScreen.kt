package com.example.letssopt.presentation.purchase

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.core.data.LibraryDatabase
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.purchase.component.PurchaseVerticalGrid
import com.example.letssopt.presentation.purchase.state.PurchaseSideEffect
import com.example.letssopt.presentation.purchase.state.PurchaseUiState
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PurchaseRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current
    val database = remember { LibraryDatabase.getDatabase(context) }

    val viewModel: PurchaseViewModel = viewModel {
        PurchaseViewModel(database.libraryDao())
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collectLatest { effect ->
            when (effect) {
                is PurchaseSideEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    PurchaseScreen(
        paddingValues = paddingValues,
        uiState = uiState,
        onPurchaseClick = { id, imageRes, title ->
            viewModel.onPurchaseClick(id = id, title = title, imageRes = imageRes)        }
    )
}

@Composable
private fun PurchaseScreen(
    paddingValues: PaddingValues,
    uiState: PurchaseUiState,
    onPurchaseClick: (Long, Int, String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "개별 구매",
            style = LETSSOPTTheme.typography.bold.h1,
            color = LETSSOPTTheme.colors.textPrimary,
            modifier = Modifier.padding(top = 70.dp)
        )
        Spacer(modifier = Modifier.height(45.dp))

        PurchaseVerticalGrid(
            items = uiState.items,
            onPurchaseClick = onPurchaseClick
        )
    }
}