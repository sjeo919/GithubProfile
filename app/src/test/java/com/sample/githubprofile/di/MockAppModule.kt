package com.sample.githubprofile.di

import com.sample.githubprofile.data.repository.GithubRepository
import com.sample.githubprofile.data.repository.GithubRepositoryImpl
import com.sample.githubprofile.data.repository.MockGithubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

// This annotation tells Hilt to use this module for tests and to replace
// the production AppModule.
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
@Module
object MockAppModule {

    @Provides
    @Singleton
    fun provideMockGithubRepository(): GithubRepository {
        // Provide the MockGithubRepository whenever a GithubRepository is requested.
        return MockGithubRepository()
    }

    // We don't need to provide Retrofit or GithubApiService for tests
    // since the mock repository doesn't use them.
}