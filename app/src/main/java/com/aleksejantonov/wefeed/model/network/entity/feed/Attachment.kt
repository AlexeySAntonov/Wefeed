package com.aleksejantonov.wefeed.model.network.entity.feed

sealed class Attachment {
  data class PhotoAttachment(val type: String, val photo: PhotoContent) : Attachment()
  data class VideoAttachment(val type: String, val video: VideoContent) : Attachment()
}

data class PhotoContent(
    val id: Long,
    val album_id: Long,
    val owner_id: Long,
    val user_id: Long,
    val photo_75: String,
    val photo_130: String,
    val photo_604: String,
    val photo_807: String,
    val photo_1280: String,
    val photo_2560: String,
    val width: Int,
    val height: Int,
    val text: String,
    val date: Long,
    val post_id: Long,
    val access_key: String
)

data class VideoContent(
    val id: Long,
    val owner_id: Long,
    val title: String,
    val duration: Long,
    val description: String,
    val date: Long,
    val comments: Long,
    val views: Long,
    val width: Int,
    val height: Int,
    val photo_130: String,
    val photo_320: String,
    val photo_800: String,
    val access_key: String,
    val first_frame_320: String,
    val first_frame_160: String,
    val first_frame_130: String,
    val first_frame_800: String,
    val can_add: Int
)