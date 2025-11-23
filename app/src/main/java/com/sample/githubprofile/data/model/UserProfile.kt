package com.sample.githubprofile.data.model

import com.google.gson.annotations.SerializedName

data class UserProfile(
    val id: Int,
    @SerializedName("login") val username: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("name") val name: String?,
    @SerializedName("html_url") val profileUrl: String,
    @SerializedName("public_repos") val publicRepos: Int,
    @SerializedName("followers") val followers: Int,
    @SerializedName("following") val following: Int
)
