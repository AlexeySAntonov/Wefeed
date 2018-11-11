package com.aleksejantonov.wefeed.ui.feed.viewModel

data class PostVM(
    val text: String = "",
    val name: String = "",
    val avatarUrl: String = "",
    val date: String = "",
    val imageUrls: List<String> = emptyList()
)