package com.sample.githubprofile.data.repository

import com.sample.githubprofile.data.model.User
import com.sample.githubprofile.data.model.UserProfile

interface GithubRepository {
    suspend fun searchUsers(query: String): Result<List<User>>
    suspend fun getUserProfile(username: String): Result<UserProfile>
}