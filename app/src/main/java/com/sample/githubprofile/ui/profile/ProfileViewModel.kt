package com.sample.githubprofile.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.githubprofile.data.model.UserProfile
import com.sample.githubprofile.data.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileUiState(
    val isLoading: Boolean = false,
    val userProfile: UserProfile? = null,
    val error: String? = null
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState(isLoading = true))
    val uiState: StateFlow<ProfileUiState> = _uiState

    fun getUserProfile(username: String) {
        _uiState.value = ProfileUiState(isLoading = true)

        viewModelScope.launch {
            repository.getUserProfile(username).onSuccess { profile ->
                    _uiState.value = ProfileUiState(userProfile = profile)
                }.onFailure { exception ->
                    _uiState.value = ProfileUiState(error = exception.message)
                }
        }
    }
}