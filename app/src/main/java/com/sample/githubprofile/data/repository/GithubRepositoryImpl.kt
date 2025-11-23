package com.sample.githubprofile.data.repository

import com.sample.githubprofile.data.model.User
import com.sample.githubprofile.data.model.UserProfile
import com.sample.githubprofile.data.remote.GithubApiService
import javax.inject.Inject

open class GithubRepositoryImpl @Inject constructor(
    private val githubApiService: GithubApiService
) : GithubRepository {
    override suspend fun searchUsers(query: String): Result<List<User>> {
        return try {
            val response = githubApiService.searchUsers(query)
            Result.success(response.users)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserProfile(username: String): Result<UserProfile> {
        return try {
            val profile = githubApiService.getUserProfile(username)
            Result.success(profile)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
