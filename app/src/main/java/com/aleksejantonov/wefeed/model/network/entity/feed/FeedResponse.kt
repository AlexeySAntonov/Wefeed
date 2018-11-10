package com.aleksejantonov.wefeed.model.network.entity.feed

data class FeedResponseContainer(val response: FeedResponse)
data class FeedResponse(val items: List<Post>)

data class Post(
    val type: String,
    val source_id: Long,
    val date: Long,
    val post_id: Long,
    val post_type: String,
    val text: String,
    val marked_as_ads: Int,
    val attachments: List<Attachment>,
    val post_source: PostSource,
    val comments: Comments,
    val likes: Likes,
    val reposts: Reposts
)

data class PostSource(val type: String)
data class Comments(val count: Long, val can_post: Int)
data class Likes(val count: Long, val user_likes: Int, val can_like: Int, val can_publish: Int)
data class Reposts(val count: Long, val user_reposted: Int)