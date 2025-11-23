package com.sample.githubprofile.data.repository

import com.sample.githubprofile.data.model.MockUserData
import com.sample.githubprofile.data.model.User
import com.sample.githubprofile.data.model.UserProfile

class MockGithubRepository : GithubRepository {

    private var showError = false

    fun setShouldReturnError(value: Boolean) {
        showError = value
    }

    override suspend fun searchUsers(query: String): Result<List<User>> {
        if (showError) {
            return Result.failure(Exception("Mock network error"))
        }

        val results =
            MockUserData.mockUserList.filter { it.username.contains(query, ignoreCase = true) }
        return Result.success(results)
    }

    override suspend fun getUserProfile(username: String): Result<UserProfile> {
        if (showError) {
            return Result.failure(Exception("Mock profile fetch error"))
        }

        return if (username == MockUserData.mockUserProfile.username) {
            Result.success(MockUserData.mockUserProfile)
        } else {
            Result.failure(Exception("User not found"))
        }
    }
}