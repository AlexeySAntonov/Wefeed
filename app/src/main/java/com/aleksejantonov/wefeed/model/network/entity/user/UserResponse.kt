package com.aleksejantonov.wefeed.model.network.entity.user

data class UserResponseContainer(val response: List<User>)
data class User(
    val id: Long,
    val first_name: String,
    val last_name: String,
    val photo_50: String
) {
  val fullName = "$first_name $last_name"
}
