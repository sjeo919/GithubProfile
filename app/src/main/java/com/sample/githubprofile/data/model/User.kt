package com.sample.githubprofile.data.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int,
    @SerializedName("login") val username: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("type") val type: String
)

data class UserSearchResponse(
    @SerializedName("items") val users: List<User>
)
