package com.sample.githubprofile.data.model

object MockUserData {

    val mockUserList = listOf(
        User(1, "andrew", "https://avatars.githubusercontent.com/u/1060?v=4", "User"),
        User(2, "kevin", "https://avatars.githubusercontent.com/u/35074883?v=4", "User"),
        User(3, "jay", "https://avatars.githubusercontent.com/u/965580?v=4", "User")
    )

    val mockUserProfile = UserProfile(
        id = 1,
        username = "andrew",
        avatarUrl = "https://avatars.githubusercontent.com/u/1060?v=4",
        name = "Andrew Nesbitt",
        followers = 3300,
        following = 3400,
        publicRepos = 363,
        profileUrl = "https://github.com/andrew"
    )

    val mockEmptyUserList = emptyList<User>()
}