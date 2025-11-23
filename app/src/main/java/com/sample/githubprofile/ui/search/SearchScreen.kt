package com.sample.githubprofile.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sample.githubprofile.R
import com.sample.githubprofile.data.model.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onUserClick: (String) -> Unit,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val users by searchViewModel.users.collectAsState()
    val error by searchViewModel.error.collectAsState()
    val isLoading by searchViewModel.isLoading.collectAsState()

    error?.let { errorMessage ->
        AlertDialog(
            onDismissRequest = { searchViewModel.clearError() },
            title = { Text("Error") },
            text = { Text(errorMessage) },
            confirmButton = {
                Button(onClick = { searchViewModel.clearError() }) {
                    Text("OK")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("GitHub Search") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = if (active) 0.dp else 16.dp),
                query = query,
                onQueryChange = {
                    query = it
                    searchViewModel.onQueryChanged(it)
                },
                onSearch = { },
                active = active,
                onActiveChange = {
                    active = it
                    if (!it) {
                        searchViewModel.clearSearchResults()
                    }
                },
                placeholder = { Text("Search Github users...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
                trailingIcon = {
                    if (active) {
                        IconButton(
                            onClick = {
                                if (query.isNotEmpty()) {
                                    query = ""
                                    searchViewModel.clearSearchResults()
                                } else {
                                    active = false
                                }
                            }
                        ) {
                            Icon(Icons.Default.Close, contentDescription = "Close/Clear Search")
                        }
                    }
                }
            ) {
                if (query.isNotEmpty()) {
                    if (isLoading) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    else if (users.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("No user found for '$query'")
                        }
                    } else {
                        LazyColumn {
                            items(users) { user ->
                                UserListItem(user = user, onUserClick = {
                                    onUserClick(it)
                                    active = false
                                    query = ""
                                })
                            }
                        }
                    }
                }
            }

            if (!active) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Search for Github users to get started",
                        color = Color.Gray
                    )
                }
            }
        }
    }
}


@Composable
fun UserListItem(user: User, onUserClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onUserClick(user.username) }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(user.avatarUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "${user.username}'s avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = user.username,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = user.type,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}
