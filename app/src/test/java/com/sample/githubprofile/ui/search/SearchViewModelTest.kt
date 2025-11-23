package com.sample.githubprofile.ui.search

import com.sample.githubprofile.MainDispatcherRule
import com.sample.githubprofile.data.repository.MockGithubRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var mockRepository: MockGithubRepository
    private lateinit var viewModel: SearchViewModel

    @Before
    fun setUp() {
        mockRepository = MockGithubRepository()
        viewModel = SearchViewModel(mockRepository)
    }

    @Test
    fun `onQueryChanged with valid query returns success and updates users`() = runTest {
        // Given
        val query = "andrew"

        // When
        viewModel.onQueryChanged(query)

        this.testScheduler.advanceTimeBy(600)

        // Then
        val users = viewModel.users.value
        Assert.assertEquals(1, users.size)
        Assert.assertEquals("andrew", users[0].username)
        Assert.assertNull(viewModel.error.value)
    }

    @Test
    fun `onQueryChanged with no results returns empty list`() = runTest {
        // Given
        val query = "nonexistentuser"

        // When
        viewModel.onQueryChanged(query)

        this.testScheduler.advanceTimeBy(600)

        // Then
        val users = viewModel.users.value
        Assert.assertEquals(0, users.size)
        Assert.assertNull(viewModel.error.value)
    }

    @Test
    fun `searchUsers failure updates error state`() = runTest {
        // Given
        val query = "anything"
        mockRepository.setShouldReturnError(true)

        // When
        viewModel.onQueryChanged(query)

        this.testScheduler.advanceTimeBy(600)

        // Then
        Assert.assertNotNull(viewModel.error.value)
        Assert.assertEquals("Mock network error", viewModel.error.value)
        Assert.assertEquals(0, viewModel.users.value.size)
    }
}