package com.example.letssopt.presentation.home

import androidx.lifecycle.ViewModel
import com.example.letssopt.presentation.home.state.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        HomeUiState(
            bannerList = HomeUiState.dummyBannerList,
            hotList = HomeUiState.dummyContentList,
            upcomingList = HomeUiState.dummyContentList,
            partyList = HomeUiState.dummyPartyList
        )
    )
    val uiState = _uiState.asStateFlow()
}