package com.sample.githubprofile.data.remote

import com.sample.githubprofile.data.model.UserProfile
import com.sample.githubprofile.data.model.UserSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {
    @GET("search/users")
    suspend fun searchUsers(@Query("q") searchTerm: String): UserSearchResponse

    @GET("users/{username}")
    suspend fun getUserProfile(@Path("username") username: String): UserProfile
}
