package com.example.letssopt.presentation.home

import androidx.lifecycle.ViewModel
import com.example.letssopt.presentation.home.state.HomeUiState
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        HomeUiState(
            bannerList = HomeUiState.dummyBannerList.toImmutableList(),
            hotList = HomeUiState.dummyContentList.toImmutableList(),
            upcomingList = HomeUiState.dummyContentList.toImmutableList(),
            partyList = HomeUiState.dummyPartyList.toImmutableList()
        )
    )
    val uiState = _uiState.asStateFlow()
}