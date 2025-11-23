package com.sample.githubprofile.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.githubprofile.data.model.User
import com.sample.githubprofile.data.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    private val _searchQuery = MutableStateFlow("")

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        _searchQuery
            .debounce(500L) // debounce as stated in the requirements
            .filter { query ->
                return@filter query.isNotBlank()
            }
            .onEach { query ->
                searchUsers(query)
            }
            .launchIn(viewModelScope)
    }

    fun onQueryChanged(newQuery: String) {
        _searchQuery.value = newQuery
        if (newQuery.isNotBlank()) {
            _isLoading.value = true
        }
    }

    private fun searchUsers(query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.searchUsers(query)
                .onSuccess { userList ->
                    _users.value = userList
                    _error.value = null
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "An unknown error occurred"
                    _users.value = emptyList()
                }
            _isLoading.value = false
        }
    }

    fun clearError() {
        _error.value = null
    }

    fun clearSearchResults() {
        _users.value = emptyList()
        _isLoading.value = false
    }
}
