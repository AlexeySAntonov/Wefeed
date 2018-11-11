package com.aleksejantonov.wefeed.ui.feed.viewModel

data class PostVM(
    val ownerId: Long,
    val postId: Long,
    val type: String = "photo",
    val text: String = "",
    val name: String = "",
    val avatarUrl: String = "",
    val date: String = "",
    val imageUrls: List<String> = emptyList(),
    val linkButtonTitle: String? = "",
    val linkButtonLink: String? = ""
)