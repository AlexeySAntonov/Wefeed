package com.aleksejantonov.wefeed.model.network.http.config

object ApiConstants {
  const val BASE_URL = "https://api.vk.com/method/"
  const val VERSION = "v"
  const val TOKEN = "access_token"
  const val GROUP_IDS = "group_ids"
  const val USER_ID = "user_id"
  const val FIELDS = "fields"

  // Additional fields
  const val PHOTO_SMALL = "photo_50"

  // Likes.add
  const val ITEM_TYPE = "type"
  const val OWNER_ID = "owner_id"
  const val ITEM_ID = "item_id"
}