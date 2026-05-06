package com.example.letssopt.presentation.home.state

import androidx.compose.runtime.Immutable
import com.example.letssopt.R
import com.example.letssopt.presentation.home.model.HomePartyUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

@Immutable
data class HomeUiState(
    val bannerList: ImmutableList<Int> = persistentListOf(),
    val hotList: ImmutableList<Int> = persistentListOf(),
    val upcomingList: ImmutableList<Int> = persistentListOf(),
    val partyList: ImmutableList<HomePartyUiModel> = persistentListOf()
) {
    companion object {
        val dummyBannerList = listOf(
            R.drawable.img_banner,
            R.drawable.img_content1,
            R.drawable.img_content2,
        ).toImmutableList()
        val dummyContentList = listOf(
            R.drawable.img_content1,
            R.drawable.img_content2,
            R.drawable.img_content3,
            R.drawable.img_content1,
            R.drawable.img_content2,
            R.drawable.img_content3,
        ).toImmutableList()
        val dummyPartyList = listOf(
            HomePartyUiModel(R.drawable.img_party, "오늘 21:13에 시작", "# 왕과 사는 남자"),
            HomePartyUiModel(R.drawable.img_party, "오늘 22:22에 시작", "# 파묘"),
            HomePartyUiModel(R.drawable.img_party, "오늘 21:13에 시작", "# 왕과 사는 남자"),
            HomePartyUiModel(R.drawable.img_party, "오늘 22:22에 시작", "# 파묘"),
        ).toImmutableList()
    }
}