package com.aleksejantonov.wefeed.model.network.entity.group

data class GroupResponseContainer(val response: List<Group>)
data class Group(
    val id: Long,
    val name: String,
    val screen_name: String,
    val is_closed: Int,
    val type: String,
    val photo_50: String,
    val photo_100: String,
    val photo_200: String
)
